package paszko.dawid.projekt;

import javax.swing.*;

public class Shell implements Runnable, Sortowania {
    private ArrayDiagram diagram;
    public int[] data;
    private Finished finished;

    public void sortuj(int[] tab) {
        int dlugosc = tab.length;

        for(int przerwa=dlugosc/2; przerwa>0; przerwa/=2) {
            for (int i = przerwa; i < dlugosc; i++) {
                int klucz = tab[i];
                int j = i;
                while(j>=przerwa && tab[j-przerwa]>klucz) {
                    tab[j] = tab[j-przerwa];
                    j-=przerwa;
                }
                tab[j] = klucz;

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
