package paszko.dawid.projekt;

import javax.swing.*;
import java.util.Scanner;

public class Babel { //2000 190 180 170 160 150 140 130 120 110 100 90 80 70 60 50 40 30 20 10 0
    private ArrayDiagram diagram;

    public void sortuj(int tab[]) {
        int temp, a = 1;
        while(a>0) {
            a = 0;
            for (int i = 0; i < tab.length - 1; i++) {
                if (tab[i] > tab[i + 1]) {
                    temp = tab[i + 1];
                    tab[i + 1] = tab[i];
                    tab[i] = temp;
                    a++;
                }
            }
            /*SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        diagram.setData(tab);
                    } catch (ArrayDiagram.InvalidTypeException e) {
                        e.printStackTrace();
                    }
                }
            });*/
            try {
                diagram.setData(tab);
            } catch (ArrayDiagram.InvalidTypeException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
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
        sortuj(tab);*/
    }

    public void setDiagram(ArrayDiagram diagram) {
        this.diagram = diagram;
    }
}
