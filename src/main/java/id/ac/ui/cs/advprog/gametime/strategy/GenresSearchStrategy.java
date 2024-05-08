package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;

import java.util.ArrayList;
import java.util.List;

public class GenresSearchStrategy implements SearchFilterStrategy {

    private final List<String> searchedGenres;

    public GenresSearchStrategy(List<String> searchedGenres) {
        this.searchedGenres = searchedGenres;
    }

    @Override
    public List<Game> search(List<Game> gameData) {
        List<Game> matchingGames = new ArrayList<>();
        for (Game game : gameData) {
            if (containsAnyGenres(game.getGameGenres(), searchedGenres)) {
                matchingGames.add(game);
            }
        }
        return matchingGames;
    }

    private boolean containsAnyGenres(List<String> gameGenres, List<String> searchedGenres) {
        for (String genre : gameGenres) {
            if (searchedGenres.contains(genre)) {
                return true;
            }
        }
        return false;
    }
}
