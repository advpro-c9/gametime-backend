package id.ac.ui.cs.advprog.gametime.service;

import id.ac.ui.cs.advprog.gametime.model.Game;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public interface SearchFilterService {
    CompletableFuture<List<Game>> search(String type, String input);
}