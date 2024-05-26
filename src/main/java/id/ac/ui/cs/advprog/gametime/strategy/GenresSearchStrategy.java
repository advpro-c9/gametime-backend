package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GenresSearchStrategy implements SearchFilterStrategy {

    private final SearchFilterRepository searchFilterRepository;

    public GenresSearchStrategy(SearchFilterRepository searchFilterRepository) {
        this.searchFilterRepository = searchFilterRepository;
    }
    @Async
    @Override
    public CompletableFuture<List<Game>> search(String gameGenre) {
        List<Game> foundGames = searchFilterRepository.findByGameGenreContaining(gameGenre);
        return CompletableFuture.completedFuture(foundGames);
    }
}
