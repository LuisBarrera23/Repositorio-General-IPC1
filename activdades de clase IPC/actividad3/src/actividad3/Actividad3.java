package actividad3;

import java.util.Scanner;

public class Actividad3 {

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        System.out.println("Ingrese el rango de numeros que desea evaluar");
        System.out.println("primero indique por cual numero quiere empezar:");
        int inf=sc.nextInt();
        System.out.println("ahora ingrese hasta que numero desea evaluar:");
        int sup=sc.nextInt();
        int cantidad=0;
        for (int i = inf; i <= sup; i++) {
            cantidad++;
        }
        int[] valores=new int[cantidad];
        int pos=0;
        for (int i = inf; i <= sup; i++) {
            valores[pos]=i;
            pos++;
        }
        for (int i = 0; i < valores.length; i++) {
            System.out.print(valores[i]+",");
        }
        System.out.println(" ");
        
        String numpares="";
        String numimpares="";
        int contpares=0;
        int contimpares=0;
        int sumatoria=0;
        int sumatoria1=0;
        int sumatoria2=0;
        
        for (int i = 0; i < valores.length; i++) {
            if(valores[i]%2==0){
                numpares+=valores[i]+",";
                contpares++;
                sumatoria+=valores[i];
                sumatoria1+=valores[i];
            }else{
                numimpares+=valores[i]+",";
                contimpares++;
                sumatoria+=valores[i];
                sumatoria2+=valores[i];
            }
        }
        double promedio=sumatoria/valores.length;
        System.out.println("Hay "+contpares+" numeros pares los cuales son: "+numpares+ " y suman "+sumatoria1);
        System.out.println("Hay "+contimpares+" numeros impares los cuales son: "+numimpares+" y suman "+sumatoria2);
        System.out.println("el promedio de todos los valores es "+promedio);
    }
    
}
