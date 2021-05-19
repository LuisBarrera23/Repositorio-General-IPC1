package proyecto.pkg1;


import java.io.BufferedReader; //librerias para leer archivos
import java.io.File; //librerias para leer archivos
import java.io.FileReader;//librerias para leer archivos
import java.io.FileWriter;//librerias para escribir archivos
import java.io.PrintWriter;//librerias para escribir archivos
import java.time.LocalDate;//Libreria para visualizar la fecha en los reportes
import java.time.LocalTime; //Liberia para visualizar la hora en los reportes
import java.util.Scanner;//Liberia para el Scanner

public class Proyecto1 {
    static String mensaje; //aca se guardara el mensaje ingresado
    static int c; //para guardar la cantidad de columnas de la matriz M
    //matrices que se usaran en la ejecucion de los procesos
    static int[][] matrizM;//guardar el mensaje en enteros
    static int[][] matrizA=new int[3][3];//para guardar la matriz A
    static int[][] matrizB;//PARA GUARDAR LA MATRIZ B
    static int[][] matrizAXM;//paso de encriptacion
    static int[][] matrizC;//guardar la matriz con el mensaje encriptado
    static int[][] matrizCmenosB;//paso de desencriptacion
    static int[][] matrizMD;//para guardar el mensaje desencriptado
    static double[][] inversaA=new double[3][3];
    //matrices especiales creadas para mostrar pasos en reportes
    static double[][] matrizgauss = new double[3][6];
    static double[][] metodogauss=new double[3][6];
    static char[][] hdecifrado=new char[3][c];

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;//para guardar la opcion que selecciono el usuario

        do {
            try {
                
                //mostramos el menu principal 
                System.out.println("============MENU PRINCIPAL============");
                System.out.println("|1.Encriptar                         |");
                System.out.println("|2.Desencriptar                      |");
                System.out.println("|3.Ataque con texto claro            |");
                System.out.println("|4.Genear Reportes                   |");
                System.out.println("|5.Salir                             |");
                System.out.println("======================================");
                System.out.println("Ingresa el numero de la opcion deseada: ");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        menu_encriptar();
                        break;
                    case 2:
                        System.out.println("Desencriptar");
                        CmenosB();// para realizar C-B
                        inversaporgauss();// para encontrar la inversa de A
                        multiplicarinversa();//multiplicar la inversa con el resultado de C-B
                        mostrardecifrado();//para imprimir el mensaje cifrado en una sola linea
                        break;
                    case 3:
                        System.out.println("Ataque con texto claro");
                        break;
                    case 4:
                        if (matrizMD != null) {
                            System.out.println("---------Generando Reportes--------");
                            reporte1();//para generar el reporte de encriptacion
                            reporte2();//para generar el reporte de desencriptacion
                            System.out.println("----Reportes Generados con Exito---");
                        }else{
                            System.out.println("No ha completado el proceso Encriptacion/Desencriptacion.");
                        }
                        
                        break;
                    case 5:
                        break;
                    default:
                        System.out.println("Opcion no valida");

                }
            } catch (Exception e) {
                sc.nextLine();
            }
        } while (opcion != 5);
    }

    public static void menu_encriptar() {
        Scanner sc = new Scanner(System.in);
        int opcion = 0;

        do {
            try {
                System.out.println("============MENU ENCRIPTAR============");
                System.out.println("|1.Ingreso Mensaje                   |");
                System.out.println("|2.Ingreso Matriz Clave A            |");
                System.out.println("|3.Ingreso Matriz Clave B            |");
                System.out.println("|4.Encriptar                         |");
                System.out.println("|5.Regresar al Menu Principal        |");
                System.out.println("======================================");
                System.out.println("Ingresa el numero de la opcion deseada: ");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        ingresomensaje();//para ingresar y procesar el mensaje que se ingreso
                        break;
                    case 2:
                        ingresomatrizA();//leer el archivo de la ruta ingresada y guardarla en la matriz A
                        break;
                    case 3:
                        ingresomatrizB();//leer el archivo de la ruta ingresada y guardarla en la matriz B
                        break;
                    case 4:
                        if (matrizA == null || matrizB == null) {
                            System.out.println("Debe ingresar primero las dos matrices");
                        } else {
                            System.out.print("El mensaje que se desea Encriptar es: ");
                            System.out.println(mensaje);
                            //pasos para la realizacion de la encriptacion
                            AXM();
                            AXMsumaB();
                            mostrarmensajecrifrado();
                            
                        }
                        break;
                    case 5:
                        System.out.println("-----Regresando al menu principal------");
                        break;
                    default:
                        System.out.println("Opcion no valida");

                }
            } catch (Exception e) {
                sc.nextLine();
                System.out.println("error");
            }
        } while (opcion != 5);

    }
    
    public static void ingresomensaje() {
        Scanner sc = new Scanner(System.in,"ISO-8859-1");
        String frase;
        System.out.println("Ingrese el mensaje que desee Encriptar");
        frase = sc.nextLine();
        mensaje = frase.toUpperCase();
        System.out.println("Su mensaje ingresado es: " + mensaje);
        char letras[] = mensaje.toCharArray();
        //proceso para saber cuantas columnas tiene que tener el mensaje
        int largo = letras.length;
        if (largo % 3 != 0) {
            while (largo % 3 != 0) {
                largo += 1;
            }
        }
        int columnas = largo / 3;
        c=columnas;
        //para obtener matriz M
        int contador = 0;
        matrizM = new int[3][columnas];
        for (int j = 0; j < columnas; j++) {
            for (int i = 0; i < matrizM.length; i++) {
                if (contador < letras.length) {
                    matrizM[i][j] = letras_numeros(letras[contador]);
                }
                if (contador >= letras.length) {
                    matrizM[i][j] = 27;//para rellenar la matriz con espacios para completar la matriz 3xn
                }
                contador++;

            }
        }
        mostrarmatriz(matrizM);

    }
    
    public static void mostrarmatriz(int[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "}");
            }
            System.out.println(" ");
        }
    }
    
    public static int letras_numeros(char letra) {
        switch (letra) {
            case 'A':
                return 0;
            case 'B':
                return 1;
            case 'C':
                return 2;
            case 'D':
                return 3;
            case 'E':
                return 4;
            case 'F':
                return 5;
            case 'G':
                return 6;
            case 'H':
                return 7;
            case 'I':
                return 8;
            case 'J':
                return 9;
            case 'K':
                return 10;
            case 'L':
                return 11;
            case 'M':
                return 12;
            case 'N':
                return 13;
            case 'Ñ':
                return 14;
            case 'O':
                return 15;
            case 'P':
                return 16;
            case 'Q':
                return 17;
            case 'R':
                return 18;
            case 'S':
                return 19;
            case 'T':
                return 20;
            case 'U':
                return 21;
            case 'V':
                return 22;
            case 'W':
                return 23;
            case 'X':
                return 24;
            case 'Y':
                return 25;
            case 'Z':
                return 26;
            case ' ':
                return 27;
        }
        return 27;

    }
    
    public static void ingresomatrizA() {
        Scanner rt = new Scanner(System.in);
        File archivo = null;
        FileReader fr = null;
        BufferedReader bf = null;

        try {
            System.out.println("Ingrese la ruta donde esta la matriz A que desea usar: ");
            String ruta = rt.nextLine();

            archivo = new File(ruta);
            fr = new FileReader(archivo);
            bf = new BufferedReader(fr);
            String linea;
            int i = 0;

            while ((linea = bf.readLine()) != null) {
                String numeros[] = linea.split(",");
                for (int j = 0; j < numeros.length; j++) {
                    matrizA[i][j] = Integer.parseInt(numeros[j]);
                }
                i++;
            }
            System.out.println("La matriz A seleccionada es la siguiente: ");
            mostrarmatriz(matrizA);
            fr.close();
            
        } catch (Exception e) {
        }
    }
    
    public static void ingresomatrizB(){
        if(c==0){
            System.out.println("usted no ha ingresado el mensaje para encriptar");
        }else{
          matrizB=new int[3][c];
        Scanner rt = new Scanner(System.in);
        File bloc = null;
        FileReader contenedor = null;
        BufferedReader lector = null;
        try {

            System.out.println("Ingrese la ruta donde esta la matriz B que desea usar: ");
            String ruta = rt.nextLine();

            bloc = new File(ruta);
            contenedor = new FileReader(bloc);
            lector = new BufferedReader(contenedor);

            String lineas;
            int i = 0;

            while ((lineas = lector.readLine()) != null) {
                String numeros[] = lineas.split(",");
                for (int j = 0; j < numeros.length; j++) {
                    matrizB[i][j] = Integer.parseInt(numeros[j]);
                }
                i++;

            }
            mostrarmatriz(matrizB);
            contenedor.close();
            
        }catch(Exception e){ 
            System.out.println("ruta no valida");
        }  
        }    
    }
    
    public static void AXM() {
        matrizAXM = new int[3][c];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < c; j++) {
                for (int h = 0; h < 3; h++) {
                   matrizAXM[i][j]+=matrizA[i][h]*matrizM[h][j];
                }
            }
        }
        System.out.println("Multiplicamos la matriz AxM y nos queda asi:");
        mostrarmatriz(matrizAXM);
    }
    
    public static void AXMsumaB(){
      matrizC=new int[3][c];
        System.out.println("Luego sumamos la matriz B y obtenemos la matriz C con el mensaje encriptado; ");
        for (int i = 0; i < matrizC.length; i++) {
            for (int j = 0; j < c; j++) {
                matrizC[i][j]=matrizAXM[i][j]+matrizB[i][j];
            }  
        }
        mostrarmatriz(matrizC);    
    }
    
    public static void mostrarmensajecrifrado(){
        System.out.println("El mensaje cifrado es: ");
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < matrizC.length; i++) {
                System.out.print("[" + matrizC[i][j] + "}");
            }
        }
        System.out.println(" ");
    }
    
    public static void mostrarmatrizdouble(double[][] matriz){
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                System.out.print("[" + matriz[i][j] + "}");
            }
            System.out.println(" ");
        }
    }
    
    public static void inversaporgauss(){
        System.out.println("nuestra matriz A: ");
        mostrarmatriz(matrizA);
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
        mostrarmatriz(identidad);
        
        System.out.println("Armamos el metodo de Gauss Jordan");
        for (int i = 0; i < matrizgauss.length; i++) {
            for (int j = 0; j < matrizgauss[i].length; j++) {
                if (j < 3) {
                    matrizgauss[i][j] = matrizA[i][j];
                    metodogauss[i][j] = matrizA[i][j];//para el reporte
                } else {
                    matrizgauss[i][j] = identidad[i][j - 3];
                    metodogauss[i][j] = identidad[i][j - 3];//para el reporte
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
            System.out.println("volvemos 1 la posicion [1][1]");
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
    
    public static void CmenosB(){
        matrizCmenosB=new int[3][c];
        System.out.println("A la matriz C le restamos B y se obtiene: ");
        for (int i = 0; i < matrizC.length; i++) {
            for (int j = 0; j < c; j++) {
                matrizCmenosB[i][j]=matrizC[i][j]-matrizB[i][j];
            }  
        }
        mostrarmatriz(matrizCmenosB);
    }
    
    public static void multiplicarinversa(){
        double[][] matriz=new double[3][c];
        matrizMD=new int[3][c];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < c; j++) {
                for (int h = 0; h < 3; h++) {
                   matriz[i][j]+=inversaA[i][h]*matrizCmenosB[h][j];
                }
            }
        }
        
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                matrizMD[i][j]=(int)Math.round(matriz[i][j]);
            }
            
        }
        System.out.println("Al realizar la operacion A^-1*(C-B) nos da como resultado: ");
        mostrarmatriz(matrizMD);
    }
    
    public static char numeros_letras(int numero){
        switch (numero) {
            case 0:
                return 'A';
            case 1:
                return 'B';
            case 2:
                return 'C';
            case 3:
                return 'D';
            case 4:
                return 'E';
            case 5:
                return 'F';
            case 6:
                return 'G';
            case 7:
                return 'H';
            case 8:
                return 'I';
            case 9:
                return 'J';
            case 10:
                return 'K';
            case 11:
                return 'L';
            case 12:
                return 'M';
            case 13:
                return 'N';
            case 14:
                return 'Ñ';
            case 15:
                return 'O';
            case 16:
                return 'P';
            case 17:
                return 'Q';
            case 18:
                return 'R';
            case 19:
                return 'S';
            case 20:
                return 'T';
            case 21:
                return 'U';
            case 22:
                return 'V';
            case 23:
                return 'W';
            case 24:
                return 'X';
            case 25:
                return 'Y';
            case 26:
                return 'Z';
            case 27:
                return ' ';
        }
        return ' ';
    }
    
    public static void mostrardecifrado(){
        char[][] decifrado=new char[3][c];
        System.out.println("Mensaje descifrado es: ");
        for (int j = 0; j < c; j++) {
            for (int i = 0; i < 3; i++) {
                decifrado[i][j]=numeros_letras(matrizMD[i][j]);
                System.out.print(decifrado[i][j]);
            }  
        }
        System.out.println(" ");
        decifrado=hdecifrado;
    }
    
    public static void reporte1() {
        LocalTime hora= LocalTime.now();
        LocalDate fecha=LocalDate.now();
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw = null;//para ir escribiendo el archivo
        

        try {
            fichero=new FileWriter("ReporteEncriptado.html");
            pw=new PrintWriter(fichero);
            //pw.println("");
            pw.println("<html>");
            pw.println("<title>Reporte de Encriptacion</title>");
            pw.println("<h1><b><center><head>Reporte de Encriptación</head></center></b></h1>");
            pw.println("<body style=\"background-color: F0E68C;\">");
            pw.println("<p>Hora del reporte: "+hora+"</p>");
            pw.println("<p>Fecha del reporte: "+fecha+"</p>");
            pw.println("<p>El usuario ingreso un mensaje que deseaba encriptar el cual era el siguiente:</p>");
            pw.println("<b><p>"+mensaje+"</p></b>");
            pw.println("<p>Despues de eso se ordeno el mensaje y se acomodo en una matriz convirtiendola a numeros enteros: </p>");
            
            //estructura para mostrar una matriz
            pw.println("<h3>Matriz M:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizM.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizM[i].length; j++) {
                    pw.print("<td>" + matrizM[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            //fin de la estructura para matriz
            
            
            
            pw.println("<p>Se le pedio al usuario ingresar dos matrices por medio de una ruta. En este caso las matrices fueron: </p>");
            
            
            
            pw.println("<h3>Matriz A:</h3>");
            
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizA.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizA[i].length; j++) {
                    pw.print("<td>" + matrizA[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<h3>Matriz B:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizB.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizB[i].length; j++) {
                    pw.print("<td>" + matrizB[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<p>Despues se realizo la multiplicacion de matrices A por la matriz del mensaje es decir A  X  M y el resultado fue el siguiente:</p>");
            
            
            pw.println("<h3>Matriz AXM:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizAXM.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizAXM[i].length; j++) {
                    pw.print("<td>" + matrizAXM[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<p>A la matriz resultado se le sumo la matriz B obteniendo dentro de ella nuestro mensaje encriptado </p>");
            
            
            pw.println("<h3>Matriz C:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizC.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizC[i].length; j++) {
                    pw.print("<td>" + matrizC[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<p>El mensaje encriptado es: </p>");
            
            pw.print("<p>");
            for (int j = 0; j < c; j++) {
            for (int i = 0; i < matrizC.length; i++) {
                pw.print(matrizC[i][j] + "  ");
            }
        }
            pw.println("</p>");
            
            
            
            pw.println("</body>");
            pw.println("</html>");
            
            
            fichero.close();
        }catch(Exception e){
            System.out.println("error");
        }
    }
    
    public static void reporte2() {
        LocalTime hora= LocalTime.now();
        LocalDate fecha=LocalDate.now();
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw = null;//para ir escribiendo el archivo
        

        try {
            fichero=new FileWriter("ReporteDesencriptado.html");
            pw=new PrintWriter(fichero);
            //pw.println("");
            pw.println("<html>");
            pw.println("<title>Reporte Desencriptado</title>");
            pw.println("<h1><b><center><head>Reporte Desencriptado</head></center></b></h1>");
            pw.println("<body style=\"background-color: F0E68C;\">");
            pw.println("<p>Hora del reporte: "+hora+"</p>");
            pw.println("<p>Fecha del reporte: "+fecha+"</p>");
            
            
            pw.println("<p>Nuestra matriz C con el mensaje encriptado es la siguiente: </p>");
            
            
            pw.println("<h3>Matriz C:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizC.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizC[i].length; j++) {
                    pw.print("<td>" + matrizC[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<p>A esta matriz le restamos la matriz B y nos queda:</p>");
            
            pw.println("<h3>Matriz C-B:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizCmenosB.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizCmenosB[i].length; j++) {
                    pw.print("<td>" + matrizCmenosB[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            
            
            pw.println("<p>Ahora debemos calcular la matriz Inversa de A</p>");
            pw.println("<p>Para esto usaremos el metodo de Gauss </p>");
            pw.println("<p>Debemos poner la matriz A y a la derecha una matriz identidad de la siguiente forma</p>");
            
            pw.println("<h3>Armando metodo de Gauss para calcular inversa de A:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < metodogauss.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < metodogauss[i].length; j++) {
                    pw.print("<td>" + metodogauss[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<p>Al resolver por Gauss a manera que la matriz identidad nos quede al lado izquierdo: </p>");
            
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizgauss.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizgauss[i].length; j++) {
                    pw.print("<td>" + matrizgauss[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<p>Ahora la matriz que quedo al lado derecho de la matriz identidad es nuestra matriz Inversa de A: </p>");
            
            pw.println("<h3>Matriz Inversa de A:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < inversaA.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < inversaA[i].length; j++) {
                    pw.print("<td>" + inversaA[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<p>Luego se debe multiplicar la matriz Inversa de A con la matriz C-B y nos da la matriz MD que tiene nuestro mensaje decifrado aun en numeros: </p>");
            
            pw.println("<h3>Matriz MD:</h3>");
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < matrizMD.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < matrizMD[i].length; j++) {
                    pw.print("<td>" + matrizMD[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("<p>Nuestro mensaje Descifrado es:</p>");
            pw.println("<p>"+mensaje+"</p>");


            
            pw.println("</body>");
            pw.println("</html>");
            
            fichero.close();
        }catch(Exception e){
            e.printStackTrace();
            System.out.println("error");
        }
    }
    
}