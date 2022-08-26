package week3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CompanyChoosing {

    /*
    Find subset of elements with higher friendship factor where difference of money between elements < diff
    input:
    n diff
    money1 frienshipFactor1
    money2 frienshipFactor2
    ...
    4 5
    75 5
    0 100
    150 20
    75 1
    output
    max general frienship factor
    100
    */
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));

        String paramString = reader.readLine();
        Long difference = Long.valueOf(paramString.split(" ")[1]);

        Long numberOfFriends = Long.valueOf(paramString.split(" ")[0]);
        List<Long[]> friends = new ArrayList<>();
        for (int i = 1; i <= numberOfFriends; i++) {
            String friendString = reader.readLine();
            Long[] singleFriend = Arrays.stream(friendString.split(" ")).map(Long::valueOf)
                    .toArray(Long[]::new);
            friends.add(singleFriend);
        }

        friends.sort(Comparator.comparing(friendParams -> friendParams[0]));

        System.out.println(getMaxFriendshipFactor(friends, difference));
    }

    public static long getMaxFriendshipFactor(List<Long[]> friends, Long difference) {
        int left = 0;
        long res = 0;
        long friendshipFactor = 0;
        for (int right = 0; right < friends.size(); right++) {
            friendshipFactor += friends.get(right)[1];
            while (friends.get(left)[0] <= friends.get(right)[0] - difference && left <= right) {
                friendshipFactor = Math.max(friendshipFactor - friends.get(left)[1], 0);
                left++;
            }
            res = Math.max(res, friendshipFactor);
        }
        return res;
    }

}
