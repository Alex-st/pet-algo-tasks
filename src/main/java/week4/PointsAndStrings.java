package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

public class PointsAndStrings {
    /*
    Задано N відрізків на числовій прямій та M точок на цій же прямій.
    Для кожної з заданих точок визначте, скільком відрізкам вона належить.
    Точка x вважається такою, що належить відрізку з кінцями a та b, якщо виконується подвійна нерівність
    min(a, b) ≤ x ≤ max(a, b).
    input:
    2 2
    0 5
    7 10
    1 6

    output:
    1 0
    */
    public static void main(String[] args) throws IOException {
        //----------- read params -------------
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String initialString = reader.readLine();
        String[] paramsString = initialString.split(" ");
        List<Integer> initialParameters = Arrays.asList(paramsString).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        Integer[][] actions = new Integer[2 * initialParameters.get(0) + initialParameters.get(1)][3];

        for (int i = 0; i < 2 * initialParameters.get(0); i = i + 2) {
            String segmentString = reader.readLine();
            String[] requestParams = segmentString.split(" ");
            actions[i][0] = -i;
            actions[i][1] = Integer.parseInt(requestParams[0]);
            actions[i][2] = -1;
            actions[i + 1][0] = -i;
            actions[i + 1][1] = Integer.parseInt(requestParams[1]);
            actions[i + 1][2] = 1;
        }

        String pointsString = reader.readLine();
        List<Integer> points = Arrays.asList(pointsString.split(" ")).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

        for (int i = 0; i < points.size(); i++) {
            int counter = i;
            actions[2 * initialParameters.get(0) + i][0] = counter;
            actions[2 * initialParameters.get(0) + i][1] = points.get(i);
            actions[2 * initialParameters.get(0) + i][2] =  0;
        }
        //----------- end of read params -------------

       Arrays.sort(actions, new Comparator<Integer[]>() {
           public int compare(Integer[] o1, Integer[] o2) {
               if (o1[1] != o2[1]) {
                   //by value
                   return o1[1].compareTo(o2[1]);
               } else {
                   //by type
                   return o1[2].compareTo(o2[2]);
               }
           }
       });

        int segmentsCounter = 0;
        Map<Integer, Integer> answers = new HashMap<>();
        for (int i = 0; i < actions.length; i++) {
            if (actions[i][2] == -1) {
                segmentsCounter++;
            }
            if (actions[i][2] == 1) {
                segmentsCounter--;
            }
            if (actions[i][2] == 0) {
                answers.put(actions[i][0], segmentsCounter);
            }
        }

        for (int i = 0; i < answers.size(); i++) {
            System.out.println(answers.get(i));
        }
    }

    //type: -1 - start segment, +1 - end, 0 - point
}
