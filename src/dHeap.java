/*
 * Name: Mia Jerphagnon
 * PID:  A16821297
 */

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * Title: dHeap Description: This program creates a Heap with d branching factor
 *
 * @author Mia Jerphagnon
 * @since November 29, 2023
 *
 * @param <T> the type of elements held in this collection
 */

public class dHeap<T extends Comparable<? super T>> implements HeapInterface<T> {

    private T[] heap;   // backing array
    private int d;      // branching factor
    private int nelems; // number of elements
    private boolean isMaxHeap; // indicates whether heap is max or min

    private int heapSize; //capacity of heap array

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(10, 2, true);
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this(heapSize, 2, true);

    }

    /**
     * Initializes a d-ary heap (with a given value for d), with a given initial
     * capacity.
     *
     * @param d         The number of child nodes each node in the heap should have.
     * @param heapSize  The initial capacity of the heap.
     * @param isMaxHeap indicates whether the heap should be max or min
     * @throws IllegalArgumentException if d is less than one.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int d, int heapSize, boolean isMaxHeap) throws IllegalArgumentException {
        if (d<1) {throw new IllegalArgumentException();}
        heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        nelems= 0;
        this.heapSize = heapSize;
    }

    @Override
    public int size() {
        return nelems;
    }

    @Override
    public T remove() throws NoSuchElementException {
        if (nelems==0) {throw new NoSuchElementException();}
        T root = heap[0];
        heap[0] = heap[nelems-1];
        nelems--;
        if (nelems > 0) {trickleDown(0);}
        return root;
    }

    @Override
    public void add(T item) throws NullPointerException {
        if (item == null) {throw new NullPointerException();}
        if (nelems == heap.length) {
            resize();
        }
        heap[nelems] = item;
        bubbleUp(nelems);
        nelems++;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        heap = (T[]) new Comparable[heapSize];
        nelems = 0;

    }

    @Override
    public T element() throws NoSuchElementException {
        if (nelems==0) {throw new NoSuchElementException();}
        return heap[0];
    }

    private int parent(int j) {
        return (j-1)/d;
    }

    private void bubbleUp(int index) {
        int parentIndex = parent(index);
        while (index > 0 && compare(heap[index], heap[parentIndex]) == 1) {
            swap(index, parentIndex);
            index = parentIndex;
            parentIndex = parent(index);
        }
    }

    private void trickleDown(int index) {
        int child = indexOfMaxOrMinChild(index);
        while (child < nelems && compare(heap[index], heap[child]) == -1) {
                swap(index, child);
                index = child;
                child = indexOfMaxOrMinChild(index);
        }

    }

    @SuppressWarnings("unchecked")
    private void resize() {
        T[] oldHeap = heap;
        heap = (T[]) new Comparable[nelems*2];
        for (int i=0; i<nelems; i++) {
            heap[i] = oldHeap[i];
        }
        heapSize = nelems*2;
    }
    private void swap(int x, int y) {
        T temp = heap[x];
        heap[x] = heap[y];
        heap[y] = temp;
    }

    private int compare(T x, T y) {
        //Integer elem1 = (Integer) x;
        //Integer elem2 = (Integer) y;
        //if (elem1 == elem2) {return 0;}
        //if (elem1 > elem2) {
            //if (isMaxHeap) {return 1;}
            //else {return -1;}
        //}
        //if (elem1 < elem2) {
            //if (isMaxHeap) {return -1;}
            //else {return 1;}
        //}
        //return 0;
        return isMaxHeap ? x.compareTo(y) : y.compareTo(x);
    }

    private int indexOfMaxOrMinChild(int j){
        int indexOfMaxOrMin = j;
        for (int i = d*j+2; i<=d*j+d; i++) {
            if (isMaxHeap && compare(heap[i], heap[indexOfMaxOrMin]) == 1) {
                indexOfMaxOrMin = i;
            }
            if (!isMaxHeap && compare(heap[i], heap[indexOfMaxOrMin]) == -1) {
                indexOfMaxOrMin = i;
            }
        }
        return indexOfMaxOrMin;
    }

}
