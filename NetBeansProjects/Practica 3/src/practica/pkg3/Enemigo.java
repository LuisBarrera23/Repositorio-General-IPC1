package practica.pkg3;

public class Enemigo {
private int vida;
private int puntos;
private boolean estado;

    public Enemigo(int vida, int puntos) {
        this.vida = vida;
        this.puntos = puntos;
        this.estado=true;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
}
