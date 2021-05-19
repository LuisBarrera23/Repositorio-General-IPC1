package practica.pkg2;
//librerias para la lectura de archivos
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

//librerias para la Serialización
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

//librerias para la hora
import java.time.LocalDate;//Libreria para visualizar la fecha en los reportes
import java.time.LocalTime; //Liberia para visualizar la hora en los reportes

//librerias para escribir archivos en este caso .html
import java.io.FileWriter;//librerias para escribir archivos
import java.io.PrintWriter;//librerias para escribir archivos


import java.util.Scanner;//libreria de scanner

public class Practica2 {
    
    // OIS -> ObjectInputStream es el que nos permite leer un Objeto
    static ObjectInputStream ois;
    // OOS -> ObjectOutputStream es el que nos permite convertir un Objeto para guardarlo.
    static ObjectOutputStream oos;


    static Pokemon[] pokemon;
    static int contadorpokemons;

    static Entrenador[] entrenador;
    static int contadorentrenadores;
    
    static Pokeball[] pokeball;
    static int contadorpokeballs;
    
    static Gimnasio[] gimnasio;
    static int contadorgimnasios;
    
    static Alimento[] alimento;
    static int contadoralimentos;
    
    static Alimentar[] alimentos;
    static int contadoralimentacion;
    
    //arreglo de objeto que guardara unicamente los contadores generales que en esta practica se usaron
    static contadores[] cont;
    

    public static void main(String[] args) {
        Serializaciongeneral();
        recuperarcontadores();
        Scanner sc = new Scanner(System.in);// variable para scannear
        int opcion;
        boolean salida = false;
        while (!salida) {
            try {
                System.out.println("----------MENU PRINCIPAL----------");
                System.out.println("1.Cargar Pokemons");
                System.out.println("2.Cargar Entrenadores");
                System.out.println("3.Poke Ball");
                System.out.println("4.Gimnasios");
                System.out.println("5.Alimentos");
                System.out.println("6.Asignar Pokemons");
                System.out.println("7.Asignar Poke Ball");
                System.out.println("8.Asignar Actividad de Comida");
                System.out.println("9.Asignar Actividad");
                System.out.println("10.Reportes");
                System.out.println("11.Salir");
                System.out.println("Ingrese el numero de la opcion que desea realizar");
                opcion = sc.nextInt();
                switch (opcion) {
                    case 1:
                        cargarpokemons();
                        break;
                    case 2:
                        cargarentrenadores();
                        break;
                    case 3:
                        cargarpokeballs();                        
                        break;
                    case 4:
                        cargargimnasios();
                        break;
                    case 5:
                        cargaralimentos();
                        break;
                    case 6:
                        asignarpokemons();
                        break;
                    case 7:
                        asignarpokeball();
                        break;
                    case 8:
                        asignarcomida();
                        break;
                    case 9:
                        pelea();
                        break;
                    case 10:
                        int opcion2=0;
                        do{
                            
                            System.out.println("---------------Menu reportes---------------");
                            System.out.println("1.Reporte de entrenadores");
                            System.out.println("2.Reporte de Pokemons Salvajes");
                            System.out.println("3.Reporte comidas:");
                            System.out.println("4.Reporte Peleas");
                            System.out.println("5.Top 5 pokemons con mayor ataque");
                            System.out.println("6.Top 5 Alimentos que mayor salud dan");
                            System.out.println("7.Regresar al menu principal");
                            opcion2=sc.nextInt();
                            
                            switch (opcion2) {
                                case 1:
                                    System.out.println("-------Generando Reporte-------");
                                    ReporteEntrenadores();
                                    break;
                                case 2:
                                    System.out.println("-------Generando Reporte-------");
                                    ReporteSalvajes();
                                    break;
                                case 3:
                                    System.out.println("-------Generando Reporte-------");
                                    ReporteComidas();
                                    break;
                                case 4:
                                    System.out.println("El reporte es generado durante la pelea");
                                    break;
                                case 5:
                                    System.out.println("-------Generando Reporte-------");
                                    ReportetopPokemon();
                                    break;
                                case 6:
                                    ReportetopAlimentos();
                                    break;
                                default:
                                    System.out.println("opcion incorrecta");
                                    break;

                            }
                            
                        }while(opcion2!=7);
                        break;
                    case 11:
                        guardarcontadores();
                        String ruta;
                        ruta="Contadores.bin";
                        Serializar(cont,ruta);
                        
                        ruta="Pokemon.bin";
                        Serializar(pokemon,ruta);
                        
                        ruta = "Entrenador.bin";
                        Serializar(entrenador, ruta);
                        
                        ruta="Pokeball.bin";
                        Serializar(pokeball,ruta);
                        
                        ruta="Gimnasio.bin";
                        Serializar(gimnasio,ruta);
                        
                        ruta="Alimento.bin";
                        Serializar(alimento,ruta);
                        
                        ruta="Alimentar.bin";
                        Serializar(alimentos,ruta);
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Opción no valida");

                }//fin del switch

            } catch (Exception e) {
                e.printStackTrace();
                sc.nextLine();
                System.out.println("Error");
            }//final del cathc
        }//final del While
    }
    
    //metodos para entrenadores
    public static void cargarpokemons() {
        Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        contadorpokemons=0;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contienen los pokemons");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String tipo = datos[1];
                String nombre = datos[2];
                double vida = Double.parseDouble(datos[3]);
                double puntosataque = Double.parseDouble(datos[4]);

                boolean capturado;
                if (datos[5].equals("capturado")) {
                    capturado = true;
                } else {
                    capturado = false;
                }

                boolean estado;
                if (datos[6].equals("vivo")) {
                    estado = true;
                } else {
                    estado = false;
                }
                
                
                boolean repetido=false;
                if(contadorpokemons>0){
                    for (int i = 0; i < contadorpokemons; i++) {
                        int comp=pokemon[i].getId();
                        
                        if(comp==id){
                            repetido=true;
                        } 
                        
                    }
                }
                //System.out.println("es repetido?: "+repetido);
                if(repetido==false){
                    pokemon[contadorpokemons] = new Pokemon(id, tipo, nombre, vida, puntosataque, capturado, estado);
                    contadorpokemons++;
                }
                if (contadorpokemons == 0) {
                    pokemon[contadorpokemons] = new Pokemon(id, tipo, nombre, vida, puntosataque, capturado, estado);
                    contadorpokemons++;
                }
                
            }
            //mostrarpokemons(pokemon);
            
            

        } catch (Exception e) {
            
        }

    }

    public static void mostrarpokemons(Pokemon[] arreglo) {
        for (int i = 0; i < contadorpokemons; i++) {
            arreglo[i].mostrarpokemon();
        }
    }
    
    //metodos para entrenadores
    public static void cargarentrenadores() {
        Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        contadorentrenadores=0;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contienen los entrenadores");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];

                entrenador[contadorentrenadores] = new Entrenador(id, nombre);
                contadorentrenadores++;
                
            }
            //mostrarentrenadores(entrenador);
            cont[1].setCont(contadorentrenadores);

        } catch (Exception e) {
            
        }
    }

    public static void mostrarentrenadores(Entrenador[] arreglo) {
        for (int i = 0; i < contadorentrenadores; i++) {
            arreglo[i].mostrarentrenador();
        }
    }
    
    //metodos para Pokeballs
    public static void cargarpokeballs(){
        Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        contadorpokeballs=0;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contienen las pokeballs");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            
            //RECORRIDO PARA SABER CUANTAS POKEBALLS SE INGRESARON
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);
            
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) { 
                contadorpokeballs++;
            }
            //System.out.println("se ingresaro: "+contadorpokeballs+"pokeballs");
            pokeball=new Pokeball[contadorpokeballs];
            contadorpokeballs=0;

            //lectura normal del archivo
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);
            linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String tipo = datos[1];

                pokeball[contadorpokeballs] = new Pokeball(id, tipo);
                contadorpokeballs++;
                
            }
            //mostrarpokeballs(pokeball);
            cont[2].setCont(contadorpokeballs);

        } catch (Exception e) {
            
        }
    }
    
    public static void mostrarpokeballs(Pokeball[] arreglo){
        for (int i = 0; i <contadorpokeballs; i++) {
            arreglo[i].mostrarpokeball();
        }
    }
    
    //metodos para gimnasios
    public static void cargargimnasios(){
        Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        contadorgimnasios=0;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contienen los gimnasios");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String lugar = datos[1];

                gimnasio[contadorgimnasios] = new Gimnasio(id, lugar);
                contadorgimnasios++;
                
            }
            //mostrargimnasios(gimnasio);
            cont[3].setCont(contadorgimnasios);

        } catch (Exception e) {
            
        }
    }
    
    public static void mostrargimnasios(Gimnasio[] arreglo){
        for (int i = 0; i <contadorgimnasios; i++) {
            arreglo[i].mostrargimnasio();
        }
    }
    
    //metodos para Alimentos
    public static void cargaralimentos(){
       Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        contadoralimentos=0;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contienen los alimentos");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                String nombre = datos[1];
                double vida= Double.parseDouble(datos[2]);
                alimento[contadoralimentos] = new Alimento(id, nombre, vida);
                contadoralimentos++;
                
            }
            //mostraralimentos(alimento);
            cont[4].setCont(contadoralimentos);
            

        } catch (Exception e) {
            
        } 
    }
    
    public static void mostraralimentos(Alimento[] arreglo){
        for (int i = 0; i <contadoralimentos; i++) {
            arreglo[i].mostraralimento();
        }
    }
    
    //para asignar pokemons a las pokebolas
    public static void asignarpokemons(){
        Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contienen las asignaciones de pokemons");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            int c=0;
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                c++;
                int idball = Integer.parseInt(datos[0]);
                int idpoke = Integer.parseInt(datos[1]);

                int posball = 0;
                for (int i = 0; i < contadorpokeballs; i++) {
                    int idvar = pokeball[i].getId();
                    if (idball == idvar) {
                        posball = i;
                    }
                }
                //System.out.println("la posicion de la ball es: "+posball);

                int pospoke = 0;
                for (int i = 0; i < contadorpokemons; i++) {
                    int idvar = pokemon[i].getId();
                    if (idpoke == idvar) {
                        pospoke = i;
                    }
                }
                //System.out.println("la posicion del pokemon es: "+pospoke);

                pokeball[posball].asignarpokemon(pokemon[pospoke]);
                pokemon[pospoke].setCapturado(true);
                System.out.println("Linea: " +c);
                pokeball[posball].mostrardatos();

            }
            //mostrarpokemons(pokemon);

        } catch (Exception e) {

        }
    }
    
    //para asignar pokebolas a los entrenadores
    public static void asignarpokeball(){
        Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contienen las asignaciones de pokeballs");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);

            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int identrenador = Integer.parseInt(datos[0]);
                int idball = Integer.parseInt(datos[1]);

                int posentrenador = 0;
                for (int i = 0; i < contadorentrenadores; i++) {
                    int idvar = entrenador[i].getId();
                    if (identrenador == idvar) {
                        posentrenador = i;
                    }
                }
                System.out.println("la posicion del entrenador es: "+posentrenador);

                int posball = 0;
                for (int i = 0; i < contadorpokeballs; i++) {
                    int idvar = pokeball[i].getId();
                    if (idball == idvar) {
                        posball = i;
                    }
                }
                System.out.println("la posicion de la pokeball es: "+posball);
                
                entrenador[posentrenador].asignarpokeball(pokeball[posball]);
                entrenador[posentrenador].mostrardatos();

                

            }

        } catch (Exception e) {
               System.out.println("Error");
               e.printStackTrace();
        }
    }
    
    //para asignar algun alimento a los pokemones
    public static void asignarcomida(){
       Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contiene la asignacion de alimentos");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);
            contadoralimentacion=0;
                       
            System.out.println("--------------------Alimentando Pokemons--------------------");
            String linea = br.readLine();
            while ((linea = br.readLine()) != null) {
                String[] datos = linea.split(",");
                int id = Integer.parseInt(datos[0]);
                int idpokemon = Integer.parseInt(datos[1]);
                
                int pospoke = 0;
                for (int i = 0; i < contadorpokemons; i++) {
                    int idvar = pokemon[i].getId();
                    if (idpokemon == idvar) {
                        pospoke = i;
                    }
                }
                
                int posalimento=0;
                for (int i = 0; i < contadoralimentos; i++) {
                    int idvar=alimento[i].getId();
                    if(idvar==id){
                        posalimento=i;
                    }
                    
                }
                
                alimentos[contadoralimentacion] = new Alimentar(id, idpokemon);
                alimentos[contadoralimentacion].asignarpokemon(pokemon[pospoke]);
                
                System.out.println("La vida del pokemon "+pokemon[pospoke].getNombre()+" antes de alimentarse es de: "+pokemon[pospoke].getVida());
                pokemon[pospoke].setVida(pokemon[pospoke].getVida()+alimento[posalimento].getVida());
                System.out.println("y despues de alimentarse: "+pokemon[pospoke].getVida());
                System.out.println("___________________________________");
                
                if((pokemon[pospoke].getVida())>0){
                    pokemon[pospoke].setEstado(true);
                }
                
                
                contadoralimentacion++;
            }
            //mostraralimentacion(alimentos);
            //mostrarpokemons(pokemon);

        } catch (Exception e) {
            
        }  
    }
    
    public static void mostraralimentacion(Alimentar[] arreglo){
        for (int i = 0; i <contadoralimentacion; i++) {
            arreglo[i].mostraralimentacion();
        }
    }
    
    
    public static void pelea() {
        Scanner sc = new Scanner(System.in);// variable para scannear
        File Archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            System.out.println("Ingrese la ruta del archivo CSV que contiene la asignacion de actividad de pelea");
            String ruta = sc.nextLine();
            Archivo = new File(ruta);
            fr = new FileReader(Archivo);
            br = new BufferedReader(fr);
            String r;

            String linea = br.readLine();
            int batalla=0;
            
            r="<table>";
            while ((linea = br.readLine()) != null) {
                
                r+="<tr><th colspan=\"3\" class=\"title2\">BATALLA: "+(batalla+1)+"</th></tr>";
                String[] datos = linea.split(",");
                int idgym = Integer.parseInt(datos[0]);
                int idpokemon1 = Integer.parseInt(datos[1]);
                int idpokemon2 = Integer.parseInt(datos[2]);

                int pospoke1 = 0;
                for (int i = 0; i < contadorpokemons; i++) {
                    int idvar = pokemon[i].getId();
                    if (idpokemon1 == idvar) {
                        pospoke1 = i;
                    }
                }

                int pospoke2 = 0;
                for (int i = 0; i < contadorpokemons; i++) {
                    int idvar = pokemon[i].getId();
                    if (idpokemon2 == idvar) {
                        pospoke2 = i;
                    }
                }

                int posgym = 0;
                for (int i = 0; i < contadorgimnasios; i++) {
                    int idvar = gimnasio[i].getId();
                    if (idvar == idgym) {
                        posgym = i;
                    }

                }
                
                r+="<tr>\n"
                        +"<td class=\"title1\">"+pokemon[pospoke1].getNombre()+"</td>"
                        +"<td class=\"cont1\"><center><img class=\"vs\" src=\"https://i.pinimg.com/originals/06/1d/de/061dde1c16977f7d2ae3a2c6976e6a99.png\"></img></center></td>"
                        +"<td class=\"title1\">"+pokemon[pospoke2].getNombre()+"</td>"
                        +"</tr>"
                        +"<tr><th colspan=\"3\" class=\"cont2\">GIMNASIO: "+gimnasio[posgym].getLugar()+"</th></tr>"
                        ;
                int round=0;
                System.out.println("-------------------------"+"Batalla "+(batalla+1)+"-------------------------");
                System.out.println("La pelea sera en el gimnasio: " + gimnasio[posgym].getLugar());
                System.out.println("El pokemon 1 es: " + pokemon[pospoke1].getNombre() + " con una vida de: " + pokemon[pospoke1].getVida());
                System.out.println("El pokemon 2 es: " + pokemon[pospoke2].getNombre() + " con una vida de: " + pokemon[pospoke2].getVida());
                for (int i = 1; i < 4; i++) {
                    if ((pokemon[pospoke1].getVida() > 0) && (pokemon[pospoke2].getVida() > 0)) {
                        pokemon[pospoke2].setVida(pokemon[pospoke2].getVida() - pokemon[pospoke1].getPuntosAtaque());
                        if (pokemon[pospoke2].getVida() <= 0) {
                            round=i;
                            pokemon[pospoke2].setVida(0);
                            pokemon[pospoke2].setEstado(false);
                        }
                        if ((pokemon[pospoke1].getVida() > 0) && (pokemon[pospoke2].getVida() > 0)) {
                            pokemon[pospoke1].setVida(pokemon[pospoke1].getVida() - pokemon[pospoke2].getPuntosAtaque());
                            if (pokemon[pospoke1].getVida() <= 0) {
                                round=i;
                                pokemon[pospoke1].setVida(0);
                                pokemon[pospoke1].setEstado(false);
                            }
                        }
                    }                   
                }
                if(round==0&&(pokemon[pospoke1].getVida()==0||pokemon[pospoke2].getVida()==0)){
                    round=0;
                }else if(round==0&&pokemon[pospoke1].getVida()>0&&pokemon[pospoke2].getVida()>0){
                    round=3;
                }
                
                
                if(pokemon[pospoke1].getVida()<pokemon[pospoke2].getVida()){
                    System.out.println("pokemon 2 gano la batalla en el Round: "+round);
                    r+="<tr><th colspan=\"3\" class=\"title2\">Gano: "+pokemon[pospoke2].getNombre()+"</th></tr>";
                }else{
                    System.out.println("pokemon 1 gano la batalla en el Round: "+round);
                    r+="<tr><th colspan=\"3\" class=\"title2\">Gano: "+pokemon[pospoke1].getNombre()+"</th></tr>";
                }
                System.out.println("El pokemon 1 es: " + pokemon[pospoke1].getNombre() + " con una vida de: " + pokemon[pospoke1].getVida());
                System.out.println("El pokemon 2 es: " + pokemon[pospoke2].getNombre() + " con una vida de: " + pokemon[pospoke2].getVida());
                System.out.println("-----------------------------------------------------------\n");
                
                batalla++;
                r+="</table><br>";
                r+="<table>";
            }
            Reportarpelea(r);

        } catch (Exception e) {
        }

    }
    
    
    public static void Serializaciongeneral(){
        String ruta;
        if(cont==null){
            ruta="Contadores.bin";
            cont=(contadores[]) Recuperar(ruta);
        }
        if(cont==null){
            cont=new contadores[6];
        }
        //recuperacion de la clase Pokemon
        if(pokemon==null){
        ruta="Pokemon.bin";
        pokemon = (Pokemon[]) Recuperar(ruta);  
        }
        if(pokemon==null){
            pokemon=new Pokemon[150];
        }
        
        //recuperacion de la case Entrenadores
        if(entrenador==null){
            ruta = "Entrenador.bin";
            entrenador=(Entrenador[]) Recuperar(ruta);
        }
        if(entrenador==null){
            entrenador= new Entrenador[25];
        }
        
        //recuperando la clase Pokeball
        if(pokeball==null){
            ruta="Pokeball.bin";
            pokeball=(Pokeball[]) Recuperar(ruta);
        }
        
        //recupecion de la clase Gimnasio
        if(gimnasio==null){
            ruta="Gimnasio.bin";
            gimnasio=(Gimnasio[])Recuperar(ruta);
        }
        if(gimnasio==null){
            gimnasio=new Gimnasio[25];
        }
        
        //recuperacion de la clase Alimento
        if(alimento==null){
            ruta="Alimento.bin";
            alimento=(Alimento[])Recuperar(ruta);
        }
        if(alimento==null){
            alimento=new Alimento[15];
        }
        
        //recuperacion de la clase Alimentar que sirve para guardar los datos de las actividades de comida.
        if(alimentos==null){
            ruta="Alimentar.bin";
            alimentos=(Alimentar[]) Recuperar(ruta);
        }
        if(alimentos==null){
            alimentos=new Alimentar[500];
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
    public static void guardarcontadores(){
        cont[0]=new contadores(contadorpokemons);
        cont[1]=new contadores(contadorentrenadores);
        cont[2]=new contadores(contadorpokeballs);
        cont[3]=new contadores(contadorgimnasios);
        cont[4]=new contadores(contadoralimentos);
        cont[5]=new contadores(contadoralimentacion);
    }
    public static void recuperarcontadores(){
        try{
        contadorpokemons=cont[0].getCont();
        contadorentrenadores=cont[1].getCont();
        contadorpokeballs=cont[2].getCont();
        contadorgimnasios=cont[3].getCont();
        contadoralimentos=cont[4].getCont();
        contadoralimentacion=cont[5].getCont();
        }catch(Exception e){
            
        }
    }
    
    
    public static void ReporteEntrenadores(){
        LocalTime hora= LocalTime.now();
        LocalDate fecha=LocalDate.now();
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw = null;//para ir escribiendo el archivo
        String RI,R,RF;
        
        RI="<!DOCTYPE html>"
                +"<head>\n"
                +"<title>Reporte de Entrenadores</title>\n"
                +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                +"<link rel=\"stylesheet\" href=\"CSS/styles.css\">\n"
                +"</head>\n"
                +"<body>\n"
                +"<h1>Reporte Entrenadores</h1>"
                +"<center><img src=\"https://as.com/meristation/imagenes/2019/09/04/noticias/1567575136_299892_1567575816_noticia_normal.jpg\"></img></center>";
        
        
        for (int i = 0; i < contadorentrenadores; i++) {
            RI+="<table>\n";
            RI+="<tr><th colspan=\"3\" class=\"title\">Entrenador "+entrenador[i].getNombre()+"</th></tr>\n";
            for (int j = 0; j < entrenador[i].cantidadpokeballs(); j++) {
                RI+="<tr>\n";
                RI+="<th class=\"tp\">pokeball "+entrenador[i].mostraridball(j)+"</th>\n"
                        +"<td class=\"cont\">"+entrenador[i].nombrePokemon(j)+"</td>"
                        +"<td class=\"cont\">"+entrenador[i].MoV(j)+"</td>";
                RI+="</tr>";
            }

            RI+="</table><br>\n";
        }

        
        RF="<div class=\"bhora\"><center><p>Hora del reporte: "+hora+"</p>\n"
                +"<p>Fecha del reporte: "+fecha+"</p>"+"</center></div>\n"
                +"</body>\n"
                +"</html>\n"
                ;
        

        try {
            fichero=new FileWriter("ReporteEntrenadores.html");
            pw=new PrintWriter(fichero);
            pw.print(RI+RF);
            
            
            fichero.close();
        }catch(Exception e){
            System.out.println("error");
        }
    }
    
    public static void ReporteSalvajes(){
        LocalTime hora= LocalTime.now();
        LocalDate fecha=LocalDate.now();
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw = null;//para ir escribiendo el archivo
        String RI,R,RF;
        
        RI="<!DOCTYPE html>"
                +"<head>\n"
                +"<title>Reporte de Pokemones Salvajes</title>\n"
                +"<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                +"<link rel=\"stylesheet\" href=\"CSS/styles.css\">\n"
                +"</head>\n"
                +"<body>\n"
                +"<h1>Reporte de Pokemons en Estado Salvaje</h1>\n"
                +"<center><img src=\"https://e00-marca.uecdn.es/assets/multimedia/imagenes/2021/01/11/16103766087845.jpg\"></img></center>\n";
        
        R="<table>\n";
        R+="<tr>\n"
                +"<th class=\"tp\">ID</th>\n"
                +"<th class=\"tp\">TIPO</th>\n"
                +"<th class=\"tp\">NOMBRE</th>\n"
                +"<th class=\"tp\">VIDA</th>\n"
                +"<th class=\"tp\">PUNTOS DE ATAQUE</th>\n"
                +"<th class=\"tp\">ESTADO</th>\n"
                +"</tr>\n";
        for (int i = 0; i < contadorpokemons; i++) {
            if (pokemon[i].isCapturado() == false) {
                R += "<tr>\n"
                        + "<td class=\"tp\">" + pokemon[i].getId() + "</th>\n"
                        + "<td class=\"tp\">" + pokemon[i].getTipo() + "</th>\n"
                        + "<td class=\"tp\">" + pokemon[i].getNombre() + "</th>\n"
                        + "<td class=\"tp\">" + Math.floor(pokemon[i].getVida()) + "</th>\n"
                        + "<td class=\"tp\">" + Math.floor(pokemon[i].getPuntosAtaque()) + "</th>\n";
                String MoV;
                if (pokemon[i].isEstado() == true) {
                MoV="Vivo";
                }else{
                    MoV="Muerto";
                }
                R += "<td class=\"tp\">"+MoV+"</th>\n"
                        + "</tr>\n";

            }
        }

        R+="</table>";
        
        RF="<div class=\"bhora\"><center><p>Hora del reporte: "+hora+"</p>\n"
                +"<p>Fecha del reporte: "+fecha+"</p>"+"</center></div>\n"
                +"</body>\n"
                +"</html>\n"
                ;
        

        try {
            fichero=new FileWriter("ReportePokemonesSalvajes.html");
            pw=new PrintWriter(fichero);
            pw.print(RI+R+RF);
            
            
            fichero.close();
        }catch(Exception e){
            System.out.println("error");
        }
    }
    
    public static void ReporteComidas() {
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.now();
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw = null;//para ir escribiendo el archivo
        String RI, R, RF;

        RI = "<!DOCTYPE html>"
                + "<head>\n"
                + "<title>Reporte de Comidas</title>\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "<link rel=\"stylesheet\" href=\"CSS/styles.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Reporte de Comidas</h1>\n"
                + "<center><img class=\"mod\" src=\"https://images.wikidexcdn.net/mwuploads/wikidex/thumb/6/61/latest/20201006045910/EP1119_Pok%C3%A9mon_comiendo.png/300px-EP1119_Pok%C3%A9mon_comiendo.png\"></img></center>\n";

        R = "<table>\n";
        R += "<tr>\n"
                + "<th class=\"tp\">ID ALIMENTO</th>\n"
                + "<th class=\"tp\">NOMBRE ALIMENTO</th>\n"
                + "<th class=\"tp\">ID POKEMON</th>\n"
                + "<th class=\"tp\">NOMBRE POKEMON</th>\n"
                + "<th class=\"tp\">PUNTOS DE VIDA QUE GANO</th>\n"
                + "</tr>\n";
        for (int i = 0; i < contadoralimentacion; i++) {
            R += "<tr>\n"
                    + "<th class=\"tp\">" + alimentos[i].getId() + "</th>\n";
            int posalimento=0;
            for (int j = 0; j < contadoralimentos; j++) {
                int idvar=alimento[j].getId();
                if((alimentos[i].getId())==idvar){
                    posalimento=j;
                }
            }
            R += "<th class=\"tp\">"+alimento[posalimento].getNombre()+"</th>\n"
                    + "<th class=\"tp\">"+alimentos[i].idpokemon()+"</th>\n"
                    + "<th class=\"tp\">"+alimentos[i].nombrepokemon()+"</th>\n"
                    + "<th class=\"tp\">"+Math.round(alimento[posalimento].getVida())+"</th>\n"
                    + "</tr>\n";
        }

        R += "</table>";

        RF = "<div class=\"bhora\"><center><p>Hora del reporte: " + hora + "</p>\n"
                + "<p>Fecha del reporte: " + fecha + "</p>" + "</center></div>\n"
                + "</body>\n"
                + "</html>\n";

        try {
            fichero = new FileWriter("Reportes de Comidas.html");
            pw = new PrintWriter(fichero);
            pw.print(RI + R + RF);

            fichero.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    
    public static void Reportarpelea(String R) {
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.now();
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw = null;//para ir escribiendo el archivo
        String RI, RF;

        RI = "<!DOCTYPE html>"
                + "<head>\n"
                + "<title>Reporte de Peleas</title>\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "<link rel=\"stylesheet\" href=\"CSS/styles.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Reporte de Peleas</h1>\n"
                + "<center><img src=\"https://images.wikidexcdn.net/mwuploads/wikidex/0/0d/latest/20200823150249/EP1123_Pok%C3%A9mon_de_Judith.png\"></img></center>\n";

        

        RF = "<div class=\"bhora\"><center><p>Hora del reporte: " + hora + "</p>\n"
                + "<p>Fecha del reporte: " + fecha + "</p>" + "</center></div>\n"
                + "</body>\n"
                + "</html>\n";

        try {
            fichero = new FileWriter("Reporte de Peleas.html");
            pw = new PrintWriter(fichero);
            pw.print(RI + R + RF);

            fichero.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    
    public static void ReportetopPokemon() {
        LocalTime hora= LocalTime.now();
        LocalDate fecha=LocalDate.now();
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw = null;//para ir escribiendo el archivo
        String RI,R,RF;
        double[] ataques = new double[contadorpokemons];
        RI = "<!DOCTYPE html>"
                + "<head>\n"
                + "<title>Reporte Top 5 pokemons con Mayor Ataque</title>\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "<link rel=\"stylesheet\" href=\"CSS/styles.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Reporte Top 5 pokemons con Mayor Ataque</h1>\n"
                + "<center><img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png\"></img></center>\n";

        for (int i = 0; i < contadorpokemons; i++) {
            ataques[i] = pokemon[i].getPuntosAtaque();
        }
        for (int i = 0; i < ataques.length - 1; i++) {
            for (int j = 0; j < ataques.length - 1; j++) {
                if (ataques[j] < ataques[j + 1]) {
                    double tmp = ataques[j + 1];
                    ataques[j + 1] = ataques[j];
                    ataques[j] = tmp;
                }
            }
        }
        
        double[] top=new double[5];
        for (int i = 0; i < 5; i++) {
            top[i]=ataques[i]; 
        }
        
        R="<table>\n";
        R+="<tr>\n"
                +"<th class=\"tp\">ID</th>\n"
                +"<th class=\"tp\">TIPO</th>\n"
                +"<th class=\"tp\">NOMBRE</th>\n"
                +"<th class=\"tp\">PUNTOS DE ATAQUE</th>\n"
                +"</tr>\n";
        for (int i = 0; i < top.length; i++) {
            for (int j = 0; j < contadorpokemons; j++) {
                if (pokemon[j].getPuntosAtaque() == top[i]) {
                    R += "<tr>\n"
                        + "<td class=\"tp\">" + pokemon[j].getId() + "</th>\n"
                        + "<td class=\"tp\">" + pokemon[j].getTipo() + "</th>\n"
                        + "<td class=\"tp\">" + pokemon[j].getNombre() + "</th>\n"
                        + "<td class=\"tp\">" + Math.floor(pokemon[j].getPuntosAtaque()) + "</th>\n";
                }
            }

        }
        R+="</table>";

        RF = "<div class=\"bhora\"><center><p>Hora del reporte: " + hora + "</p>\n"
                + "<p>Fecha del reporte: " + fecha + "</p>" + "</center></div>\n"
                + "</body>\n"
                + "</html>\n";
        
        try {
            fichero = new FileWriter("Reporte Top 5 Pokemons con mas Ataque.html");
            pw = new PrintWriter(fichero);
            pw.print(RI + R + RF);

            fichero.close();
        } catch (Exception e) {
            System.out.println("error");
        }
    }
    
    public static void ReportetopAlimentos(){
       LocalTime hora= LocalTime.now();
        LocalDate fecha=LocalDate.now();
        FileWriter fichero = null;//para crear el archivo
        PrintWriter pw = null;//para ir escribiendo el archivo
        String RI,R,RF;
        double[] vida = new double[contadoralimentos];
        RI = "<!DOCTYPE html>"
                + "<head>\n"
                + "<title>Reporte Top 5 Alimentos que mas Vida Regeneran</title>\n"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\n"
                + "<link rel=\"stylesheet\" href=\"CSS/styles.css\">\n"
                + "</head>\n"
                + "<body>\n"
                + "<h1>Reporte Top 5 Alimentos que mas Vida Regeneran</h1>\n"
                + "<center><img src=\"https://upload.wikimedia.org/wikipedia/commons/thumb/9/98/International_Pok%C3%A9mon_logo.svg/1200px-International_Pok%C3%A9mon_logo.svg.png\"></img></center>\n";

        for (int i = 0; i < contadoralimentos; i++) {
            vida[i] = alimento[i].getVida();
        }

        for (int i = 0; i < vida.length - 1; i++) {
            for (int j = 0; j < vida.length - 1; j++) {
                if (vida[j] < vida[j + 1]) {
                    double tmp = vida[j + 1];
                    vida[j + 1] = vida[j];
                    vida[j] = tmp;
                }
            }
        }
        
        double[] top=new double[5];
        for (int i = 0; i < 5; i++) {
            top[i]=vida[i]; 
        }
        
        R="<table>\n";
        R+="<tr>\n"
                +"<th class=\"tp\">ID DEL ALIMENTO</th>\n"
                +"<th class=\"tp\">NOMBRE</th>\n"
                +"<th class=\"tp\">PUNTOS DE VIDA QUE GENERA</th>\n"
                +"</tr>\n";
        for (int i = 0; i < top.length; i++) {
            for (int j = 0; j < contadoralimentos; j++) {
                if (alimento[j].getVida() == top[i]) {
                    R += "<tr>\n"
                        + "<td class=\"tp\">" + alimento[j].getId() + "</th>\n"
                        + "<td class=\"tp\">" + alimento[j].getNombre() + "</th>\n"
                        + "<td class=\"tp\">" + Math.floor(alimento[j].getVida()) + "</th>\n";
                }
            }

        }
        R+="</table>";

        RF = "<div class=\"bhora\"><center><p>Hora del reporte: " + hora + "</p>\n"
                + "<p>Fecha del reporte: " + fecha + "</p>" + "</center></div>\n"
                + "</body>\n"
                + "</html>\n";
        
        try {
            fichero = new FileWriter("Reporte Top 5 Alimentos que mas Vida Regeneran.html");
            pw = new PrintWriter(fichero);
            pw.print(RI + R + RF);

            fichero.close();
        } catch (Exception e) {
            System.out.println("error");
        } 
    }
}

    

