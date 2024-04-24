package id.ac.ui.cs.advprog.gametime.repository;

import id.ac.ui.cs.advprog.gametime.model.Game;

import java.util.List;

public class FindByIdIterator implements GameIterator {
    private final List<Game> games;
    private int index = 0;
    private final String id;

    public FindByIdIterator(List<Game> games, String id) {
        this.games = games;
        this.id = id;
    }

    @Override
    public boolean hasNext() {
        while (index < games.size()) {
            Game game = games.get(index);
            if (game.getGameId().equals(id)) {
                return true;
            }
            index++;
        }
        return false;
    }

    @Override
    public Game next() {
        if (hasNext()) {
            return games.get(index++);
        }
        return null;
    }
}