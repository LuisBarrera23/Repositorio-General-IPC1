
package tarea.pkg1;
import java.util.Scanner;

public class Tarea1 {

        public static void main(String[] args) {
            
            //variable del menu principal
            int menu_principal = 0;
            Scanner menu= new Scanner (System.in);
            Scanner numero= new Scanner (System.in);
            
            do{
                try{
                System.out.println("---MENU PRINCIPAL---");
                System.out.println("1.  VERIFICACION DE NUMERO");
                System.out.println("2.  POTENCIACION");
                System.out.println("3.  NUMEROS PRIMOS");
                System.out.println("4.  CALCULADORA BASICA");
                System.out.println("5.  SALIR");
                System.out.println("Ingrese el numero de la opcion deseada: ");
                menu_principal=menu.nextInt();
                
                switch(menu_principal){
                    
                    case 1:
                        System.out.println("---VERIFICACION DE NUMERO---");
                        Verificacion_numero();
                    break;
                    
                    
                    case 2:
                        System.out.println("---POTENCIACION---");
                        potenciacion();
                    break;
                    
                    
                    case 3:
                        System.out.println("---NUMEROS PRIMOS---");
                        Numeros_primos();
                    break;
                    
                    
                    case 4:
                        System.out.println("---CALCULADORA BASICA---");
                        Calculadora();
                    break;
                    
                    
                    case 5:
                        System.out.println("---GRACIAS POR UTILIZAR EL PROGRAMA---");
                    break;
                    
                    default:
                        System.out.println("Esta opcion no esta en el menú");
                        System.out.println("Por favor vuelva a intentarlo");
                    break;
                } // final del switch
                
                
                } //final del try
                catch(Exception e){
                    System.out.println("Ingreso una letra o decimal vuelva a intentarlo");
                    menu.nextLine();

                        
                        }//final del catch
                    
                       
                    
                
            }while(menu_principal !=5);
            
            
            }
        
public static void potenciacion(){
        Scanner numero = new Scanner(System.in);
        int base, limite, resultado, i;
        int retorno = 0;
        do {
            try {
                System.out.println("ingrese el numero base");
                base = numero.nextInt();
                System.out.println("ingrese el numero limite de potencia");
                limite = numero.nextInt();
                resultado = 1;

                for (i = 1; i <= limite; i++) {
                    resultado = resultado * base;
                    System.out.println(base + "^" + i + "=" + resultado);
                }

                do {

                    System.out.println("---¿Desea volver a calcular otra potencia?---");
                    System.out.println("1.  sí");
                    System.out.println("2.  no");
                    System.out.println("Ingrese el numero de la opcion");
                    retorno = numero.nextInt();
                } while (retorno > 2 || retorno < 1);
            } catch (Exception e) {
                System.out.println("Ingreso un numero decimal o letras");
                System.out.println("por favor, vuelva a intentarlo");
                numero.nextLine();
            }
        } while (retorno != 2);
    }
            
public static void Verificacion_numero(){
        Scanner numero = new Scanner(System.in);

        int num, resultado;
        System.out.println("Ingrese un numero que desee verificar");
        num = numero.nextInt();
        if (num > 0) {
            if (num % 2 == 0) {
                System.out.println("El numero ingresado es par");
            } else {
                System.out.println("El numero es impar");
            }
        } else {
            System.out.println("Solo se aceptan numeros enteros");
            Verificacion_numero(); // para no salir al menu principal de nuevo 
        }

    }

public static void Numeros_primos(){
        Scanner numeros = new Scanner(System.in);
        int n_inicial, n_final, resultado, primos;
        primos = 0;
        resultado = 0;

        System.out.println("Debe asignar un rango de numeros para verificar");
        System.out.println("ingrese el numero menor del rango");
        n_inicial = numeros.nextInt();
        System.out.println("ingrese el numero mayor del rango");
        n_final = numeros.nextInt();

        if (n_inicial > 0 && n_final > 0 && n_inicial < n_final) {
            for (int i = n_inicial; i <= n_final; i++) {

                for (int h = 1; h <= i; h++) {
                    if (i % h == 0) {
                        resultado = resultado + 1;
                    }
                }

                if (resultado == 2) {
                    primos = primos + 1;
                    resultado = 0;
                } else {
                    resultado = 0;
                }

            }
            System.out.println("En el intervalo dado hay: " + primos + " numeros primos");
        }
        if (n_inicial < 0 || n_final < 0) {
            System.out.println("NO se aceptan numeros negativos");
        }
        if (n_inicial > n_final) {
            System.out.println("El numero inicial es mayor que el final");
        }

    }



public static void Calculadora(){
        Scanner numeros = new Scanner(System.in);
        int opcion;
        do {
            System.out.println("----MENU CALCULADORA BASICA----");
            System.out.println("1.  SUMA");
            System.out.println("2.  RESTA");
            System.out.println("3.  MULTIPLICACION");
            System.out.println("4.  DIVISION");
            System.out.println("5.  REGRESAR AL MENU PRINCIPAL");
            opcion = numeros.nextInt();
            switch (opcion) {
                case 1:
                    suma();
                    break;
                case 2:
                    resta();
                    break;
                case 3:
                    multiplicacion();
                    break;
                case 4:
                    division();
                    break;
                case 5:
                    System.out.println("Regresando al menu principal");
                    break;
            }

        } while (opcion != 5);

    }

public static void suma(){
    Scanner numero= new Scanner(System.in);
    double numero1,numero2;
    
    System.out.println("Ingrese el primer numero:");
    numero1=numero.nextDouble();
    System.out.println("Ingrese el segundo numero:");
    numero2=numero.nextDouble();
    
    System.out.println("La suma de ambos numeros es:  " +(numero1 + numero2));
}

public static void resta(){
    Scanner numero= new Scanner(System.in);
    double numero1,numero2;
    
    System.out.println("Ingrese el primer numero:");
    numero1=numero.nextDouble();
    System.out.println("Ingrese el segundo numero:");
    numero2=numero.nextDouble();
    
    System.out.println("La resta de ambos numeros es:  " +(numero1 - numero2));
}

public static void multiplicacion(){
    Scanner numero= new Scanner(System.in);
    double numero1,numero2;
    
    System.out.println("Ingrese el primer numero:");
    numero1=numero.nextDouble();
    System.out.println("Ingrese el segundo numero:");
    numero2=numero.nextDouble();
    
    System.out.println("La multiplicación de ambos numeros es:  " +(numero1 * numero2)); 
}

public static void division(){
    Scanner numero = new Scanner(System.in);
    double numero1, numero2;
    int validacion = 0;
    do {
        System.out.println("Ingrese el primer numero:");
        numero1 = numero.nextDouble();
        System.out.println("Ingrese el segundo numero:");
        numero2 = numero.nextDouble();
        if (numero2 > 0 || numero2 < 0) {
            System.out.println("La división de ambos numeros es:  " + (numero1 / numero2));
            validacion = 1;
        }
        if (numero2 == 0) {
            System.out.println("El segundo numero debe de ser diferente de 0 (cero)");
            System.out.println("Vuelva a ingresar los numeros");
            validacion = 0;
        }
    } while (validacion != 1);
}
}
