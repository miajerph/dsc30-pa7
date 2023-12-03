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

    public T[] heap;   // backing array
    public int d;      // branching factor
    public int nelems; // number of elements
    public boolean isMaxHeap; // indicates whether heap is max or min
    public int heapSize; //capacity of heap array

    /**
     * Initializes a binary max heap with capacity = 10
     */
    @SuppressWarnings("unchecked")
    public dHeap() {
        this(2, 10, true);
    }

    /**
     * Initializes a binary max heap with a given initial capacity.
     *
     * @param heapSize The initial capacity of the heap.
     */
    @SuppressWarnings("unchecked")
    public dHeap(int heapSize) {
        this(2, heapSize, true);

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
        this.heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        this.nelems= 0;
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
        trickleDown(0);
        return root;
    }

    @Override
    public void add(T item) throws NullPointerException {
        if (item==null) {throw new NullPointerException();}
        if (nelems==heap.length) {resize();}
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
        //if (index > 0) {
            //int parentIndex = parent(index);
            //if (compare(heap[index], heap[parentIndex]) > 0) {
                //swap(index, parentIndex);
                //index = parentIndex;
                //bubbleUp(index);
            //}
        //}
        while (index>0 && compare(heap[index], heap[parent(index)]) > 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private void trickleDown(int index) {
        //if (!isLeaf(index)) {
            //int child = indexOfMaxOrMinChild(index);
            //if (compare(heap[index], heap[child]) < 0) {
                //swap(index, child);
                //index = child;
                //trickleDown(index);
            //}
        //}
        int child ;
        T temp = heap[index];
        while (kChild(index, 1) < nelems) {
            child = indexOfMaxOrMinChild(index);
            if (compare(temp, heap[child]) <0) {
                heap[index] = heap[child];
            }
            else {
                break;
            }
            index = child;
        }
        heap[index] = temp;
    }

    //private boolean isLeaf(int index) {
        //if (index > (nelems / d) && index < nelems) {
            //return true;
        //
        //return false;
    //}
    @SuppressWarnings("unchecked")
    private void resize(){
        T[] tempHeap = (T[]) new Comparable[heap.length*2];
        for (int i=0; i<heap.length; i++) {
            tempHeap[i] = heap[i];
        }
        heapSize = heap.length*2;
        heap = tempHeap;
    }
    private void swap(int x, int y) {
        T temp = heap[x];
        heap[x] = heap[y];
        heap[y] = temp;
    }

    private int compare(T a, T b) {
        if (isMaxHeap) {
            return a.compareTo(b);
        }
        else {
            return b.compareTo(a);
        }
    }

    private int indexOfMaxOrMinChild(int index){
        //int indexOfMaxOrMin = d*j+1;
        //for (int i = d*j+2; i<=d*j+d; i++) {
            //if (compare(heap[i], heap[indexOfMaxOrMin]) > 0) {
                //indexOfMaxOrMin = i;
            //}
            //if (!isMaxHeap && compare(heap[i], heap[indexOfMaxOrMin]) < 0) {
                //indexOfMaxOrMin = i;
            //}
        //return indexOfMaxOrMin;

        int maxOrMin = kChild(index, 1);
        int k=2;
        int childCompare = kChild(index, k);
        while ((k<=d) && (childCompare<nelems)) {
            if (compare(heap[childCompare], heap[maxOrMin]) > 0) {
                maxOrMin = childCompare;
            }
            k++;
            childCompare = kChild(index, k);
        }
        return maxOrMin;
        //}
    }

    private int kChild(int index, int k) {
        return d*index + k;
    }


}
