package paszko.dawid.projekt.sortowania;

import paszko.dawid.projekt.ArrayDiagram;

import javax.swing.*;

public class Qsort implements Runnable, Sortowanie {
    private ArrayDiagram diagram;
    public int[] data;
    private Finished finished;
    private JTextArea wynikArea;
    private boolean zrobione = false;

    public void quicksort(int tab[], int poczatek, int koniec) {
        if(poczatek<koniec)  {
            int indeks = dziel(tab, poczatek, koniec);

            quicksort(tab, poczatek, indeks-1);
            quicksort(tab, indeks+1, koniec);
        }
        else {
            if(finished!=null) {
                finished.finish(this);
            }
            zrobione = true;
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
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
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

    @Override
    public void setDiagram(ArrayDiagram diagram) {
        this.diagram = diagram;
    }

    @Override
    public void setData(int[] data) {
        this.data = data;
    }

    @Override
    public void setFinished(Finished finished, JTextArea wynikArea) {
        this.finished = finished;
        this.wynikArea = wynikArea;
    }

    @Override
    public void run() {
        quicksort(data, 0, data.length-1);
    }

    public void wynik(int[] tab) {
        for (int i = 0; i < tab.length; i++) {
            if(i==tab.length-1)
                wynikArea.append(tab[i] + ".");
            else
                wynikArea.append(tab[i] + ", ");
        }
    }
}
