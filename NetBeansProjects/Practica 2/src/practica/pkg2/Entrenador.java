
package practica.pkg2;

import java.io.Serializable;

public class Entrenador implements Serializable {
    private int id;
    private String nombre;
    private Pokeball[] pokeballs;
    private int contador;

    public Entrenador(int id, String nombre){
        this.id=id;
        this.nombre=nombre;
        this.pokeballs=new Pokeball[5];
        this.contador=0;
        
    }
    
    
    
    
    
    //gets y sets apartir de aca
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void mostrarentrenador(){
        System.out.println("id: "+id+" pertenece al entrenador: "+nombre);
    }
    
    public void asignarpokeball(Pokeball pokeball){
        
        if(contador<pokeballs.length){
            this.pokeballs[contador]=pokeball;
            contador++;
        }

    }
    public void mostrardatos(){
        mostrarentrenador();
        System.out.println("tiene asignados lo siguiente:");
        for (int i = 0; i < contador; i++) {
            
            pokeballs[i].mostrardatos();
        }
    }
    
    public int mostraridball(int i){
        
        return pokeballs[i].getId();
        
    }
    
    public int cantidadpokeballs(){
        
        return contador;        
    }
    public String nombrePokemon(int i){
        
        return pokeballs[i].nombrePokemon();      
    }
    public String MoV(int i){
        return pokeballs[i].MoV();
        
    }

}
