package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SubarraySum {

    /*
    Given an array of n positive integers, your task is to count the number of subarrays having sum x.
    input:
    n sum
    array
    5 7
    2 4 1 2 7
 */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String paramString = reader.readLine();
        String array = reader.readLine();
        List<Integer> arrayList = Arrays.asList(array.split(" ")).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        Integer sum = Integer.valueOf(paramString.split(" ")[1]);

        System.out.println(sum(arrayList, sum));
    }

    public static int sum(List<Integer> array, int sum) {
        int currentSum = 0;
        int resCounter = 0;
        int l = 0;
        for (int r = 0; r < array.size(); r++) {
            currentSum = currentSum + array.get(r);
            while (currentSum >= sum) {
                if (currentSum == sum) {
                    resCounter++;
                }
                currentSum = currentSum - array.get(l);
                l++;
            }
        }
        return resCounter;
    }

}
