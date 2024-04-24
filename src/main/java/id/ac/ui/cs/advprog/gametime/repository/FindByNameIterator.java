package id.ac.ui.cs.advprog.gametime.repository;

import id.ac.ui.cs.advprog.gametime.model.Game;

import java.util.List;

public class FindByNameIterator implements GameIterator {
    private final List<Game> games;
    private int index = 0;
    private final String name;

    public FindByNameIterator(List<Game> games, String name) {
        this.games = games;
        this.name = name;
    }

    @Override
    public boolean hasNext() {
        while (index < games.size()) {
            Game game = games.get(index);
            if (game.getGameName().equalsIgnoreCase(name)) {
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
