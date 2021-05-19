package proyecto.pkg1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class panelalumno extends JFrame implements ActionListener{
    JButton b1,b2,b3;
    
    JLabel l1,l2,l3;
    
    JScrollPane scroll;
    
    JTable tabla;
    
    Object[][] info;
    
    private int pos;
    
    Alumno alum;

    public panelalumno(Alumno alum, int pos) {
        this.pos=pos;
        this.alum=alum;
        
        b1=new JButton("Actualizar Datos");
        b1.setBounds(1000, 10, 150, 30);
        b1.setVisible(true);
        b1.addActionListener(this);
        this.add(b1);
        
        b2=new JButton("Ver detalles del Curso");
        b2.setBounds(650,250,530,30);
        b2.addActionListener(this);
        b2.setVisible(true);
        this.add(b2);
        
        b3=new JButton("Cerrar sesión");
        b3.setBounds(1000, 500, 150, 30);
        b3.addActionListener(this);
        b3.setVisible(true);
        this.add(b3);
        
        l1=new JLabel("Bienvenido "+alum.getNombre()+" "+alum.getApellido());
        l1.setBounds(10,20,300,25);
        l1.setVisible(true);
        l1.setFont(new java.awt.Font("Tahoma", 1, 18));
        this.add(l1);
        
        l2=new JLabel("Cursos a los que usted asiste:");
        l2.setBounds(30,65,300,25);
        l2.setVisible(true);
        l2.setFont(new java.awt.Font("Tahoma", 1, 18));
        this.add(l2);
        
        l3=new JLabel("Seleccione un curso de la tabla");
        l3.setBounds(823,220,185,15);
        l3.setVisible(true);
        this.add(l3);
        
        cargartabla();
        
        //configuracion general del login
        this.setTitle("Modulo Alumno");                         //titulo del login 
        this.setBounds(350, 100, 1200, 600);                    //posicion y tamaño del login
        this.getContentPane().setBackground(Color.orange);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //para finalizar el programa cuando se cierra la pestaña
        this.setResizable(false);                               //para que no se pueda redimensionar la ventana
        this.setLayout(null);                                   //para desactivar la posicion automatica de los objetos dentro de la ventana
        this.setVisible(true);                                  //para hacerlo visible
    

    }
    
    public void cargartabla(){
        
        //para realizar la tabla 
        Object[][] datos = Proyecto1.cursosasignados(pos);
        String[] columnas = {"Codigo", "Nombre", "Profesor"};
        tabla = new JTable(datos, columnas);
        scroll = new JScrollPane(tabla);
        scroll.setBounds(30, 90, 600, 400);
        scroll.setVisible(true);
        this.add(scroll);
    }
    public void actualizar(){
        l1.setText("Bienvenido "+alum.getNombre()+" "+alum.getApellido());
    }
    @Override    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()==b1) {
           Actualizaralumno nuevo=new Actualizaralumno(Proyecto1.alumno[pos],pos,this); 
        }
        if (ae.getSource() == b2) {
            info = Proyecto1.cursosasignados(pos);
            if (tabla.getSelectedRow() != -1) {
                int codigocurso = (int) info[tabla.getSelectedRow()][0];
                for (int i = 0; i < Proyecto1.cCurso; i++) {
                    if (Proyecto1.curso[i].getCodigo() == codigocurso) {
                        JOptionPane.showMessageDialog(this, "Ha seleccionado el curso: "+Proyecto1.curso[i].getNombre());
                        Informacioncurso nuevo=new Informacioncurso(Proyecto1.curso[i],alum,alum.getCodigo());
                    }
                }
            }else{
                JOptionPane.showMessageDialog(this, "Por favor seleccione un Curso", "Error", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
        }
        if(ae.getSource()==b3){
            Login nuevo=new Login();
            this.dispose();
        }

    }


}
