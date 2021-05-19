package examen.pkgfinal;

public class ExamenFinal {
//Luis Angel Barrera Velásquez 
// Carnet: 202010223
    public static void main(String[] args) {
        C e=new E();
        System.out.println(e);
    }
    
}

interface A {public float a();}
abstract class B {public abstract void b();}
abstract class C implements A{
    public abstract int c();
    public void d(){
        System.out.println("Hola");
    }
}
class F{
    public F(){}
    public String toString(){return "f";}
}

class G{
    private F f;
    public G(){f=new F();}
    public String toString(){return f.toString();}
}

class E extends C{
    private G g;
    public E(){
        g =new G();
    }
    public int c(){
        return 1;
    }
    public float a(){
        return 0.1f;
    }
    public String toString(){
        return "c: "+c()+"; a: "+a()+"; g: "+g;
    }
}
