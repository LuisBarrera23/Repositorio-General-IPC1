
package practica.pkg2;

import java.io.Serializable;

public class Alimentar implements Serializable{
    private int id;
    private int idpokemon;
    private Pokemon pokemon;

    public Alimentar(int id, int idpokemon) {
        this.id = id;
        this.idpokemon = idpokemon;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getIdpokemon() {
        return idpokemon;
    }
    public void setIdpokemon(int idpokemon) {
        this.idpokemon = idpokemon;
    }
   
    public void asignarpokemon(Pokemon pokemon){
        this.pokemon=pokemon;
    }
    public void mostraralimentacion(){
        System.out.println("Actividad de alimento: "+id);
        System.out.println("Al pokemon con el id: " + idpokemon + " llamado: " + pokemon.getNombre());
    }
    public int idpokemon(){
        return pokemon.getId();
        
    }
    
    public String nombrepokemon(){
        return pokemon.getNombre();
    }
    
}
