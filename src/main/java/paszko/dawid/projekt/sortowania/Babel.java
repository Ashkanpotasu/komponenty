package paszko.dawid.projekt.sortowania;

import paszko.dawid.projekt.ArrayDiagram;

import javax.swing.*;

public class Babel implements Runnable, Sortowanie {
    private ArrayDiagram diagram;
    public int[] data;
    private Finished finished;
    private JTextArea wynikArea;

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
        if(finished!=null) {
            for (int i = 0; i < tab.length; i++) {
                if(i==tab.length-1)
                    wynikArea.append(tab[i] + ".");
                else
                    wynikArea.append(tab[i] + ", ");
            }
            finished.finish(this);
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
        sortuj(data);
    }
}
