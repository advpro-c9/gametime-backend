package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import java.util.List;

public class GenresSearchStrategy implements SearchFilterStrategy {

    private SearchFilterRepository searchFilterRepository;

    @Override
    public List<Game> search(String gameGenres) {
        List<String> gameGenreList = List.of(gameGenres.split(","));
        return searchFilterRepository.findByGameGenreIn(gameGenreList);
    }
}
