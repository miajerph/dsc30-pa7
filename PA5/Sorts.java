/*
 * NAME: TODO
 * PID:  TODO
 */

//Provided imports, feel free to use these if needed
import java.util.Collections;
import java.util.ArrayList;

/**
 * TODO: add class header
 */
public class Sorts {

    /**
     * Sorts a portion of an ArrayList using the insertion sort algorithm.
     *
     * @param list  The ArrayList to be sorted.
     * @param start The index where sorting begins.
     * @param end   The index where sorting ends.
     */
    public static void insertionSort(ArrayList<Integer> list, int start, int end) {
        for (int i = start + 1; i <= end; i++) {
            int key = list.get(i); // Select the current element as the key to be inserted.
            int j = i - 1; // Initialize a variable to track elements before the key.

            // Move elements of the subarray that are greater than the key
            // to one position ahead of their current position.
            while (j >= start && list.get(j) > key) {
                //TODO // Shift the element to the right.
                j = j - 1; // Move to the previous element on the left.
            }
            list.set(j + 1, key); // Insert the key into its correct position in the sorted subarray.
        }
    }

    /**
     * This method performs quick sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     * @param start The initial index on subsection of Arraylist we want to sort
     * @param end The final index of the subsection of Arraylist we want to sort
     */
    public static void quickSort(ArrayList<Integer> list, int start, int end) {
        if (start < end) {
            int p = partition(list, start, end);

            quickSort(list, start, p);
            quickSort(list, p + 1, end);
        }
    }

    /**
     * Partition helper function for quickSort.
     *
     * @param arr The arraylist we want to sort
     * @param lo low
     * @param hi high
     */
    private static int partition(ArrayList<Integer> arr, int lo, int hi) {
        int pivot_index = lo;
        int pivot = arr.get(pivot_index);
        
        int i = lo; // left index
        int j = hi; // right index

        while (true) {
            // Move the left index to the right while the element at
            // the left index is less than the pivot
            while (arr.get(i) < pivot){
                i++;
            }
            // Move the right index to the left while the element at
            // the right index is greater than the pivot
            while (arr.get(j) > pivot){
                j--;
            }

            // If the indices crossed, return
            if (i >= j){
                return j;
            }
            // TODO // Swaps elements at indices i and j
            i++;
            j--;
        }
    }

    /**
     * this helper finds the appropriate number of buckets you should allocate
     * based on the range of the values in the input list
     * @param list the input list to bucket sort
     * @return number of buckets
     */
    private static int assignNumBuckets(ArrayList<Integer> list) {
        Integer max = Collections.max(list);
        Integer min = Collections.min(list);
        return (int) Math.sqrt(max - min) + 1;
    }

    /**
     * this helper finds the appropriate bucket index that a data should be
     * placed in
     * @param data a particular data from the input list if you are using
     *             loop iteration
     * @param numBuckets number of buckets
     * @param listMin the smallest element of the input list
     * @return the index of the bucket for which the particular data should
     * be placed in
     */
    private static int assignBucketIndex(Integer data, int numBuckets, Integer listMin) {
        return (data - listMin) / numBuckets;
    }

    /**
     * This method performs bucket sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     */
    public static ArrayList<Integer> bucketSort(ArrayList<Integer> list) {
        ArrayList<ArrayList<Integer>> buckets = new ArrayList<>();
        int numBuckets = assignNumBuckets(list);
        for (int i = 0; i < numBuckets; i++) {
            ArrayList<Integer> freshBucket = new ArrayList<>();
            buckets.add(i, freshBucket);
        }
        Integer listMin = Collections.min(list);
        for (Integer data : list) {
            int bucketIndex = assignBucketIndex(data, numBuckets, listMin);
            // TODO // Adds data to correct bucket
        }

        ArrayList<Integer> sortedList = new ArrayList<>();
        for (ArrayList<Integer> bucket : buckets) {
            if (bucket.size() > 0)
                insertionSort(bucket, 0, bucket.size() - 1);
            //TODO // Adds all elements in bucket to sortedList
        }
        return sortedList;
    }

    /**
     * This method performs count sort on the input arraylist
     *
     * @param list The arraylist we want to sort
     */
    public static ArrayList<Integer> countSort(ArrayList<Integer> list) {
        ArrayList<Integer> output = new ArrayList<Integer>();

        // Find the largest element of the array
        int max = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) > max)
                max = list.get(i);
        }
        int[] count = new int[max + 1];

        // Initialize count array with all zeros.
        for (int i = 0; i < max; ++i) {
            count[i] = 0;
        }

        // Initialize output with all zeros.
        for (int i = 0; i < list.size(); ++i) {
            output.add(0);
        }

        // Store the count of each element
        for (int i = 0; i < list.size(); i++) {
            int value = list.get(i);
            // TODO // increment count array at correct index
        }

        // Store the cummulative count of each array
        for (int i = 1; i <= max; i++) {
            // TODO
        }

        // Find the index of each element of the original array in count array, and
        // place the elements in output array
        for (int i = 0; i < list.size(); i++) {
            count[list.get(i)]--;
            output.set(count[list.get(i)], list.get(i));
        }

        return output;
    }

    ////////////////////////
    ///// EXTRA CREDIT /////
    ////////////////////////
    
    public static boolean inspectInsertion(int[] arr, int n) {
        // TODO
        return false;
    }

    public static boolean inspectSelection(int[] arr, int n) {
        // TODO
        return false;
    }

    public static boolean inspectMerge(int[] arr, int n) {
        // TODO
        return false;
    }
    
}
}