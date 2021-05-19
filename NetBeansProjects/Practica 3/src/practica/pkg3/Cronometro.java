package practica.pkg3;

public class Cronometro extends Thread{
int segundos;
boolean fin=false;
Partida vtn;

    public Cronometro(Partida vtn) {
        this.vtn=vtn;
        this.segundos=Practica3.configuracion.getTiempo();
    }
    
@Override
    public void run(){
        
        try{
            
            while (true) {
                vtn.l1.setText(Integer.toString(segundos));
                if (segundos <= 0) {
                    vtn.tiempoterminado();
                }
                segundos--;
                sleep(1000);
            }

        }catch(Exception e){
            
        }
        
    }






    
}
