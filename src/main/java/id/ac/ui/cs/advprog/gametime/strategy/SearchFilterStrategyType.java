package id.ac.ui.cs.advprog.gametime.strategy;

import org.springframework.stereotype.Component;
@Component
public class SearchFilterStrategyType {
    public SearchFilterStrategy getStrategy(String type) {

        if (type.equalsIgnoreCase("NAME")) {
            return new NameSearchStrategy();
        } else if (type.equalsIgnoreCase("GENRES")) {
            return new GenresSearchStrategy();
        } else if (type.equalsIgnoreCase("PRICE RANGE")) {
            return new PriceRangeSearchStrategy();
        } else {
            return null;
        }
    }
}
