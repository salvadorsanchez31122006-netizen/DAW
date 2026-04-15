/**
 * Esa es la parte de descripción
 * 
 * @etiqueta Uso de la etiqueta y su comentario
 */
public class DelegacionDeExcepciones {
  public static void main(String[] args){
    boolean fueraDeLimites=true;
    int i; //Entero que tomará valores aleatorios de 0 a 9
    String texto[] = {"uno","dos","tres","cuatro","cinco"}; //String que representa la moneda

    while(fueraDeLimites){
        try{
            i=  (int) Math.round(Math.random()*9); //Generamos un índice aleatorio
            System.out.println(texto[i]);
            fueraDeLimites=false;
        }catch(ArrayIndexOutOfBoundsException exc){
            System.out.println("Fallo en el índice.");
        }
    }     
  } 
}


