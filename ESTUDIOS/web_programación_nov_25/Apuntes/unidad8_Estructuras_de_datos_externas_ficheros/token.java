package tokenizer;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;


public class Token {

    public void contarPalabrasyNumeros(String nombreFichero) {

        StreamTokenizer sTokenizer = null;
        int contadorPalabras = 0, numeroDeNumeros = 0;

        try {

            sTokenizer = new StreamTokenizer(new FileReader(nombreFichero));

            while (sTokenizer.nextToken() != StreamTokenizer.TT_EOF) {

                if (sTokenizer.ttype == StreamTokenizer.TT_WORD)
                    contadorPalabras++;
                else if (sTokenizer.ttype == StreamTokenizer.TT_NUMBER)
                    numeroDeNumeros++;
            }

            System.out.println("Número de palabras en el fichero: " + contadorPalabras);
            System.out.println("Número de números en el fichero: " + numeroDeNumeros);

        } catch (FileNotFoundException ex) {
           System.out.println(ex.getMessage()) ;
        } catch (IOException ex) {
           System.out.println(ex.getMessage()) ;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        new Token().contarPalabrasyNumeros("c:\\datos.txt");
    }
}

