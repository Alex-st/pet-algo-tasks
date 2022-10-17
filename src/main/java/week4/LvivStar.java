package week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LvivStar {
    /*
        Sample Input
        5 - initial networks number
        2 0 2 3 1 - list of users in each network
        9 - number of next operations
        COUNT 2 4
        ENTER 2
        LEAVE 1
        COUNT 2 4
        LEAVE 5
        COUNT 4 5
        COUNT 1 2
        ENTER 2
        COUNT 1 2

      Sample Output 0
                5
                6
                3
                2
                3
    */
    public static void main(String[] args) throws IOException {
        //----------- read params -------------
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String nString = reader.readLine();

        String initialString = reader.readLine();
        List<Long> initialCapacity = Arrays.asList(initialString.split(" ")).stream()
                .map(Long::valueOf)
                .collect(Collectors.toList());

        String requestNumberString = reader.readLine();
        long requestsNumber = Long.parseLong(requestNumberString);

        List<Request> requests = new ArrayList<>();

        for (int i = 0; i < requestsNumber; i++) {
            String requestString = reader.readLine();
            String[] requestParams = requestString.split(" ");
            String action = requestParams[0];
            Integer param1 = Integer.parseInt(requestParams[1]);
            Integer param2 = null;
            if (requestParams.length > 2) {
                param2 = Integer.parseInt(requestParams[2]);
            }
            requests.add(new Request(action, param1, param2));
        }
        //----------- end of read params -------------

        var size = initialCapacity.size();
        int chunkCapacity = (int) Math.sqrt(size);
        long[] chunkSums = new long[chunkCapacity];


        // -------- count sum for each chunk ------------
        for (int i = 0; i < initialCapacity.size(); i++) {
            int chunkIndex = Math.floorDiv(i, chunkCapacity);
            long currentChunkSum = chunkSums[chunkIndex];
            chunkSums[i] = currentChunkSum + initialCapacity.get(i);
        }
        // -------- end of counting sum of each chunk -----

        requests.forEach(request -> {
            processRequests(initialCapacity, chunkSums, chunkCapacity, request);
        });
    }

    public static void processRequests(List<Long> initialCapacity,
                                       long[] chunkSums,
                                       int chunkCapacity,
                                       Request request) {
        switch (request.getAction()) {
            case ("COUNT"):
                int chunkKeyLeft = Math.floorDiv(request.getParam1() - 1, chunkCapacity);
                int chunkKeyRight = Math.floorDiv(request.getParam2() - 1, chunkCapacity);

                // ---- optimization if params in single chunk ---------
                if (chunkKeyLeft == chunkKeyRight) {
                    int sum = 0;
                    for (int i = request.getParam1() - 1; i <= request.getParam2() - 1; i++) {
                        sum+=initialCapacity.get(i);
                    }
                    System.out.println(sum);
                    break;
                }
                // ---- end of optimization if params in single chunk ---------

                // ---- count required sum in first (left) chunk -------
                long leftSumOfChunkToAvoid = 0;
                for (int i = chunkKeyLeft * chunkCapacity; i < request.getParam1() - 1; i++) {
                    leftSumOfChunkToAvoid += initialCapacity.get(i);
                }
                long leftChunkSum = chunkSums[chunkKeyLeft] - leftSumOfChunkToAvoid;
                // ---- end of count required sum in first (left) chunk -------

                // ---- count required sum in last (right) chunk -------
                long rightChunkSum = 0;
                for (int i = chunkKeyRight * chunkCapacity; i <= request.getParam2() - 1; i++) {
                    rightChunkSum += initialCapacity.get(i);
                }
                // ---- end of count required sum in last (right) chunk -------

                long finalSum = 0;

                finalSum = leftChunkSum + rightChunkSum;
                for (int i = chunkKeyLeft + 1; i < chunkKeyRight; i++) {
                    finalSum+= chunkSums[i];
                }

                System.out.println(finalSum);

                break;
            case ("ENTER"):
                Long elementValue = initialCapacity.get(request.getParam1() - 1);
                initialCapacity.set(request.getParam1() - 1, elementValue + 1);

                Integer chunkIndex = Math.floorDiv(request.getParam1() - 1, chunkCapacity);
                long currentChunkSum = chunkSums[chunkIndex];
                chunkSums[chunkIndex] = currentChunkSum + 1;
                break;
            case ("LEAVE"):
                Long elementValue2 = initialCapacity.get(request.getParam1() - 1);
                initialCapacity.set(request.getParam1() - 1, elementValue2 - 1);

                Integer chunkKey2 = Math.floorDiv(request.getParam1() - 1, chunkCapacity);
                long currentChunkSum2 = chunkSums[chunkKey2];
                chunkSums[chunkKey2] = currentChunkSum2 - 1;
                break;
        }
    }

    public static class Request {
        String action;
        Integer param1;
        Integer param2;

        public Request(String action, Integer param1, Integer param2) {
            this.action = action;
            this.param1 = param1;
            this.param2 = param2;
        }

        public String getAction() {
            return action;
        }

        public Integer getParam1() {
            return param1;
        }

        public Integer getParam2() {
            return param2;
        }
    }
}
