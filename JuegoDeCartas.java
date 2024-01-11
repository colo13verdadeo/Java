package prueba;
import java.util.Scanner;
import java.util.ArrayList;
public class Prueba {
  public static ArrayList mazo() {
  ArrayList<ArrayList<Double>> mazo = new ArrayList<>();
  mazo.add(new ArrayList<Double>());
  mazo.add(new ArrayList<Double>());
  mazo.add(new ArrayList<Double>());
  mazo.add(new ArrayList<Double>());

  double i1;
  i1 = 0;

  while (i1 < 7)
  {
    mazo.get(0).add((int)i1, i1 + 1);
    mazo.get(1).add((int)i1, i1 + 1);
    mazo.get(2).add((int)i1, i1 + 1);
    mazo.get(3).add((int)i1, i1 + 1);
    i1++;
  }
    while (i1 < 10) 
  {
    mazo.get(0).add((int)i1, 0.5);
    mazo.get(1).add((int)i1, 0.5);
    mazo.get(2).add((int)i1, 0.5);
    mazo.get(3).add((int)i1, 0.5);
    i1 += 1;
  }

  return mazo;
}
public static void Carta (int num, int carta)
{
    num++;
    //System.out.printf("<< [%d] - [%d] >>", num, carta);
    if (num > 7)
    {
        switch (num - 7)
        {
            case 1:
                if(carta == 0)
                    System.out.printf("J de Diamantes\n");
                else if (carta == 1)
                    System.out.printf("J de Trebol\n");
                else if (carta == 2)
                    System.out.printf("J de Corazones\n");
                else
                    System.out.printf("J de Pîcas\n");
                break;
            case 2:
                if(carta == 0)
                    System.out.printf("Q de Diamantes\n");
                else if (carta == 1)
                    System.out.printf("Q de Trebol\n");
                else if (carta == 2)
                    System.out.printf("Q de Corazones\n");
                else
                    System.out.printf("Q de Pîcas\n");
                break;
            case 3:
                if(carta == 0)
                    System.out.printf("K de Diamantes\n");
                else if (carta == 1)
                    System.out.printf("K de Trebol\n");
                else if (carta == 2)
                    System.out.printf("K de Corazones\n");
                else
                    System.out.printf("K de Pîcas\n");
                break;
        }
    }
    else
    {
        switch (carta)
        {
            case 0:
                System.out.printf("%d de Diamantes\n", num);
                break;
            case 1:
                System.out.printf("%d de Trebol\n", num);
                break;
            case 2:
                System.out.printf("%d de Corazones\n", num);
                break;
            case 3:
                System.out.printf("%d de Pîcas\n", num);
                break;
        }
    }
}
public static void Tomar(ArrayList<ArrayList <Double>> mazo, double jugador[])
{
    double salida;
    int ix;
    int iy;

    salida = (double) 0;
    while (salida == (double) 0)
    {
        ix = (int) (Math.random() * 3);
        iy = (int) (Math.random() * 10);
       if(mazo.get(ix).get(iy) != (double) 0)
       {
           Carta(iy,ix);
           salida = mazo.get(ix).get(iy);
           mazo.get(ix).set(iy,(double) 0);
           jugador[0] = jugador[0] + salida;
       }
    }
}
public static boolean Estatus(double PC[], double jugador[], ArrayList<ArrayList <Double>> mazo)
{
    if (PC[0] == 7.5 && jugador[0] == PC[0])
    {
        System.out.printf("PC GANA!\n");
        PC[1] = 1 + PC[1];
        return (false);
    }
    else if (jugador[0] > 7.9 || jugador[0] == PC[0])
    {
        System.out.printf("PC GANA!\n");
        PC[1] = 1 + PC[1];
        return (false);
    }
    else if (PC[0] > 7.9)
    {
        System.out.printf("USUARIO GANA!\n");
        jugador[1] = 1 + jugador[1];
        return (true);
    }
    else if (jugador[0] < PC[0])
    {
        System.out.printf("PC GANA!\n");
        PC[1] = 1 + PC[1];
        return (false);
    }
    else
    {
        System.out.printf("La PC toma la carta: ");
        Tomar(mazo, PC);
        return (Estatus(PC, jugador, mazo));
    }
}
  public static void main(String[] args) 
  {
      //Leyenda: <JUGADOR>
      //[0]: PUNTOS ACTUALES
      //[1]: PUNTOS DE PARTIDA (GANADOS)
      Scanner sc = new Scanner(System.in);
        ArrayList<ArrayList <Double>> mazo;
        double  PC[] = new double[3];
        double  Usuario[] = new double[3];
        String  entrada[] = new String[2];
        while(PC[1] < 5 && Usuario[1] < 5)
        {
            PC[0] = 0;
            Usuario[0] = 0;
        mazo = mazo();
        System.out.printf("Jugador toma la carta: ");
        Tomar(mazo, Usuario);
        System.out.printf("La PC toma la carta: ");
        Tomar(mazo, PC);
        System.out.printf("Puntos de cartas PC: %,.2f\n", PC[0]);
        System.out.printf("Puntos de cartas Usuario: %,.2f\n", Usuario[0]);
        System.out.printf("Deseas agarrar otra carta? <SI / NO>, es sensible a mayusculas.\n");
        entrada[0] = sc.nextLine();
        while(!"NO".equals(entrada[0]) && !"SI".equals(entrada[0]))
        {
            System.out.printf("Deseas agarrar otra carta? <SI / NO>, es sensible a mayusculas.\n");
            entrada[0] = sc.nextLine();
        }
        while (!"NO".equals(entrada[0]) && Usuario[0] < 7.5)
        {
            Tomar(mazo, Usuario);
            System.out.printf("Puntos de cartas PC: %,.2f\n", PC[0]);
            System.out.printf("Puntos de cartas Usuario: %,.2f\n", Usuario[0]);
            if (Usuario[0] < 7.9)
            {
                System.out.printf("Deseas agarrar otra carta? <SI / NO>, es sensible a mayusculas.\n");
                entrada[0] = sc.nextLine();
            }
           
        }
        if (Estatus(PC,Usuario,mazo))
        {
            System.out.printf("\n=======\n");
            System.out.printf("Puntos finales de cartas Usuario: %,.2f\n", Usuario[0]);
            System.out.printf("Puntos finales de cartas PC: %,.2f\n", PC[0]);
            System.out.printf("Gana jugador!\n");
            System.out.printf("=======\n");
            System.out.printf("============\n");
            System.out.printf("Puntos de partidas:\n Usuario:   %,.0f/5\n", Usuario[1]);
            System.out.printf("Puntos de partidas:\n PC:        %,.0f/5 \n", PC[1]);
            System.out.printf("============\n");
        }
        else
        {
            System.out.printf("\n=======\n");
            System.out.printf("Puntos finales de cartas Usuario: %,.2f\n", Usuario[0]);
            System.out.printf("Puntos finales de cartas PC: %,.2f\n", PC[0]);
            System.out.printf("Gana jugador!\n");
            System.out.printf("=======\n");
            System.out.printf("============\n");
            System.out.printf("Puntos de partidas:\n Usuario:   %,.0f/5\n", Usuario[1]);
            System.out.printf("Puntos de partidas:\n PC:        %,.0f/5 \n", PC[1]);
            System.out.printf("============\n");
        }
    }
  }
}