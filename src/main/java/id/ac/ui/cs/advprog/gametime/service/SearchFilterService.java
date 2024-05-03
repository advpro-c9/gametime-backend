package id.ac.ui.cs.advprog.gametime.service;
import id.ac.ui.cs.advprog.gametime.model.Game;
import java.util.List;

public interface SearchFilterService {
    Game create(Game game);
    List<Game> findAll();
    Game findById(String gameId);
    List<Game> findByName(String gameName);
    List<Game> findByPriceRange(double minPrice, double maxPrice);
    List<Game> findByGenres(List<String> genres);
}