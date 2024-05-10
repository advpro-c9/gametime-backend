package id.ac.ui.cs.advprog.gametime.service;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.strategy.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class SearchFilterServiceImpl implements SearchFilterService {

    @Autowired
    private SearchFilterStrategyType searchFilterStrategyType;

    public List<Game> search(String type, String input) {
        SearchFilterStrategy strategy = searchFilterStrategyType.getStrategy(type);
        if (strategy != null) {
            return strategy.search(input);
        }
        throw new IllegalArgumentException("Tipe " + type + " tidak sesuai untuk dicari");
    }
}
