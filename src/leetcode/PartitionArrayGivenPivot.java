package leetcode;

// https://leetcode.com/problems/partition-array-according-to-given-pivot/description/

public class PartitionArrayGivenPivot {
    public static void main(String[] args) {
        print(pivotArray(new int[]{9, 12, 5, 10, 14, 3, 10}, 10));
        print(pivotArray(new int[]{-3, 4, 3, 2}, 2));
    }

    private static void print(int[] array) {
        Integer[] objArray = new Integer[array.length];

        for (int i = 0; i < array.length; i++) {
            objArray[i] = Integer.valueOf(array[i]);
        }

        Utils.printArray(objArray);
    }

    // You take in an array []int and a int and return []int
    // - original order is kept beyond pivot
    //
    // Any edge cases or gotchas?
    // - array's length is unaffected
    // - int values are not changing
    // - Looks like the pivot needs to exist within the array
    //
    // Sudocode:
    // create 4 different arrays (all same length)
    // - minArray
    // - equalArray
    // - maxArray
    // - result
    // Also want to create a max index for each. The reason is cause when we add these all back together,
    //      we will need to iterate through each array and there's no reason to over iterate if we run out of elements.
    // Iterate through original array:
    //      - Depending on where the value sits, add it to the index of array, then iterate the index.
    // Iterate through each of the 3 arrays and update the result index as we add them in.
    // return result.
    public static int[] pivotArray(int[] nums, int pivot) {
        int[] minArray = new int[nums.length];
        int[] equalArray = new int[nums.length];
        int[] maxArray = new int[nums.length];
        int[] result = new int[nums.length];
        int minIndex = 0;
        int equalIndex = 0;
        int maxIndex = 0;
        int index = 0;
        for (int num : nums) {
            if (num < pivot) {
                minArray[minIndex] = num;
                minIndex++;
            } else if (num == pivot) {
                equalArray[equalIndex] = num;
                equalIndex++;
            } else {
                maxArray[maxIndex] = num;
                maxIndex++;
            }
        }
        for (int i = 0; i < minIndex; i++) {
            result[index] = minArray[i];
            index++;
        }
        for (int j = 0; j < equalIndex; j++) {
            result[index] = equalArray[j];
            index++;
        }
        for (int k = 0; k < maxIndex; k++) {
            result[index] = maxArray[k];
            index++;
        }
        return result;
    }
}
