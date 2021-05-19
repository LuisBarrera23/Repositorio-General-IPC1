package pruebas;

public class Pruebas {
    public static void main(String[] args) {
        double[][] inversaA=new double[3][3];
        int[][] matrizA = new int[3][3];
        matrizA[0][0] = 1;
        matrizA[0][1] = -5;
        matrizA[0][2] = 6;
        matrizA[1][0] = 5;
        matrizA[1][1] = 3; 
        matrizA[1][2] = 1;
        matrizA[2][0] = -10;
        matrizA[2][1] = 10;
        matrizA[2][2] = 3;
        System.out.println("nuestra matriz A: ");
        mostrarmatrizint(matrizA);
        int[][] identidad = new int[3][3];
        identidad[0][0] = 1;
        identidad[0][1] = -0;
        identidad[0][2] = 0;
        identidad[1][0] = 0;
        identidad[1][1] = 1;
        identidad[1][2] = 0;
        identidad[2][0] = 0;
        identidad[2][1] = 0;
        identidad[2][2] = 1;
        mostrarmatrizint(identidad);
        double[][] matrizgauss = new double[3][6];

        System.out.println("Armamos el metodo de Gauss Jordan");
        for (int i = 0; i < matrizgauss.length; i++) {
            for (int j = 0; j < matrizgauss[i].length; j++) {
                if (j < 3) {
                    matrizgauss[i][j] = matrizA[i][j];
                } else {
                    matrizgauss[i][j] = identidad[i][j - 3];
                }
            }
        }
        mostrarmatrizdouble(matrizgauss);
        if (matrizgauss[0][0] != 1) {
            System.out.println("volvemos 1 la posicion [0][0]");
            double operador = matrizgauss[0][0];
            for (int j = 0; j < matrizgauss[0].length; j++) {
                matrizgauss[0][j] /= operador;
            }
            mostrarmatrizdouble(matrizgauss);
        }
        System.out.println("volvemos 0 las pocisiones abajo del 1");
        for (int i = 1; i < matrizgauss.length; i++) {
            if (matrizgauss[i][0] != 0) {
                double operador = matrizgauss[i][0];
                for (int j = 0; j < matrizgauss[i].length; j++) {
                    matrizgauss[i][j] = (-1 * operador * matrizgauss[0][j]) + matrizgauss[i][j];
                }
            }
        }
        mostrarmatrizdouble(matrizgauss);
        
        if (matrizgauss[1][1] != 1) {
            //System.out.println("volvemos 1 la posicion [1][1]");
            double operador = matrizgauss[1][1];
            for (int j = 0; j < matrizgauss[1].length; j++) {
                matrizgauss[1][j] /= operador;
            }
            mostrarmatrizdouble(matrizgauss);
        }
        
        System.out.println("volvemos 0 los demas numeros que estan por arriba y abajo del pivote");
        for (int i = 0; i < matrizgauss.length; i++) {
            if(i!=1){
             if (matrizgauss[i][1] != 0) {
                double operador = matrizgauss[i][1];
                for (int j = 0; j < matrizgauss[i].length; j++) {
                    matrizgauss[i][j] = (-1 * operador * matrizgauss[1][j]) + matrizgauss[i][j];
                }
            }   
            }
        }
        mostrarmatrizdouble(matrizgauss);
        
        if (matrizgauss[2][2] != 1) {
            System.out.println("volvemos 1 la posicion [2][2]");
            double operador = matrizgauss[2][2];
            for (int j = 0; j < matrizgauss[2].length; j++) {
                matrizgauss[2][j] /= operador;
            }
            mostrarmatrizdouble(matrizgauss);
        }
        
        System.out.println("ahora hacemos 0 las pocisiones arriba del 1 de la tercera columna");
        for (int i = 0; i < 2; i++) {
            if (matrizgauss[i][2] != 0) {
                double operador = matrizgauss[i][2];
                for (int j = 0; j < matrizgauss[i].length; j++) {
                    matrizgauss[i][j] = (-1 * operador * matrizgauss[2][j]) + matrizgauss[i][j];
                }
            }
        }
        mostrarmatrizdouble(matrizgauss);
        
        System.out.println("ahora capturamos unicamente la matriz inversa: ");
        for (int i = 0; i < matrizgauss.length; i++) {
            for (int j = 0; j < matrizgauss[i].length; j++) {
                if(j>=3){
                    inversaA[i][j-3]=matrizgauss[i][j];
                }
            }
            
        }
        mostrarmatrizdouble(inversaA);
        
        
    }
    public static void mostrarmatrizint(int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "}");
            }
            System.out.println(" ");
        }
    }
    public static void mostrarmatrizdouble(double[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "}");
            }
            System.out.println(" ");
        }
    }
    
}
