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
        heap = (T[]) new Comparable[heapSize];
        this.d = d;
        this.isMaxHeap = isMaxHeap;
        nelems= 0;
        this.heapSize = heapSize;
    }

    /**
     * Returns the size of the heap
     * @return the number of elements in heap
     */
    @Override
    public int size() {
        return nelems;
    }

    /**
     * Removes and returns the top of the heap
     *
     * @return the top of the heap, max in max-heap, min in min-heap
     * @throws NoSuchElementException
     */
    @Override
    public T remove() throws NoSuchElementException {
        if (nelems==0) {throw new NoSuchElementException();}
        T root = heap[0];
        heap[0] = heap[nelems-1];
        nelems--;
        trickleDown(0);
        return root;
    }

    /**
     * Inserts an element into the heap
     *
     * @param item The element to add.
     * @throws NullPointerException
     */
    @Override
    public void add(T item) throws NullPointerException {
        if (item==null) {throw new NullPointerException();}
        if (nelems==heap.length) {resize();}
        heap[nelems] = item;
        bubbleUp(nelems);
        nelems++;
    }

    /**
     * Clears and empties the heap
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        heap = (T[]) new Comparable[heapSize];
        nelems = 0;

    }

    /**
     * Peeks the top of the heap
     *
     * @return the top of the heap
     * @throws NoSuchElementException
     */
    @Override
    public T element() throws NoSuchElementException {
        if (nelems==0) {throw new NoSuchElementException();}
        return heap[0];
    }

    /**
     * Gets the index of the parent
     * @param j the current index of the child
     * @return integer index
     */
    private int parent(int j) {
        return (j-1)/d;
    }

    /**
     * helper function to bubble up
     *
     * @param index to start from
     */
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

    /**
     * trickle down helper function
     *
     * @param index to start from
     */
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

    /**
     * resizes the heap once it reaches capacity
     */
    @SuppressWarnings("unchecked")
    private void resize(){
        T[] tempHeap = (T[]) new Comparable[heap.length*2];
        for (int i=0; i<heap.length; i++) {
            tempHeap[i] = heap[i];
        }
        heapSize = heap.length*2;
        heap = tempHeap;
    }

    /**
     * swaps the position of two elements in the heap
     * @param x index of first element
     * @param y index of second element
     */
    private void swap(int x, int y) {
        T temp = heap[x];
        heap[x] = heap[y];
        heap[y] = temp;
    }

    /**
     * compares two elements, returns opposite integers for min heap
     * @param a first element
     * @param b second element
     * @return integer 0, -1, or 1
     */
    private int compare(T a, T b) {
        if (isMaxHeap) {
            return a.compareTo(b);
        }
        else {
            return b.compareTo(a);
        }
    }

    /**
     * finds the greatest or smallest child to help with trickle down
     *
     * @param index of parent
     * @return the integer position of child
     */
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

    /**
     * formula for the kth child of a parent node
     *
     * @param index the parent node
     * @param k child position within d
     * @return position of child in heap
     */
    private int kChild(int index, int k) {
        return d*index + k;
    }


}
