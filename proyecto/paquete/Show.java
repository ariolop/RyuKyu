package paquete;

//Clase donde encontraremos métodos para mostrar.
public class Show {
    
    public static void mostrarInterfaz(String cartasEligir[][], int cartasRestantes[], String zonaJuego[][], int puntosFinales, String resultadoManoComprobada[])
    {
        mostrarCartasElegir(cartasEligir, cartasRestantes);
        
        mostrarTablero(zonaJuego, puntosFinales, resultadoManoComprobada);
    }

    public static void mostrarCartasElegir(String cartasEligir[][], int restantes[])
    {
        int filas = cartasEligir.length;
        int columnas = cartasEligir[0].length;
        String espacios = "";
        String espaciosDesdeElBorde = "                              ";

        String simbolo = new String (Character.toChars(0x1F0A0));
       
        //Calculamos la cantidad de espacios para que quede centrado
        switch(columnas)
        {
            case 1 -> {espacios = "            ";}
            case 2 -> {espacios = "         ";}
            case 3 -> {espacios = "      ";}
            case 4 -> {espacios = "   ";}
        }

        //Mostramos la cantidad restante de cartas y los símbolos correspondientes
        switch(columnas)
        {
            case 1 -> 
                    {
                        System.out.print("                                            \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[0];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                    }
            case 2 -> 
                    {
                        System.out.print("                                         \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[0];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                        
                        System.out.print("                                               \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[1];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                    }   
            case 3 -> 
                    {
                        System.out.print("                                      \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[0];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                        
                        System.out.print("                                            \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[1];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                        
                        System.out.print("                                                  \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[2];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");               
                    }
            case 4 -> 
                    {
                        System.out.print("                                   \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[0];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                        
                        System.out.print("                                         \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[1];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                        
                        System.out.print("                                               \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[2];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                        
                        System.out.print("                                                     \u2BA3 \u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF\u23AF|");
                        for(int i = 0; i < restantes[3];i++)
                        {
                            System.out.print(simbolo);
                        }
                        System.out.println("|");
                        
                    }
        }

        System.out.print(espacios);
        System.out.print("                             |");

        for(int j = 0; j < columnas; j++)
        {
            if(restantes[j] > 9)
            {
                System.out.print(" " + restantes[j] + "  |");
            }
            else
            {
                System.out.print("  " + restantes[j] + "  |");
            }
        }
        System.out.println(" <---------Cartas Restantes");
        System.out.println(espaciosDesdeElBorde + "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");

        //Mostramos las cartas a elegir
        for(int i = 0; i < filas;i++)
        {
            System.out.print(espaciosDesdeElBorde);
            System.out.print(espacios);
            System.out.print("| ");
            for(int j = 0; j < columnas;j++)
            {
                System.out.print(cartasEligir[i][j] + " | ");
            }

            System.out.println();
        }

        //Mostramos las flechas
        System.out.print(espaciosDesdeElBorde);
        System.out.print(espacios);
        for(int j = 0; j < columnas; j++)
        {
            if(j == 0)
            {
                System.out.print("   " + "|");
            }
            else
            {
                System.out.print("     " + "|");
            }
        }
        System.out.println();
        System.out.print(espaciosDesdeElBorde);
        System.out.print(espacios);

        for(int j = 0; j < columnas; j++)
        {
            if(j == 0)
            {
                System.out.print("   " + "\u2193");
            }
            else
            {
                System.out.print("     " + "\u2193");
            }
        }
        System.out.println();

    }
    
    public static void mostrarTablero(String zonaJuego[][], int puntos, String resultadoManoComprobada[])
    {
        int filas = zonaJuego.length;
        int columnas = zonaJuego[0].length;

        String espaciosDesdeElBorde = "                              ";

        System.out.println("VALOR CORRESPONDIENTE         -------------------------------------");

        for(int i = 0; i < filas;i++)
        {
            switch(i)
            {
                case 0 -> {System.out.print("RF  2800 | SF  2400 |         ");}
                case 1 -> {System.out.print("4K  2000 | FH  1800 |         ");}
                case 2 -> {System.out.print("FL  1400 | STR 1000 |         ");}
                case 3 -> {System.out.print("3K  0800 | 2PR 0400 |         ");}
                case 4 -> {System.out.print("1PR 0200 |          |         ");}
            }

            System.out.print("| ");
            for(int j = 0; j < columnas;j++)
            {
                System.out.print(zonaJuego[i][j] + " | ");
            }

            System.out.print(resultadoManoComprobada[i] + " |");
            if( i == 0)
            {
                System.out.print(" Puntos: " + puntos);
                System.out.println();
            }
            else
            {
                System.out.println();
            }
            
            System.out.print(espaciosDesdeElBorde);
            System.out.println("-------------------------------------");
        }

        System.out.print("                        | " + resultadoManoComprobada[11] +" |");
        for(int i = 0 ; i < 5 ; i++ )
        {
            System.out.print(" ");
            System.out.print(resultadoManoComprobada[i+5]);
            System.out.print(" |");
        }

        System.out.print(" " + resultadoManoComprobada[10] + " |");

        System.out.println();
    }
}