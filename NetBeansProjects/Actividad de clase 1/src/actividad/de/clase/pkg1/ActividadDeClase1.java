package actividad.de.clase.pkg1;

import java.util.Scanner;//libreria para el scanner

public class ActividadDeClase1 {
    static int[]numeros={8,45,2,69,1};
    
    public static void main(String[] args) {
        //multiplicacion(); 
        int a=numeros.length;
        int b=0;
        System.out.println(reto(a,b));
        
    }
    public static void multiplicacion(){
        Scanner sc=new Scanner(System.in);
       
        System.out.println("Programa para la realizacion de multiplicacion:");
        System.out.println("Ingrese el primero numero");
        int numero1=sc.nextInt();
        System.out.println("Ingrese el segundo numero");
        int numero2=sc.nextInt();
        int multiplicacion;
        multiplicacion = multiplicarvalores(numero1,numero2);
        System.out.println("lo que retorna nuestra funcion es:"+multiplicacion);
    }

    private static int multiplicarvalores(int numero1, int numero2) {
        int valor1 = 0;
        boolean condicion=true;
        while(condicion){
            valor1++;
            System.out.println(valor1);
            if(valor1 == numero1 * numero2){
                
                break;
            }
        }
        System.out.println("se ha llegado, el resultado es: "+valor1);
        
        return valor1;
    
    }    

    private static int reto(int a,int b) {
        if(a>0){
            b=numeros[a];
            a--;
            return reto(a-1,b)+b;
        }else{
            return b;
        }
    }
}
