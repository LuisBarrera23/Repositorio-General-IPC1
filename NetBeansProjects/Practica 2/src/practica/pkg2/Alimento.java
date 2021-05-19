package practica.pkg2;

import java.io.Serializable;

class Alimento implements Serializable{
    private int id;
    private String nombre;
    private double vida;
    
    
    public Alimento(int id, String nombre, double vida) {
        this.id = id;
        this.nombre = nombre;
        this.vida = vida;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getVida() {
        return vida;
    }

    public void setVida(double vida) {
        this.vida = vida;
    }
    
    public void mostraralimento() {
        System.out.println("Alimento id: " + id + "\t" + "nombre: " + nombre+"\t"+"puntos de vida que regenera: "+vida);
    }

}
