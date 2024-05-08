package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;

import java.util.ArrayList;
import java.util.List;

public class PriceRangeSearchStrategy implements SearchFilterStrategy {

    private final double minPrice;
    private final double maxPrice;

    public PriceRangeSearchStrategy(double minPrice, double maxPrice) {
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
    }

    @Override
    public List<Game> search(List<Game> gameData) {
        List<Game> gamesInRange = new ArrayList<>();
        for (Game game : gameData) {
            double gamePrice = game.getGamePrice();
            if (gamePrice >= minPrice && gamePrice <= maxPrice) {
                gamesInRange.add(game);
            }
        }
        return gamesInRange;
    }
}
