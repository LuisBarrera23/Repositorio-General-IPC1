package proyecto.pkg1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

//librerias para la creacion de la grafica 
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.chart.ChartFactory;

public class Informacioncurso extends JFrame implements ActionListener{
    JLabel l1,l2,l3,l4;
    
    JTable tabla;
    
    JScrollPane scroll;
    
    ChartPanel panel3;
    
    Curso curso;
    
    Alumno alum;
    
    private int codigoalum;

    public Informacioncurso(Curso curso,Alumno alum,int codigoalum) {
        this.curso=curso;
        this.alum=alum;
        this.codigoalum=codigoalum;
        
        //configuracion de los JLabels
        l1=new JLabel(curso.getNombre());
        l1.setBounds(30,20,1000,30);
        l1.setVisible(true);
        l1.setFont(new java.awt.Font("Tahoma", 1, 28));
        this.add(l1);
        
        l2=new JLabel("Actividades");
        l2.setBounds(30,70,200,30);
        l2.setVisible(true);
        l2.setFont(new java.awt.Font("Tahoma", 0, 22));
        this.add(l2);
        
        l3=new JLabel(String.valueOf(curso.acumulado()));
        l3.setBounds(940,300,200,20);
        l3.setVisible(true);
        l3.setFont(new java.awt.Font("Tahoma", 0, 22));
        this.add(l3);
        
        l4=new JLabel(String.valueOf(Math.round(alum.notatotal(curso.getCodigo()))));
        l4.setBounds(1080,300,200,20);
        l4.setVisible(true);
        l4.setFont(new java.awt.Font("Tahoma", 0, 22));
        this.add(l4);
        
        cargartabla();
        graficabarras();
        
        //configuracion general del login
        this.setTitle("Detalles del curso: "+curso.getNombre()+" y notas de "+alum.getNombre()+" "+alum.getApellido());//titulo del login 
        this.setBounds(350, 100, 1200, 800);                    //posicion y tamaño del login
        this.getContentPane().setBackground(Color.orange);
        //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //para finalizar el programa cuando se cierra la pestaña
        this.setResizable(false);                               //para que no se pueda redimensionar la ventana
        this.setLayout(null);                                   //para desactivar la posicion automatica de los objetos dentro de la ventana
        this.setVisible(true);                                  //para hacerlo visible
    }
    
    public void cargartabla(){
        //para realizar la tabla 
        Object[][] datos = Proyecto1.mostraractividades(curso, alum.getCodigo());
        String[] columnas = {"Nombre", "Descripción","Ponderación", "Nota Obtenida"};
        tabla = new JTable(datos, columnas);
        javax.swing.table.TableColumn columna=tabla.getColumnModel().getColumn(1);
        columna.setPreferredWidth(650);
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tabla.getColumnModel().getColumn(0).setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(1).setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(2).setCellRenderer(centrado);
        tabla.getColumnModel().getColumn(3).setCellRenderer(centrado);
        scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 100, 1140, 200);
        scroll.setVisible(true);
        this.add(scroll);
    }
    
    public void graficabarras() {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        Actividades[] actividad=curso.getActividades();
        for (int i = 0; i < curso.getcActividades(); i++) {
            Notas[] nota=actividad[i].getNotas();
            for (int j = 0; j < actividad[i].getcNotas(); j++) {
                if(nota[j].getCodigo()==alum.getCodigo()){
                    datos.setValue(nota[j].getNota(), actividad[i].getNombre(), actividad[i].getNombre());
                }
            }
        }
        
//        for (int i = 0; i < Proyecto1.cCurso; i++) {
//            if (i < 3) {
//                datos.setValue(Proyecto1.curso[i].getcAlumnos(),Integer.toString(Proyecto1.curso[i].getCodigo())+" "+Proyecto1.curso[i].getNombre(), Integer.toString(Proyecto1.curso[i].getCodigo())+" "+Proyecto1.curso[i].getNombre());
//            }
//        }
        JFreeChart grafica = ChartFactory.createBarChart3D("Nota obtenida por actividad", "Actividad", "Punteo obtenido", datos, PlotOrientation.VERTICAL, true, false, false);
        panel3 = new ChartPanel(grafica);
        panel3.setBounds(150, 350, 900, 400);
        this.add(panel3);

    }

    
    
    @Override
    public void actionPerformed(ActionEvent ae) {
        
    }
    
}
