package paquete;

import java.text.DecimalFormat;

//Clase donde tendremos algunas herramientas que utilizaremos para la ejecucción del programa
public class Herramientas {
    
    public static String espaciosNombreMarcador(String nombre)
    {
        //Método para poner tantos espacios como necesite para que quede centrado
        int cantEspacios = 10 - nombre.length();
        String espacios = "";

        for(int i = 0; i < cantEspacios; i++)
        {
            espacios += " ";
        }

        nombre = nombre + espacios;

        return nombre;
    }

    public static String customFormat (String pattern, double value) 
    {
        //Método para dar formato a números
        DecimalFormat myFormatter = new DecimalFormat(pattern);
        String output = myFormatter.format(value);
        return output;
    }
    
    public static void delay(int seg)
    {
        try{
            Thread.sleep(seg * 1000);
            }
            catch (Exception e) {
                System.out.println(e);
            }
    }

    public static void limpiarPantalla()
    {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static int[] tiempo(long fin, long principio)
    {
        long milisegundos=0;

        milisegundos = fin - principio;

        int tiempoSegundos = (int) milisegundos/1000;

        int tiempo[] = new int [2];
        if(tiempoSegundos >= 60)
        {
            tiempo[0] = tiempoSegundos/60;
            tiempo[1] = tiempoSegundos%60;
        }
        else
        {
            tiempo[0] = 0; //Minutos
            tiempo[1] = tiempoSegundos; //Segundos 
        }

        return tiempo;
    }

    public static String convertirAPoker(int cartaVirtual)
    {
        //Método para convertir de entero a String y añadir color
        String simbolo="";
        String carta="";
        String color="";

        int palo = cartaVirtual/100;

        switch(palo)
        {
            case 1 -> {simbolo = "\u2663"; color=PintarTexto.ANSI_BLACK;}
            case 2 -> {simbolo = "\u2665"; color=PintarTexto.ANSI_RED;}
            case 3 -> {simbolo = "\u2666"; color=PintarTexto.ANSI_RED;}
            case 4 -> {simbolo = "\u2660"; color=PintarTexto.ANSI_BLACK;}
        }

        String numero;

        switch(cartaVirtual - (palo*100))
        {
            case 1 -> {numero = "A";}
            case 11 -> {numero = "J";}
            case 12 -> {numero = "Q";}
            case 13 -> {numero = "K";}
            default -> {numero = String.valueOf(cartaVirtual - (palo*100));}
        }

        if(numero.equals("10"))
        {
            if(simbolo == "\u2660" || simbolo == "\u2663")
            {
                carta = PintarTexto.ANSI_WHITE_BACKGROUND + color + numero + simbolo + PintarTexto.ANSI_RESET;    
            }
            else
            {
                carta = PintarTexto.ANSI_WHITE_BACKGROUND + color + numero + simbolo + PintarTexto.ANSI_RESET;
            }
        }
        else
        {
            if(simbolo == "\u2660" || simbolo == "\u2663")
            {
                carta = PintarTexto.ANSI_WHITE_BACKGROUND + color + numero + simbolo + " " + PintarTexto.ANSI_RESET;    
            }
            else
            {
                carta = PintarTexto.ANSI_WHITE_BACKGROUND + color + numero + simbolo + " " + PintarTexto.ANSI_RESET;
            }
        }
        
        return carta;
    }
}
