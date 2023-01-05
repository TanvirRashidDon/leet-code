package leetcode.dataStructure;

import java.lang.reflect.Array;
import java.util.*;

public class ArrayCompression {
    public Object compress() {
        int[] inputArray = new int[] {1, 0, 0, 2, 5, 2, 1, 0, 4, 5, 1, 2}; // 0 <= a[i] <= 5; n = 12;
        int[] inputArrayWithNegativeAndLargeValue = new int[] {-1, -1, Integer.MAX_VALUE - 1, Integer.MAX_VALUE};
//        return compressUsingArray(inputArray);
        return compressUsingMap(inputArrayWithNegativeAndLargeValue);
    }

    /*
        0 <= ds[i] <= (Integer.MAX_VALUE - 5) //java VM limit
        value must be positive integer
        cannot be used for large value ie
     */
    private Object compressUsingArray(int[] input) {
        int maxInput = 5;
        int outputArraySize = maxInput + 1;
        ArrayList<Integer>[] outputArray = getArrayOfArrayList(outputArraySize);

        System.out.println(Arrays.toString(outputArray));

        for (int i = 0; i < input.length; i++) {
            int value = input[i];
            outputArray[value].add(i);
        }

        System.out.println(Arrays.toString(outputArray));
        return outputArray;
    }

    private ArrayList<Integer>[] getArrayOfArrayList(int size) {
        ArrayList<Integer>[] array = new ArrayList[size];

        for (int i = 0; i < size; i ++) array[i] = new ArrayList<>();

        return array;
    }

    private Object compressUsingMap(int[] inputArray) {
        Map<Integer, Integer> outputMap = new HashMap<>();
        List<List<Integer>> outputArray = new ArrayList<>();

        for (int i = 0, mappedValue = 0; i < inputArray.length; i ++) {

            if (!outputMap.containsKey(inputArray[i])) {
                outputMap.put(inputArray[i], i); //
                List<Integer> listOfIndex = new ArrayList<>();
                listOfIndex.add(i);
                outputArray.add(listOfIndex);
                mappedValue ++;
                continue;
            }
            List<Integer> indexList = outputArray.get(outputMap.get(inputArray[i]));
            indexList.add(i);
        }
        return outputArray;
    }

}
