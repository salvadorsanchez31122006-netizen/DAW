import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
 * Ejemplo de entrada por teclado con la clase System
 * @author FMA
 */

public class EntradaTeclado {

    
    public static void main(String[] args) {
        try
        {
            InputStreamReader isr = new InputStreamReader(System.in);
            BufferedReader br = new BufferedReader(isr);

            System.out.print("Introduce el texto: ");
            String cad = br.readLine();

            //salida por pantalla del texto introducido
            System.out.println(cad);

            System.out.print("Introduce un número: ");
            int num = Integer.parseInt(br.readLine());

            // salida por pantalla del nÃºmero introducido
            System.out.println(num);

        } catch (Exception e) {
           // System.out.println("Error al leer datos");
            e.printStackTrace();
        }
    }

}
