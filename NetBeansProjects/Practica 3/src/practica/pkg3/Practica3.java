package practica.pkg3;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Practica3{
    public static Configuracion configuracion;
    public static Jugador[] jugador;
    public static int cJugador;
    public static Contador contador;

    // OIS -> ObjectInputStream es el que nos permite leer un Objeto
    static ObjectInputStream ois;
    // OOS -> ObjectOutputStream es el que nos permite convertir un Objeto para guardarlo.
    static ObjectOutputStream oos;

    public static void main(String[] args) {
        Recuperardatos();
        int[] potenciadores={0,1,2,3,4,5};
        configuracion=new Configuracion(90,1,1,1,2,1,potenciadores);
        Pantallaprincipal nuevo= new Pantallaprincipal();
        nuevo.setVisible(true);
    }
    
    public static void registrarjugador(String nombre,int punteo){
        jugador[cJugador]=new Jugador(nombre,punteo);
        cJugador++;
        ordenar();
    }
    
    public static void ordenar(){
        for (int i = 0; i < cJugador - 1; i++) {
            for (int j = 0; j < cJugador - 1; j++) {
                if (jugador[j].getPunteo() < jugador[j + 1].getPunteo()) {
                    Jugador tmp = jugador[j + 1];
                    jugador[j + 1] = jugador[j];
                    jugador[j] = tmp;
                }
            }
        }
        guardar();
        mostrardatos();
    }
    
    public static void mostrardatos(){
        for (int i = 0; i < cJugador; i++) {
            System.out.println((i+1)+". "+"nombre del jugador: "+jugador[i].getNombre()+" su punteo es: "+jugador[i].getPunteo());
        }
    }
    
    public static void guardar(){
        //guardado de los contadores
        contador=new Contador(cJugador);
        
        //guardado de objetos
        String ruta;
        ruta="Jugadores.bin";
        Serializar(jugador,ruta);
        
        ruta="Contador.bin";
        Serializar(contador,ruta);
        
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
        if(contador==null){
            ruta="Contador.bin";
            contador=(Contador) Recuperar(ruta);
        }
        if(contador==null){
            contador=new Contador(cJugador);
        }
        System.out.println(cJugador);
        
        //recuperacion de Jugadores
        if(jugador==null){
            ruta="Jugadores.bin";
            jugador=(Jugador[]) Recuperar(ruta);
        }
        if(jugador==null){
            jugador = new Jugador[100];
        }

        //recuperacion de Contadores
        try{     
        cJugador=contador.getContador();
        }catch(Exception e){
            
        }
        System.out.println(cJugador);

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
    
}
