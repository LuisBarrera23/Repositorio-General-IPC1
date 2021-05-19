package proyecto.pkg1;

import java.io.Serializable;

public class Notas implements Serializable{
    private int codigo;
    private double nota;
    private double neto;

    public Notas(int codigo, double nota,double valor) {
        this.codigo = codigo;
        this.nota = nota;
        this.neto=(valor/100)*nota;
    }
    



    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
    
    public double getNeto() {
        return neto;
    }

}
