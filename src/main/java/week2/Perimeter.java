package week2;

import org.apache.commons.lang3.tuple.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Perimeter {

    public static void main(String[] args) throws IOException {
        // Enter data using BufferReader
        List<Pair<Integer, Integer>> points = readPoints();
        Double perimeter = 0.;
        for (int i = 0; i < points.size() - 2; i++) {
            for (int j = i + 1; j < points.size() - 1; j++) {
                Double side1 = getLength(points.get(i), points.get(j));
                for (int k = j + 1; k < points.size(); k++) {
                    Double side2 = getLength(points.get(i), points.get(k));
                    Double side3 = getLength(points.get(j), points.get(k));
                    var currentPerimeter =  side1 + side2 + side3;
                    if (perimeter.compareTo(currentPerimeter) < perimeter) {
                        perimeter = currentPerimeter;
                    }
                }
            }
        }
        System.out.println(perimeter);
    }

    private static List<Pair<Integer, Integer>> readPoints() throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        System.out.println("Please add points");
        // Reading data using readLine
        String numberOfLines = reader.readLine();
        Integer lines = Integer.valueOf(numberOfLines);
        List<Pair<Integer, Integer>> points = new ArrayList<>();

        for (Integer i = 1; i <= lines; i++) {
            String[] point = reader.readLine().split(" ");
            points.add(Pair.of(Integer.valueOf(point[0]), Integer.valueOf(point[1])));
        }
        // Printing the read line
        System.out.println(points);
        return points;
    }

    private static Double getLength(Pair<Integer, Integer> point1, Pair<Integer,Integer> point2) {
        return Math.sqrt(Math.pow((double) (point2.getKey() - point1.getKey()), 2)
                + Math.pow((double) (point2.getValue() - point1.getValue()), 2));
    }
}
