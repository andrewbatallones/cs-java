package algorithms;

public class QuickSort {
    static public void main(String[] args) {
        int[] ex1 = {9, 3, 7, 4, 69, 420, 42};

        sort(ex1);

        for (int i : ex1) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    static void sort(int[] arr) {
        qs(arr, 0, arr.length - 1);
    }

    static void qs(int[] arr, int low, int high) {
        if (low >= high) {
            return;
        }

        int pivotIdx = partition(arr, low, high);

        // repeat the quicksort on the left and right (without the pivot)
        qs(arr, low, pivotIdx - 1);
        qs(arr, pivotIdx + 1, high);
    }

    static int partition(int[] arr, int low, int high) {
        int pivot = arr[high];
        int idx = low - 1;

        for (int i = low; i < high; i++) {
            if (arr[i] <= pivot) {
                idx++;

                // swap
                int tmp = arr[i];
                arr[i] = arr[idx];
                arr[idx] = tmp;
            }
        }

        idx++;
        arr[high] = arr[idx];
        arr[idx] = pivot;

        return idx;
    }
}
