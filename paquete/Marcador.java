package paquete;

import java.util.Arrays;
import java.util.Scanner;

//Clase donde encontraremos métodos relacionados con el marcador
public class Marcador {
    static Scanner teclado = new Scanner(System.in);

    public static void inicializarMarcador(String marcador[][])
    {
        //La primera fila de nuestro marcador tendrá el título de cada columna
        marcador[0][0] = "Nombre    ";
        marcador[0][1] = "Puntos";
        marcador[0][2] = "Tiempo";
        marcador[0][3] = "Dificultad";
    }
    
    public static void mostrarMarcador(String marcador[][])
    {
        //Método para mostrar el marcador de las partidas jugadas
        Herramientas.limpiarPantalla();

        System.out.println("Marcador");

        for(int i = 0; i < marcador.length;i++)
        {
            System.out.print("| " + marcador[i][0] + " | ");
            System.out.print(marcador[i][1] + " | "); 
            System.out.print(marcador[i][2] + " | "); 
            System.out.print(marcador[i][3] + " |");
            System.out.println();
        }

        System.out.println();

        //Pausa para leer la información, introducir cualquier cosa para volver al menú
        System.out.println("Presiona Enter para continuar...");
        String seguir = teclado.nextLine();
        
        }

    public static String[][] añadirMarcador(String puntuacionJugador[], String marcador[][])
    {   
        //Método para agregar una nueva puntuación al marcador

        //Cuando ampliamos una fila deberemos de ponerle las columnas correspondientes sino da fallo "null"
        marcador = Arrays.copyOf(marcador, marcador.length+1);
        marcador[marcador.length-1] = Arrays.copyOf(marcador[0], marcador[0].length);

        for(int i = 0; i < puntuacionJugador.length;i++)
        {
            marcador[marcador.length-1][i] = puntuacionJugador[i];
        }

        return marcador;
    }
}
