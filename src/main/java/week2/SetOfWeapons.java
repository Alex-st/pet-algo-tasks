package week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SetOfWeapons {

    /*
    Found number of all combinations of first three params that ax + by + cz = sum
    input:
    a b c sum
    10 25 15 40
 */
    public static void main(String[] args) throws IOException {
        //optimal solution
        int res = 0;
        List<Integer> params = readParams();
        for(int i = 0; i <= params.get(3)/params.get(0); i++) {
            for(int j = 0; j <= params.get(3)/params.get(1); j++) {
                if ((params.get(3) - i * params.get(0) - j * params.get(1)) % params.get(2) == 0) {
                    res ++;
                }
            }
        }
        System.out.println(res);
    }

    public static void main2(String[] args) throws IOException {
        //not optimal
        List<Integer> params = readParams();
        List<Boolean> res = new ArrayList<>();
        List<Integer> stack = new ArrayList<>();
        List<List<Integer>> successfullComponents = new ArrayList<>();

        res(params.get(3), params.subList(0,3), res, stack, successfullComponents);

        System.out.println(res.size());
    }

    private static void res(int weight, List<Integer> components, List<Boolean> res,
                            List<Integer> componentStack, List<List<Integer>> successfullComponents) {
        if (weight == 0 ) {
            Optional<List<Integer>> alreadyTheSameList = successfullComponents.stream()
                    .filter(successfullComponentsSet -> successfullComponentsSet.containsAll(componentStack))
                    .findFirst();

            if (alreadyTheSameList.isEmpty()) {
                res.add(true);
                successfullComponents.add(new ArrayList<>(componentStack));
            }
            return;
        }
        if (weight < 0) {
            return;
        }

        for (int componentWeight: components) {
            var updatedWeight = weight - componentWeight;
            componentStack.add(componentWeight);
            res(updatedWeight, components, res, componentStack, successfullComponents);
            componentStack.remove(componentStack.size() - 1);
        }
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
