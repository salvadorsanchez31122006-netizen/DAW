
import java.util.Scanner;
/*
 * Ejemplo de entrada de teclado con la clase Scanner
 */

/**
 *
 * @author FMA
 */
public class EntradaTecladoScanner {
  
    public static void main(String[] args) {
        
        // Creamos objeto teclado
        Scanner teclado = new Scanner(System.in);

        // Declaramos variables a utilizar
        String nombre;
        int edad;
        boolean estudias;
        float salario;

        // Entrada de datos
        System.out.println("Nombre: ");
        nombre=teclado.nextLine();
        System.out.println("Edad: ");
        edad=teclado.nextInt();
        System.out.println("Estudias: ");
        estudias=teclado.nextBoolean();
        System.out.println("Salario: ");
        salario=teclado.nextFloat();

        // Salida de datos
        System.out.println("Bienvenido: " + nombre);
        System.out.println("Tienes: " + edad +" años");
        System.out.println("Estudias: " + estudias);
        System.out.println("Tu salario es: " + salario +" euros");
    }

}
