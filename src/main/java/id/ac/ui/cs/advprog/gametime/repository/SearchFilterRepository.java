package id.ac.ui.cs.advprog.gametime.repository;
import id.ac.ui.cs.advprog.gametime.model.Game;
import org. springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class SearchFilterRepository {

    public Game create (Game game) {return null;}

    public Iterator<Game> findAll(){return null;}

    public Game findById(String id) {return null;}

    public List<Game> findByName(String gameName) {return null;}

    public List<Game> findByPriceRange(double minPrice, double maxPrice) {return null;}

    public List<Game> findByGenres(List<String> genres) {return null;}

}