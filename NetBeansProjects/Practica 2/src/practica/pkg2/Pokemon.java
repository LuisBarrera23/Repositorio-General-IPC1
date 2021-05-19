
package practica.pkg2;

import java.io.Serializable;

public class Pokemon implements Serializable {

    private int Id;
    private String Tipo;
    private String Nombre;
    private double Vida;
    private double PuntosAtaque;
    private boolean Capturado;
    private boolean Estado;
    

    //crearemos nuestro constructor
    public Pokemon(int id,String tipo,String nombre,double vida,double puntosataque,boolean capturado,boolean estado){
        this.Id=id;
        this.Tipo=tipo;
        this.Nombre=nombre;
        this.Vida=vida;
        this.PuntosAtaque=puntosataque;
        this.Capturado=capturado;
        this.Estado=estado;
    }
    
    public void mostrarpokemon() {
        String capturado;
        if (Capturado == true) {
            capturado = "Capturado";
        } else {
            capturado = "Salvaje";
        }
        String estado;
        if (Estado == true) {
            estado = "Vivo";
        } else {
            estado = "Muerto";
        }
        System.out.println(Id + "," + Tipo + "," + Nombre + "," + Vida + "," + PuntosAtaque + "," + capturado + "," + estado);
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public double getVida() {
        return Vida;
    }

    public void setVida(double Vida) {
        this.Vida = Vida;
    }

    public double getPuntosAtaque() {
        return PuntosAtaque;
    }

    public void setPuntosAtaque(double PuntosAtaque) {
        this.PuntosAtaque = PuntosAtaque;
    }

    public boolean isCapturado() {
        return Capturado;
    }

    public void setCapturado(boolean Capturado) {
        this.Capturado = Capturado;
    }

    public boolean isEstado() {
        return Estado;
    }

    public void setEstado(boolean Estado) {
        this.Estado = Estado;
    }

}
