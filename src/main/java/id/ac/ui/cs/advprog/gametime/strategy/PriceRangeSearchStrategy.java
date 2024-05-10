package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import java.util.List;

public class PriceRangeSearchStrategy implements SearchFilterStrategy {

    private SearchFilterRepository searchFilterRepository;

    @Override
    public List<Game> search(String gamePriceRange) {
        String[] prices = gamePriceRange.split(",");
        double minPrice = Double.parseDouble(prices[0]);
        double maxPrice = Double.parseDouble(prices[1]);
        return searchFilterRepository.findByGamePriceBetween(minPrice, maxPrice);
    }
}
