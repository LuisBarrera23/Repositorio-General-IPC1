package examen.pkgfinal.recursividad;

import java.util.Scanner;

public class ExamenFinalRecursividad {

    public static void main(String[] args) {
        Scanner entrada=new Scanner(System.in);
        System.out.println("Ingrese un numero para invertirlo");
        int numero=entrada.nextInt();
        System.out.println("Su numero invertido es: "+InvertirNumero2(numero,Integer.toString(numero).length()-1));
        
    }

    public static int InvertirNumero(int num) {
        if (num < 10) {
            return num;
        } else {
            return (num % 10) + InvertirNumero(num / 10);
        }
    }
    
    public static int InvertirNumero2(int num, int largo){
        if (num < 10) {
            return num;
        } else {
            return (num % 10)*(int)Math.pow(10,largo) + InvertirNumero2(num / 10, largo-1);
        }

    
    }

}
