package practica.pkg3;

import java.io.Serializable;

public class Jugador implements Serializable{
private String Nombre;
private int punteo;

    public Jugador(String Nombre, int punteo) {
        this.Nombre = Nombre;
        this.punteo = punteo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public int getPunteo() {
        return punteo;
    }

    public void setPunteo(int punteo) {
        this.punteo = punteo;
    }


    
}
