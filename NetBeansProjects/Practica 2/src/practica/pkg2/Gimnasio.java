package practica.pkg2;

import java.io.Serializable;

public class Gimnasio implements Serializable{

    private int id;
    private String lugar;

    public Gimnasio(int id, String lugar) {
        this.id = id;
        this.lugar = lugar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLugar() {
        return lugar;
    }

    public void setLugar(String lugar) {
        this.lugar = lugar;
    }
    public void mostrargimnasio() {
        System.out.println("Gimnasio id: " + id + "\t" + "lugar: " + lugar);
    }

}
