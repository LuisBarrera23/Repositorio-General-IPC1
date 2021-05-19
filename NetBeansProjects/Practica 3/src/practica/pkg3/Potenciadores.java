package practica.pkg3;

public class Potenciadores extends Thread {
    int[] potenciadores=Practica3.configuracion.getPotenciadores();
    
    Partida p;
    
    int vel=Practica3.configuracion.getVelocidad4();

    public Potenciadores(Partida p) {
        this.p = p;
    }
    
    public void run(){
        try{
            while(true){
                int pos=(int) (Math.random()*potenciadores.length+0);
                int potenciador=potenciadores[pos];
                
                if(potenciador==0){
                   Potenciador2 p2=new Potenciador2(p);
                   p2.start();
                }
                if(potenciador==1){
                    Potenciador1 p1=new Potenciador1(p);
                    p1.start();
                }
                if(potenciador==2){
                    Potenciador3 p3=new Potenciador3(p);
                    p3.start();
                }
                if(potenciador==3){
                    Potenciador4 p4=new Potenciador4(p);
                    p4.start();
                }
                if(potenciador==4){
                    Potenciador5 p5=new Potenciador5(p);
                    p5.start();
                }
                if(potenciador==5){
                    Potenciador6 p6=new Potenciador6(p);
                    p6.start();
                }
                
                
                sleep(3000/vel);
            }
            
        }catch(Exception e){
            
        }
    }

    
}
