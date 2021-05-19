package proyecto.pkg1;

import java.io.Serializable;

class Profesor implements Serializable {


    private int codigo;
    private String nombre;
    private String apellido;
    private String correo;
    private String genero;
    private String contraseña;
    private int[] cursos;
    private int contadorcursos;

    public Profesor(int codigo, String nombre, String apellido, String correo, String genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.genero = genero;
        this.contraseña = "1234";
        cursos=new int[50];
        contadorcursos=0; 
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

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    
    public int[] getCursos() {
        return cursos;
    }

    public int getContadorcursos() {
        return contadorcursos;
    }
    
    public void asignarcurso(int curso){
        if(contadorcursos<cursos.length){
            cursos[contadorcursos]=curso;
            contadorcursos++;
        }
    }

}
