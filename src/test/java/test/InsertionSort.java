package test;

import org.testng.annotations.Test;

/**
 * Created by yan on 16/5/17.
 */
public class InsertionSort {

    @Test
    public void insertionSortTest() {
//        int[] a = new int[]{94, 19, 29, 30, 10, 59, 13, 103, 1231, 123, 122, 123};
        int[] a = new int[]{1, 2, 3, 4, 5, 6, 7, 9, 8};


        for (int i = 1; i < a.length; i++) {
            int currentValue = a[i];
            int position = i;
            for (int j = i - 1; j >= 0; j--) {
                if (a[j] > currentValue) {
                    a[j + 1] = a[j];
                    position -= 1;
                } else {
                    break;
                }
            }
            a[position] = currentValue;
        }

        for (int i = 0; i < a.length; i++) {
            System.out.println(a[i]);
        }

    }
}
