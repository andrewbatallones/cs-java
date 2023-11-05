package algorithms;

public class Mergesort {
    static public void main(String[] args) {
        int[] ex1 = {9, 3, 7, 4, 69, 420, 42};

        sort(ex1, ex1.length);

        for (int i : ex1) {
            System.out.print(i);
            System.out.print(" ");
        }
    }

    static void sort(int[] arr, int n) {
        if (n < 2) return;

        int mid = n / 2;
        int[] l = new int[mid];
        int[] r = new int[n - mid];

        System.arraycopy(arr, 0, l, 0, mid);
        System.arraycopy(arr, mid, r, 0, n - mid);

        sort(l, mid);
        sort(r, n - mid);

        merge(arr, l, r, mid, n - mid);
    }

    static void merge(int[] arr, int[] l, int[] r, int left, int right) {
        int i = 0, j = 0, k = 0;

        while (i < left && j < right) {
            if (l[i] <= r[j]) {
                arr[k++] = l[i++];
            } else {
                arr[k++] = r[j++];
            }
        }

        while (i < left) {
            arr[k++] = l[i++];
        }

        while (j < right) {
            arr[k++] = r[j++];
        }
    }
}
