package id.ac.ui.cs.advprog.gametime.repository;
import id.ac.ui.cs.advprog.gametime.model.Game;
import org. springframework.stereotype.Repository;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Repository
public class SearchFilterRepository {
    static int id = 0;
    private List<Game> gameData = new ArrayList<>();

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

    public List<Game> findByName(String gameName) {
        List<Game> result = new ArrayList<>();
        for (Game game : gameData) {
            if (game.getGameName().equalsIgnoreCase(gameName)) {
                result.add(game);
            }
        }
        return result;
    }

    public List<Game> findByPriceRange(double minPrice, double maxPrice) {
        List<Game> gamesInRange = new ArrayList<>();
        for (Game game : gameData) {
            if (game.getGamePrice() >= minPrice && game.getGamePrice() <= maxPrice) {
                gamesInRange.add(game);
            }
        }
        return gamesInRange;
    }

    public List<Game> findByGenres(List<String> genres) {
        List<Game> matchingGames = new ArrayList<>();
        for (Game game : gameData) {
            for (String genre : genres) {
                if (game.getGameGenres().contains(genre)) {
                    matchingGames.add(game);
                    break;
                }
            }
        }
        return matchingGames;
    }
    
    // //case insensitif genres (sementara ngga butuh)
    // public List<Game> findByGenre(List<String> genres) {
    //     List<Game> matchingGames = new ArrayList<>();
    //     for (Game game : gameData) {
    //         for (String genre : genres) {
    //             for (String gameGenre : game.getGameGenres()) {
    //                 if (gameGenre.equalsIgnoreCase(genre)) {
    //                     matchingGames.add(game);
    //                     break; // keluar dari loop genre karena game sudah cocok dengan setidaknya satu genre
    //                 }
    //             }
    //         }
    //     }
    //     return matchingGames;
    // }
      
}
