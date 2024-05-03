package id.ac.ui.cs.advprog.gametime.repository;
import id.ac.ui.cs.advprog.gametime.model.Game;
import org. springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class SearchFilterRepository {

    private final List<Game> gameData = new ArrayList<>();

    public Game create (Game game) {
        if(game.getGameId() == null){
            UUID uuid = UUID.randomUUID();
            game.setGameId(uuid.toString());
        }
        gameData.add(game);
        return game;
    }

    public Iterator<Game> findAll(){
        return gameData.iterator();
    }

    public Game findById(String id) {
        for (Game game : gameData) {
            if (game.getGameId().equals(id)) {
                return game;
            }
        }
        return null;
    }

    public List<Game> findByName(String searchedGameName) {
        List<Game> result = new ArrayList<>();
        for (Game game : gameData) {
            if (game.getGameName().equalsIgnoreCase(searchedGameName)) {
                result.add(game);
            }
        }
        return result;
    }

    public List<Game> findByPriceRange(double minPrice, double maxPrice) {
        List<Game> gamesInRange = new ArrayList<>();
        for (Game game : gameData) {
            double gamePrice = game.getGamePrice();
            if (gamePrice >= minPrice && gamePrice <= maxPrice) {
                gamesInRange.add(game);
            }
        }
        return gamesInRange;
    }

    public List<Game> findByGenres(List<String> searchedGenres) {
        List<Game> matchingGames = new ArrayList<>();
        for (Game game : gameData) {
            if (containsAnyGenres(game.getGameGenres(), searchedGenres)) {
                matchingGames.add(game);
            }
        }
        return matchingGames;
    }

    private boolean containsAnyGenres(List<String> gameGenres, List<String> searchedGenres) {
        for (String genre : gameGenres) {
            if (searchedGenres.contains(genre)) {
                return true;
            }
        }
        return false;
    }
}