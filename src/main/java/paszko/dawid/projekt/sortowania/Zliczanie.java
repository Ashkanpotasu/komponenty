package paszko.dawid.projekt.sortowania;

import paszko.dawid.projekt.ArrayDiagram;
import paszko.dawid.projekt.MainFrame;

import javax.swing.*;

public class Zliczanie implements Runnable, Sortowanie {
    private ArrayDiagram diagram;
    public int[] data;
    private Finished finished;
    private JTextArea wynikArea;

    public void sortuj(int[] tab) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < tab.length; i++) {
            max = Math.max(max, tab[i]);
            min = Math.min(min, tab[i]);
        }
        int z = max - min + 1;
        int[] temp = new int[z];
        int a;
        for(int i=0; i<tab.length; i++) {
            a = tab[i] - min;
            temp[a]++;
        }
        for(int i=1; i<temp.length; i++) {
            temp[i] += temp[i-1];
        }
        int[] tempans = new int[tab.length];

        for(int i=tab.length-1; i>=0; i--) {
            int pos = temp[tab[i]-min];
            a = pos -1;
            tempans[a] = tab[i];
            temp[tab[i]-min]--;
        }
        for(int i=0; i<tempans.length; i++) {
            tab[i] = tempans[i];
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
