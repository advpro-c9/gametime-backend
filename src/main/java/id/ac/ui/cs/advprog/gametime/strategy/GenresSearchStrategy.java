package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import org.springframework.scheduling.annotation.Async;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class GenresSearchStrategy implements SearchFilterStrategy {

    private SearchFilterRepository searchFilterRepository;

    public GenresSearchStrategy(SearchFilterRepository searchFilterRepository) {
        this.searchFilterRepository = searchFilterRepository;
    }

//    @Override
//    public List<Game> search(String gameGenres) {
//        List<String> gameGenreList = List.of(gameGenres.split(","));
//        return searchFilterRepository.findByGameGenreIn(gameGenreList);
//    }

    @Override
    @Async
    public CompletableFuture<List<Game>> search(String gameGenres) {
        List<String> gameGenreList = List.of(gameGenres.split(","));
        List<Game> foundGames = searchFilterRepository.findByGameGenreIn(gameGenreList);
        return CompletableFuture.completedFuture(foundGames);
    }

}
