package paszko.dawid.projekt;

import javax.swing.*;

public class Zliczanie implements Runnable, Sortowania {
    private ArrayDiagram diagram;
    public int[] data;
    private Finished finished;

    public void sortuj(int[] tab) {
        int[] temp = new int[tab.length];
        int a=0;

        for(int i=0; i<temp.length; i++) {
            temp[tab[i]]++;
        }
        for(int i=0; i<tab.length; i++) {
            if(temp[i]>0){
                for(int j=1; j<temp[i]+1; j++) {
                    tab[a] = i;
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
    public void setFinished(Finished finished) {
        this.finished = finished;
    }

    @Override
    public void run() {
        sortuj(data);
    }
}
