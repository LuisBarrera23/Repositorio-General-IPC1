package actividades.de.clase.pkg04.pkg03;

import java.util.Scanner;

public class ActividadesDeClase0403 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        contador(n);
    }
    
    public static void calcularfactorial(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println("el resultado de " + n + "! es: " + factorial(n));
    }

    public static int factorial(int n) {
        System.out.println("valor actual de n " + n);
        if (n == 0) {
            return 1;
        } else {
            return n * factorial(n - 1);
        }
    }

    public static String calcularinverso(int n) {
        System.out.println("valor actual: "+n);
        if(n==0){
            return "";
        }else{
            return ((n%10)+calcularinverso(n/10));
        }
    }
    
    public static int contador(int n) {
        System.out.println(n);
        if (n == 0) {
            return 0;
        } else {
            return contador(n - 1);
        }
    }
}
