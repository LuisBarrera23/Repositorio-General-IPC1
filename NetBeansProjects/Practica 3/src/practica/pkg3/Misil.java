package practica.pkg3;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.*;

public class Misil extends Thread{
JLabel imagen;

ImageIcon explosion;

Rectangle rct;

Partida p;

int vel=Practica3.configuracion.getVelocidad2();

    public Misil(Rectangle rct, Partida p) {
        this.rct = rct;
        this.p = p;
        
        imagen=new JLabel();
        imagen.setBounds(rct.x+62,rct.y+40,50,25);
        imagen.setVisible(true);
        
        ImageIcon misil=new ImageIcon("src/imagenes/Misil.png");
        Image nuevo=misil.getImage().getScaledInstance(imagen.getWidth(), imagen.getHeight(), Image.SCALE_DEFAULT);
        ImageIcon render=new ImageIcon(nuevo);
        imagen.setIcon(render);
        
        
        ImageIcon imagen2=new ImageIcon("src/imagenes/explosion2.png");
        Image nuevo2=imagen2.getImage().getScaledInstance(65, 65, Image.SCALE_DEFAULT);
        explosion=new ImageIcon(nuevo2);
        
        p.jPanel2.add(imagen);
        p.repaint();
    }
    
    public void run(){
        try{
            while(true){
                if(p.pausa==false){
                    imagen.setLocation(imagen.getLocation().x+25, imagen.getLocation().y);
                }
                sleep(50/vel);
                
                
                if((imagen.getLocation().x)>=p.jPanel2.getWidth()-80){
                    p.jPanel2.remove(imagen);
                    p.repaint();
                    System.out.println("choque");
                    this.stop();
                }
                
                for (int i = 0; i < p.enemigo1.length; i++) {
                    for (int j = 0; j < p.enemigo1[i].length; j++) {
                        if(imagen.getBounds().intersects(p.enemigo1[i][j].getBounds())){
                            System.out.println("enemigo 1 impactado");
                            p.jPanel2.remove(imagen);

                            p.n1[i][j].setVida(p.n1[i][j].getVida() - 1);

                            if (p.n1[i][j].getVida() == 0) {
                                p.enemigo1[i][j].setIcon(explosion);
                                sleep(500);
                                p.jPanel2.remove(p.enemigo1[i][j]);
                                p.enemigo1[i][j].setBounds(0, 0, 0, 0);
                                p.n1[i][j].setEstado(false);
                                p.punteo+=p.n1[i][j].getPuntos();
                                p.enemigos1--;
                                if (p.enemigos1 == 0) {
                                    p.verificar();
                                    if(!Practica3.configuracion.isModificada()){
                                        p.mov.vel = 2;
                                    }
                                }
                                p.actualizar();
                            }
                            p.actualizar();
                            p.jPanel2.repaint();
                            this.stop();
                        }
                    }
                }
                
                for (int i = 0; i < p.enemigo2.length; i++) {
                    for (int j = 0; j < p.enemigo2[i].length; j++) {
                        if(imagen.getBounds().intersects(p.enemigo2[i][j].getBounds())){
                            System.out.println("enemigo 2 impactado");
                            p.jPanel2.remove(imagen);

                            p.n2[i][j].setVida(p.n2[i][j].getVida() - 1);

                            if (p.n2[i][j].getVida() == 0) {
                                p.enemigo2[i][j].setIcon(explosion);
                                sleep(500);
                                p.jPanel2.remove(p.enemigo2[i][j]);
                                p.enemigo2[i][j].setBounds(0, 0, 0, 0);
                                p.n2[i][j].setEstado(false);
                                p.punteo+=p.n2[i][j].getPuntos();
                                p.enemigos2--;
                                if (p.enemigos2 == 0) {
                                    p.verificar();
                                    if(!Practica3.configuracion.isModificada()){
                                        p.mov.vel = 3;
                                    }
                                }
                                p.actualizar();
                            }
                            p.actualizar();
                            p.jPanel2.repaint();
                            this.stop();
                        }
                    }
                }
                
                for (int i = 0; i < p.enemigo3.length; i++) {
                    for (int j = 0; j < p.enemigo3[i].length; j++) {
                        if(imagen.getBounds().intersects(p.enemigo3[i][j].getBounds())){
                            System.out.println("enemigo 3 impactado");
                            p.jPanel2.remove(imagen);

                            p.n3[i][j].setVida(p.n3[i][j].getVida() - 1);

                            if (p.n3[i][j].getVida() == 0) {
                                p.enemigo3[i][j].setIcon(explosion);
                                sleep(500);
                                p.jPanel2.remove(p.enemigo3[i][j]);
                                p.enemigo3[i][j].setBounds(0, 0, 0, 0);
                                p.n3[i][j].setEstado(false);
                                p.punteo+=p.n3[i][j].getPuntos();
                                p.enemigos3--;
                                p.actualizar();
                                if(p.enemigos3==0){
                                    p.verificar();
                                }
                            }
                            p.actualizar();
                            p.jPanel2.repaint();
                            this.stop();
                        }
                    }
                }
                p.repaint();
                
                
                
            }
            
        }catch(Exception e){
            
        }
        
        
    }



    
}
