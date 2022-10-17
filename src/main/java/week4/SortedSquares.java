package week4;

import java.util.ArrayList;
import java.util.List;

public class SortedSquares {
    /*
    Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.

    Input: nums = [-4,-1,0,3,10]
    Output: [0,1,9,16,100]
    Explanation: After squaring, the array becomes [16,1,0,9,100].
    After sorting, it becomes [0,1,9,16,100].

    Input: nums = [-7,-3,2,3,11]
    Output: [4,9,9,49,121]
    */
    public static int[] sort(int[] nums) {
        //---create heap
        int[] heap = getSumSorted(nums);
        List<Integer> finalList = new ArrayList<>();
        int[] finalArray = new int[nums.length];

        int last = heap.length - 1;
        while (last >= 0) {
            int minimum = getMinimum(heap, last);
            finalArray[heap.length - last - 1] = minimum;
            System.out.println(minimum);
//            System.out.println(finalArray[heap.length - last - 1]);
            last = last -1;
        }
        return finalArray;
    }

    public static int[] getSumSorted(int[] initialArray) {
        int[] heap = new int[initialArray.length];
        for (int i = 0; i < initialArray.length; i++) {
            heapAdd(initialArray[i] * initialArray[i], heap, i);
        }
        return heap;
    }

    public static void heapAdd(int element, int[] heap, int index) {
        heap[index] = element;
        int i = index;
        while (i > 0) {
            int parentIndex = i/2;
            if (heap[i] < heap[parentIndex]) {
                int left = heap[i];
                heap[i] = heap[parentIndex];
                heap[parentIndex] = left;
                i = parentIndex;
            } else {
                break;
            }
        }
    }

    public static int getMinimum(int[] heap, int pointerToLast) {
        int min = heap[0];
        if (heap.length == 1) {
            return min;
        }
//        int pointerToLast = heap.length - 1;
        int lastElement = heap[pointerToLast];
        heap[0] = lastElement;
        pointerToLast = pointerToLast - 1;

        int i = 0;
        int j = 1;
        while (2 * i <= pointerToLast) {
            int minEl = i;
            if (heap[i] > heap[j]) {
                minEl = j;
            }
            if ( j + 1 < heap.length && heap[minEl] > heap[j + 1]) {
                minEl = j + 1;
            }
            if (minEl == i) {
                break;
            }
            int temp = heap[i];
            heap[i] = heap[minEl];
            heap[minEl] = temp;
            i = minEl;
            j = minEl * 2 + 1;
        }
        return min;
    }
}
