package practica.pkg3;

public class Configuracion {

    private int tiempo;
    private int velocidad1;
    private int velocidad2;
    private int velocidad3;
    private int velocidad4;
    private int velocidad5;
    private int[] potenciadores;
    private boolean modificada;

    public Configuracion(int tiempo, int velocidad1, int velocidad2, int velocidad3, int velocidad4,int velocidad5,int[] potenciadores) {
        this.tiempo = tiempo;
        this.velocidad1 = velocidad1;
        this.velocidad2 = velocidad2;
        this.velocidad3 = velocidad3;
        this.velocidad4 = velocidad4;
        this.velocidad5 = velocidad5;
        this.potenciadores = potenciadores;
        this.modificada=false;
    }
    

    public int getTiempo() {
        return tiempo;
    }

    public void setTiempo(int tiempo) {
        this.tiempo = tiempo;
    }

    public int getVelocidad1() {
        return velocidad1;
    }

    public void setVelocidad1(int velocidad1) {
        this.velocidad1 = velocidad1;
    }

    public int getVelocidad2() {
        return velocidad2;
    }

    public void setVelocidad2(int velocidad2) {
        this.velocidad2 = velocidad2;
    }

    public int getVelocidad3() {
        return velocidad3;
    }

    public void setVelocidad3(int velocidad3) {
        this.velocidad3 = velocidad3;
    }

    public int getVelocidad4() {
        return velocidad4;
    }

    public void setVelocidad4(int velocidad4) {
        this.velocidad4 = velocidad4;
    }

    public int[] getPotenciadores() {
        return potenciadores;
    }

    public void setPotenciadores(int[] potenciadores) {
        this.potenciadores = potenciadores;
    }

    public int getVelocidad5() {
        return velocidad5;
    }

    public void setVelocidad5(int velocidad5) {
        this.velocidad5 = velocidad5;
    }

    public boolean isModificada() {
        return modificada;
    }

    public void setModificada(boolean modificada) {
        this.modificada = modificada;
    }
    
    
}
