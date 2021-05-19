package proyecto.pkg1;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Informacionalumno extends JFrame implements ActionListener{
    JButton b1, b2;

    JLabel l1, l2, l3, l4, l5, l6, l7,imagen;

    JTextField t1, t2, t3, t4, t5,t6;
    
    Administrarcurso ventana;
    
    Alumno alum;
    
    Curso curso;
    
    public Informacionalumno(Administrarcurso ventana,Alumno alum,Curso curso) {
        this.ventana=ventana;
        this.alum=alum;
        this.curso=curso;
        
        b1 = new JButton("Eliminar estudiante del curso");
        b1.setBounds(50, 600, 400, 40);
        b1.setVisible(true);
        b1.addActionListener(this);
        this.add(b1);

        b2 = new JButton("Regresar");
        b2.setBounds(50, 660, 400, 40);
        b2.setVisible(true);
        b2.addActionListener(this);
        this.add(b2);
        
        
        //agregando etiquetes de texto
        l1=new JLabel("Ver mas informacion del Alumno");
        l1.setBounds(10,20,300,25);
        l1.setVisible(true);
        l1.setFont(new java.awt.Font("Tahoma", 1, 18));
        this.add(l1);
        
        l2=new JLabel("Código");
        l2.setBounds(10,360,70,15);
        l2.setVisible(true);
        this.add(l2);
        
        l3=new JLabel("Nombre");
        l3.setBounds(10,400,70,15);
        l3.setVisible(true);
        this.add(l3);
        
        l4=new JLabel("Apellido");
        l4.setBounds(10,440,70,15);
        l4.setVisible(true);
        this.add(l4);
        
        l5=new JLabel("Correo");
        l5.setBounds(10,480,70,15);
        l5.setVisible(true);
        this.add(l5);
        
        l6=new JLabel("Contraseña");
        l6.setBounds(10,520,70,15);
        l6.setVisible(true);
        this.add(l6);
        
        l7=new JLabel("Genero");
        l7.setBounds(10,560,70,15);
        l7.setVisible(true);
        this.add(l7);
        
        
        //configuracion de los Textbox
        t1=new JTextField(String.valueOf(alum.getCodigo()));
        t1.setEditable(false);
        t1.setBounds(100,357,300,20);
        t1.setVisible(true);
        this.add(t1);
        
        t2=new JTextField(alum.getNombre());
        t2.setEditable(false);
        t2.setBounds(100,397,300,20);
        t2.setVisible(true);
        this.add(t2);
        
        t3=new JTextField(alum.getApellido());
        t3.setEditable(false);
        t3.setBounds(100,437,300,20);
        t3.setVisible(true);
        this.add(t3);
        
        t4=new JTextField(alum.getCorreo());
        t4.setEditable(false);
        t4.setBounds(100,477,300,20);
        t4.setVisible(true);
        this.add(t4);
        
        t5=new JTextField(alum.getContraseña());
        t5.setEditable(false);
        t5.setBounds(100,517,300,20);
        t5.setVisible(true);
        this.add(t5);
        
        String genero;
        if(alum.getGenero().equals("m")){
            genero="Masculino";
        }else{
            genero="Femenino";
        }
        t6=new JTextField(genero);
        t6.setEditable(false);
        t6.setBounds(100,557,100,20);
        t6.setVisible(true);
        this.add(t6);
        
        imagen=new JLabel();
        ImageIcon img=new ImageIcon(alum.getImagen());
        imagen.setBounds(100,75,300,200);
        Image nuevo = img.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_SMOOTH);
        ImageIcon renderizada = new ImageIcon(nuevo);
        imagen.setIcon(renderizada);
        imagen.setVisible(true);
        this.add(imagen);
        
        //configuracion de la ventana
        this.setTitle("Actualizar datos de "+alum.getNombre()+" "+alum.getApellido());                //titulo del login 
        this.setBounds(500,200,500,800);                        //posicion y tamaño del login
        this.getContentPane().setBackground(Color.orange);      //color para un JFrame
        this.setResizable(false);                               //para que no se pueda redimensionar la ventana
        this.setLayout(null);                                   //para desactivar la posicion automatica de los objetos dentro de la ventana
        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) { //accion de eliminar estudiante
            curso.eliminaralumno(alum.getCodigo());
            ventana.actualizar();
            this.dispose();
        }
        if (ae.getSource() == b2) { //accion de regresar a la ventana anterior
            this.dispose();
            ventana.actualizar();
        }
    }

}
