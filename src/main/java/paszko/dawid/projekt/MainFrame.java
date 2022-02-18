package paszko.dawid.projekt;

import paszko.dawid.projekt.sortowania.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.text.AbstractDocument;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
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
    private JButton losujButton;
    private JButton shellButton;
    private JButton shakerButton;
    private JButton wyborButton;
    private JButton zliczButton;
    private JPanel losujPanel;
    private JButton wyczyscLButton;
    private ArrayDiagram diagram;
    private JButton[] buttons = {qsortButton, wstawButton, babelButton, shellButton, shakerButton, wyborButton, zliczButton};

    private int wybor=0;

    public MainFrame() throws IOException {
        super("Program sortujący");
        var panelGlowny = new JPanel();
        var diagramPanel = new JPanel();
        panelGlowny.setLayout(new BorderLayout());
        panelGlowny.add(contentPanel, BorderLayout.CENTER);
        this.setContentPane(panelGlowny);
        setMinimumSize(new Dimension(500, 400));
        var ikona_url = Thread.currentThread().getContextClassLoader().getResource("ikona.png");
        var ikona = ImageIO.read(ikona_url);
        setIconImage(ikona);
        diagram = new ArrayDiagram();
        diagramPanel.setLayout(new BorderLayout());
        diagramPanel.add(diagram, BorderLayout.CENTER);
        diagramPanel.setPreferredSize(new Dimension(500, 100));
        diagramPanel.setMinimumSize(new Dimension(500, 100));
        panelGlowny.add(diagramPanel, BorderLayout.LINE_END);
        TitledBorder border = new TitledBorder(new EtchedBorder (EtchedBorder.LOWERED), "Diagram", TitledBorder.LEFT, TitledBorder.DEFAULT_POSITION);
        border.setTitleFont(border.getTitleFont().deriveFont(Font.BOLD));
        diagramPanel.setBorder(border);
        this.pack();


        babelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 1;
                isFocused(babelButton);
            }
        });
        qsortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 2;
                isFocused(qsortButton);
            }
        });
        wstawButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 3;
                isFocused(wstawButton);
            }
        });
        shellButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 4;
                isFocused(shellButton);
            }
        });
        shakerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 5;
                isFocused(shakerButton);
            }
        });
        wyborButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 6;
                isFocused(wyborButton);
            }
        });
        zliczButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                wybor = 7;
                isFocused(zliczButton);
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
        wyczyscLButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                liczbyArea.setText("");
            }
        });

        losujButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                losuj();
            }
        });
    }

    private void isFocused(JButton button) {
        button.setBackground(Color.RED);
        for (JButton btn: buttons) {
            if(btn!=button)
                btn.setBackground(Color.WHITE);
        }
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
        boolean jestTab = false;
        if(liczbyArea.getText().length()!=0) {
            String liczby = liczbyArea.getText().toString();
            String[] licz = liczby.split(" ");
            tab = new int[licz.length];
            for (int i = 0; i < tab.length; i++) {
                tab[i] = Integer.parseInt(licz[i]);
            }
            jestTab = true;
        }
        final long start = System.currentTimeMillis();
        if(jestTab) {
            Finished finished = new Finished() {
                @Override
                public void finish(Sortowanie source) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            long stop = System.currentTimeMillis();
                            wynikArea.append(" Obliczone w " + (stop-start) + " milisekund.\n");
                        }
                    });
                }
            };

            switch (wyb) {
                case 1:
                    Babel babel = new Babel();
                    babel.setDiagram(diagram);
                    babel.setData(tab);
                    babel.setFinished(finished, wynikArea);
                    new Thread(babel).start();
                    break;
                case 2:
                    Qsort qsort = new Qsort();
                    qsort.setDiagram(diagram);
                    qsort.setData(tab);
                    qsort.setFinished(finished, wynikArea);
                    new Thread(qsort).start();
                    break;
                case 3:
                    Wstawianie wstawianie = new Wstawianie();
                    wstawianie.setDiagram(diagram);
                    wstawianie.setData(tab);
                    wstawianie.setFinished(finished, wynikArea);
                    new Thread(wstawianie).start();
                    break;
                case 4:
                    Shell shell = new Shell();
                    shell.setDiagram(diagram);
                    shell.setData(tab);
                    shell.setFinished(finished, wynikArea);
                    new Thread(shell).start();
                    break;
                case 5:
                    Shaker shaker = new Shaker();
                    shaker.setDiagram(diagram);
                    shaker.setData(tab);
                    shaker.setFinished(finished, wynikArea);
                    new Thread(shaker).start();
                    break;
                case 6:
                    Wybor wybor = new Wybor();
                    wybor.setDiagram(diagram);
                    wybor.setData(tab);
                    wybor.setFinished(finished, wynikArea);
                    new Thread(wybor).start();
                    break;
                case 7:
                    Zliczanie zliczanie = new Zliczanie();
                    zliczanie.setDiagram(diagram);
                    zliczanie.setData(tab);
                    zliczanie.setFinished(finished, wynikArea);
                    new Thread(zliczanie).start();
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Wybierz algorytm sortujący!", "Nie wybrano sortowania", JOptionPane.ERROR_MESSAGE);
                    break;
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Wpisz liczby do posortowania!", "Nie wpisano liczb", JOptionPane.ERROR_MESSAGE);
        }
    }
}