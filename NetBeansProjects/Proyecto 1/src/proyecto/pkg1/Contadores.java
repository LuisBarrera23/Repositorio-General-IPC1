package proyecto.pkg1;

import java.io.Serializable;

public class Contadores implements Serializable {

    private int cont;

    public Contadores(int cont) {
        this.cont = cont;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }
}
