package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SearchFilterStrategy {
    CompletableFuture<List<Game>> search(String input);
}
