package proyecto.pkg1;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;

//PARA LA LECTURA Y ESCRITOURA DE ARCHIVOS CSV Y JSON
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class Administrarcurso extends JFrame implements ActionListener{

    JTable tabla,tabla2;
    
    JTextField t1,t2,t3;
    
    JLabel l1,l2,l3,l4,l5,l6,l7,l8,l9,l10,l11;
    
    JButton b1,b2,b3,b4,b5,b6,b7;
    
    JScrollPane scroll,scroll2;
    
    Curso curso;
    
    File CSV=null;

    public Administrarcurso(Curso curso) {
        this.curso=curso;
        tabla1();
        tabla2();
        
        //configuracion general de JButton
        
        b1=new JButton("Carga Masiva Alumnos");
        b1.setBounds(30, 500, 600, 50);
        b1.setVisible(true);
        b1.addActionListener(this);
        this.add(b1);
        
        b2=new JButton("Top 5 -Estudiantes con Mejor Rendimiento");
        b2.setBounds(30, 680, 600, 50);
        b2.setVisible(true);
        b2.addActionListener(this);
        this.add(b2);
        
        b3=new JButton("Top 5 -Estudiantes con Peor Rendimiento");
        b3.setBounds(30, 780, 600, 50);
        b3.setVisible(true);
        b3.addActionListener(this);
        this.add(b3);
        
        b4=new JButton("Seleccionar archivo CSV");
        b4.setBounds(965, 730, 400, 30);
        b4.setVisible(true);
        b4.addActionListener(this);
        this.add(b4);
        
        b5=new JButton("Crear Actividad");
        b5.setBounds(765, 780, 600, 50);
        b5.setVisible(true);
        b5.addActionListener(this);
        this.add(b5);
        
        b6=new JButton("Informacion del Alumno seleccionado");
        b6.setBounds(380, 65, 250, 20);
        b6.setVisible(true);
        b6.addActionListener(this);
        this.add(b6);
        
        b7=new JButton("Cerrar sesión");
        b7.setBounds(1320,10, 150, 30);
        b7.addActionListener(this);
        b7.setVisible(true);
        this.add(b7);
        
        //configuracion general de JLabels
        
        l1=new JLabel(curso.getNombre());
        l1.setBounds(30,20,1000,30);
        l1.setVisible(true);
        l1.setFont(new java.awt.Font("Tahoma", 1, 28));
        this.add(l1);
        
        l2=new JLabel("Listado Alumnos");
        l2.setBounds(30,60,150,30);
        l2.setVisible(true);
        l2.setFont(new java.awt.Font("Tahoma", 1, 18));
        this.add(l2);
        
        l3=new JLabel("Actividades");
        l3.setBounds(650,60,150,30);
        l3.setVisible(true);
        l3.setFont(new java.awt.Font("Tahoma", 1, 18));
        this.add(l3);
        
        l4=new JLabel("Acumulado");
        l4.setBounds(1285,40,150,30);
        l4.setVisible(true);
        l4.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(l4);
        
        l5=new JLabel(curso.acumulado()+"/100");
        l5.setBounds(1300,60,150,30);
        l5.setVisible(true);
        l5.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(l5);
        
        l6=new JLabel("Crear Actividad");
        l6.setBounds(995,530,140,30);
        l6.setVisible(true);
        l6.setFont(new java.awt.Font("Tahoma", 1, 18));
        this.add(l6);
        
        l7=new JLabel("Ponderación");
        l7.setBounds(850,680,140,30);
        l7.setVisible(true);
        l7.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(l7);
        
        l8=new JLabel("Nombre");
        l8.setBounds(850,580,140,30);
        l8.setVisible(true);
        l8.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(l8);
        
        l9=new JLabel("Descripción");
        l9.setBounds(850,630,140,30);
        l9.setVisible(true);
        l9.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(l9);
        
        l10=new JLabel("Notas");
        l10.setBounds(850,730,140,30);
        l10.setVisible(true);
        l10.setFont(new java.awt.Font("Tahoma", 0, 18));
        this.add(l10);
        
        l11=new JLabel("Reportes");
        l11.setBounds(30,640,100,30);
        l11.setVisible(true);
        l11.setFont(new java.awt.Font("Tahoma", 1, 18));
        this.add(l11);
        
        //configuracion de los JTextField
        t1=new JTextField();
        t1.setBounds(965, 580, 400, 30);
        t1.setVisible(true);
        this.add(t1);
        
        t2=new JTextField();
        t2.setBounds(965, 630, 400, 30);
        t2.setVisible(true);
        this.add(t2);
        
        t3=new JTextField();
        t3.setBounds(965, 680, 400, 30);
        t3.setVisible(true);
        this.add(t3);
        
        this.setTitle("Administración de Curso");              //titulo  
        this.setBounds(200, 70, 1500, 900);                    //posicion y tamaño
        this.getContentPane().setBackground(Color.orange);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //para finalizar el programa cuando se cierra la pestaña
        this.setResizable(false);                               //para que no se pueda redimensionar la ventana
        this.setLayout(null);                                   //para desactivar la posicion automatica de los objetos dentro de la ventana
        this.setVisible(true);                                  //para hacerlo visible
    
    }
            
    public void tabla1(){
        //para realizar la tabla 
        Object[][] datos =Proyecto1.alumnosasignados(curso);
        String[] columnas = {"Codigo", "Nombre", "Apellido"};
        tabla = new JTable(datos, columnas);
        scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 90, 600, 400);
        scroll.setVisible(true);
        this.add(scroll);
    }
    public void actualizartabla1(){
      //para actualizar la tabla 1
        this.remove(scroll);
        Object[][] datos =Proyecto1.alumnosasignados(curso);
        String[] columnas = {"Codigo", "Nombre", "Apellido"};
        tabla = new JTable(datos, columnas);
        scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 90, 600, 400);
        scroll.setVisible(true);
        this.add(scroll);  
    }
    public void tabla2(){
        //para realizar la tabla 
        Object[][] datos = Proyecto1.actividades(curso);
        String[] columnas = {"Nombre", "Descripción", "Ponderación","Promedio"};
        tabla2 = new JTable(datos, columnas);
        javax.swing.table.TableColumn columna=tabla2.getColumnModel().getColumn(1);
        columna.setPreferredWidth(550);
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tabla2.getColumnModel().getColumn(0).setCellRenderer(centrado);
        tabla2.getColumnModel().getColumn(1).setCellRenderer(centrado);
        tabla2.getColumnModel().getColumn(2).setCellRenderer(centrado);
        tabla2.getColumnModel().getColumn(3).setCellRenderer(centrado);
        scroll2 = new JScrollPane(tabla2);
        scroll2.setBounds(650, 90, 800, 350);
        scroll2.setVisible(true);
        this.add(scroll2);
    }
    
    public void actualizartabla2(){
        this.remove(scroll2);
        //para actualizar la tabla 2
        Object[][] datos = Proyecto1.actividades(curso);
        String[] columnas = {"Nombre", "Descripción", "Ponderación","Promedio"};
        tabla2 = new JTable(datos, columnas);
        javax.swing.table.TableColumn columna=tabla2.getColumnModel().getColumn(1);
        columna.setPreferredWidth(550);
        DefaultTableCellRenderer centrado = new DefaultTableCellRenderer();
        centrado.setHorizontalAlignment(SwingConstants.CENTER);
        tabla2.getColumnModel().getColumn(0).setCellRenderer(centrado);
        tabla2.getColumnModel().getColumn(1).setCellRenderer(centrado);
        tabla2.getColumnModel().getColumn(2).setCellRenderer(centrado);
        tabla2.getColumnModel().getColumn(3).setCellRenderer(centrado);
        scroll2 = new JScrollPane(tabla2);
        scroll2.setBounds(650, 90, 800, 350);
        scroll2.setVisible(true);
        this.add(scroll2);
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
                
                curso.asignaralumno(codigo); 
            }
            actualizar();

        } catch (Exception e) {

        }
    }
    
    public void actualizar(){
        actualizartabla1();
        actualizartabla2();
        
        Proyecto1.guardar();
        Proyecto1.ordenarcursos();
        l5.setText(curso.acumulado()+"/100");
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        if(ae.getSource()==b1){
            leerJSON();
        }
        if (ae.getSource() == b2) {//reporte de los 5 mejores estudiantes
            Proyecto1.pdftop5mejores(curso);
        }
        if(ae.getSource()==b3){//reporte de peores estudiantes
            Proyecto1.pdftop5peores(curso);
        }
        if (ae.getSource() == b4) {//seleccionar archivo csv

            try {
                JFileChooser fc = new JFileChooser();
                int op = fc.showOpenDialog(this);
                if (op == JFileChooser.APPROVE_OPTION) {
                    CSV = fc.getSelectedFile();
                }
            } catch (Exception e) {
            }
        }
        if (ae.getSource() == b5) {//creacion de actividades
            if ((t1.getText().isEmpty() || t2.getText().isEmpty() || t3.getText().isEmpty() || CSV == null)) {
                JOptionPane.showMessageDialog(this, "Por favor rellene todos los campos anteriores");

            } else {
                String nombre = t1.getText();
                String descripcion = t2.getText();
                int ponderacion = Integer.parseInt(t3.getText());
                
                Actividades actividad=new Actividades(nombre,descripcion,ponderacion);
                
                FileReader fr = null;
                BufferedReader br = null;
                try {
                    //lectura normal del archivo
                    fr = new FileReader(CSV);
                    br = new BufferedReader(fr);
                    String linea = br.readLine();
                    while ((linea = br.readLine()) != null) {
                        String[] datos = linea.split(",");
                        int codigo = Integer.parseInt(datos[0]);
                        double nota = Double.parseDouble(datos[1]);
                        Notas nueva=new Notas(codigo,nota,ponderacion);
                        actividad.asignarnota(nueva);
                        

                    }
                    curso.asignaractividad(actividad);
                    for (int i = 0; i < Proyecto1.cAlumnos; i++) {
                    Curso[] listado=Proyecto1.alumno[i].getCursos();
                        for (int j = 0; j < Proyecto1.alumno[i].getcCursos(); j++) {
                            if (listado[j].getCodigo()==curso.getCodigo()) {
                                Proyecto1.alumno[i].asignarcurso(curso);
                            }
                        }
                    }

                } catch (Exception e) {
                }
                actualizar();
            }

        }
        if (ae.getSource() == b6) {
            Alumno[] alumnos=curso.getAlumnos();
            if(tabla.getSelectedRow()!=-1){
                for (int i = 0; i < Proyecto1.cAlumnos; i++) {
                    if(Proyecto1.alumno[i].getCodigo()==alumnos[tabla.getSelectedRow()].getCodigo()){
                        Informacionalumno nuevo=new Informacionalumno(this,Proyecto1.alumno[i],curso);
                    }
                }
            }
        }
        
        if(ae.getSource()==b7){
            Login nuevo=new Login();
            this.dispose();
        }
        
    }

    
}
