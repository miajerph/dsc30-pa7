import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class dHeapTester {
    @Test
    public void constructor1Test() {
        dHeap testHeap = new dHeap();
        assertEquals(2, testHeap.d);
        assertEquals(10, testHeap.heapSize);
        assertTrue(testHeap.isMaxHeap);
    }

    @Test
    public void constructor2Test() {
        dHeap testHeap = new dHeap(5);
        assertEquals(2, testHeap.d);
        assertEquals(5, testHeap.heapSize);
        assertTrue(testHeap.isMaxHeap);

    }

    @Test
    public void constructor3Test() {
        dHeap testHeap = new dHeap(3, 15, false);
        testHeap.add(8);
        testHeap.add(5);
        testHeap.add(9);
        assertEquals(3, testHeap.d);
        assertEquals(15, testHeap.heapSize);
        assertFalse(testHeap.isMaxHeap);
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
        assertEquals((T) 5, testHeap.remove());
        assertEquals((T) 4, testHeap.remove());
        assertEquals((T) 2, testHeap.remove());
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
        assertEquals((T) 5, testHeap.remove());
        assertEquals((T) 8, testHeap.remove());
        assertEquals((T) 9, testHeap.remove());
        assertThrows(NullPointerException.class, ()-> {
            T nullElem = null;
            testHeap.add(nullElem);
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
        assertEquals(testHeap.heapSize, 15);
        assertTrue(testHeap.isMaxHeap);
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
        assertEquals((T) 6, testHeap.element());
        testHeap.remove();
        assertEquals((T) 5, testHeap.element());
        testHeap.remove();
        assertEquals((T) 3, testHeap.element());
    }
}