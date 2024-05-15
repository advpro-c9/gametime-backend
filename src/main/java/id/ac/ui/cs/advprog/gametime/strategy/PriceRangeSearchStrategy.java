package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PriceRangeSearchStrategy implements SearchFilterStrategy {

    private SearchFilterRepository searchFilterRepository;

    public PriceRangeSearchStrategy(SearchFilterRepository searchFilterRepository) {
        this.searchFilterRepository = searchFilterRepository;
    }

    @Override
    @Async
    public CompletableFuture<List<Game>> search(String gamePriceRange) {
        String[] prices = gamePriceRange.split(",");
        double minPrice = Double.parseDouble(prices[0]);
        double maxPrice = Double.parseDouble(prices[1]);
        List<Game> foundGames = searchFilterRepository.findByGamePriceBetween(minPrice, maxPrice);
        return CompletableFuture.completedFuture(foundGames);
    }
}
