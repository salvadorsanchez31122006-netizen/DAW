package minipos.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Entidad Producto.
 * @author Profesorado
 */
@Entity
public class Producto implements Serializable {    
    
    private static final long serialVersionUID = 99488577171L;
    
    /**
     * Constructor sin parámetros. Necesario para JPA. No instanciable fuera
     * de esta clase.
     */
    protected Producto ()
    {
        
    }
    
    /**
     * Constructor de producto con todos los atributos obligatorios de un producto.
     * @param nombre Nombre del producto.
     * @param precio Precio del producto.
     * @param barCode Código de barras.
     */
    public Producto(String nombre, double precio, String barCode)
    {
        this.nombre=nombre;
        this.precio=precio>=0?precio:0;
        this.barCode=barCode;
    }
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false, length = 200)
    private String nombre;

    @Column(nullable = false)
    private Double precio;
    
    @Column(nullable = false, length=24)
    private String barCode;     
    
    
    /**
     * Obtiene el ID único del producto.
     * @return ID del producto.
     */
    public Long getId() {
        return id;
    }

    /**
     * Obtiene el nombre del producto.
     * @return Nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Establece el nombre del producto.
     * @param nombre Nombre del producto.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * Obtiene el precio de cada unidad del producto.
     * @return Precio de cada unidad.
     */
    public Double getPrecio() {
        return precio;
    }

    /**
     * Establece el precio de cada unidad de producto.
     * @param precio Precio.
     */
    public void setPrecio(Double precio) {
        this.precio = precio;
    }

    /**
     * Obtiene el código de barras del producto.
     * @return Código de barras del producto.
     */
    public String getBarCode() {
        return barCode;
    }

    /**
     * Establece el código de barras del producto.
     * @param barCode Código de barras del producto.
     */
    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }      

    @Override
    public String toString() {
        return String.format("ejemplo04.model.Producto[ id=%d;"
                + "nombre=%s;"
                + "precio=%f;"
                + "código de barras=%s ]",
                id,nombre,precio,barCode);
        
    }
    
}
