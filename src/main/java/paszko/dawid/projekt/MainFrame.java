package paszko.dawid.projekt;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Random;

public class MainFrame extends JFrame {
    private JPanel contentPanel;
    private JPanel sortowaniePanel;
    private JTextArea wynikArea;
    private JButton qsortButton;
    private JButton wstawButton;
    private JButton babelButton;
    private JTextArea liczbyArea;
    private JScrollPane liczbyPanel;
    private JButton sortujButton;
    private JButton wyczyscButton;
    private JPanel diagramPanel;
    private JButton losujButton;
    private ArrayDiagram diagram;

    private int wybor=0;

    public MainFrame() throws IOException {
        super("Program sortujący");
        this.setContentPane(contentPanel);
        setMinimumSize(new Dimension(500, 400));
        var ikona_url = Thread.currentThread().getContextClassLoader().getResource("ikona.png");
        var ikona = ImageIO.read(ikona_url);
        setIconImage(ikona);
        diagram = new ArrayDiagram();
        diagramPanel.setLayout(new BorderLayout());
        diagramPanel.add(diagram, BorderLayout.CENTER);
        this.pack();


        babelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 1;
            }
        });
        qsortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 2;
            }
        });
        wstawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 3;
            }
        });

        sortujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wykonaj(wybor);
            }
        });
        wyczyscButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wynikArea.setText("");
            }
        });

        losujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                losuj();
            }
        });
    }

    private void losuj() {
        liczbyArea.setText("");
        int[] tab = new int[1000];
        Random random = new Random();
        for(int i=0; i<1000; i++) {
            tab[i] = random.nextInt(1000);
            liczbyArea.append(tab[i] + " ");
        }
    }

    private void wykonaj(int wyb) {
        int[] tab = new int[0];
        boolean jesttab = false;
        if(liczbyArea.getText().length()!=0) {
            String liczby = liczbyArea.getText().toString();
            String[] licz = liczby.split(" ");
            tab = new int[licz.length];
            for (int i = 0; i < tab.length; i++) {
                tab[i] = Integer.parseInt(licz[i]);
            }
            jesttab = true;
        }
        boolean tak = false;
        long start=0, stop=0;
        if(jesttab) {
            switch (wyb) {
                case 1:
                    start = System.currentTimeMillis();
                    Babel babel = new Babel();
                    babel.setDiagram(diagram);
                    babel.sortuj(tab);
                    stop = System.currentTimeMillis();
                    tak = true;
                    break;
                case 2:
                    start = System.currentTimeMillis();
                    Qsort qsort = new Qsort();
                    qsort.setDiagram(diagram);
                    qsort.quicksort(tab, 0, tab.length - 1);
                    stop = System.currentTimeMillis();
                    tak = true;
                    break;
                case 3:
                    start = System.currentTimeMillis();
                    Wstawianie wstawianie = new Wstawianie();
                    wstawianie.setDiagram(diagram);
                    wstawianie.wstaw(tab);
                    stop = System.currentTimeMillis();
                    tak = true;
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Wybierz algorytm sortujący!", "Nie wybrano sortowania", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Wpisz liczby do posortowania!", "Nie wpisano liczb", JOptionPane.ERROR_MESSAGE);
        }

        if(tak) {
            for(int i=0; i<tab.length; i++) {
                if(i==tab.length-1)
                    wynikArea.append(tab[i] + ". Obliczone w " + (stop-start) + " milisekund.\n");
                else
                    wynikArea.append(tab[i] + ", ");
            }
        }
    }
}
