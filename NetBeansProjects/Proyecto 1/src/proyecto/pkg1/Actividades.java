package proyecto.pkg1;

import java.io.Serializable;

public class Actividades implements Serializable{

    private String nombre;
    private String descripcion;
    private int ponderacion;
    private double promedio;
    private Notas[] notas;
    private int cNotas;

    public Actividades(String nombre, String descripcion, int ponderacion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.ponderacion = ponderacion;
        this.promedio=0;
        this.notas=new Notas[50];
        this.cNotas=0;
    }
    
    

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPonderacion() {
        return ponderacion;
    }

    public void setPonderacion(int ponderacion) {
        this.ponderacion = ponderacion;
    }

    public double getPromedio() {
        promedio=0;
        for (int i = 0; i < cNotas; i++) {
            promedio+=notas[i].getNota();
        }
        return promedio/cNotas;
    }

    public void setPromedio(double promedio) {
        this.promedio = promedio;
    }

    public Notas[] getNotas() {
        return notas;
    }

    public int getcNotas() {
        return cNotas;
    }

    public void setcNotas(int cNotas) {
        this.cNotas = cNotas;
    }
    
    public void asignarnota(Notas nota){
        if(cNotas<notas.length){
            notas[cNotas]=nota;
            cNotas++;
        }
    }
    
    public void eliminaralumno(int codigoalum) {
        boolean encontrada=false;
        if (cNotas > 0) {
            int pos = 0;
            for (int i = 0; i < cNotas; i++) {
                if (notas[i].getCodigo() == codigoalum) {
                    pos = i;
                    System.out.println("nota encontrada en la pos: " + i);
                    encontrada=true;
                }
            }
            if (encontrada) {
                for (int i = pos; i < cNotas - 1; i++) {
                    notas[i] = notas[i + 1];
                }
                notas[cNotas - 1] = null;
                cNotas--;
            }else{
                //System.out.println("nota de alumno no encontrada xd");
            }
            
        }

        
        
    }


}
