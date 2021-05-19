package actividad.de.clase.pkg09.pkg03;

import java.util.Scanner;

public class ActividadDeClase0903 {

    public static int[][] mat = new int[5][5];

    public static void main(String[] args) {
        actividad1();

    }

    public static void actividad1() {

//        for (int i = 0; i < 5; i++) {
//            for (int j = 0; j <5; j++) {
//                mat[i][j]=5;
//            }
//            
//        }
        latino(5, 5, 5, 5, mat);
        System.out.println("----------------------------");
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                System.out.print(mat[i][j]);
            }
            System.out.println(" ");

        }
        System.out.println("-----------------------------");

    }
    
    public static void actividad2(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Introduce el texto: ");
        String cadena = sc.nextLine();
        String invertida = invertir(cadena);
        System.out.printf("Texto invertido: "+ invertida);
    }

    public static void latino(int fila, int col, int cont, int orden, int mat[][]) {
        if (fila == 0 && col == 0) {
            mat[0][0] = 1;
        } else if (fila == col) {
            latino(fila - 1, orden - 1, orden, orden, mat);
        } else {
            mat[fila][col] = cont;
            latino(fila, col - 1, orden + 1, orden, mat);
        }

    }

    private static String invertir(String cadena) {
        if (cadena.length() == 1) {
            return cadena;
        } else {
            return invertir(cadena.substring(1)) + cadena.charAt(0);

        }
    }

}
