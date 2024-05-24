package id.ac.ui.cs.advprog.gametime.service;

import id.ac.ui.cs.advprog.gametime.model.Game;
import java.util.List;

public interface SearchFilterService {
    List<Game> search(String type, String input);
}