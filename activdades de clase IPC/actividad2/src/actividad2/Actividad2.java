package actividad2;

public class Actividad2 {

    public static void main(String[] args) {
        int[][] matrizA=new int[5][5];
        matrizA[0][0]=3;
        matrizA[0][1]=5;
        matrizA[0][2]=6;
        matrizA[0][3]=2;
        matrizA[0][4]=3;
        matrizA[1][0]=5;
        matrizA[1][1]=8;
        matrizA[1][2]=2;
        matrizA[1][3]=7;
        matrizA[1][4]=5;
        matrizA[2][0]=8;
        matrizA[2][1]=6;
        matrizA[2][2]=1;
        matrizA[2][3]=6;
        matrizA[2][4]=8;
        matrizA[3][0]=1;
        matrizA[3][1]=7;
        matrizA[3][2]=6;
        matrizA[3][3]=7;
        matrizA[3][4]=8;
        matrizA[4][0]=5;
        matrizA[4][1]=1;
        matrizA[4][2]=7;
        matrizA[4][3]=6;
        matrizA[4][4]=6;
        int[][] matrizB=new int[5][5];
        matrizB[0][0]=2;
        matrizB[0][1]=8;
        matrizB[0][2]=3;
        matrizB[0][3]=2;
        matrizB[0][4]=8;
        matrizB[1][0]=5;
        matrizB[1][1]=6;
        matrizB[1][2]=5;
        matrizB[1][3]=6;
        matrizB[1][4]=7;
        matrizB[2][0]=8;
        matrizB[2][1]=3;
        matrizB[2][2]=8;
        matrizB[2][3]=3;
        matrizB[2][4]=5;
        matrizB[3][0]=6;
        matrizB[3][1]=4;
        matrizB[3][2]=4;
        matrizB[3][3]=5;
        matrizB[3][4]=6;
        matrizB[4][0]=3;
        matrizB[4][1]=2;
        matrizB[4][2]=8;
        matrizB[4][3]=5;
        matrizB[4][4]=2;
        
        int[][] matrizC=new int[5][5];
        
        //PROCESO DE OBTENCION MATRIZ C
        System.out.println("Matriz A");
        mostrarmatriz(matrizA);
        System.out.println(" ");
        System.out.println("Matriz B");
        mostrarmatriz(matrizB);
        System.out.println(" ");
        
        int indice;
        for (int j = 0; j < 5; j++) {
            indice=4;
            for (int i = 0; i < 5; i++) {
                matrizC[i][j]=matrizA[j][i]*matrizB[indice][j];
                indice--;
            }
            
        }
        mostrarmatriz(matrizC);
    }
    
    public static void mostrarmatriz(int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "]\t");
            }
            System.out.println(" ");
        }
    }
}
