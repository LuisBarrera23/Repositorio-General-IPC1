package practica.pkg3;

public class Movimiento extends Thread {
    
Partida p;

boolean arriba=false;
boolean abajo=false;

int vel=Practica3.configuracion.getVelocidad3();


    public Movimiento(Partida p) {
        this.p = p;
    }

    public void run() {
        try {
            while (true) {
                p.actualizar();
                sleep(1000/vel);
                if(arriba==false&&abajo==false){
                  bajar();
                }
                if(arriba==true&&abajo==false){
                    bajar();
                }
                if(arriba==false&&abajo==true){
                    subir();
                }
                verificarbordes();
                p.repaint();
            }

        } catch (Exception e) {

        }

    }
    
    public void verificarbordes() {
        for (int i = 0; i < p.enemigo1.length; i++) {
            for (int j = 0; j < p.enemigo1[i].length; j++) {
                if((p.enemigo1[i][j].getLocation().y+65)>=713&&p.n1[i][j].isEstado()){
                    System.out.println("topo");
                    arriba=false;
                    abajo=true;
                    subir();
                    avanzar();
                    return;
                }
                if((p.enemigo1[i][j].getLocation().y)<=0&&p.n1[i][j].isEstado()){
                    System.out.println("topo");
                    arriba=true;
                    abajo=false;
                    bajar();
                    avanzar();
                    return;
                }
                if(p.enemigo1[i][j].getBounds().intersects(p.lnave.getBounds())){
                    p.naveimpactada();
                }
                if(p.enemigo1[i][j].getLocation().x<=0&&p.n1[i][j].isEstado()){
                    p.naveimpactada();
                }
            }
        }
        
        for (int i = 0; i < p.enemigo2.length; i++) {
            for (int j = 0; j < p.enemigo2[i].length; j++) {
                if((p.enemigo2[i][j].getLocation().y+65)>=713&&p.n2[i][j].isEstado()){
                    System.out.println("topo");
                    arriba=false;
                    abajo=true;
                    subir();
                    avanzar();
                    return;
                }
                if((p.enemigo2[i][j].getLocation().y)<=0&&p.n2[i][j].isEstado()){
                    System.out.println("topo");
                    arriba=true;
                    abajo=false;
                    bajar();
                    avanzar();
                    return;
                }
                if(p.enemigo2[i][j].getBounds().intersects(p.lnave.getBounds())){
                    p.naveimpactada();
                }
                if(p.enemigo2[i][j].getLocation().x<=0&&p.n2[i][j].isEstado()){
                    p.naveimpactada();
                }
            }
        }
        
        for (int i = 0; i < p.enemigo3.length; i++) {
            for (int j = 0; j < p.enemigo3[i].length; j++) {
                if((p.enemigo3[i][j].getLocation().y+65)>=713&&p.n3[i][j].isEstado()){
                    System.out.println("topo");
                    arriba=false;
                    abajo=true;
                    subir();
                    avanzar();
                    return;
                }
                if((p.enemigo3[i][j].getLocation().y)<=0&&p.n3[i][j].isEstado()){
                    System.out.println("topo");
                    arriba=true;
                    abajo=false;
                    bajar();
                    avanzar();
                    return;
                }
                if(p.enemigo3[i][j].getBounds().intersects(p.lnave.getBounds())){
                    p.naveimpactada();
                }
                if(p.enemigo3[i][j].getLocation().x<=0&&p.n3[i][j].isEstado()){
                    p.naveimpactada();
                }
            }
        }

    }

    public void bajar() {
        for (int i = 0; i < p.enemigo1.length; i++) {
            for (int j = 0; j < p.enemigo1[i].length; j++) {
                p.enemigo1[i][j].setLocation(p.enemigo1[i][j].getLocation().x, p.enemigo1[i][j].getLocation().y + 30);
            }

        }
        
        for (int i = 0; i < p.enemigo2.length; i++) {
            for (int j = 0; j < p.enemigo2[i].length; j++) {
                p.enemigo2[i][j].setLocation(p.enemigo2[i][j].getLocation().x, p.enemigo2[i][j].getLocation().y + 30);
            }

        }
        
        for (int i = 0; i < p.enemigo3.length; i++) {
            for (int j = 0; j < p.enemigo3[i].length; j++) {
                p.enemigo3[i][j].setLocation(p.enemigo3[i][j].getLocation().x, p.enemigo3[i][j].getLocation().y + 30);
            }

        }
    }

    public void subir() {
        for (int i = 0; i < p.enemigo1.length; i++) {
            for (int j = 0; j < p.enemigo1[i].length; j++) {
                p.enemigo1[i][j].setLocation(p.enemigo1[i][j].getLocation().x, p.enemigo1[i][j].getLocation().y - 30);
            }

        }
        
        for (int i = 0; i < p.enemigo2.length; i++) {
            for (int j = 0; j < p.enemigo2[i].length; j++) {
                p.enemigo2[i][j].setLocation(p.enemigo2[i][j].getLocation().x, p.enemigo2[i][j].getLocation().y - 30);
            }

        }
        
        for (int i = 0; i < p.enemigo3.length; i++) {
            for (int j = 0; j < p.enemigo3[i].length; j++) {
                p.enemigo3[i][j].setLocation(p.enemigo3[i][j].getLocation().x, p.enemigo3[i][j].getLocation().y - 30);
            }

        }
    }
    
    public void avanzar(){
        for (int i = 0; i < p.enemigo1.length; i++) {
            for (int j = 0; j < p.enemigo1[i].length; j++) {
                p.enemigo1[i][j].setLocation(p.enemigo1[i][j].getLocation().x-70, p.enemigo1[i][j].getLocation().y);
            }

        }
        
        for (int i = 0; i < p.enemigo2.length; i++) {
            for (int j = 0; j < p.enemigo2[i].length; j++) {
                p.enemigo2[i][j].setLocation(p.enemigo2[i][j].getLocation().x-70, p.enemigo2[i][j].getLocation().y);
            }

        }
        
        for (int i = 0; i < p.enemigo3.length; i++) {
            for (int j = 0; j < p.enemigo3[i].length; j++) {
                p.enemigo3[i][j].setLocation(p.enemigo3[i][j].getLocation().x-70, p.enemigo3[i][j].getLocation().y);
            }

        }
    }

}