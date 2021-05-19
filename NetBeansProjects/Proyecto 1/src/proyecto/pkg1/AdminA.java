package proyecto.pkg1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

//librerias para la lectura de archivos JSON
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

// LIBRERIAS PARA INTERPRETAR UN JSON
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Locale;
//librerias para la grafica de pie
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class AdminA extends JPanel implements ActionListener {

    JButton b13, b14, b15;

    JLabel l1;


    JTable tabla3;  //para la tabla

    JScrollPane scroll3; //para el scroll de la tabla
    
    ChartPanel panel2;//para la grafica de pastel

    public AdminA() {

        b13 = new JButton("Carga Masiva");
        b13.setBounds(650, 50, 450, 50);
        b13.setVisible(true);
        b13.addActionListener(this);
        this.add(b13);

        b14 = new JButton("Exportar Listado a PDF");
        b14.setBounds(650, 150, 450, 50);
        b14.setVisible(true);
        b14.addActionListener(this);
        this.add(b14);

        b15 = new JButton("Actualizar listado");
        b15.setBounds(130, 28, 140, 20);
        b15.setVisible(true);
        b15.addActionListener(this);
        this.add(b15);

        l1 = new JLabel("Listado Oficial");
        l1.setBounds(30, 30, 100, 15);
        l1.setVisible(true);
        this.add(l1);

        graficapastel();
        cargartabla();

        this.setBackground(Color.orange);
        //this.setBounds(20,20,1150,720);   dimensiones de referencia de la pestaña admin
        this.setLayout(null);
        this.setVisible(true);

    }

    public void leerJSON() {
        // ATRIBUTOS
        String contenido = "";
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        try {
            JFileChooser fc = new JFileChooser();
            int op = fc.showOpenDialog(this);
            if (op == JFileChooser.APPROVE_OPTION) {
                archivo = fc.getSelectedFile();
            }
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                contenido += linea;
            }
            fr.close();
            //interpretacion del archivo JSON
            JsonParser parser = new JsonParser();
            JsonArray arreglo = parser.parse(contenido).getAsJsonArray();
            System.out.println("se ingresaron " + arreglo.size() + " Alumnos");
            for (int i = 0; i < arreglo.size(); i++) {
                JsonObject objeto = arreglo.get(i).getAsJsonObject();
                int codigo = objeto.get("codigo").getAsInt();
                String nombre = objeto.get("nombre").getAsString();
                String apellido = objeto.get("apellido").getAsString();
                String correo = objeto.get("correo").getAsString();
                String genero = objeto.get("genero").getAsString();
                Alumno nuevo = new Alumno(codigo, nombre, apellido, correo, genero);
                Proyecto1.AgregarAlumno(nuevo);
            }

        } catch (Exception e) {

        }
    }
    
    public void cargartabla(){
        
        //para realizar la tabla 
        Object[][] datos = Proyecto1.datosalumno();
        String[] columnas = {"Codigo", "Nombre", "Apellido","Correo","Genero"};
        tabla3 = new JTable(datos, columnas);
        scroll3 = new JScrollPane(tabla3);
        scroll3.setBounds(30, 50, 600, 600);
        scroll3.setVisible(true);
        this.add(scroll3);
    }
    public void actualizartabla(){
         //para actualizar la tabla 
        this.remove(scroll3);
        Object[][] datos = Proyecto1.datosalumno();
        String[] columnas = {"Codigo", "Nombre", "Apellido","Correo","Genero"};
        tabla3 = new JTable(datos, columnas);
        scroll3 = new JScrollPane(tabla3);
        scroll3.setBounds(30, 50, 600, 600);
        scroll3.setVisible(true);
        this.add(scroll3);
    }
    
    public void graficapastel() {
        int femenino = 0, masculino = 0;
        for (int i = 0; i < Proyecto1.cAlumnos; i++) {
            if (Proyecto1.alumno[i].getGenero().equals("f")) {
                femenino++;
            } else {
                masculino++;
            }
        }
        //System.out.println("masculino: "+masculino+" Femenino: "+femenino);

        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Masculino", masculino);
        data.setValue("Femenino", femenino);
        JFreeChart grafica = ChartFactory.createPieChart("Género de Alumnos", data, true, true, false);
        panel2 = new ChartPanel(grafica);
        panel2.setBounds(650, 350, 450, 300);
        this.add(panel2);

    }
    
    public void actualizargrafica(){
        this.remove(panel2);
       int femenino = 0, masculino = 0;
        for (int i = 0; i < Proyecto1.cAlumnos; i++) {
            if (Proyecto1.alumno[i].getGenero().equals("f")) {
                femenino++;
            } else {
                masculino++;
            }
        }
        //System.out.println("masculino: "+masculino+" Femenino: "+femenino);

        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Masculino", masculino);
        data.setValue("Femenino", femenino);
        JFreeChart grafica = ChartFactory.createPieChart("Género de Alumnos", data, true, true, false);
        panel2 = new ChartPanel(grafica);
        panel2.setBounds(650, 350, 450, 300);
        this.add(panel2); 
    }
    
    public void actualizar(){
        actualizartabla();
        actualizargrafica();
        Proyecto1.guardar();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b13) {
            System.out.println("se preciono el boton");
            leerJSON();
            actualizar();
        }
        
        if(ae.getSource()==b14){
            Proyecto1.pdfalumnos();
        }
        if(ae.getSource()==b15){
            actualizar();
        }
    }

}
