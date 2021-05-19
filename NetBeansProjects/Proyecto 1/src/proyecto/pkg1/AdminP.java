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

//Librerias para la graficacion pie
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class AdminP extends JPanel implements ActionListener {

    JButton b1, b2, b3, b4, b5,b6;//para panel 1

    JLabel l1;

    JTextField  t2;
    
    JTable tabla;  //para la tabla
    
    JScrollPane scroll; //para el scroll de la tabla
    
    ChartPanel panel;//para la grafica de pastel

    public AdminP() {


        b1 = new JButton("Carga Masiva");
        b1.setBounds(900, 50, 200, 50);
        b1.setVisible(true);
        b1.addActionListener(this);
        this.add(b1);

        b2 = new JButton("Eliminar");
        b2.setBounds(900, 150, 200, 50);
        b2.setVisible(true);
        b2.addActionListener(this);
        this.add(b2);

        b3 = new JButton("Crear");
        b3.setBounds(650, 50, 200, 50);
        b3.setVisible(true);
        b3.addActionListener(this);
        this.add(b3);

        b4 = new JButton("Actualizar");
        b4.setBounds(650, 150, 200, 50);
        b4.setVisible(true);
        b4.addActionListener(this);
        this.add(b4);

        b5 = new JButton("Exportar Listado a PDF");
        b5.setBounds(650, 250, 450, 50);
        b5.setVisible(true);
        b5.addActionListener(this);
        this.add(b5);
        
        b6 = new JButton("Actualizar listado");
        b6.setBounds(130, 28, 140, 20);
        b6.setVisible(true);
        b6.addActionListener(this);
        this.add(b6);

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
    
    public void cargartabla(){
        
        //para realizar la tabla 
        Object[][] datos = Proyecto1.datosprofesores();
        String[] columnas = {"Codigo", "Nombre", "Apellido","Correo","Genero"};
        tabla = new JTable(datos, columnas);
        scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 50, 600, 600);
        scroll.setVisible(true);
        this.add(scroll);
    }
    
    public void actualizartabla() {
        this.remove(scroll);
        Object[][] datos = Proyecto1.datosprofesores();
        String[] columnas = {"Codigo", "Nombre", "Apellido","Correo","Genero"};
        tabla = new JTable(datos, columnas);
        scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 50, 600, 600);
        scroll.setVisible(true);
        this.add(scroll);
    }
    
    public void graficapastel() {
        int femenino = 0, masculino = 0;
        for (int i = 0; i < Proyecto1.cProfesor; i++) {
            if (Proyecto1.profesor[i].getGenero().equals("f")) {
                femenino++;
            } else {
                masculino++;
            }
        }
        //System.out.println("masculino: "+masculino+" Femenino: "+femenino);

        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Masculino", masculino);
        data.setValue("Femenino", femenino);
        JFreeChart grafica = ChartFactory.createPieChart("Género de Profesores", data, true, true, false);
        panel = new ChartPanel(grafica);
        panel.setBounds(650, 350, 450, 300);
        this.add(panel);

    }

    public void actualizargrafica() {
        this.remove(panel);
        int femenino = 0, masculino = 0;
        for (int i = 0; i < Proyecto1.cProfesor; i++) {
            if (Proyecto1.profesor[i].getGenero().equals("f")) {
                femenino++;
            } else {
                masculino++;
            }

        }

        DefaultPieDataset data = new DefaultPieDataset();
        data.setValue("Masculino", masculino);
        data.setValue("Femenino", femenino);
        JFreeChart grafica = ChartFactory.createPieChart("Género de Profesores", data, true, true, false);
        panel = new ChartPanel(grafica);
        panel.setBounds(650, 350, 450, 300);
        this.add(panel);
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
            System.out.println("se ingresaron " + arreglo.size() + " profesores");
            for (int i = 0; i < arreglo.size(); i++) {
                JsonObject objeto = arreglo.get(i).getAsJsonObject();
                int codigo = objeto.get("codigo").getAsInt();
                String nombre = objeto.get("nombre").getAsString();
                String apellido = objeto.get("apellido").getAsString();
                String correo = objeto.get("correo").getAsString();
                String genero = objeto.get("genero").getAsString();
                Profesor nuevo = new Profesor(codigo, nombre, apellido, correo, genero);
                Proyecto1.AgregarProfesor(nuevo);
            }

        } catch (Exception e) {

        }
    }
    
    public void Actalizar(){
        actualizartabla();
        actualizargrafica();
        this.repaint();
        Proyecto1.guardar();
    }
    
   

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            System.out.println("preciono el boton de carga masiva de profesores");
            leerJSON();
            Actalizar();
            
        }
        if (ae.getSource() == b2) {
            System.out.println("preciono el boton de eliminar profesores");
            if ((tabla.getSelectedRow() != -1) && (Proyecto1.cProfesor > 0)) {
                Proyecto1.eliminarprofesor(tabla.getSelectedRow());
                Actalizar();
            }

        }
        if (ae.getSource() == b3) {
            System.out.println("preciono el boton de crear profesores");
            Crearprofesor crear=new Crearprofesor();
        }
        if (ae.getSource() == b4) {
            System.out.println("preciono el boton de Actualizar profesores");
            System.out.println("se selecciono la fila "+tabla.getSelectedRow());
            if(tabla.getSelectedRow()!=-1){
                Profesor nuevo=Proyecto1.profesor[tabla.getSelectedRow()];
                Actualizarprofesor actualizar=new Actualizarprofesor(nuevo,tabla.getSelectedRow(),this);
            }
            
        }
        if (ae.getSource() == b5) {
            System.out.println("preciono el boton de Exportar Listado a PDF de Profesores");
            Proyecto1.pdfprofesores();
        }
        if (ae.getSource() == b6) {
            Actalizar();
        }
    }

}
