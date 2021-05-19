
package clase.pkg1;

//importar librerias
import java.util.Scanner;

public class Clase1 {

    
    
    public static void main(String[] args) {
        
            // escanner para cadenas
    Scanner texto = new Scanner (System.in);

    //escaner para los demas datos
    Scanner numeros = new Scanner(System.in);
    
      //DECLARACION DE VARIABLES
      boolean valor;
      int entero;
      char caracter;
      double decimal;
      
      //variables no primitivas
      String cadenas;
      
      // VARIABLES BOOLEAN
      valor=true;
      valor=false;
      
      //VARIABLES CHAR
      caracter = 'A';
      caracter = 65;
      
      //VARIABLES INT
      entero = 1000;
      entero = -1000;
      
      //VARIABLES DOUBLE
      decimal=100.25;
      decimal=-100.25;
      
      //VARIABLES STRING
      cadenas = "HOLA MUNDO :)";
      
      //MOSTRAR EN CONSOLA
      System.out.println("HOLA LABORATORIO IPC1");
      System.out.println(cadenas);
      //sout+tab
        System.out.println(cadenas + "-concatenamos texto");
        System.out.println(cadenas + "" + entero);
        System.out.println(decimal+entero);
        /* 
        //obtener texto del teclado y almacenarlo
        System.out.println("BIENVENIDO AL LABORATORIO DE IPC1");
        System.out.println("INGRESE SU NOMBRE:  ");
        cadenas = texto.nextLine();
        System.out.println("INGRESE SU EDAD");
        entero= numeros.nextInt();
        System.out.println("¿CUAL ES SU NOTA DE LABORATORIO?");
        decimal= numeros.nextDouble();
        
        System.out.println("--- RESUMEN DE LOS DATOS---");
        System.out.println("NOMBRE: "+cadenas);
        System.out.println("EDAD:"+ entero);
        System.out.println("NOTA: " +decimal);
        
        //SENTENCIA IF Y ELSE IF
        if(decimal>60){
            System.out.println("FELICIDADES" +cadenas+ "GANASTE LABORATORIO");
            
        } else if(decimal>57){
            System.out.println("TE QUEDASTE POR POQUITITITO");
        }
        else {
            System.out.println("F, echale ganas");
        }

        
        //SENTENCIA SWITCH
        System.out.println("INGRESE UN NUMERO DEL 1 AL 7");
        entero=numeros.nextInt();
        
        switch(entero){
            case 1:
                System.out.println(" es dia lunes ");
                break;
        }

        int limite = 10;
        while(limite > 0){
            System.out.println(limite+  "  dentro del while");
            limite -= 1;
            // tambien se puede usar limite= limite -1
            // limite --
        }

        
        //sentencias DO-WHILE
        
        do{
            System.out.println("---MENU---");
            System.out.println("1. mensaje 1");
            System.out.println("2. mensaje 1");
            System.out.println("3. mensaje 1");
            System.out.println("4. salir");
            System.out.println("salir");
            System.out.println("ESCOGER UNA OPCION:  ");
            opcion=numeros.nextInt();
        } while(opcion !=4);
        System.out.println("");
    }
*/
        
        int i;
        for(i=0; i<=10; i++){
            System.out.println("TABLAS DE MULTIPLICAR");
            int numero;
            System.out.print("ingrese ");
        }
    
}
}
