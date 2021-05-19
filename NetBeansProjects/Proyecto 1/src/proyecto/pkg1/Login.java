package proyecto.pkg1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.*;


public class Login extends JFrame implements ActionListener{
    
    //para los textos en este caso usuario y contraseña
    JLabel l1,l2;
    
    //para los cuadros donde se introducira texto
    JTextField t1;
    
    //para el cuadro donde se escribira la contraseña
    JPasswordField p1;
    
    //para los botones 
    JButton b1;
    
    String usuario,password;
    
    private String admin="admin";
    

    public Login() {
        

        //configuracion de los textos o labels
        l1= new JLabel("Código:");
        l1.setBounds(20, 30, 50, 15);
        l1.setVisible(true);
        this.add(l1);
        
        l2= new JLabel("Contraseña:");
        l2.setBounds(20, 80, 70, 10);
        l2.setVisible(true);
        this.add(l2);
        
        //configuracion de los cuadros de entrada de texto o textbox
        t1=new JTextField();
        t1.setBounds(110, 22, 300, 30);
        t1.setVisible(true);
        this.add(t1);
        
        p1=new JPasswordField();
        p1.setBounds(110, 72, 300, 30);
        p1.setVisible(true);
        this.add(p1);
        
        //configuracion del boton
        
        b1=new JButton("Iniciar Sesión");
        b1.setBounds(150,110,150,30);
        b1.setVisible(true);
        b1.addActionListener(this);
        this.add(b1);
        
        
        //configuracion general del login
        this.setTitle("Modulo de Autenticación");               //titulo del login 
        this.setBounds(500,200,450,200);                        //posicion y tamaño del login
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //para finalizar el programa cuando se cierra la pestaña
        this.setResizable(false);                               //para que no se pueda redimensionar la ventana
        this.setLayout(null);                                   //para desactivar la posicion automatica de los objetos dentro de la ventana
        this.setVisible(true);                                  //para hacerlo visible
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            System.out.println("precionaste inicar sesion");
            usuario = t1.getText();
            password = p1.getText();

            System.out.println("Los datos para iniciar sesion son: ");
            System.out.println("Usuario: " + usuario);
            System.out.println("Password: " + password);
            
            boolean logueado = false;
            if (usuario.equals(admin) && password.equals(admin)) {
                logueado = true;
                Admin admin = new Admin();
                this.dispose();
                System.out.println("se ha logueado el administrador");
            } else {
                for (int i = 0; i < Proyecto1.cProfesor; i++) {
                    if ((Proyecto1.profesor[i].getCodigo() == Integer.parseInt(usuario)) && Proyecto1.profesor[i].getContraseña().equals(password)) {
                        logueado = true;
                        panelprofesor nuevo = new panelprofesor(Proyecto1.profesor[i],i);
                        this.dispose();
                    }
                }
                
                for (int i = 0; i < Proyecto1.cAlumnos; i++) {
                    if((Proyecto1.alumno[i].getCodigo() == Integer.parseInt(usuario)) && Proyecto1.alumno[i].getContraseña().equals(password)){
                        logueado = true;
                        panelalumno nuevo=new panelalumno(Proyecto1.alumno[i],i);
                        this.dispose();
                    }
                }
                

            }
            if (logueado) {
                JOptionPane.showMessageDialog(this, "Se ha iniciado sesión correctamente", "Bienvenido", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "No se ha encontrado ningun usuario con los datos ingresados", "Error", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    
}
