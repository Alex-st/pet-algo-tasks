package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class PowersOfTen {

    /*
    Let's consider an infinite sequence of digits constructed of ascending powers of 10 written one after another. Here is the beginning of the sequence:
    You are to find out what digit is located at the definite position of the sequence.

    Input Format
    There is the only integer in the first line . The -th of left lines contains the integer
    — the number of position in the sequence.

    Output Format
    You are to output
    digits or separated with a space. More precisely, the -th digit of output is to be equal to the
    -th digit of the sequence described above.

    Sample Input
    4
    3
    14
    7
    6

    Sample Output
    0 0 1 0
    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String paramString = reader.readLine();
        Long n = Long.valueOf(paramString);
        List<Long> params = new ArrayList<>();

        long maxNumber = 0;
        for (int i = 0; i < n; i++) {
            String element = reader.readLine();
            Long elementInteger = Long.valueOf(element);
            params.add(elementInteger);
            if (maxNumber < elementInteger) {
                maxNumber = elementInteger;
            }
        }

        List<Long> onesIndexesList = getOnesIndexesList(maxNumber);

//        String tempString = onesIndexesList.stream()
//                .map(Objects::toString)
//                .collect(Collectors.joining(" "));
//        System.out.println(tempString);

        List<Integer> answers = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Integer res = getPlace(params.get(i), onesIndexesList);
            answers.add(res);
        }
        String finalString = answers.stream()
                .map(Objects::toString)
                .collect(Collectors.joining(" "));
        System.out.println(finalString);
    }

    private static List<Long> getOnesIndexesList(Long maxNumber) {
        List<Long> onePlaces = new ArrayList<>();
        long xn = 1;
        onePlaces.add(xn);
        long counter = 1;
        while (xn <= maxNumber) {
            xn = xn + counter;
            onePlaces.add(xn);
            counter++;
        }
        return onePlaces;
    }

    public static Integer getPlace(Long param, List<Long> oneIndexes) {
        int bad = 0;
        int good = oneIndexes.size() - 1;
        while (good - bad > 1) {
            int middle = bad + (good - bad)/2;

            if (oneIndexes.get(middle).equals(param)) {
                return 1;
            }

            if (param > oneIndexes.get(middle)) {
                bad = middle;
            } else {
                good = middle;
            }
            System.out.println("bad:" + bad + " good:" + good);
        }
        if (param.equals(oneIndexes.get(good)) || param.equals(oneIndexes.get(bad))) {
            return 1;
        }
        return 0;
    }

}
