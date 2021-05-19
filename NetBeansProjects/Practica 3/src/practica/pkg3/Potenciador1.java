package practica.pkg3;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Potenciador1 extends Thread{
    JLabel label;
    
    Partida p;
    
    int vel=Practica3.configuracion.getVelocidad5();

    public Potenciador1(Partida p) {
        this.p=p;
        label=new JLabel();
        int posy=(int) (Math.random()*685+25);
        label.setBounds(1170,posy,40,40);
        ImageIcon imagen=new ImageIcon("src/imagenes/potenciador 1.png");
        Icon icono=new ImageIcon(imagen.getImage().getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT));
        label.setVisible(true);
        label.setIcon(icono);
        p.jPanel2.add(label);
        
        
    }
    
    public void run() {
        try {
            while (true) {
                sleep(50/vel);
                if(p.pausa==false){
                    label.setLocation(label.getLocation().x-5, label.getLocation().y);
                }
                
                
                
                if(p.lnave.getBounds().intersects(label.getBounds())){
                    p.punteo+=10;
                    p.actualizar();
                    p.jPanel2.remove(label);
                    this.stop();
                }
                if(label.getBounds().x<=0){
                    p.jPanel2.remove(label);
                    this.stop();
                }
            }

        } catch (Exception e) {

        }
    }
    
    
    
}
