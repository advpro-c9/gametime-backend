package id.ac.ui.cs.advprog.gametime.controller;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.service.SearchFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/games")
public class SearchFilterController {

    @Autowired
    private SearchFilterService searchFilterService;

    @PostMapping
    public ResponseEntity<Game> createGame(@RequestBody Game game) {
        Game createdGame = searchFilterService.create(game);
        return ResponseEntity.ok(createdGame);
    }

    @GetMapping
    public ResponseEntity<List<Game>> getAllGames() {
        List<Game> games = searchFilterService.findAll();
        return ResponseEntity.ok(games);
    }

    @GetMapping("/{gameId}")
    public ResponseEntity<Game> getGameById(@PathVariable String gameId) {
        Game game = searchFilterService.findById(gameId);
        if (game != null) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/name/{gameName}")
    public ResponseEntity<List<Game>> getGamesByName(@PathVariable String gameName) {
        List<Game> games = searchFilterService.findByName(gameName);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/price")
    public ResponseEntity<List<Game>> getGamesByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<Game> games = searchFilterService.findByPriceRange(minPrice, maxPrice);
        return ResponseEntity.ok(games);
    }

    @GetMapping("/genres")
    public ResponseEntity<List<Game>> getGamesByGenres(@RequestParam List<String> genres) {
        List<Game> games = searchFilterService.findByGenres(genres);
        return ResponseEntity.ok(games);
    }
}
