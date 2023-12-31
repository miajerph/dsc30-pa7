import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class dHeapTester {
    @Test
    public void constructor1Test() {
        dHeap testHeap = new dHeap();
        assertEquals(0, testHeap.size());
        testHeap.add(1);
        assertEquals(1, testHeap.element());
        assertEquals(1, testHeap.size());
    }

    @Test
    public void constructor2Test() {
        dHeap testHeap = new dHeap(5);
        assertEquals(0, testHeap.size());
        testHeap.add(1);
        assertEquals(1, testHeap.element());
        assertEquals(1, testHeap.size());

    }

    @Test
    public void constructor3Test() {
        dHeap testHeap = new dHeap(3, 15, false);
        testHeap.add(8);
        testHeap.add(5);
        testHeap.add(9);
        assertEquals(3, testHeap.size());
        assertEquals(5, testHeap.remove());
        assertEquals(2, testHeap.size());
    }

    @Test
    public void sizeTest() {
        dHeap testHeap = new dHeap(3, 15, false);
        assertEquals(0, testHeap.size());
        testHeap.add(1);
        assertEquals(1, testHeap.size());
        testHeap.remove();
        assertEquals(0, testHeap.size());
    }

    @Test
    public void removeTest() {
        dHeap testHeap = new dHeap(3, 15, true);
        testHeap.add(4);
        testHeap.add(2);
        testHeap.add(5);
        assertEquals(5, testHeap.remove());
        assertEquals(4, testHeap.remove());
        assertEquals(2, testHeap.remove());
        assertThrows(NoSuchElementException.class, ()-> {
            testHeap.remove();
        });
    }
    @Test
    public void addTest() {
        dHeap testHeap = new dHeap(3, 15, false);
        testHeap.add(8);
        testHeap.add(5);
        testHeap.add(9);
        assertEquals(5, testHeap.remove());
        assertEquals(8, testHeap.remove());
        assertEquals(9, testHeap.remove());
        assertThrows(NullPointerException.class, ()-> {
            testHeap.add(null);
        });
    }

    @Test
    public void clearTest() {
        dHeap testHeap = new dHeap(3, 15, true);
        testHeap.add(3);
        testHeap.add(5);
        testHeap.add(1);
        testHeap.clear();
        assertEquals(0, testHeap.size());
        assertThrows(NoSuchElementException.class, ()-> {
            testHeap.element();
        });
        assertThrows(NoSuchElementException.class, ()-> {
            testHeap.remove();
        });
    }

    @Test
    public void elementTest() {
        dHeap testHeap = new dHeap(3, 15, true);
        assertThrows(NoSuchElementException.class, ()-> {
            testHeap.element();
        });
        testHeap.add(3);
        testHeap.add(5);
        testHeap.add(6);
        assertEquals(6, testHeap.element());
        testHeap.remove();
        assertEquals(5, testHeap.element());
        testHeap.remove();
        assertEquals(3, testHeap.element());
    }
}