package proyecto.pkg1;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// LIBRERIAS PARA INTERPRETAR UN JSON
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

//librerias para la lectura de archivos JSON O CSV
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

public class AdminC extends JPanel implements ActionListener {

    JButton b7, b8, b9, b10, b11, b12;//para panel 2

    JLabel l1;

    JTextField t1;

    JTable tabla2;  //para la tabla

    JScrollPane scroll2; //para el scroll de la tabla
    
    ChartPanel panel3;

    public AdminC() {

        b7 = new JButton("Carga Masiva");
        b7.setBounds(900, 50, 200, 50);
        b7.setVisible(true);
        b7.addActionListener(this);
        this.add(b7);

        b8 = new JButton("Eliminar");
        b8.setBounds(900, 150, 200, 50);
        b8.setVisible(true);
        b8.addActionListener(this);
        this.add(b8);

        b9 = new JButton("Crear");
        b9.setBounds(650, 50, 200, 50);
        b9.setVisible(true);
        b9.addActionListener(this);
        this.add(b9);

        b10 = new JButton("Actualizar");
        b10.setBounds(650, 150, 200, 50);
        b10.setVisible(true);
        b10.addActionListener(this);
        this.add(b10);

        b11 = new JButton("Exportar Listado a PDF");
        b11.setBounds(650, 250, 450, 50);
        b11.setVisible(true);
        b11.addActionListener(this);
        this.add(b11);

        b12 = new JButton("Actualizar listado");
        b12.setBounds(130, 28, 140, 20);
        b12.setVisible(true);
        b12.addActionListener(this);
        this.add(b12);

        l1 = new JLabel("Listado Oficial");
        l1.setBounds(30, 30, 100, 15);
        l1.setVisible(true);
        this.add(l1);


        cargartabla();
        graficabarras();
        
        this.setBackground(Color.orange);
        //this.setBounds(20,20,1150,720);   dimensiones de referencia de la pestaña admin
        this.setLayout(null);
        this.setVisible(true);

    }

    public void cargartabla() {

        //para realizar la tabla 
        Object[][] datos = Proyecto1.datoscursos();
        String[] columnas = {"Codigo", "Nombre", "Creditos","Alumnos", "Profesor"};
        tabla2 = new JTable(datos, columnas);
        scroll2 = new JScrollPane(tabla2);
        scroll2.setBounds(30, 50, 600, 600);
        scroll2.setVisible(true);
        this.add(scroll2);
    }

    public void actualizartabla() {
        this.remove(scroll2);
        Object[][] datos = Proyecto1.datoscursos();
        String[] columnas = {"Codigo", "Nombre", "Creditos", "Alumnos", "Profesor"};
        tabla2 = new JTable(datos, columnas);
        scroll2 = new JScrollPane(tabla2);
        scroll2.setBounds(30, 50, 600, 600);
        scroll2.setVisible(true);
        this.add(scroll2);
    }

    public void graficabarras() {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for (int i = 0; i < Proyecto1.cCurso; i++) {
            if (i < 3) {
                datos.setValue(Proyecto1.curso[i].getcAlumnos(),Integer.toString(Proyecto1.curso[i].getCodigo())+" "+Proyecto1.curso[i].getNombre(), Integer.toString(Proyecto1.curso[i].getCodigo())+" "+Proyecto1.curso[i].getNombre());
            }
        }
        JFreeChart grafica = ChartFactory.createBarChart3D("Top Cursos con más Estudiantes", "Cursos", "Alumnos", datos, PlotOrientation.VERTICAL, true, false, false);
        panel3 = new ChartPanel(grafica);
        panel3.setBounds(650, 350, 450, 300);
        this.add(panel3);

    }

    public void actualizargrafica() {
        this.remove(panel3);
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for (int i = 0; i < Proyecto1.cCurso; i++) {
            if (i < 3) {
                datos.setValue(Proyecto1.curso[i].getcAlumnos(),Integer.toString(Proyecto1.curso[i].getCodigo())+" "+Proyecto1.curso[i].getNombre(), Integer.toString(Proyecto1.curso[i].getCodigo())+" "+Proyecto1.curso[i].getNombre());
            }
        }
        JFreeChart grafica = ChartFactory.createBarChart3D("Top Cursos con más Estudiantes", "Cursos", "Alumnos", datos, PlotOrientation.VERTICAL, true, false, false);
        panel3 = new ChartPanel(grafica);
        panel3.setBounds(650, 350, 450, 300);
        this.add(panel3);
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
            System.out.println("se ingresaron " + arreglo.size() + " Cursos");
            for (int i = 0; i < arreglo.size(); i++) {
                JsonObject objeto = arreglo.get(i).getAsJsonObject();
                int codigo = objeto.get("codigo").getAsInt();
                String nombre = objeto.get("nombre").getAsString();
                String creditos = objeto.get("creditos").getAsString();
                int profesor=objeto.get("profesor").getAsInt();
                Curso nuevo=new Curso(codigo,nombre,creditos);
                Proyecto1.Agregarcurso(nuevo, profesor);

            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void actualizar(){
        actualizartabla();
        actualizargrafica();
        Proyecto1.ordenarcursos();
        Proyecto1.guardar();
        this.repaint();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b7) {
            System.out.println("preciono el boton de carga masiva de Cursos");
            leerJSON();
            Proyecto1.ordenarcursos();
            actualizar();
        }
        if (ae.getSource() == b8) {
            System.out.println("preciono el boton de eliminar Cursos");
            if(tabla2.getSelectedRow()!=-1){
                Proyecto1.eliminarcurso(tabla2.getSelectedRow());
                actualizar();
            }
        }
        if (ae.getSource() == b9) {
            System.out.println("preciono el boton de crear Cursos");
            Crearcurso nuevo=new Crearcurso();
        }
        if (ae.getSource() == b10) {
            System.out.println("preciono el boton de Actualizar Cursos");
            if(tabla2.getSelectedRow()!=-1){
                Actualizarcurso actualizacion=new Actualizarcurso(Proyecto1.curso[tabla2.getSelectedRow()],tabla2.getSelectedRow());
            }
            
        }
        if (ae.getSource() == b11) {
            System.out.println("preciono el boton de Exportar Listado a PDF de Cursos");
            Proyecto1.pdfcursos();
        }
        if(ae.getSource()==b12){
            System.out.println("actualizar cursos");
            actualizar();
        }
    }

}
