package pruebas.reportes;

import java.io.FileWriter;
import java.io.PrintWriter;

public class PruebasReportes {
    static int[][] identidad;

    public static void main(String[] args) {
        identidad = new int[3][3];
        identidad[0][0] = 1;
        identidad[0][1] = -0;
        identidad[0][2] = 0;
        identidad[1][0] = 0;
        identidad[1][1] = 1;
        identidad[1][2] = 0;
        identidad[2][0] = 0;
        identidad[2][1] = 0;
        identidad[2][2] = 1;
        reporte1();

    }

    public static void reporte1() {
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw=null;//para ir escribiendo el archivo
        
        try{
            fichero=new FileWriter("Reportes/Reporte.html");
            pw=new PrintWriter(fichero);
            
            pw.println("<html>");
            //pw.println("");
            pw.println("<title>Reporte de Encriptacion</title>");
            pw.println("<body style=\"background-color: F0E68C;\">");
            pw.println("<p>xd todo esto hay que ver si se mira xd</p>");
            
            //mostrarmatrizhtml(identidad);
            
            pw.println("<center>");
            pw.println("<table border=\"8\" style=\"background-color: #E6E6FA;\">");
            for (int i = 0; i < identidad.length; i++) {
                pw.println("<tr>");
                for (int j = 0; j < identidad[i].length; j++) {
                    pw.print("<td>" + identidad[i][j] + "</td>");
                }
                pw.println("</tr>");
            }
            pw.println("</table>");
            pw.println("</center>");
            
            pw.println("</body>");
            pw.println("</html>");
            
            
            fichero.close();
        }catch(Exception e){
            System.out.println("error");
        }
    }
    
}