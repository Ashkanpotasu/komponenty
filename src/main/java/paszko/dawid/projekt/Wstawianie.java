package paszko.dawid.projekt;

import javax.swing.*;

public class Wstawianie {
    private ArrayDiagram diagram;

    public void wstaw(int tab[]) {
        int klucz, a;
        for(int i=1; i<tab.length; i++) {
            a=i;
            klucz = tab[i];
            while(a>0 && tab[a-1]>klucz) {
                tab[a] = tab[a-1];
                a--;
            }
            tab[a] = klucz;
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
    }


    public static void main(String[] args) {

    }

    public void setDiagram(ArrayDiagram diagram) {
        this.diagram = diagram;
    }
}
