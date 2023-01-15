package paquete;

import java.util.Arrays;

//Clase donde inicializamos matrices y vectores al valor que necesitemos
public class Inicializar {

    public static void resultadoManoComprobada(String resultadoManoComprobada[])
    {
        Arrays.fill(resultadoManoComprobada,"###");
    }

    public static void cartasRestantes(int restantes[])
    {
        int tamañoMazos;
        int cantidadMazos = restantes.length;
        
        if(cantidadMazos == 3)
        {
            tamañoMazos = 52/cantidadMazos;   
            Arrays.fill(restantes, tamañoMazos);
            restantes[cantidadMazos-1] = tamañoMazos+1;
        }
        else
        {
            tamañoMazos = 52/cantidadMazos;   
            Arrays.fill(restantes, tamañoMazos); 
        }

    }
    
    public static void CartasElegir(String cartas[][])
    {
        int filas = cartas.length;
        int columnas = cartas[0].length;

        for(int i = 0; i < filas;i++)
        {
            for(int j = 0; j < columnas;j++)
            {
                cartas[i][j] = "";
            }
        }
    }

    public static void CartasElegir(int cartasVirtual[][])
    {
        int filas = cartasVirtual.length;
        int columnas = cartasVirtual[0].length;

        for(int i = 0; i < filas;i++)
        {
            for(int j = 0; j < columnas;j++)
            {
                cartasVirtual[i][j] = ' ';
            }
        }
    }

    public static void MapaJuego(String zonaJuego[][])
    {
        int filas = zonaJuego.length;
        int columnas = zonaJuego[0].length;
        

        for(int i = 0; i < filas;i++)
        {
            for(int j = 0; j < columnas;j++)
            {

                zonaJuego[i][j] = " - ";
            }
        }
    }

    public static void MapaJuego(int zonaJuegoVirtual[][])
    {
        int filas = zonaJuegoVirtual.length;
        int columnas = zonaJuegoVirtual[0].length;
        

        for(int i = 0; i < filas;i++)
        {
            for(int j = 0; j < columnas;j++)
            {
                zonaJuegoVirtual[i][j] = 0;
            }
        }
    }

    public static void Baraja(int baraja[])
    {
        int num;

        num=1;
        for(int i = 0; i < 13; i++) //Trebol
        {
            baraja[i] = 100+num;
            num++;
        }

        num=1;
        for(int i = 13; i < 26; i++) //Corazon
        {
            baraja[i] = 200+num;
            num++;
        }

        num=1;
        for(int i = 26; i < 39; i++) //Diamantes
        {
            baraja[i] = 300+num;
            num++;
        }
        
        num=1;
        for(int i = 39; i < 52; i++) //Picas
        {
            baraja[i] = 400+num;
            num++;
        }
    }
}