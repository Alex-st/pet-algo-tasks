package week2;

import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SetOfWeapons {

    public static void main(String[] args) throws IOException {

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
