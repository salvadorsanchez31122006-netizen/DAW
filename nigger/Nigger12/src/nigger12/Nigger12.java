/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package nigger12;
import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author rggal
 */
public class Nigger12 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Scanner sexo = new Scanner(System.in);
        int txt;
        
        
        
        try{
        do{
        Connection miConexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/nigger12","root","Hola-123");
        Statement miConsulta = miConexion.createStatement();
           System.out.println("introduce el sexo");
        txt=sexo.nextInt();
        sexo.nextLine();
     
        switch (txt){
            case 1:
                ResultSet miRespuesta = miConsulta.executeQuery("Select * from sexo_anal");
                while (miRespuesta.next()){
                    System.out.println(miRespuesta.getInt(1)+" "+miRespuesta.getString(2));
                }
                break;
            
            case 2:
                System.out.println("Introduce el nombre del violado");
                String txt2=sexo.nextLine();
                miConsulta.executeUpdate("INSERT INTO `nigger12`.`sexo_anal` (`nigger`) VALUES ('"+txt2+"')");
                break;
       
            case 3:
                System.out.println("Introduce el nº del empleado que quieres modificar");
                int txt3=sexo.nextInt();
                sexo.nextLine();
                System.out.println("Introduce el nombre que quieres poner");
                String txt4=sexo.nextLine();
                
                miConsulta.executeUpdate("update sexo_anal set nigger = '"+txt4+"' where id="+txt3+"");
        
                break;
            
            case 4:
                System.out.println("Introduce el nº del empleado que quieres modificar");
                int txt5=sexo.nextInt();
                sexo.nextLine();
                
                miConsulta.executeUpdate("delete from sexo_anal where id="+txt5+"");
                break;
            default:
            System.out.println("Maricon");
            break;
        }
        
        }while(txt!=5);
        }catch(Exception e){
            System.err.println(e);}
    }
    
}
