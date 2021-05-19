package actvidad4;

import java.util.Scanner;

public class Actvidad4 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int num1,num2=0;
        System.out.println("ingrese el primer numero");
        num1=sc.nextInt();
        System.out.println("ingrese el segundo numero");
        num2=sc.nextInt();
        
        int resultado=dividir(num1,num2);
        System.out.println(resultado);
    }

    private static int dividir(int num1, int num2) {
    int cociente=0;
    while(num1>0){
        System.out.println(num1+"-"+num2);
        num1=num1-num2;
        cociente++;
    }
    return cociente;
    }
    
}
