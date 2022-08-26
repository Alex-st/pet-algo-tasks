package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class BooksReading {

    /*
    Find size of longest sequence of elements with sum of sequence elements <= time
    input:
    n time
    array
    4 5
    3 1 2 1
    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String paramString = reader.readLine();
        String booksArrayString = reader.readLine();
        List<Integer> arrayList = Arrays.asList(booksArrayString.split(" ")).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());
        Integer time = Integer.valueOf(paramString.split(" ")[1]);

        System.out.println(getBooks(arrayList, time));
    }

    public static int getBooks(List<Integer> books, int time) {
        int left = 0;
        int res = 0;
        int counter = 0;
        int spentTime = 0;
        for (int right = 0; right < books.size(); right++) {
            spentTime += books.get(right);
            counter++;
            while (spentTime > time && left <= right) {
                spentTime -= books.get(left);
                counter = counter == 0 ? 0 : counter - 1;
                left++;
            }
            res = Math.max(res, counter);
        }
        return res;
    }

}
