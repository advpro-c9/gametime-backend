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
    public Game create(Game game) {
        searchFilterRepository.create(game);
        return game;
    }

    @Override
    public List<Game> findAll() {
        Iterator<Game> gameIterator = searchFilterRepository.findAll();
        List<Game> allGame = new ArrayList<>();
        gameIterator.forEachRemaining(allGame::add);
        return allGame;
    }

    @Override
    public Game findById(String gameId){
        Game game = searchFilterRepository.findById(gameId);
        return game;
    }

    @Override
    public List<Game> findByName(String gameName){
        List<Game> games = searchFilterRepository.findByName(gameName);
        return games;
    }

    @Override
    public List<Game> findByPriceRange(double minPrice, double maxPrice){
        List<Game> games = searchFilterRepository.findByPriceRange(minPrice, maxPrice);
        return games;
    }

    @Override
    public List<Game> findByGenres (List<String> genres){
        List<Game> game = searchFilterRepository.findByGenres(genres);
        return game;
    }
}
