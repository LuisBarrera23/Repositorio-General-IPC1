package proyecto.pkg1;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Admin extends JFrame implements ActionListener{
    
    JTabbedPane pestañas;
    AdminP panel1;
    AdminC panel2;
    AdminA panel3;
    JButton b6,b7,b8,b9,b10;//para panel 2

    public Admin() {
        panel1=new AdminP();
        panel2=new AdminC();
        panel3=new AdminA();

        
        //configuracion de las pestañas
        pestañas=new JTabbedPane();
        pestañas.setBounds(10,10,1150,720);
        pestañas.addTab("Profesores", panel1);
        pestañas.addTab("Cursos", panel2);
        pestañas.addTab("Alumnos", panel3);
        pestañas.setVisible(true);
        this.add(pestañas);

        
        
        //configuracion general del login
        this.setTitle("Modulo de Administración");              //titulo del login 
        this.setBounds(350, 100, 1200, 800);                    //posicion y tamaño del login
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //para finalizar el programa cuando se cierra la pestaña
        this.setResizable(false);                               //para que no se pueda redimensionar la ventana
        this.setLayout(null);                                   //para desactivar la posicion automatica de los objetos dentro de la ventana
        this.setVisible(true);                                  //para hacerlo visible
    }
    
    

    @Override
    public void actionPerformed(ActionEvent ae) {
            
    }
    
}
