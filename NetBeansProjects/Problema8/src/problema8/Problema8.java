
package problema8;

public class Problema8 {
    public static void main(String[] args) {
 //Creamos el objeto
        Aula aula=new Aula();
         
        //Indicamos si se puede dar la clase
        if(aula.darClase()){
            aula.notas();
        }
         
    }
     
}