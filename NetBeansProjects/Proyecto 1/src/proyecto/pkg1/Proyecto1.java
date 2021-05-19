package proyecto.pkg1;
//librerias para crear PDF

import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.Document;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;

//librerias para serializar
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Proyecto1 {

    static Alumno[] alumno;
    static int cAlumnos;

    static Profesor[] profesor;
    static int cProfesor;
    
    static Curso[] curso;
    static int cCurso;
    
    //clase para guardar contadores
    static Contadores[] cont;
    
    // OIS -> ObjectInputStream es el que nos permite leer un Objeto
    static ObjectInputStream ois;
    // OOS -> ObjectOutputStream es el que nos permite convertir un Objeto para guardarlo.
    static ObjectOutputStream oos;

    public static void main(String[] args) {
        Recuperardatos();
        Login login=new Login();
        //Admin admin = new Admin();
        //Informacioncurso nuevo=new Informacioncurso(curso[0],alumno[0],0);

    }
    //metodos para la serializacion
    public static void guardar(){
        //guardado de los contadores
        cont[0]=new Contadores(cAlumnos);
        cont[1]=new Contadores(cProfesor);
        cont[2]=new Contadores(cCurso);
        
        //guardado de objetos
        String ruta;
        ruta="Profesores.bin";
        Serializar(profesor,ruta);
        
        ruta="Alumnos.bin";
        Serializar(alumno,ruta);
        
        ruta="Cursos.bin";
        Serializar(curso,ruta);
        
        ruta="Contadores.bin";
        Serializar(cont,ruta);
        
    }
    public static void Serializar(Object object,String ruta) {
        try {
            // Creamos un archivo, entre comillas va la ruta donde queremos almacenar el archivo
            oos = new ObjectOutputStream(new FileOutputStream(ruta));
            // Utilizamos el metodo writeObject, para convertir el objeto serializable en parte del archivo
            oos.writeObject(object);
            // Cerramos el archivo para que se efectuen los cambios
            oos.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void Recuperardatos(){
        String ruta;
        
        //recuperacion de contadores
        if(cont==null){
            ruta="Contadores.bin";
            cont=(Contadores[]) Recuperar(ruta);
        }
        if(cont==null){
            cont=new Contadores[3];
        }
        
        //recuperacion de Profesores
        if(profesor==null){
            ruta="Profesores.bin";
            profesor=(Profesor[])Recuperar(ruta);
        }
        if(profesor==null){
            profesor=new Profesor[50];
        }
        
        //recuperacion de Alumnos
        if(alumno==null){
            ruta="Alumnos.bin";
            alumno=(Alumno[])Recuperar(ruta);
        }
        if(alumno==null){
            alumno = new Alumno[300];
        }
        
        //recuperacion de Cursos
        if(curso==null){
            ruta="Cursos.bin";
            curso=(Curso[])Recuperar(ruta);
        }
        if(curso==null){
            curso=new Curso[50];
        }
        //recuperacion de Contadores
        try{     
        cAlumnos=cont[0].getCont();
        cProfesor=cont[1].getCont();
        cCurso=cont[2].getCont();
        }catch(Exception e){
            
        }

    }
    public static Object Recuperar(String ruta) {
        // Creamos un objeto Object, porque podemos recuperar cualquier cosa del archivo binario
        Object object;
        
        try {
            // Abrimos el archivo en la direccion que esta entre comillas
            // SE RECOMIENDA UTILIZAR LA RUTA RELATIVA
            ois = new ObjectInputStream(new FileInputStream(ruta));
            // Ahora el objeto que creamos, le asignamos el objeto que leimos del archivo
            object = ois.readObject();
            // Retornamos el objeto serializado ya interpreado por el lector de la libreria
            return object;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Si no existiera el objeto, le vamos a retornar un null, es decir que no existe un archivo
        // por lo tanto, no hay un registro guardado anteriormente
        return null;
    }
    
    //metodos para profesor admin

    public static Object[][] datosprofesores() {
        Object[][] arreglo = new Object[cProfesor][5];
        for (int i = 0; i < cProfesor; i++) {
            arreglo[i][0] = profesor[i].getCodigo();
            arreglo[i][1] = profesor[i].getNombre();
            arreglo[i][2] = profesor[i].getApellido();
            arreglo[i][3] = profesor[i].getCorreo();
            arreglo[i][4] = profesor[i].getGenero();
        }
        return arreglo;
    }

    public static void AgregarProfesor(Profesor prof) {
        boolean repetido = false;
        for (int i = 0; i < cProfesor; i++) {
            if (prof.getCodigo() == profesor[i].getCodigo()) {
                repetido = true;
            }
        }
        if (cProfesor < profesor.length && repetido == false) {
            profesor[cProfesor] = prof;
            cProfesor++;
        }
    }

    public static void eliminarprofesor(int pos) {
        int[]cursos=profesor[pos].getCursos();
        for (int i = 0; i < profesor[pos].getContadorcursos(); i++) {
            System.out.println("curso id: "+cursos[i]);
            for (int j = 0; j < cCurso; j++) {
                if(cursos[i]==curso[j].getCodigo()){
                    System.out.println("encontrado");
                    curso[j].setProfesor(null);
                }else{
                    System.out.println("no encontrado");
                }
            }
        }
        
        if (cProfesor > 0) {
            for (int i = pos; i < cProfesor - 1; i++) {
                profesor[i] = profesor[i + 1];
            }

            profesor[cProfesor-1]=null;
            cProfesor--;
        }

    }

    public static void CrearProfesor(Profesor prof, String contra) {
        boolean repetido = false;
        for (int i = 0; i < cProfesor; i++) {
            if (prof.getCodigo() == profesor[i].getCodigo()) {
                repetido = true;
            }
        }
        if (cProfesor < profesor.length && repetido == false) {
            profesor[cProfesor] = prof;
            profesor[cProfesor].setContraseña(contra);
            cProfesor++;
        }
    }

    public static void pdfprofesores() {
        try {
            Document documento = new Document();
            FileOutputStream ubicacion = new FileOutputStream("Listado de Profesores.pdf");
            PdfWriter.getInstance(documento, ubicacion);
            documento.open();
            documento.add(new Paragraph("Los Profesores cargados en el sistema son los siguientes: "));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Código");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("Correo");
            tabla.addCell("Genero");

            for (int i = 0; i < cProfesor; i++) {
                tabla.addCell(Integer.toString(profesor[i].getCodigo()));
                tabla.addCell(profesor[i].getNombre());
                tabla.addCell(profesor[i].getApellido());
                tabla.addCell(profesor[i].getCorreo());
                tabla.addCell(profesor[i].getGenero().toUpperCase());
            }

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {

        }

    }
    
    //metodos para alumnos admin

    public static void AgregarAlumno(Alumno alum) {
        boolean repetido = false;
        for (int i = 0; i < cAlumnos; i++) {
            if (alum.getCodigo() == alumno[i].getCodigo()) {
                repetido = true;
            }
        }
        if (cAlumnos < alumno.length&& repetido == false) {
            alumno[cAlumnos] = alum;
            cAlumnos++;
        }
    }
    
    public static Object[][] datosalumno() {
        Object[][] arreglo = new Object[cAlumnos][5];
        for (int i = 0; i < cAlumnos; i++) {
            arreglo[i][0] = alumno[i].getCodigo();
            arreglo[i][1] = alumno[i].getNombre();
            arreglo[i][2] = alumno[i].getApellido();
            arreglo[i][3] = alumno[i].getCorreo();
            arreglo[i][4] = alumno[i].getGenero();
        }
        return arreglo;
    }
    
    public static void pdfalumnos() {
        try {
            Document documento = new Document();
            FileOutputStream ubicacion = new FileOutputStream("Listado de Alumnos.pdf");
            PdfWriter.getInstance(documento, ubicacion);
            documento.open();
            documento.add(new Paragraph("Los Alumnos cargados en el sistema son los siguientes: "));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(5);
            tabla.addCell("Código");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("Correo");
            tabla.addCell("Genero");

            for (int i = 0; i < cAlumnos; i++) {
                tabla.addCell(Integer.toString(alumno[i].getCodigo()));
                tabla.addCell(alumno[i].getNombre());
                tabla.addCell(alumno[i].getApellido());
                tabla.addCell(alumno[i].getCorreo());
                tabla.addCell(alumno[i].getGenero().toUpperCase());
            }

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {

        }

    }
    
    //metodos para cursos admin
    public static void Agregarcurso(Curso nuevo, int prof) {
        boolean repetido = false;
        for (int i = 0; i < cCurso; i++) {
            if (nuevo.getCodigo() == curso[i].getCodigo()) {
                repetido = true;
            }
        }
        int posprofesor=0;
        for (int i = 0; i < cProfesor; i++) {
            if (prof == profesor[i].getCodigo()) {
                posprofesor=i;
            }
        }
        if (cCurso < curso.length && repetido == false) {
            curso[cCurso] = nuevo;
            curso[cCurso].setProfesor(profesor[posprofesor]);
            profesor[posprofesor].asignarcurso(curso[cCurso].getCodigo());
            cCurso++;
        }
    }
    
    public static Object[][] datoscursos(){
         Object[][] arreglo=new Object[cCurso][5];
        try{
           
        for (int i = 0; i < cCurso; i++) {
            arreglo[i][0]=curso[i].getCodigo();
            arreglo[i][1]=curso[i].getNombre();
            arreglo[i][2]=curso[i].getCreditos();
            arreglo[i][3]=curso[i].getcAlumnos();
            if(curso[i].getProfesor()!=null){
                arreglo[i][4]=curso[i].getProfesor().getNombre()+" "+curso[i].getProfesor().getApellido();
            }else{
                arreglo[i][4]="";
            }
            
        }
        
        }catch(Exception e){
            //e.printStackTrace();
        }
        return arreglo;
    }
    
    public static void ordenarcursos(){
        for (int i = 0; i < cCurso - 1; i++) {
            for (int j = 0; j < cCurso - 1; j++) {
                if (curso[j].getcAlumnos() < curso[j + 1].getcAlumnos()) {
                    Curso tmp = curso[j + 1];
                    curso[j + 1] = curso[j];
                    curso[j] = tmp;
                }
            }
        }
    }
    
    public static void pdfcursos(){
         try {
            Document documento = new Document();
            FileOutputStream ubicacion = new FileOutputStream("Listado de Cursos.pdf");
            PdfWriter.getInstance(documento, ubicacion);
            documento.open();
            documento.add(new Paragraph("Los Cursos cargados en el sistema son los siguientes: "));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(5);
            int[]medidas={2,3,3,2,6};
            tabla.setWidths(medidas);
            tabla.addCell("Código");
            tabla.addCell("Nombre");
            tabla.addCell("Creditos");
            tabla.addCell("Alumnos");
            tabla.addCell("Profesor");
            

            for (int i = 0; i < cCurso; i++) {
                tabla.addCell(Integer.toString(curso[i].getCodigo()));
                tabla.addCell(curso[i].getNombre());
                tabla.addCell(curso[i].getCreditos());
                tabla.addCell(Integer.toString(curso[i].getcAlumnos()));
                if(curso[i].getProfesor()!=null){
                 tabla.addCell(curso[i].getProfesor().getNombre()+" "+curso[i].getProfesor().getApellido());   
                }else{
                    tabla.addCell(" ");
                }
                
            }

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void eliminarcurso(int pos){
        if (cCurso > 0) {
            for (int i = pos; i < cCurso - 1; i++) {
                curso[i] = curso[i + 1];
            }

            curso[cCurso-1]=null;
            cCurso--;
        }
    }
    
    
    //metodos para modulo profesor
    public static Object[][] cursosimpartidos(int pos){
        int cantidad=0;
        for (int i = 0; i < cCurso; i++) {
            if(profesor[pos].getCodigo()==curso[i].getProfesor().getCodigo()){
                cantidad++;
            }
        }
        Object [][] arreglo=new Object[cantidad][3];
        cantidad=0;
        for (int i = 0; i < cCurso; i++) {
            if(profesor[pos].getCodigo()==curso[i].getProfesor().getCodigo()){
                arreglo[cantidad][0]=curso[i].getCodigo();
                arreglo[cantidad][1]=curso[i].getNombre();
                arreglo[cantidad][2]=curso[i].getcAlumnos();
                cantidad++;
            }
        }
        
        return arreglo;
        
    }
    
    public static Object[][] alumnosasignados(Curso curso){
        Object[][] arreglo=new Object[curso.getcAlumnos()][3];
        Alumno[] alum=curso.getAlumnos();
        for (int i = 0; i < curso.getcAlumnos(); i++) {
            for (int j = 0; j < cAlumnos; j++) {
                if (alum[i].getCodigo()==alumno[j].getCodigo()) {
                    arreglo[i][0]=alumno[j].getCodigo();
                    arreglo[i][1]=alumno[j].getNombre();
                    arreglo[i][2]=alumno[j].getApellido();
                }
            }
        }
        
        return arreglo;
    }
    
    public static Object[][] actividades(Curso curso){
        Object[][] arreglo=new Object[curso.getcActividades()][4];
        Actividades[] actividad=curso.getActividades();
        for (int i = 0; i < curso.getcActividades(); i++) {
            arreglo[i][0]=actividad[i].getNombre();
            arreglo[i][1]=actividad[i].getDescripcion();
            arreglo[i][2]=actividad[i].getPonderacion();
            arreglo[i][3]=Math.round(actividad[i].getPromedio());
        }
        return arreglo;
    }
    
    public static void pdftop5mejores(Curso curso){
        Alumno[] alum=curso.getAlumnos();
        int cantidadalumnos=curso.getcAlumnos();
        
        
        for (int i = 0; i < curso.getcAlumnos(); i++) {
            System.out.println(alum[i].getNombre()+" tiene de punteo: "+alum[i].notatotal(curso.getCodigo()));
            alum[i].setPunteo(alum[i].notatotal(curso.getCodigo())); 
        }
        
        
        System.out.println("ahora trataremos de ordenarlo------------------------------------------ ");
        for (int i = 0; i < cantidadalumnos - 1; i++) {
            for (int j = 0; j < cantidadalumnos - 1; j++) {
                if (alum[j].notatotal(curso.getCodigo()) < alum[j+1].notatotal(curso.getCodigo())) {
                    Alumno tmp = alum[j + 1];
                    alum[j + 1] = alum[j];
                    alum[j] = tmp;
                }
            }
        }
        for (int i = 0; i < curso.getcAlumnos(); i++) {
            System.out.println(alum[i].getNombre()+" tiene de punteo: "+alum[i].notatotal(curso.getCodigo()));   
        }
        
        try {
            Document documento = new Document(PageSize.A2.rotate());
            FileOutputStream ubicacion = new FileOutputStream("Top 5 mejores estudiantes de "+curso.getNombre()+".pdf");
            PdfWriter.getInstance(documento, ubicacion);
            documento.open();
            documento.add(new Paragraph("Los estudiantes con mejores punteos son: "));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6+curso.getcActividades());
            tabla.addCell("Posición");
            tabla.addCell("Código");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("Correo");
            Actividades[] actividad=curso.getActividades();
            for (int i = 0; i < curso.getcActividades(); i++) {
                tabla.addCell(actividad[i].getNombre());
            }
            tabla.addCell("Nota final");
            
            for (int i = 0; i < 5; i++) {
                tabla.addCell(String.valueOf(i+1));
                tabla.addCell(String.valueOf(alum[i].getCodigo()));
                tabla.addCell(alum[i].getNombre());
                tabla.addCell(alum[i].getApellido());
                tabla.addCell(alum[i].getCorreo());
                //tabla.addCell("contenido");
                
                for (int j = 0; j < curso.getcActividades(); j++) {
                   Notas[] calificacion=actividad[j].getNotas();
                    for (int k = 0; k < actividad[j].getcNotas(); k++) {
                        if(calificacion[k].getCodigo()==alum[i].getCodigo()){
                            tabla.addCell(String.valueOf(calificacion[k].getNota()));
                        }
                    }
                }
                
                tabla.addCell(String.valueOf(alum[i].notatotal(curso.getCodigo())));
            }

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    
    
    }
    
    public static void pdftop5peores(Curso curso){
       
        Alumno[] alum=curso.getAlumnos();
        int cantidadalumnos=curso.getcAlumnos();
        
        
        for (int i = 0; i < curso.getcAlumnos(); i++) {
            System.out.println(alum[i].getNombre()+" tiene de punteo: "+alum[i].notatotal(curso.getCodigo()));
            alum[i].setPunteo(alum[i].notatotal(curso.getCodigo())); 
        }
        
        
        System.out.println("ahora trataremos de ordenarlo------------------------------------------ ");
        for (int i = 0; i < cantidadalumnos - 1; i++) {
            for (int j = 0; j < cantidadalumnos - 1; j++) {
                if (alum[j].notatotal(curso.getCodigo()) > alum[j+1].notatotal(curso.getCodigo())) {
                    Alumno tmp = alum[j + 1];
                    alum[j + 1] = alum[j];
                    alum[j] = tmp;
                }
            }
        }
        for (int i = 0; i < curso.getcAlumnos(); i++) {
            System.out.println(alum[i].getNombre()+" tiene de punteo: "+alum[i].notatotal(curso.getCodigo()));   
        }
        
        try {
            Document documento = new Document(PageSize.A2.rotate());
            FileOutputStream ubicacion = new FileOutputStream("Top 5 peores estudiantes de "+curso.getNombre()+".pdf");
            PdfWriter.getInstance(documento, ubicacion);
            documento.open();
            documento.add(new Paragraph("Los 5 estudiantes con peores punteos son: "));
            documento.add(new Paragraph(" "));

            PdfPTable tabla = new PdfPTable(6+curso.getcActividades());
            tabla.addCell("Posición");
            tabla.addCell("Código");
            tabla.addCell("Nombre");
            tabla.addCell("Apellido");
            tabla.addCell("Correo");
            Actividades[] actividad=curso.getActividades();
            for (int i = 0; i < curso.getcActividades(); i++) {
                tabla.addCell(actividad[i].getNombre());
            }
            tabla.addCell("Nota final");
            
            for (int i = 0; i < 5; i++) {
                tabla.addCell(String.valueOf(i+1));
                tabla.addCell(String.valueOf(alum[i].getCodigo()));
                tabla.addCell(alum[i].getNombre());
                tabla.addCell(alum[i].getApellido());
                tabla.addCell(alum[i].getCorreo());
                //tabla.addCell("contenido");
                
                for (int j = 0; j < curso.getcActividades(); j++) {
                   Notas[] calificacion=actividad[j].getNotas();
                    for (int k = 0; k < actividad[j].getcNotas(); k++) {
                        if(calificacion[k].getCodigo()==alum[i].getCodigo()){
                            tabla.addCell(String.valueOf(calificacion[k].getNota()));
                        }
                    }
                }
                
                tabla.addCell(String.valueOf(alum[i].notatotal(curso.getCodigo())));
            }

            documento.add(tabla);
            documento.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    
    
     
    }
    
    public static void eliminaralumnodelcurso(int codigoalum,int codigocurso){
        for (int i = 0; i < cAlumnos; i++) {
            if(alumno[i].getCodigo()==codigoalum){
                alumno[i].eliminarcurso(codigocurso);
            }else{
            }
        }
    }
    
    //metodos para el modulo alumno
    public static Object[][] cursosasignados(int pos){
        Object[][] arreglo=new Object[alumno[pos].getcCursos()][3];
        Curso[] recibidos= alumno[pos].getCursos();
        for (int i = 0; i < alumno[pos].getcCursos(); i++) {
            for (int j = 0; j < cCurso; j++) {
                if (curso[j].getCodigo()==recibidos[i].getCodigo()) {
                    arreglo[i][0]=curso[j].getCodigo();
                    arreglo[i][1]=curso[j].getNombre();
                    arreglo[i][2]=curso[j].getProfesor().getNombre()+" "+curso[j].getProfesor().getApellido();
                }
            }
        }
        return arreglo;
    }
    
    public static Object[][] mostraractividades(Curso curso,int codigo){
        Actividades[] actividad=curso.getActividades();
        int cActividades=curso.getcActividades();
        Object[][] arreglo=new Object[cActividades][4];
        
        for (int i = 0; i < cActividades; i++) {
            arreglo[i][0]=actividad[i].getNombre();
            arreglo[i][1]=actividad[i].getDescripcion();
            arreglo[i][2]=actividad[i].getPonderacion();
            
            Notas[] nota=actividad[i].getNotas();
            for (int j = 0; j < actividad[i].getcNotas(); j++) {
                if(nota[j].getCodigo()==codigo){
                    arreglo[i][3]=nota[j].getNota();
                }
            }
            
        }
        
        
        return arreglo;
    }
    
}
