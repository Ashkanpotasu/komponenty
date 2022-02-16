package paszko.dawid.projekt.sortowania;

import paszko.dawid.projekt.ArrayDiagram;

import javax.swing.*;

public interface Sortowanie {
    void setDiagram(ArrayDiagram diagram);
    void setData(int[] data);
    void setFinished(Finished finished, JTextArea wynikArea);
}
