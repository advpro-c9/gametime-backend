package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;

import java.util.List;

public interface SearchFilterStrategy {
    List<Game> search(List<Game> gameData);
}
