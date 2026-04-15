package minipos.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

/**
 * Entidad Ticket
 * @author Profesorado
 */
@Entity
public class Ticket implements Serializable{

    private static final long serialVersionUID = 393918818888588L; 
    
    /**
    * Constructor necesario para JPA.
    */
    protected Ticket()
    {
    }
    
    /**
     * Constructor con los parámetros mínimos para crear un ticket.
     * @param fecha Fecha del ticket.
     * @param hora Hora del ticket.
     */
    public Ticket(LocalDate fecha, LocalTime hora)
    {
        this.fecha=Date.valueOf(fecha);
        this.hora=Time.valueOf(hora);
        lineas=new ArrayList<>();
        ticketCerrado=false;        
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable=false)
    private Date fecha;
    
    @Column(nullable=false)
    private Time hora;
    
    @OneToMany(mappedBy = "ticket", orphanRemoval = true, cascade = CascadeType.PERSIST)
    private List<LineaDeTicket> lineas;
    
    @OneToOne(optional = true, orphanRemoval = true, cascade = CascadeType.PERSIST)
    private DatosDeEntrega direccionEnvio;
       
    @Basic
    @Column(nullable=false)
    private boolean ticketCerrado;
    
    @Transient
    private Double valorTicket=null;
    
   
    /**
     * Obtiene el ID asociado de un ticket.
     * @return Obtiene el ID del ticket.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene la fecha del ticket.
     * @return Fecha del ticket.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Obtiene la hora del ticket.
     * @return Fecha del ticket.
     */ 
    public Time getHora() {
        return hora;
    }

    /**
     * Obtiene la lista de líneas del ticket.
     * @return Líneas del ticket.
     */
    public List<LineaDeTicket> getLineas() {
        return lineas;
    }

    /**
     * Establece las líneas del ticket.
     * @param lineas Líneas del ticket.
     */
    public void setLineas(List<LineaDeTicket> lineas) {      
        if (!isTicketCerrado())
            this.lineas = lineas;
    }   
    
    /**
     * (Fluent API) Establece la dirección de entrega de este ticket y
     * retorna la misma instancia de dirección de envío para seguir completando
     * la información (hora, fecha, etc.).
     * @param direccion Dirección de envío.
     * @return Instancia de la dirección de envío.
     */
    public DatosDeEntrega direccionEnvio(String direccion){
        if (direccionEnvio==null)
        {
            direccionEnvio=new DatosDeEntrega(this,direccion);
        }
        return direccionEnvio;
    }

    /**
     * Obtiene la dirección de entrega.
     * @return Dirección de entrega
     */
    public DatosDeEntrega getDireccionEnvio() {
        
        return direccionEnvio;
    }

    
    /**
     * Límpia la dirección de entrega.  
     */
    public void limpiarDireccionEntrega() {
        this.direccionEnvio = null;
    }        
    
    /**
     * Obtiene el valor del ticket.
     * @return Valor del ticket.
     */
    public double getValorTicket() {
        
        if (lineas!=null && (!isTicketCerrado() || valorTicket == null)) {
            double valor = 0;
            for (LineaDeTicket lt : lineas) {
                valor += lt.getCosteLinea();
            }
            this.valorTicket = valor;
        }
        return valorTicket==null?0:valorTicket;
    }  
    
    /**
     * Retorna true si el ticket está cerrado o no. Una vez el ticket
     * está cerrado no se debe modificar bajo ningún concepto.
     * @return true si el ticket está cerrado y false en caso contrario.
     */
    public boolean isTicketCerrado()
    {
        return ticketCerrado;
    }
    
    /**
     * Añade una línea de ticket de un producto o modifica una línea de ticket 
     * existente asociada con el producto dado. Si el producto ya está en una
     * línea, la cantidad de dicho producto en la línea dada se cambiará.
     * @param p Producto del que se añadirá la línea de ticket
     * @param cantidad Cantidad de items a añadir.
     */
    public void lineaTicket(Producto p, int cantidad) {
        if (!isTicketCerrado()) {
            LineaDeTicket n = buscarLineaDeTicketConProducto(p);
            if (n == null) {
                n = new LineaDeTicket(p, this, cantidad);
                lineas.add(n);
            } else {
                n.setCantidad(cantidad);
            }
        }
    } 
    
    /**
     * Eliminar línea de ticket.
     * @param idLinea línea de ticket a borrar.
     * @return "true" si se ha podido realizar la operación, "false" en 
     * caso contrario.
     */
    public boolean removeLineaTicket(long idLinea) {
        boolean encontrado = false;
        if (!isTicketCerrado()) {
            Iterator<LineaDeTicket> t = this.lineas.iterator();
            while (!encontrado && t.hasNext()) {
                if (t.next().getId() == idLinea) {
                    t.remove();
                    encontrado = true;
                }
            }
        }
        return encontrado;
    }    
    
    /**
     * Método auxiliar para buscar una línea de ticket que tenga 
     * el producto pasado como parámetro.
     * @param p Producto a buscar.
     * @return Línea de ticket existente con el producto indicado o null
     * si no existe ninguna línea de ticket con dicho producto.
     */
    private LineaDeTicket buscarLineaDeTicketConProducto(Producto p)
    {
        Iterator<LineaDeTicket> t=this.lineas.iterator();
        LineaDeTicket result=null;
        while (result==null && t.hasNext())
        {
            LineaDeTicket toInspect=t.next();
            if (toInspect.getProducto().getId()==p.getId())
            {
                result=toInspect;                
            }
        }
        return result;
    }
   
        

    @Override
    public String toString() {    
        
        return String.format("Ticket %d {"
                + "fecha=%s;"
                + "hora=%s;"
                + "nºlíneas de caja=%s;valor=%f;%s%s]",
                id,this.fecha.toString(),this.hora.toString(),
                lineas.size(),getValorTicket(),
                this.isTicketCerrado()?"CERRADO":"ABIERTO",
                this.direccionEnvio!=null?"\n\t"+this.direccionEnvio+"\n":"");
    }
    
}
