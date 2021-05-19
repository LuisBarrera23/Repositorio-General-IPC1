package practica.pkg2;

import java.io.Serializable;

public class Pokeball implements Serializable{

    /**
     * @return the pokemon
     */
    public Pokemon getPokemon() {
        return pokemon;
    }

    private int Id;
    private String Tipo;
    private Pokemon pokemon;

    public Pokeball(int Id, String Tipo) {
        this.Id = Id;
        this.Tipo = Tipo;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String Tipo) {
        this.Tipo = Tipo;
    }

    public void mostrarpokeball() {
        System.out.println("id: " + Id + "\t" + "pokeball tipo: " + Tipo);
    }
    
    public void asignarpokemon(Pokemon pokemon){
        this.pokemon=pokemon;
    }
    
    public void mostrardatos(){
        System.out.println("Pokebola con id: "+Id+" = "+Tipo);
        System.out.println("Pokemon con id "+getPokemon().getId()+" = "+getPokemon().getNombre());
        }
    
    public String nombrePokemon(){
        return getPokemon().getNombre();
    }
    
    public String MoV(){
        boolean Estado;
        Estado=getPokemon().isEstado();
        String estado;
        if (Estado == true) {
            estado = "Vivo";
        } else {
            estado = "Muerto";
        }
        return estado;
    }
    

    
    
    

}
