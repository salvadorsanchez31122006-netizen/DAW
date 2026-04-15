import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EjemploINSERTProducto01 {
   
    /**
     * Método que inserta un nuevo producto en la base de datos.  
     * @param con Conexión con la base de datos. 
     * @param nombre Nombre del nuevo producto.
     * @param barcode Nombre del código de barras.
     * @param precio Nombre del precio.
     * @return Id del producto recién creado.
     */
    public static long nuevoProducto(Connection con, String nombre,
            String barcode, double precio) {
        String query = "INSERT INTO producto (nombre, barcode, precio) VALUES (?,?,?)";
        long id=-1;
        if (con != null) {

            try ( PreparedStatement consulta = con.prepareStatement(query,
                                      Statement.RETURN_GENERATED_KEYS)) {

                consulta.setString(1, nombre);
                consulta.setString(2, barcode);
                consulta.setDouble(3, precio);

                int registrosAfectados = consulta.executeUpdate();
                if (registrosAfectados>0)
                {
                    ResultSet m=consulta.getGeneratedKeys();
                    if (m.next())
                    {
                        id=m.getLong(1);
                        System.out.printf("Producto insertado con ID=%d\n",id);                        
                    }
                } else {
                    System.out.println("El producto no ha podido ser insertado.");
                }

            } catch (SQLException ex) {
                System.err.printf("Se ha producio un error al ejecutar la consulta SQL.");
            }
        }
        return id;
    }
}
