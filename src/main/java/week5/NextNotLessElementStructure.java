package week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class NextNotLessElementStructure {

    private static TreeSet<Long> set = new TreeSet<>();
    /*
    Implement data sctructure that supports:

    𝑎𝑑𝑑(𝑖) — add to structure 𝑆 number 𝑖
    𝑛𝑒𝑥𝑡(𝑖) — min element not less that 𝑖. -1 if such element absent.


    Если операция «+ 𝑖» идет во входном файле в начале или после другой операции «+», то она задает операцию 𝑎𝑑𝑑(𝑖).
    Если же она идет после запроса «?», и результат этого запроса был 𝑦, то выполняется операция 𝑎𝑑𝑑((𝑖+𝑦)mod109)

    Input
        6
        + 1
        + 3
        + 3
        ? 2
        + 1
        ? 4

    Output
        3
        4
    */
    public static void main(String[] args) throws IOException {
        //----------- read params -------------
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String nString = reader.readLine();

        long requestsNumber = Long.parseLong(nString);
        Long flag = null;

        for (int i = 0; i < requestsNumber; i++) {
            String requestString = reader.readLine();
            String[] requestParams = requestString.split(" ");
            String action = requestParams[0];
            Long param1 = Long.parseLong(requestParams[1]);
            if (action.equals("+")) {
                addElement(param1, flag);
                flag = null;
            } else {
                long res = getElementNotLess(param1);
                System.out.println(res);
                flag = res;
            }
        }
        //----------- end of read params -------------
    }

    private static Long getElementNotLess(Long param1) {
        Long ceiling = set.ceiling(param1);
        return Objects.isNull(ceiling) ? -1 : ceiling;
    }

    private static void addElement(long element, Long flag) {
        long objectToInsert = Objects.isNull(flag) ? element : (flag + element) % 1_000_000_000;
        set.add(objectToInsert);
    }
}
