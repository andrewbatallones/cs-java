package leetcode;

public class Utils {
    /**
     * Prints out an array of objects.
     * Takes in an array of any object T and prints it.
    */
    public static <T> void printArray(T[] array) {
        for (T element : array) {
            System.out.print(element + " ");
        }

        System.out.println();
    }
}
