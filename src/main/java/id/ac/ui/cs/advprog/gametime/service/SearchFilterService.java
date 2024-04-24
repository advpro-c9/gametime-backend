package id.ac.ui.cs.advprog.gametime.service;
import id.ac.ui.cs.advprog.gametime.model.Game;
import java.util.List;

public interface SearchFilterService {
    public Game create(Game game);
    public List<Game> findAll();
    public List<Game> findByPriceRange(double minPrice, double maxPrice);
    public List<Game> findByGenres(List<String> genres);
}