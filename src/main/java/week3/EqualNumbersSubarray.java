package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class EqualNumbersSubarray {

    /*
    Find size of longest sequence of elements with sum of sequence elements <= time
    input:
    n number_of_equal_numbers
    array
    4 2
    1 2 1 2
    output
    3
    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String paramString = reader.readLine();
        String numbersString = reader.readLine();
        List<Integer> numbersList = Arrays.asList(numbersString.split(" ")).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        Integer equalsNumbers = Integer.valueOf(paramString.split(" ")[1]);
        System.out.println(getNumbersSubarray(numbersList, equalsNumbers));
    }

    public static int getNumbersSubarray(List<Integer> numbers, int repetitionLimit) {
        int res = 0;
        int left = 0;

        //save repeated numbers of current subarray between left and right
        Map<Integer, Integer> currentRepetitionsMap = new HashMap<>();

        //save repeated numbers which volume riches repetition limit.
        //They add subarrays even if there are no current repeated numbers
        List<Integer> repeatedNumbers = new ArrayList<>();

        //move right only if [left, right] doesn't have repeated numbers
        for (int right = 0; right < numbers.size(); right++) {
//            System.out.println("R:" + right + " value:" + numbers.get(right));

            currentRepetitionsMap.compute(numbers.get(right),
                    (key, value) -> Objects.isNull(value) ? 1 : ++value);

            if (currentRepetitionsMap.get(numbers.get(right)) >= repetitionLimit) {
                repeatedNumbers.add(numbers.get(right));
//                repeatedNumbers.stream().forEach(index -> System.out.println(index + " "));
            }
            res = res + repeatedNumbers.size();
//            System.out.println("Res:" + res);

            while ( left <= right && (
                    currentRepetitionsMap.get(numbers.get(left)) >= repetitionLimit
                    || right == numbers.size() - 1)) {

//                System.out.println("L:" + left + " value:" + numbers.get(left));

                int leftRepetitions = currentRepetitionsMap.get(numbers.get(left));
                leftRepetitions--;

                if (leftRepetitions == 0) {
                    currentRepetitionsMap.remove(numbers.get(left));
                } else {
                    currentRepetitionsMap.put(numbers.get(left), leftRepetitions);
                }

                if (right == numbers.size() - 1) {
                    int missedSubbarrays = currentRepetitionsMap.values().stream()
                            .filter(value -> value >= repetitionLimit)
                            .reduce(0, Integer::sum);
                    res = res + missedSubbarrays;
                }

                left++;

            }
//            System.out.println("repeatedSize:" + repeatedNumbers.size() + "/Res:" + res);
        }
        return res;
    }

    
}
