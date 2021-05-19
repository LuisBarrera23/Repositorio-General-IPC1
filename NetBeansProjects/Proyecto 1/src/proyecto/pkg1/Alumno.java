package proyecto.pkg1;

import java.io.Serializable;

class Alumno implements Serializable{

    private int codigo;
    private String nombre;
    private String apellido;
    private String correo;
    private String genero;
    private String contraseña;
    private Curso[] cursos;
    private int cCursos;
    private String imagen;
    private double punteo;

    public Alumno(int codigo, String nombre, String apellido, String correo, String genero) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.apellido = apellido;
        this.correo = correo;
        this.genero = genero;
        this.contraseña = "1234";
        this.cursos=new Curso[50];
        this.cCursos=0;
        this.imagen="icono.jpg";
        this.punteo=0;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
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

    public Curso[] getCursos() {
        return cursos;
    }

    public int getcCursos() {
        return cCursos;
    }
    
    public void setcCursos(int cCursos) {
        this.cCursos = cCursos;
    }
    
    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
    
    
    public void asignarcurso(Curso curso){
        boolean repetido=false;
        int posrepetido=0;
        for (int i = 0; i < cCursos; i++) {
            if (curso.getCodigo()==cursos[i].getCodigo()) {
                repetido=true;
                posrepetido=i;
            }
        }
        if((cCursos<cursos.length)&&repetido==false){
            cursos[cCursos]=curso;
            cCursos++;
        }else if(repetido==true){
            cursos[posrepetido]=curso;
        }
    }
    
    public double notatotal(int codigoc) {
        double notatotal = 0;
        for (int i = 0; i < cCursos; i++) {
            if (cursos[i].getCodigo() == codigoc) {//encontre el curso que necesito el punteo

                Actividades[] actividad = cursos[i].getActividades();

                for (int j = 0; j < cursos[i].getcActividades(); j++) {//recorro las actividades existentes en el curso;

                    Notas[] nota = actividad[j].getNotas();

                    for (int k = 0; k < actividad[j].getcNotas(); k++) {
                        if (nota[k].getCodigo() == codigo) {
                            notatotal += nota[k].getNeto();
                        }
                    }

                }

            }
        }
        return notatotal;
    }
    
    
    public double getPunteo() {
        return punteo;
    }

    public void setPunteo(double punteo) {
        this.punteo = punteo;
    }
    
    public void eliminarcurso(int codigocurso){
        
        if(cCursos>0){
            for (int i = 0; i < cCursos; i++) {
                if(cursos[i].getCodigo()==codigocurso){
                    for (int j = i; j < cCursos-1; j++) {
                        cursos[j]=cursos[j+1];
                    }
                    cursos[cCursos-1]=null;
                    cCursos--;
                }
            }
        }
    }
    

}
