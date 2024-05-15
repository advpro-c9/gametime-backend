package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NameSearchStrategy implements SearchFilterStrategy {

    private final SearchFilterRepository searchFilterRepository;

    public NameSearchStrategy(SearchFilterRepository searchFilterRepository) {
        this.searchFilterRepository = searchFilterRepository;
    }
    @Override
    @Async
    public CompletableFuture<List<Game>> search(String gameName) {
        List<Game> foundGames = searchFilterRepository.findByGameNameContaining(gameName);
        return CompletableFuture.completedFuture(foundGames);
    }
}
