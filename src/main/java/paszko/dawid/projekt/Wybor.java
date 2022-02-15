package paszko.dawid.projekt;

import javax.swing.*;

public class Wybor implements Runnable, Sortowania {
    private ArrayDiagram diagram;
    public int[] data;
    private Finished finished;

    public void sortuj(int[] tab) {
        int a, temp;

        for(int i=0; i<tab.length-1; i++) {
            a=i;
            for(int j=i+1; j<tab.length; j++) {
                if(tab[j]<tab[a])
                    a=j;
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
