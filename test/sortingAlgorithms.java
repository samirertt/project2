package test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


/**
 * This class includes methods to initialize the dataset,
 * Sorts the dataset using the Radix Sort, Shell ort, Insertion Sort,
 */
public class sortingAlgorithms 
{
    public int[] dataset;

    /**
     * Constructor to initialize the dataset.
     * @param data the array of integers to be sorted
     */
    public sortingAlgorithms(int[] data) 
    {
        this.dataset = data;
    }
    /**
     * Finds the maximum value in the given array
     * @param unsigned the array to find the maximum value
     * @return the maximum value in the array
     */
    public int maxOfArray(int[] unsigned) 
    {
        int max = this.dataset[0];
        for (int i = 1; i < this.dataset.length; i++) {
            if (this.dataset[i] > max) {
                max = this.dataset[i];
            }
        }
        return max;
    }
    /**
     * Sorts the array with the Radix Sort algorithm.
     * Separates the numbers in the dataset into signed and unsigned.
     * Sorts them separately and then combines them into a single sorted array
     */
    public void radixSort() 
    {
        int[] signed = new int[this.dataset.length];
        int[] unsigned = new int[this.dataset.length];
        int signedIntegersCount = 0;
        int unsignedIntegersCount = 0;

        for (int i = 0; i < this.dataset.length; i++) {
            if (this.dataset[i] < 0) {
                signed[signedIntegersCount++] = this.dataset[i];
            } else {
                unsigned[unsignedIntegersCount++] = this.dataset[i];
            }
        }

        signed = Arrays.copyOf(signed, signedIntegersCount);
        unsigned = Arrays.copyOf(unsigned, unsignedIntegersCount);

        for (int base = 1; maxOfArray(signed) / base > 0; base *= 10) {
            countSort(signed, base);
        }

        for (int base = 1; maxOfArray(unsigned) / base > 0; base *= 10) {
            countSort(unsigned, base);
        }

        for (int i = 0; i < signedIntegersCount / 2; i++) {
            int temp = signed[i];
            signed[i] = signed[signedIntegersCount - 1 - i];
            signed[signedIntegersCount - 1 - i] = temp;
        }

        int[] sortedArray = new int[this.dataset.length];
        System.arraycopy(signed, 0, sortedArray, 0, signedIntegersCount);
        System.arraycopy(unsigned, 0, sortedArray, signedIntegersCount, unsignedIntegersCount);

        this.dataset = sortedArray;
    }
    /**
     * Sorts the given array using the Count Sort algorithm according to the specified digit base.
     * @param array to be sorted
     * @param base the digit base used for sorting
     */
    public void countSort(int[] array, int base) 
    {
        int n = array.length;
        int[] calc = new int[n];
        int[] count = new int[10];

        Arrays.fill(count, 0);

        for (int i = 0; i < n; i++) {
            int digit = Math.abs(array[i] / base) % 10;
            count[digit]++;
        }

        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digit = Math.abs(array[i] / base) % 10;
            calc[count[digit] - 1] = array[i];
            count[digit]--;
        }

        System.arraycopy(calc, 0, array, 0, n);
    }
    /**
     * Sorts the dataset using the Shell Sort algorithm.
     * Allows items that are far apart to be exchange
     * Reduces the total number of comparisons
     * @return the sorted array
     */
    public int[] shellSort() 
    {
        for (int gap = this.dataset.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < this.dataset.length; i++) {
                int temp = this.dataset[i];
                int j;
    
                for (j = i; j >= gap && this.dataset[j - gap] > temp; j -= gap) {
                    this.dataset[j] = this.dataset[j - gap];
                }
    
                this.dataset[j] = temp;
            }
        }
        return this.dataset;
    }
    /**
     * Sorts the dataset using the Heap Sort algorithm.
     * Heap Sort creates a maximum heap from the dataset
     * Repeatedly removes the max element to create a sorted array.
     * @return the sorted array.
     */
    public int[] heapSort() 
    {
    
        for (int i = this.dataset.length / 2 - 1; i >= 0; i--) {
            heapify(this.dataset, this.dataset.length, i);
        }
    
        for (int i = this.dataset.length - 1; i > 0; i--) {
            swapHeap(this.dataset, 0, i);
            heapify(this.dataset, i, 0);
        }
    
        return this.dataset;
    }
    /**
     * If the given element exists, it compares it with two sub-elements
     * If one of the sub-elements is greater, the data is changed.
     * @param array representing the heap.
     * @param size the size of the heap
     * @param index position of the element to be checked and adjusted
     */
    public void heapify(int[] array, int size, int index) 
    {
        int largest = index; 
        int left = 2 * index + 1; 
        int right = 2 * index + 2; 
    
        if (left < size && array[left] > array[largest]) {
            largest = left;
        }
    
        if (right < size && array[right] > array[largest]) {
            largest = right;
        }
    
        if (largest != index) {
            swapHeap(array, index, largest);
            heapify(array, size, largest);
        }
    }
    /**
     * Swaps two elements in the heap
     * @param array representing the heap
     * @param i the index of the first element to swap
     * @param j the index of the second element to swap
     */
    public void swapHeap(int[] array, int i, int j)
    {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
    /**
     * Sorts the dataset using the Insertion Sort algorithm
     * Compares the elements in the dataset
     * if a smaller element is found, the two elements are swapped
     * @return the sorted array.
     */
    public int[] insertionSort() 
    {
        for (int i = 1; i < this.dataset.length; i++) {
            int j = i;
            while (j > 0 && this.dataset[j] < this.dataset[j - 1]) {
                swapInsertion(j - 1, j);
                j--;
            }
        }
        return this.dataset;
    }
    /**
     * Swaps two elements in the data set for the Insertion Sort algorithm.
     * @param i the index of the first element to swap
     * @param j the index of the second element to swap
     */
    public void swapInsertion(int i, int j) 
    {
        int temp = this.dataset[i];
        this.dataset[i] = this.dataset[j];
        this.dataset[j] = temp;
    }
    /**
     * Measures the execution time of the Radix Sort algorithm.
     * execution time is measured in seconds
     * @param sort an object of the `sortingAlgorithms`
     * @return time taken to execute the Radix Sort algorithm
     */
    public static double measureradixSort(sortingAlgorithms sort) 
    {
        long startSortingTime = System.nanoTime();
        sort.radixSort();
        long endSortingTime = System.nanoTime();
        return (double) (endSortingTime - startSortingTime) / 1_000_000;
    }
    /**
     * Measures the execution time of the Shell Sort algorithm
     * execution time is measured in seconds
     * @param sort an object of the `sortingAlgorithms`
     * @return the time taken to execute the Shell Sort algorithm
     */
    public static double measureshellSort(sortingAlgorithms sort) 
    {
        long startSortingTime = System.nanoTime();
        sort.shellSort();
        long endSortingTime = System.nanoTime();
        return (double) (endSortingTime - startSortingTime) / 1_000_000;
    }
     /**
     * Measures the execution time of the Heap Sort algorithm
     * execution time is measured in seconds
     * @param sort an object of the `sortingAlgorithms`
     * @return the time taken to execute the Heap Sort algorithm
     */
    public static double measureheapSort(sortingAlgorithms sort) 
    {
        long startSortingTime = System.nanoTime();
        sort.heapSort();
        long endSortingTime = System.nanoTime();
        return (double) (endSortingTime - startSortingTime) / 1_000_000;
    }
    /**
     * Measures the execution time of the Insertion Sort algorithm in seconds.
     * execution time is measured in seconds
     * @param sort an object of the `sortingAlgorithms`
     * @return the time taken to execute the Insertion Sort algorithm
     */
    public static double measureInsertionSort(sortingAlgorithms sort) 
    {
        long startSortingTime = System.nanoTime();
        sort.insertionSort();
        long endSortingTime = System.nanoTime();
        return (double) (endSortingTime - startSortingTime) / 1_000_000;
    }
    /**
     *Checks the accuracy of a sorted dataset by comparing it with a reference sorted version of the original dataset
     * @param originalDataset the original unsorted dataset
     * @param sortedDataset the dataset sorted by a custom sorting algorithm
     * @return true if the sortedDataset matches the reference sorted array, false otherwise
     */
    public static boolean sortComparator(int[] originalDataset, int[] sortedDataset) 
    {
        ArrayList<Integer> list = new ArrayList<>();
        for (int num : originalDataset) {
            list.add(num);
        }

        Collections.sort(list);

        int[] referenceArray = list.stream().mapToInt(Integer::intValue).toArray();

        return Arrays.equals(referenceArray, sortedDataset);
    }

}