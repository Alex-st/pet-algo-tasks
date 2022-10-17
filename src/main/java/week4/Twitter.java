package week4;

import java.util.*;

    /*
    Design a simplified version of Twitter where users can post tweets, follow/unfollow another user, and is able to see the 10 most recent tweets in the user's news feed.
    Explanation
    Twitter twitter = new Twitter();
    twitter.postTweet(1, 5); // User 1 posts a new tweet (id = 5).
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5]. return [5]
    twitter.follow(1, 2);    // User 1 follows user 2.
    twitter.postTweet(2, 6); // User 2 posts a new tweet (id = 6).
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 2 tweet ids -> [6, 5]. Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
    twitter.unfollow(1, 2);  // User 1 unfollows user 2.
    twitter.getNewsFeed(1);  // User 1's news feed should return a list with 1 tweet id -> [5], since user 1 is no longer following user 2.
    */
public class Twitter {

    private Integer counter = 0;
    //key - follower, value - his subscriptors
    private Map<Integer, List<Integer>> followers = new HashMap<>();
    private Map<Integer, TreeSet<Comment>> usersHeap = new HashMap<>();

    public Twitter() {

    }

    public void postTweet(int userId, int tweetId) {
        Comment comment = new Comment(tweetId, userId, counter.intValue());
        usersHeap.putIfAbsent(userId, new TreeSet<>(Comparator.comparing(Comment::getCount).reversed()));
        if (usersHeap.get(userId).size() > 9) {
            usersHeap.get(userId).pollLast();
        }
        usersHeap.get(userId).add(comment);

        counter++;
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> followies = followers.get(userId);
        if (Objects.isNull(followies)) {
            followies = new ArrayList<>();
        }
        followies.add(userId);

        TreeSet<Comment> userFeed = new TreeSet<Comment>(Comparator.comparing(Comment::getCount).reversed());
        followies.stream().filter(followie -> Objects.nonNull(usersHeap.get(followie)))
                .flatMap(followie -> usersHeap.get(followie).stream())
                .forEach(comment -> {
                    userFeed.add(comment);
                    if (userFeed.size() > 10) {
                        userFeed.pollLast();
                    }
                });

        List<Integer> answer = new ArrayList<>();

        int n = 0;
        Iterator<Comment> iter = userFeed.iterator();
        while (n < 10  && iter.hasNext()) {
            Comment t = iter.next();
            answer.add(t.getTweetId());
            n++;
        }

        return answer;
    }

    public void follow(int followerId, int followeeId) {
        followers.compute(followerId, (key, value) -> {
            if (Objects.isNull(value)) {
                value = new ArrayList<>();
            }
            value.add(followeeId);
            return value;
        });
    }

    public void unfollow(int followerId, int followeeId) {
        List<Integer> followers = this.followers.get(followerId);
        if (Objects.nonNull(followers)) {
            followers.remove(Integer.valueOf(followeeId));
        }
    }

    public static class Comment {
        Integer tweetId;
        Integer userId;
        Integer count;

        public Comment(Integer tweetId, Integer userId, Integer param2) {
            this.tweetId = tweetId;
            this.userId = userId;
            this.count = param2;
        }

        public Integer getTweetId() {
            return tweetId;
        }

        public Integer getUserId() {
            return userId;
        }

        public Integer getCount() {
            return count;
        }
    }
}
