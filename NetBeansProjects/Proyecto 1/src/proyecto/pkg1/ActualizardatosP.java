package proyecto.pkg1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class ActualizardatosP extends JFrame implements ActionListener {
    JButton b1, b2;

    JLabel l1, l2, l3, l4, l5, l6, l7;

    JTextField t1, t2, t3, t4, t5;

    JComboBox<String> c1;
    
    private int pos;
    panelprofesor p;

    public ActualizardatosP(panelprofesor p,Profesor prof,int pos) {
        this.p=p;
        this.pos=pos;
        b1 = new JButton("Actualizar");
        b1.setBounds(50, 300, 400, 40);
        b1.setVisible(true);
        b1.addActionListener(this);
        this.add(b1);

        b2 = new JButton("Regresar");
        b2.setBounds(50, 360, 400, 40);
        b2.setVisible(true);
        b2.addActionListener(this);
        this.add(b2);
        
        
        //agregando etiquetes de texto
        l1=new JLabel("Actualizacion de datos");
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
        
        l4=new JLabel("Apellido");
        l4.setBounds(10,140,70,15);
        l4.setVisible(true);
        this.add(l4);
        
        l5=new JLabel("Correo");
        l5.setBounds(10,180,70,15);
        l5.setVisible(true);
        this.add(l5);
        
        l6=new JLabel("Contraseña");
        l6.setBounds(10,220,70,15);
        l6.setVisible(true);
        this.add(l6);
        
        l7=new JLabel("Genero");
        l7.setBounds(10,260,70,15);
        l7.setVisible(true);
        this.add(l7);
        
        
        //configuracion de los Textbox
        t1=new JTextField(Integer.toString(prof.getCodigo()));
        t1.setEditable(false);
        t1.setBounds(100,57,300,20);
        t1.setVisible(true);
        this.add(t1);
        
        t2=new JTextField(prof.getNombre());
        t2.setBounds(100,97,300,20);
        t2.setVisible(true);
        this.add(t2);
        
        t3=new JTextField(prof.getApellido());
        t3.setBounds(100,137,300,20);
        t3.setVisible(true);
        this.add(t3);
        
        t4=new JTextField(prof.getCorreo());
        t4.setBounds(100,177,300,20);
        t4.setVisible(true);
        this.add(t4);
        
        t5=new JTextField(prof.getContraseña());
        t5.setBounds(100,217,300,20);
        t5.setVisible(true);
        this.add(t5);
        
        String[] list={"m","f"};
        c1=new JComboBox(list);
        c1.setBounds(100,257,100,20);
        c1.setVisible(true);
        this.add(c1);
        
        //configuracion de la ventana
        this.setTitle("Actualizar datos de "+prof.getNombre()+" "+prof.getApellido());                //titulo del login 
        this.setBounds(500,200,500,500);                        //posicion y tamaño del login
        this.getContentPane().setBackground(Color.orange);      //color para un JFrame
        this.setResizable(false);                               //para que no se pueda redimensionar la ventana
        this.setLayout(null);                                   //para desactivar la posicion automatica de los objetos dentro de la ventana
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
    if(ae.getSource()==b1){
            int codigo=Integer.parseInt(t1.getText());
            String nombre=t2.getText();
            String apellido=t3.getText();
            String correo=t4.getText();
            String contraseña=t5.getText();
            String genero=c1.getSelectedItem().toString();
            
            Proyecto1.profesor[pos].setNombre(nombre);
            Proyecto1.profesor[pos].setApellido(apellido);
            Proyecto1.profesor[pos].setCorreo(correo);
            Proyecto1.profesor[pos].setContraseña(contraseña);
            Proyecto1.profesor[pos].setGenero(genero);
            
            Proyecto1.guardar();
            p.actualizar();
            this.dispose();
        }
        if(ae.getSource()==b2){
            this.dispose();
        }
    }
    
    
}
