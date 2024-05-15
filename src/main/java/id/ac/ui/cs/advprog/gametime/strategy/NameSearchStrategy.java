package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import java.util.List;

public class NameSearchStrategy implements SearchFilterStrategy {

    private final SearchFilterRepository searchFilterRepository;

    public NameSearchStrategy(SearchFilterRepository searchFilterRepository) {
        this.searchFilterRepository = searchFilterRepository;
    }
    @Override
    public List<Game> search(String gameName) {
        return searchFilterRepository.findByGameNameContaining(gameName);
    }
}
