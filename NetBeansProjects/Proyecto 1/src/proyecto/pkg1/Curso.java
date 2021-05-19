package proyecto.pkg1;

import java.io.Serializable;

public class Curso implements Serializable{
    

    private int codigo;
    private String nombre;
    private String creditos;
    private Profesor profesor;
    private Alumno[] alumnos;
    private int cAlumnos;
    private Actividades[] actividades;
    private int cActividades;
    

    public Curso(int codigo, String nombre, String creditos) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.creditos = creditos;
        this.alumnos=new Alumno[50];
        this.cAlumnos=0;
        this.actividades=new Actividades[30];
        this.cActividades=0;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCreditos() {
        return creditos;
    }

    public void setCreditos(String creditos) {
        this.creditos = creditos;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Alumno[] getAlumnos() {
        return alumnos;
    }

    public int getcAlumnos() {
        return cAlumnos;
    }

    public void setcAlumnos(int cAlumnos) {
        this.cAlumnos = cAlumnos;
    }
    
    public Actividades[] getActividades() {
        return actividades;
    }

    public int getcActividades() {
        return cActividades;
    }
    
    
    public void asignaralumno(int codigo){
        boolean repetido=false;
        for (int i = 0; i < cAlumnos; i++) {
            if(alumnos[i].getCodigo()==codigo){
                repetido=true;
            }
        }
        if((cAlumnos<alumnos.length)&&repetido==false){
            for (int i = 0; i < Proyecto1.cAlumnos; i++) {
                if(Proyecto1.alumno[i].getCodigo()==codigo){
                    alumnos[cAlumnos]=Proyecto1.alumno[i];
                    Proyecto1.alumno[i].asignarcurso(this);
                    cAlumnos++;
                }
            }
        }
    }
    
    public void asignaractividad(Actividades actividad){
        int total=acumulado()+actividad.getPonderacion();
        if(cActividades<actividades.length&&total<=100){
            System.out.println("actividad creada xd");
            actividades[cActividades]=actividad;
            cActividades++;
        }
    }
    
    public int acumulado(){
        int acumulado=0;
        for (int i = 0; i < cActividades; i++) {
            acumulado+=actividades[i].getPonderacion();
        }
        return acumulado;
        
    }
    
    public void eliminaralumno(int codigoalum){
        for (int i = 0; i < cActividades; i++) {
            actividades[i].eliminaralumno(codigoalum);
        }
        for (int i = 0; i < cAlumnos; i++) {
            if(alumnos[i].getCodigo()==codigoalum){
                for (int j = i; j < cAlumnos-1; j++) {
                    alumnos[j]=alumnos[j+1];
                }
                alumnos[cAlumnos-1]=null;
                cAlumnos--;
                Proyecto1.eliminaralumnodelcurso(codigoalum, codigo);
            }
        }
    }
    
    
}
