package paszko.dawid.projekt.sortowania;

import paszko.dawid.projekt.ArrayDiagram;

import javax.swing.*;

public class Shaker implements Runnable, Sortowanie {
    private ArrayDiagram diagram;
    public int[] data;
    private Finished finished;
    private JTextArea wynikArea;

    public void sortuj(int[] tab) {
        int temp;
        for(int i=0; i<tab.length/2; i++) {
            boolean swapped = false;
            for(int j=i; j<tab.length-i-1; j++) {
                if(tab[j]<tab[j+1]) {
                    temp = tab[j];
                    tab[j] = tab[j+1];
                    tab[j+1] = temp;
                    swapped = true;
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
            for(int j = tab.length-2-i; j>i; j--) {
                if(tab[j]>tab[j-1]) {
                    temp = tab[j];
                    tab[j] = tab[j-1];
                    tab[j-1] = temp;
                    swapped = true;
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
            if(!swapped)
                break;
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
