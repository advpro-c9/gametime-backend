package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import org.springframework.stereotype.Component;
@Component
public class SearchFilterStrategyType {
    public SearchFilterStrategy getStrategy(String type, SearchFilterRepository searchFilterRepository) {

        if (type.equalsIgnoreCase("NAME")) {
            return new NameSearchStrategy(searchFilterRepository);
        } else if (type.equalsIgnoreCase("GENRES")) {
            return new GenresSearchStrategy(searchFilterRepository);
        } else if (type.equalsIgnoreCase("PRICE RANGE")) {
            return new PriceRangeSearchStrategy(searchFilterRepository);
        } else {
            return null;
        }
    }
}
