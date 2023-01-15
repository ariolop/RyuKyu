package paquete;

import java.util.Scanner;

//Clase donde se encuentra el main
public class Principal {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) 
    {
        String marcador[][]; //Nombre(0), Puntacion(1), Tiempo(2), Dificultad(3)
        marcador = new String[1][4];

        Marcador.inicializarMarcador(marcador);

        int respuesta;
        
        //Valores predeterminados de las cartas a mostrar
        int filas = 3;
        int columnas = 4;

        //Mostramos el menu principal
        do{
            Herramientas.limpiarPantalla();
            System.out.println("MENU PRINCIPAL");
            System.out.println();
            System.out.println("1. Empezar la partida ");
            System.out.println("2. Modificar la cantidad de filas a mostrar" + "        " + "(" + filas + ")");
            System.out.println("3. Modificar la cantidad de columnas a mostrar" + "     " + "(" + columnas + ")");
            System.out.println("4. Mostrar el marcador actual");
            System.out.println("5. Salir");
            respuesta = sc.nextInt();

            switch(respuesta)
            {
                case 1 -> {marcador = Jugar.jugarPartida(filas, columnas, marcador);} //Jugamos la partida y devolvemos el nuevo marcador
                case 2 -> {filas = modificarFilas();} //Modifica la cantidad de filas a mostrar
                case 3 -> {columnas = modificarColumnas();} //Modifica la cantidad de mazos/columnas a jugar
                case 4 -> {Marcador.mostrarMarcador(marcador);} //Muestra el marcador actual
                case 5 -> {System.out.println("Gracias por jugar");} //Sale de la ejecución (termina el do while)
                default -> {System.out.println("ERROR: Opcion no encontrada");Herramientas.delay(2);} //Opcion no encontrada
            }
            
        }while(respuesta != 5);

    }

    public static int modificarFilas()
    {
        int filas;

        do{
            System.out.println("Introduce la cantidad de filas: ");
            filas = sc.nextInt();
        }while(filas > 3 || filas < 1); //La cantidad de fila estará entre 1 y 3
        
        return filas;
    }

    public static int modificarColumnas()
    {
        int columnas;

        do{
            System.out.println("Introduce la cantidad de columnas: ");
            columnas = sc.nextInt();
        }while(columnas > 4 || columnas < 1); //La cantidad de fila estará entre 1 y 4
        
        return columnas;
    }   
}