package minipos.model;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 * Entidad DatosDeEntrega
 * @author Profesorado
 */
@Entity
public class DatosDeEntrega implements Serializable {

    private static final long serialVersionUID = 19494938882L;       

    /**
     * Constructor sin argumentos, tal y como requiere JPA.
     */
    protected DatosDeEntrega(){
        
    }
    
    /**
     * Constructor visibilidad paquete. Será instanciado desde la clase
     * Ticket en el método direccionEnvio.
     * @see Ticket#direccionEnvio(String) direccionEnvio
     * @param ticket Ticket a los que asociar estos datos de entrega
     * @param direccion Dirección de envío.
     */
    DatosDeEntrega(Ticket ticket, String direccion) {
        this.ticket=ticket;    
        this.direccion=direccion;
        this.entregado=false;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(nullable = false, length = 1000)
    private String direccion;

    @Basic
    private Date fecha;
    
    @Basic
    private Time hora;
            
    @Basic
    private boolean entregado;
    
    @OneToOne(optional=false)
    private Ticket ticket;

    /**
     * Obtiene el ID de los datos de entrega.
     * @return ID
     */
    public Long getId() {
        return id;
    }
    
    /**
     * Obtiene la dirección de entrega.
     * @return Dirección de entrega.
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * Establece la dirección de entrega.
     * @param direccion Dirección de entrega.
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }        
    
    /**
     * Obtiene el ticket asociado a esta dirección de entrega.
     * @return Ticket asociado a la dirección de entrega.
     */
    public Ticket getTicket() {
        return ticket;
    }
    
    /**
     * Obtiene la fecha de entrega del pedido.
     * @return Fecha de entrega.
     */
    public Date getFecha() {
        return fecha;
    }

    /**
     * Establece la fecha de entrega del pedido.
     * @param fecha Fecha de entrega.
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    /**
     * Obtiene la hora de entrega.
     * @return Hora de entrega.
     */
    public Time getHora() {
        return hora;
    }

    /**
     * Establece la hora de entrega.
     * @param hora Hora de entrega.
     */
    public void setHora(Time hora) {
        this.hora = hora;
    }

    /**
     * Permite saber si se ha entregado o no.
     * @return true si fue entregado, false en caso contrario.
     */
    public boolean isEntregado() {
        return entregado;
    }

    /**
     * Establece que si el pedido ha sido entregado o no.
     * @param entregado 
     */
    public void setEntregado(boolean entregado) {
        this.entregado = entregado;
    }        
    
    /**
     * (Fluent api) Establece dirección y retorna esta misma instancia.
     * @param direccion Dirección a establecer.
     * @return Datos de entrega.
     */    
    public DatosDeEntrega direccion(String direccion)
    {
        setDireccion(direccion);
        return this;
    }
    
    /**
     * (Fluent api) Establece la fecha y retorna esta misma instancia.
     * @param fecha fecha de entrega a establecer.
     * @return Datos de entrega.
     */    
    public DatosDeEntrega fecha(LocalDate fecha)
    {
        setFecha(Date.valueOf(fecha));
        return this;
    }
    
    /**
     * (Fluent api) Establece la hora y retorna esta misma instancia.
     * @param hora hora de entrega a establecer.
     * @return Datos de entrega.
     */    
    public DatosDeEntrega hora(LocalTime hora)
    {
        setHora(Time.valueOf(hora));
        return this;
    }
    
    /**
     * (Fluent api) Establece el pedido como entregado.
     * @return Datos de entrega.
     */ 
    public DatosDeEntrega entregado()
    {
        setEntregado(true);
        return this;
    }
    
    @Override
    public String toString() {
        return String.format("DireccionEnvio[ %s ]",getDireccion());
    }
    
}
