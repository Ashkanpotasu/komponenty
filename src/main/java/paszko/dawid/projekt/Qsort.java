package paszko.dawid.projekt;

import javax.swing.*;
import java.util.Scanner;

public class Qsort {
    private ArrayDiagram diagram;

    public void quicksort(int tab[], int poczatek, int koniec) {
        if(poczatek<koniec) {
            int indeks = dziel(tab, poczatek, koniec);

            quicksort(tab, poczatek, indeks-1);
            quicksort(tab, indeks+1, koniec);
        }
    }
    public int dziel(int tab[], int poczatek, int koniec) {
        int piwot = tab[koniec];
        int a=(poczatek-1);
        int temp;
        for(int i=poczatek; i<koniec; i++) {
            if(tab[i]<=piwot) {
                a++;
                temp = tab[a];
                tab[a] = tab[i];
                tab[i] = temp;
            }
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        diagram.setData(tab);
                    } catch (ArrayDiagram.InvalidTypeException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        temp = tab[a+1];
        tab[a+1] = tab[koniec];
        tab[koniec] = temp;

        return a+1;
    }


    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        System.out.print("Podaj ilość elementów tablicy: ");
        int ilosc;
        ilosc = in.nextInt();
        int[] tab = new int[ilosc];
        System.out.print("Podaj liczby: ");
        for(int i=0; i<ilosc; i++) {
            tab[i] = in.nextInt();
        }
        quicksort(tab, 0, tab.length-1);*/
    }

    public void setDiagram(ArrayDiagram diagram) {
        this.diagram = diagram;
    }
}
