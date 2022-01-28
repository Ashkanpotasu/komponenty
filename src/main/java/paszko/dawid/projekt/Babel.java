package paszko.dawid.projekt;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Babel implements Sortowanie {
    public int[] sortuj(int tab[]) {
        int temp, a = 1;
        while(a>0) {
            a=0;
            for(int i=0; i<tab.length-1; i++) {
                if(tab[i]>tab[i+1]) {
                    temp = tab[i+1];
                    tab[i+1]=tab[i];
                    tab[i]=temp;
                    a++;
                }
            }
        }
        return tab;
        /*List<int> lista = new ArrayList<int>();
        for(int i=0; i<tab.length; i++) {
            if(i==tab.length-1)
                System.out.print(tab[i]+ ".");
            else
                System.out.print(tab[i]+ ", ");
        }*/
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Podaj ilość elementów tablicy: ");
        int ilosc;
        ilosc = in.nextInt();
        int[] tab = new int[ilosc];
        System.out.print("Podaj liczby: ");
        for(int i=0; i<ilosc; i++) {
            tab[i] = in.nextInt();
        }
        //sortuj(tab);
    }
}
