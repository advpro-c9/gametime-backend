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

    public List<Game> findAllAsList() {
        return new ArrayList<>(gameData);
    }
}