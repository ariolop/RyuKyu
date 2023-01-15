package paquete;

import java.util.Arrays;

//Clase donde haremos las comprobaciones correspondientes
public class Comprobar {
    
    //Utilizada dentro de la funcion esEscalera(mano);
    public static void swapDelAs(int mano[])
    {
        //Método para poner es As al final si es necesario
        //Intercambiamos el As solo si el último valor de la baraja es una K.
        if(mano[0] == 1 && mano[4] == 13)
        {
            int numBackup;
            numBackup = mano[0];

            for(int i = 1; i < 5 ; i++)
            {
                mano[i-1] = mano[i];
            }

            mano[4] = numBackup;
        }
    }

    public static int comprobarSiEstaRellena(int zonaJuegoVirtual[][], int puntosFinales, boolean manosComprobadas[], String resultadoManoComprobada[])
    {
        //Método para comprobar que filas, columnas o diagonales están completas
        //resultadoManoComprobada (0-4 filas, 5-9 columnas, 10 diagonal izq-der, 11 diagonal der-izq)
        int mano[] = new int [5];
        int puntosMano;

        //Comprobamos filas
        for(int i = 0; i < 5; i++)
        {
            //Coger la mano correspondiente
            for(int j = 0; j < 5; j++) 
            {
                mano[j] = zonaJuegoVirtual[i][j];
            }


            //Si encuentra un 0, la mano no está lista para ser comprobada
            //Y si manosComprobadas es igual a true, significa que ya está comprobada
            Arrays.sort(mano);
            if(Arrays.binarySearch(mano, 0) == -1 && manosComprobadas[i] == false)
            {
                //Envio la mano, el vector donde escribirá el resultado de la comprobación y la posición donde quiero que escriba: devuelve los puntos que tiene esa mano
                puntosMano = Comprobar.comprobacionMano(mano, resultadoManoComprobada, i); 
                puntosFinales += puntosMano;
                manosComprobadas[i] = true;
            }
        }
        
        //Comprobamos columnas
        for(int i = 0; i < 5; i++)
        {
            for(int j = 0; j < 5; j++)
            {
                mano[j] = zonaJuegoVirtual[j][i];
            }

            Arrays.sort(mano);
            if(Arrays.binarySearch(mano, 0) == -1 && manosComprobadas[i+5] == false)
            {
                puntosMano = Comprobar.comprobacionMano(mano, resultadoManoComprobada, i+5);
                puntosFinales += puntosMano;
                manosComprobadas[i+5] = true;
            }
        }

        //Comprobar diagonal izq-der
        for(int i = 0; i < 5; i++)
        {
            mano[i] = zonaJuegoVirtual[i][i];
        }

        Arrays.sort(mano);

        if(Arrays.binarySearch(mano, 0) == -1 && manosComprobadas[10] == false)
        {
            puntosMano = Comprobar.comprobacionMano(mano, resultadoManoComprobada, 10);
            puntosFinales += puntosMano;
            manosComprobadas[10] = true;
        }


        //Comprobar diagonal der-izq
        for(int i = 0, j = 4; i < 5; i++,j--)
        {
            mano[i] = zonaJuegoVirtual[i][j];
        }

        Arrays.sort(mano);

        if(Arrays.binarySearch(mano, 0) == -1 && manosComprobadas[11] == false)
        {
            puntosMano = Comprobar.comprobacionMano(mano, resultadoManoComprobada, 11);
            puntosFinales += puntosMano;
            manosComprobadas[11] = true;
        }

        return puntosFinales;
    }

    //pos -> Significa la posicion donde tengo que poner el resultado en "resultadoManoComprobada[]", por ejemplo, 2PR (Doble pareja)
    public static int comprobacionMano(int mano[], String resultadoManoComprobada[], int pos)
    {
        //Método para comprobar cuántos puntos tiene la mano.
        int numerosMano[];

        int puntos = 0;

        numerosMano = calcularValor(mano);
        Arrays.sort(numerosMano);

        if(esMismoPalo(mano))
        {
            //Controlado dentro de la función -> 
            //Solo hace el intercambio cuando el primer valor es 1 y el ultimo el 13
            if(esEscalera(numerosMano))
            {
                if(esEscaleraReal(numerosMano))
                {
                    resultadoManoComprobada[pos] = "RF ";
                    puntos = 2800;
                    System.out.println("Tienes una escalera Real");
                }
                else
                {
                    resultadoManoComprobada[pos] = "SF ";
                    puntos = 2400;
                    System.out.println("Tienes una escalera de color");
                }
            }
            else
            {
                resultadoManoComprobada[pos] = "FL ";
                puntos = 1400;
                System.out.println("Tienes color");
            }
        }
        else
        {
            if(esEscalera(numerosMano))
            {
                resultadoManoComprobada[pos] = "STR";
                puntos = 1000;
                System.out.println("Tienes una escalera");
            }
            else
            {
                boolean manoComprobada = false;

                int posCarta = 0;

                while(posCarta < 4 && manoComprobada==false)
                {
                    if( numerosMano[posCarta] == numerosMano[posCarta+1] )
                    {
                        if( (posCarta < 3) && numerosMano[posCarta+1] == numerosMano[posCarta+2] )
                        {
                            if( (posCarta < 2) && numerosMano[posCarta+2] == numerosMano[posCarta+3] )
                            {
                                resultadoManoComprobada[pos] = "4K ";
                                puntos = 2000;
                                manoComprobada = true;
                            }
                            else
                            {
                                if( (posCarta < 1) && numerosMano[posCarta+3] == numerosMano[posCarta+4])
                                {
                                    resultadoManoComprobada[pos] = "FH ";
                                    puntos = 1800;
                                    manoComprobada = true;
                                }
                                else
                                {
                                    resultadoManoComprobada[pos] = "3K ";
                                    puntos = 800;
                                    manoComprobada = true;
                                }
                            }
                        }
                        else
                        {
                            if( (posCarta < 2) && numerosMano[posCarta+2] == numerosMano[posCarta+3] )
                            {
                                if( (posCarta < 1) &&  numerosMano[posCarta+3] == numerosMano[posCarta+4] )
                                {
                                    resultadoManoComprobada[pos] = "FH ";
                                    puntos = 1800;
                                    manoComprobada = true;
                                }
                                else
                                {
                                    resultadoManoComprobada[pos] = "2PR";
                                    puntos = 400;
                                    manoComprobada = true;
                                }
                            }   
                            else
                            {
                                if( (posCarta < 1) && numerosMano[posCarta+3] == numerosMano[posCarta+4] )
                                {
                                    resultadoManoComprobada[pos] = "2PR";
                                    puntos = 400;
                                    manoComprobada = true;
                                }
                                else
                                {
                                    posCarta++;
                                    resultadoManoComprobada[pos] = "1PR";
                                    puntos = 200;
                                }
                            }
                        }
                    }
                    else
                    {
                        posCarta++;
                    }
                }

                if(puntos == 0)
                {
                    resultadoManoComprobada[pos] = "000";
                }
            }
        }
        
        return puntos;
    }

    public static int[] calcularValor(int mano[])
    {
        //Método que devuelve el valor númerico de cada carta de la mano
        int numerosMano[];
        numerosMano = new int[5];

        for(int i = 0; i < 5;i++)
        {
            numerosMano[i] = mano[i] % 100;
        }

        return numerosMano;
    }

    
    public static boolean esEscalera(int mano[])
    {
        boolean flag = true;

        swapDelAs(mano); 
        //Controlado dentro de la función -> 
        //Solo hace el intercambio cuando el primer valor es 1 y el ultimo el 13

        for(int i = 0; i < 4;i++)
        {
            if((mano[i]+1) != mano[i+1])
            {
                flag=false;
            }
        }

        return flag;
    }   
    
    
    public static boolean esMismoPalo(int mano[])
    {
        boolean flag = true;
        int cont = 1;
        int palo = (int) (mano[0] / 100);

        do{
            if(palo == (int) (mano[cont]/100))
            {
                cont++;
            }
            else
            {
                flag = false;
            }

        }while(flag == true && cont < 5);

        return flag;
    }

    public static boolean esEscaleraReal(int mano[])
    {
        boolean flag = true;

        //Comprueba si el ultimo valor es el as. Se ha puesto al final en la funcion swarpDelAs().
        if(mano[4] != 1) 
        {
            flag = false;
        }

        //Comprobamos que el resto de numeros van del 10 al 13
        if(flag == true)
        {
            for(int i = 0; i < 3;i++)
            {
                if(mano[i] != (10+i)) //10,11,12,13
                {
                    flag = false;
                }
            }
        }

        return flag;
    }
}