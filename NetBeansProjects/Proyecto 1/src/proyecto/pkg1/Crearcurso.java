package proyecto.pkg1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Crearcurso extends JFrame implements ActionListener{
    JButton b1,b2;
    
    JLabel l1,l2,l3,l4,l5;
    
    JTextField t1,t2,t3;
    
    JComboBox<String> c1;

    public Crearcurso() {
        b1=new JButton("Agregar");
        b1.setBounds(50,220,400,40);
        b1.setVisible(true);
        b1.addActionListener(this);
        this.add(b1);
        
        b2=new JButton("Regresar");
        b2.setBounds(50,280,400,40);
        b2.setVisible(true);
        b2.addActionListener(this);
        this.add(b2);
        
        
        //agregando etiquetes de texto
        l1=new JLabel("Agregar Nuevo Curso");
        l1.setBounds(10,20,300,25);
        l1.setVisible(true);
        l1.setFont(new java.awt.Font("Tahoma", 1, 18));
        this.add(l1);
        
        l2=new JLabel("Código");
        l2.setBounds(10,60,70,15);
        l2.setVisible(true);
        this.add(l2);
        
        l3=new JLabel("Nombre");
        l3.setBounds(10,100,70,15);
        l3.setVisible(true);
        this.add(l3);
        
        l4=new JLabel("Créditos");
        l4.setBounds(10,140,70,15);
        l4.setVisible(true);
        this.add(l4);
        
        l5=new JLabel("Profesor");
        l5.setBounds(10,180,70,15);
        l5.setVisible(true);
        this.add(l5);
        
        
        //configuracion de los Textbox
        t1=new JTextField();
        t1.setBounds(100,57,300,20);
        t1.setVisible(true);
        this.add(t1);
        
        t2=new JTextField();
        t2.setBounds(100,97,300,20);
        t2.setVisible(true);
        this.add(t2);
        
        t3=new JTextField();
        t3.setBounds(100,137,300,20);
        t3.setVisible(true);
        this.add(t3);
        
        String[] list=new String[Proyecto1.cProfesor];
        for (int i = 0; i < Proyecto1.cProfesor; i++) {
            list[i]=Proyecto1.profesor[i].getNombre()+" "+Proyecto1.profesor[i].getApellido();
            System.out.println(Proyecto1.profesor[i].getNombre()+" "+Proyecto1.profesor[i].getApellido());
        }
        
        c1=new JComboBox(list);
        c1.setBounds(100,177,300,20);
        c1.setVisible(true);
        this.add(c1);
        
        //configuracion de la ventana
        this.setTitle("Agregar nuevo Curso");                //titulo del login 
        this.setBounds(500,200,500,380);                        //posicion y tamaño del login
        this.getContentPane().setBackground(Color.orange);      //color para un JFrame
        this.setResizable(false);                               //para que no se pueda redimensionar la ventana
        this.setLayout(null);                                   //para desactivar la posicion automatica de los objetos dentro de la ventana
        this.setVisible(true);                                  //para hacerlo visible
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            int codigo = Integer.parseInt(t1.getText());
            String nombre = t2.getText();
            String creditos = t3.getText();
            int profesor = Proyecto1.profesor[c1.getSelectedIndex()].getCodigo();
            Curso nuevo = new Curso(codigo, nombre, creditos);
            Proyecto1.Agregarcurso(nuevo, profesor);
            Proyecto1.guardar();
            this.dispose();
        }
        if(ae.getSource()==b2){
            this.dispose();
        }
    }
    
    
 
}
