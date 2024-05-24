package id.ac.ui.cs.advprog.gametime.controller;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.service.SearchFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/games")
public class SearchFilterController {

    @Autowired
    private SearchFilterService searchFilterService;

    @GetMapping("/search/{type}/{input}")
    public CompletableFuture<ResponseEntity<List<Game>>> searchGame(@PathVariable String type, @PathVariable String input) {
        return searchFilterService.search(type, input).thenApply(ResponseEntity::ok).exceptionally(e -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }
}