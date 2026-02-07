import java.util.*;

class MovieRecommendationSystem {

    private Map<Integer, Map<Integer, Integer>> userRatings;
    private Map<Integer, String> movies;

    public MovieRecommendationSystem() {
        userRatings = new HashMap<>();
        movies = new HashMap<>();
    }

    void addUser(int userId) {
        userRatings.putIfAbsent(userId, new HashMap<>());
        System.out.println("User " + userId + " added.");
    }

    void addMovie(int movieId, String genre) {
        movies.put(movieId, genre);
        System.out.println("Movie " + movieId + " added with genre " + genre + ".");
    }

    void rateMovie(int userId, int movieId, int rating) {
        if (!userRatings.containsKey(userId) || !movies.containsKey(movieId)) return;
        userRatings.get(userId).put(movieId, rating);
        System.out.println("User " + userId + " rated Movie " + movieId + " with " + rating + ".");
    }

    List<Integer> getRecommendations(int userId) {
        List<Integer> result = new ArrayList<>();
        if (!userRatings.containsKey(userId)) return result;

        Map<Integer, Integer> ratings = userRatings.get(userId);
        Set<String> likedGenres = new HashSet<>();

        for (Map.Entry<Integer, Integer> e : ratings.entrySet()) {
            if (e.getValue() >= 4) {
                likedGenres.add(movies.get(e.getKey()));
            }
        }

        for (Map.Entry<Integer, String> m : movies.entrySet()) {
            if (!ratings.containsKey(m.getKey()) && likedGenres.contains(m.getValue())) {
                result.add(m.getKey());
            }
        }
        return result;
    }

    List<Integer> viewUserHistory(int userId) {
        if (!userRatings.containsKey(userId)) return new ArrayList<>();
        return new ArrayList<>(userRatings.get(userId).keySet());
    }
}
