package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SetOfWeapons {

    public static void main(String[] args) throws IOException {
        int res = 0;
        List<Integer> params = readParams();
        for(int i = 0; i <= params.get(3)/params.get(0); i++) {
            for(int j = 0; j <= params.get(3)/params.get(1); j++) {
                for(int k = 0; k <= params.get(3)/params.get(2); k++) {
                    if (i*params.get(0) + j*params.get(1) + k*params.get(2) == params.get(3)) {
                        res++;
                    }
                }
            }
        }
        System.out.println(res);
    }

    private static List<Integer> readParams() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Please add 4 params");
        String paramString = reader.readLine();
        return Arrays.asList(paramString.split(" ")).stream()
                .map(Integer::valueOf)
                .collect(Collectors.toList());

    }
}
