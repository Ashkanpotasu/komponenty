package paszko.dawid.projekt;

import static org.junit.jupiter.api.Assertions.*;

class BabelTest {

    @org.junit.jupiter.api.Test
    void sortuj() {
        int[] e= new int[]{1,2,3};
        int[] r;
        Babel a = new Babel();
        int[] t = new int[]{3,2,1,4};
        r = a.sortuj(t);
        assertArrayEquals(e, r, "Nie działa");
    }
}