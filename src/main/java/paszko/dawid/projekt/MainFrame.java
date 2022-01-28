package paszko.dawid.projekt;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private JTextArea liczbyArea;
    private JButton sortujButton;
    private JTextArea wynikArea;

    public MainFrame() {
        super("Sortowanie bąbelkowe");
        this.setContentPane(contentPanel);
        setMinimumSize(new Dimension(500, 400));
        this.pack();

        sortujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wykonaj();
            }
        });
    }

    private void wykonaj() {
        String liczby = liczbyArea.getText().toString();
        String[] licz = liczby.split(" ");
        int[] tab = new int[licz.length];
        for(int i=0; i<tab.length; i++) {
            tab[i] = Integer.parseInt(licz[i]);
        }
        new Babel().sortuj(tab);
        for(int i=0; i<tab.length; i++) {
            if(i==tab.length-1)
                wynikArea.append(tab[i] + ".");
            else
                wynikArea.append(tab[i] + ", ");
        }
    }
}

