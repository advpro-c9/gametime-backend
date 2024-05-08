package id.ac.ui.cs.advprog.gametime.service;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.strategy.SearchFilterStrategy;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import id.ac.ui.cs.advprog.gametime.strategy.GenresSearchStrategy;
import id.ac.ui.cs.advprog.gametime.strategy.NameSearchStrategy;
import id.ac.ui.cs.advprog.gametime.strategy.PriceRangeSearchStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
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
        return searchFilterRepository.findById(gameId);
    }

    @Override
    public List<Game> findByName(String gameName){
        SearchFilterStrategy strategy = new NameSearchStrategy(gameName);
        return strategy.search(searchFilterRepository.findAllAsList());
    }

    @Override
    public List<Game> findByPriceRange(double minPrice, double maxPrice){
        SearchFilterStrategy strategy = new PriceRangeSearchStrategy(minPrice, maxPrice);
        return strategy.search(searchFilterRepository.findAllAsList());
    }

    @Override
    public List<Game> findByGenres (List<String> genres){
        SearchFilterStrategy strategy = new GenresSearchStrategy(genres);
        return strategy.search(searchFilterRepository.findAllAsList());
    }
}
