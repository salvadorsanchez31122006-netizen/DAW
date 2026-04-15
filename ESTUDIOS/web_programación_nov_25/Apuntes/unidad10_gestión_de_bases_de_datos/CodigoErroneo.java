package minipos;

import java.util.Properties;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import minipos.model.Producto;

public class CodigoErroneo {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Properties propiedades = new Properties();

        /* driver utilizado en esta conexión */
        propiedades.put("javax.persistence.jdbc.driver", "org.h2.Driver");
        /* URL para conectar a la base de datos*/
        propiedades.put("javax.persistence.jdbc.url", "jdbc:h2:./ej_aptd_261.h2db");
        /* nombre de usuario para conectar */
        propiedades.put("javax.persistence.jdbc.user", "");
        /* password para necesario para conectar */
        propiedades.put("javax.persistence.jdbc.password", "");
        /* modelo de generación de base de datos */
        propiedades.put("javax.persistence.schema-generation.database.action", "create");

        try {

            EntityManagerFactory emf = Persistence.createEntityManagerFactory("EjemplosJPA", propiedades);

            EntityManager em = emf.createEntityManager();
            
            // Creamos dos instancias de entidad
            Producto p1=new Producto("Cuchara Elegance", 3.49, "8480000145895");
            Producto p2=new Producto("Cuchara Normal", 1.39, "8480000145895");
            
            // Almacenamos la primera entidad
            em.getTransaction().begin();
            em.persist(p1);
            em.getTransaction().commit();
            
            // Mostramos la entidad (el ID se habrá actualizado)
            System.out.printf("Entidad 1 guardada [Producto: %s, Precio: %.2f]\n",p1.getNombre(),p1.getPrecio());
            
            // Mostramos el precio con IVA de primera entidad
            p1.setPrecio(p1.getPrecio()*1.21);             
            System.out.printf("Precio con IVA entidad 1 [Producto: %s, Precio con IVA: %.2f]\n",p1.getNombre(),p1.getPrecio());
            
            // Almacenamos la segunda instancia de entidad
            em.getTransaction().begin();
            em.persist(p2);
            em.getTransaction().commit();
            
            // Mostramos la segunda entidad (el ID se habrá actualizado)            
            System.out.printf("Entidad 2 guardada [Producto: %s, Precio: %.2f]\n",p2.getNombre(),p2.getPrecio());
            
            // Mostramos el precio con IVA de la segunda entidad
            p2.setPrecio(p2.getPrecio()*1.21);             
            System.out.printf("Precio con IVA entidad 1 [Producto: %s, Precio con IVA: %.2f]\n",p2.getNombre(),p2.getPrecio());
            
            // Desacoplamos las entidades p1 y p2
            em.detach(p1);
            em.detach(p2);                        
            
            //Probamos ahora a leer los productos antes guardados rescatandolos a través de su ID           
            Producto p1prueba = em.find(Producto.class, p1.getId());
            Producto p2prueba = em.find(Producto.class, p2.getId());
            
            //Mostramos los productos rescatados de la base de datos.
            System.out.printf("Entidad 1 rescatada [Producto: %s, Precio: %.2f]\n",p1.getNombre(),p1.getPrecio());
            System.out.printf("Entidad 2 rescatada [Producto: %s, Precio: %.2f]\n",p2.getNombre(),p2.getPrecio());
            
            em.close();

            emf.close();

        } catch (PersistenceException pe) {
            System.err.println("Error al conectar con la base de datos.");
            pe.printStackTrace();
        }
    }

}
