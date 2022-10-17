package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class OlimpiadaScore {
    /*
    Sample Input 1:
            3
            101 80
            305 90
            200 14
    Sample Output 1:
            305 90
            101 80
            200 14
    */
    public static void main(String[] args) throws IOException {
        //----------- read params -------------
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String nString = reader.readLine();
        PriorityQueue<Integer[]> requests = new PriorityQueue<>((el1, el2) -> {
            if (el1[1].equals(el2[1])) {
                return el1[0].compareTo(el2[0]);
            }
            return el2[1].compareTo(el1[1]);
        });

        int numberOfRequest = Integer.parseInt(nString);
        for (int i = 0; i < numberOfRequest; i++) {
            String requestString = reader.readLine();
            String[] requestParams = requestString.split(" ");
            Integer id = Integer.parseInt(requestParams[0]);
            Integer score = Integer.parseInt(requestParams[1]);
            requests.add(new Integer[]{id, score});
        }
        //----------- end of read params -------------

        int j = 0;
        while (j < numberOfRequest) {
            Integer[] element = requests.poll();
            System.out.println(element[0] + " " + element[1]);
            j++;
        }
    }
}
