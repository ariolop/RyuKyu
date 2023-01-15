package paquete;

import java.util.Arrays;
import java.util.Scanner;

//Clase donde encontraremos métodos relacionados el funcionamiento de la partida
public class Jugar {
    static Scanner sc = new Scanner(System.in);

    public static String[][] jugarPartida(int filas, int columnas, String marcador[][])
    {
        int zonaJuegoVirtual[][];
        String zonaJuego[][];
        int baraja[];
        int cartasRestantes[];
        boolean manosComprobadas[];
        String resultadoManoComprobada[];

        String cartasEligir[][];
        int cartasEligirVirtual[][];
        int jugadaJugador[];
        String resultadoFinalJugador[]; //Nombre(0), Puntacion(1), Tiempo(2), Dificultad(3)
        int puntosFinales = 0;
        
        resultadoManoComprobada = new String[12]; //Filas = 0-4 | Columnas = 5-9 | Diagonales = 10-11
        manosComprobadas = new boolean [12]; //Filas = 0-4 | Columnas = 5-9 | Diagonales = 10-11
        baraja = new int [52];
        zonaJuego = new String [5][5];
        zonaJuegoVirtual = new int [5][5];
        cartasRestantes = new int [columnas];
        resultadoFinalJugador = new String [4];
        cartasEligir = new String [filas][columnas];
        cartasEligirVirtual = new int [filas][columnas];
        jugadaJugador = new int [2]; // 0 -> Carta | 1 -> Columna

        Inicializar.cartasRestantes(cartasRestantes); //Inicializa con la cantidad 
        Inicializar.Baraja(baraja); // 100 -> Trebol | 200 -> Corazon | 300 -> Diamantes | 400 -> Picas
        Inicializar.MapaJuego(zonaJuego); //Inicializa con " - "
        Inicializar.MapaJuego(zonaJuegoVirtual); //Inicializa con 0
        Inicializar.CartasElegir(cartasEligir); //Inicializa con ""
        Inicializar.CartasElegir(cartasEligirVirtual); //Inicializa con ''
        Inicializar.resultadoManoComprobada(resultadoManoComprobada); //Inicializa con "     " (5 espacios)

        //EMPIEZA LA PARTIDA

        long principio = System.currentTimeMillis();  //Empieza a contar el tiempo
        for(int i = 0; i < 25; i++)
        {
            Herramientas.limpiarPantalla();

            //Calculamos las cartas a elegir y las eliminamos de la baraja            
            baraja = calcularCartasElegir(cartasEligirVirtual, cartasEligir, baraja, cartasRestantes); 

            //Mostrar la interfaz
            Show.mostrarInterfaz(cartasEligir, cartasRestantes, zonaJuego, puntosFinales, resultadoManoComprobada); 

            //Pedimos la jugada al usuario 
            //"cartasElegirVirtual" es para saber si al usuario le quedan cartas o no.
            jugadaJugador(jugadaJugador, zonaJuego, cartasEligirVirtual, cartasRestantes); 

            //Modificamos el valor tanto en el tablero como en las cartas a elegir
            modificarValor(zonaJuego, zonaJuegoVirtual, jugadaJugador, cartasEligirVirtual, cartasEligir); 
        
            //Comprobamos si hay alguna mano para comprobar, y se hace la/s comprobaciones correspondientes
            puntosFinales = Comprobar.comprobarSiEstaRellena(zonaJuegoVirtual, puntosFinales, manosComprobadas, resultadoManoComprobada);
        }
        long fin = System.currentTimeMillis(); //Termina de contar el tiempo

        Herramientas.limpiarPantalla();//TERMINA LA PARTIDA
        System.out.println("*******FIN DE LA PARTIDA*******");

        //Se muestra el resultado final del tablero
        Show.mostrarTablero(zonaJuego, puntosFinales, resultadoManoComprobada);
        
        //Calculo del tiempo de la partido
        int tiempo[] = new int [2];
        tiempo = Herramientas.tiempo(fin, principio);

        //Introducimos los valores en el resultado final para posteriormente insertarlo en el marcacdor
        resultadoFinalJugador[2] = String.valueOf(Herramientas.customFormat("00",tiempo[0]) + ":" + Herramientas.customFormat("00", tiempo[1]) + " "); //Calculamos el tiempo que ha tardado
        resultadoFinalJugador[1] = String.valueOf(Herramientas.customFormat("00000",puntosFinales) + " "); //Hacemos las comprobaciones de las cartas
        resultadoFinalJugador[0] = Herramientas.espaciosNombreMarcador(pedirNombreJugador()); //Pedimos el nombre y le ponemos el formato que queremos
        resultadoFinalJugador[3] = " " + "F:" + filas + " " + "C:" + columnas + "  "; //Introducimos la dificultad de la partida

        marcador = Marcador.añadirMarcador(resultadoFinalJugador, marcador);

        return marcador;
    }

    public static int[] calcularCartasElegir(int cartasVirtual[][], String cartasUsuario[][], int baraja[], int restantes[])
    {
        //Método donde sacaremos las cartas a elegir de los mazos y colocaremos "---" cuando no se puedan escoger más cartas

        int filas = cartasUsuario.length;
        int columnas = cartasUsuario[0].length;
        int numAleatorio;

        for(int i = 0; i < filas;i++)
        {
            for(int j = 0; j < columnas;j++)
            {
                if(cartasUsuario[i][j].isBlank()) //Si hay espacios en blanco, significa que deberemos de sacar una nueva carta del mazo
                {
                    if(restantes[j] == 0) //Si no hay cartas...
                    {
                        cartasVirtual[i][j] = 0;

                        cartasUsuario[i][j] = "---";
                    }
                    else
                    {
                        //Robamos de la baraja, reducimos la baraja y restamos uno a las cartas restantes del mazo correspondiente
                        numAleatorio = (int) (Math.random()*(baraja.length));

                        cartasVirtual[i][j] = baraja[numAleatorio];

                        cartasUsuario[i][j] = Herramientas.convertirAPoker(baraja[numAleatorio]);

                        baraja[numAleatorio] = baraja[baraja.length - 1];
                        baraja = Arrays.copyOf(baraja, baraja.length - 1);

                        restantes[j] -= 1; 
                    }
                }
            }
        }

        return baraja;
    }

    public static void jugadaJugador(int jugadaJugador[], String zonaJuego[][], int cartasEligir[][], int restantes[])
    {
        //Método para pedir al usuario la carta y la columna donde la quiere colocar
        //Cartas -> Se controla que el (número introducido-1) esté entre 0 y las columnas, y que la cartaVirtual que escoja no sea un 0 (significa que le quedan cartas)
        //columnaTablero -> Se controla que la columna donde queramos colocar la carta no esté llena

        //jugadaJugador: 0 -> Carta | 1 -> Columna
        int columnas = cartasEligir[0].length;
        int filas = cartasEligir.length;


        do{
            System.out.println("Elige la carta que quieres colocar (1-" + columnas + "):" );
            jugadaJugador[0] = sc.nextInt()-1;
        }while(jugadaJugador[0] < 0 || jugadaJugador[0] >= columnas || cartasEligir[filas-1][jugadaJugador[0]] == 0);

        Boolean columnaLlena = false;
        do{ //Primero controlamos que el (número introducido-1) se encuentra entre 0-4
            columnaLlena = false;
            do{
                System.out.println("Elige la columna donde quieres poner la carta (1-5):");
                jugadaJugador[1] = sc.nextInt()-1;
            }while(jugadaJugador[1] < 0 || jugadaJugador[1] > 4);
            
            //Comprobaremos si la columna está llena
            if(zonaJuego[0][jugadaJugador[1]] != " - ")
            {
                columnaLlena = true;
            }

        }while(columnaLlena == true);

    }

    public static void modificarValor(String zonaJuego[][], int zonaJuegoVirtual[][], int jugadaJugador[], int cartasElegirVirtual[][], String cartasEligir[][])
    {
        //Método para modificar los valores de cartasElegirVirtual, cartasElegir, zonaJuego y zonaJuegoVirtual

        //jugadaJugador: 0 -> Carta | 1 -> Columna 
        String cartaSeleccionada;
        int cartaSeleccionadaVirtual;
        int filas = cartasEligir.length - 1;

        //Seleccionamos la carta que ha elegido el jugador mediante el vector "jugadaJugador"
        cartaSeleccionada = cartasEligir[filas][jugadaJugador[0]];
        cartaSeleccionadaVirtual = cartasElegirVirtual[filas][jugadaJugador[0]];

        //Modificamos cartas Elegir
        for(int i = 1; i < filas+1; i++)
        {
            cartasEligir[filas-i+1][jugadaJugador[0]] = cartasEligir[filas-i][jugadaJugador[0]];
        }

        cartasEligir[0][jugadaJugador[0]] = " ";

        //Modificamos cartas Elegir virtual
        for(int i = 1; i < filas+1; i++)
        {
            cartasElegirVirtual[filas-i+1][jugadaJugador[0]] = cartasElegirVirtual[filas-i][jugadaJugador[0]];
        }

        cartasElegirVirtual[0][jugadaJugador[0]] = ' ';

        //Modificamos zona juego
        int filaBuena = 0; 

        //Buscamos la fila buena para colocar la carta
        while((filaBuena < 5) && zonaJuego[filaBuena][jugadaJugador[1]] == " - ") //Si fila == 5, no hace la otra parte y por eso no peta, siendo "fila" mayor que las dimensiones de "zonaJuego"
        {
            filaBuena++;
        }

        zonaJuego[filaBuena-1][jugadaJugador[1]] = cartaSeleccionada;

        //Modificamos zona juego virtual
        zonaJuegoVirtual[filaBuena-1][jugadaJugador[1]] = cartaSeleccionadaVirtual;
    }

    public static String pedirNombreJugador()
    {
        //Método para pedir el nombre del jugador para introducirlo en el marcador
        //El nombre tendrá desde 1 hasta 10 caractéres (para que aparezca centrado en el marcador)
        String nombre;

        do
        {    
            System.out.println("Escribe tu nombre: ");
            nombre = sc.next();
        }while(nombre.length() > 10 || nombre.length() < 1);
        
        return nombre;
    }
}