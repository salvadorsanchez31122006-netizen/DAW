package separadorrutas;

import java.io.File;

/**
 *
 * @author Profesor
 */
public class SeparadorRutas {
    
    /**
     * 
     * @param ruta
     * @return 
     */
    private static String substFileSeparator(String ruta) {
        String separador = "\\";
        try {
            // Si estamos en Windows
            if (separador.equals(File.separator))
                separador = "/" ;
        
            // Reemplaza todas las cadenas que coinciden con la expresión
            // separador por la cadena File.separator
            return ruta.replaceAll(separador, File.separator);
        }catch(Exception e){
            // Por si ocurre una excepción
            return ruta.replaceAll(separador + separador, File.separator);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        String miRuta = "C:\\datos" ;
        
        String laRuta = substFileSeparator(miRuta) ;
        
        System.out.println(laRuta) ;
    }
    
}
