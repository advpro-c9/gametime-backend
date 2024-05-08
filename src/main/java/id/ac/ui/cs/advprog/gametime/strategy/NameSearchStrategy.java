package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;

import java.util.ArrayList;
import java.util.List;

public class NameSearchStrategy implements SearchFilterStrategy {

    private final String searchedGameName;

    public NameSearchStrategy(String searchedGameName) {
        this.searchedGameName = searchedGameName;
    }

    @Override
    public List<Game> search(List<Game> gameData) {
        List<Game> result = new ArrayList<>();
        for (Game game : gameData) {
            if (game.getGameName().equalsIgnoreCase(searchedGameName)) {
                result.add(game);
            }
        }
        return result;
    }
}
