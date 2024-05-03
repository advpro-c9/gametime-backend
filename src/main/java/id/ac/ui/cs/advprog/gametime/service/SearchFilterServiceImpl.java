package id.ac.ui.cs.advprog.gametime.service;
import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org. springframework.stereotype.Service;
import java.util.ArrayList;
import java.util. Iterator;
import java.util.List;

@Service
public class SearchFilterServiceImpl implements SearchFilterService {
    @Autowired
    private SearchFilterRepository searchFilterRepository;

    @Override
    public Game create(Game game) { return null; }

    @Override
    public List<Game> findAll() { return null; }

    @Override
    public Game findById(String gameId) { return null; }

    @Override
    public List<Game> findByName(String gameName) { return null; }

    @Override
    public List<Game> findByPriceRange(double minPrice, double maxPrice) { return null; }

    @Override
    public List<Game> findByGenres (List<String> genres) { return null; }
}