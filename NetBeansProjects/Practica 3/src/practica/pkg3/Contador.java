package practica.pkg3;

import java.io.Serializable;

public class Contador implements Serializable{
private int contador;

    public Contador(int contador) {
        this.contador = contador;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }


    
}
