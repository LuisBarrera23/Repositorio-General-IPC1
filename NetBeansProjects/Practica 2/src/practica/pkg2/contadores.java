package practica.pkg2;

import java.io.Serializable;

public class contadores implements Serializable {

    private int cont;

    public contadores(int cont) {
        this.cont = cont;
    }

    public int getCont() {
        return cont;
    }

    public void setCont(int cont) {
        this.cont = cont;
    }

}
