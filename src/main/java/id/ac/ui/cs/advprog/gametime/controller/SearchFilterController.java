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

    @GetMapping("/search/{type}/{input}")
    public ResponseEntity<List<Game>> searchGame(@PathVariable String type, @PathVariable String input) {
        return ResponseEntity.ok(searchFilterService.search(type, input));
    }
}