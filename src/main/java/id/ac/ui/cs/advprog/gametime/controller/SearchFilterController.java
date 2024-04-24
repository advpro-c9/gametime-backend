package id.ac.ui.cs.advprog.gametime.controller;
import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.service.SearchFilterService;

import java.util.List;

@Controller
@RequestMapping("/find")
public class SearchFilterController {
    
    @Autowired
    private SearchFilterService searchFilterService;

    @GetMapping("/createGame")
    public String createGamePage(Model model) {
        Game game = new Game();
        model.addAttribute("game", game);
        return "CreateGame";
    }

    @PostMapping("/createGame")
    public String createGamePost(@ModelAttribute Game game, Model model){
        searchFilterService.create(game);
        return "redirect:listGame";
    }

    @GetMapping("/filteredGameList")
    public String filteredGameListPage(Model model, 
                                    @RequestParam(value = "minPrice", required = false) Double minPrice, 
                                    @RequestParam(value = "maxPrice", required = false) Double maxPrice, 
                                    @RequestParam(value = "gameGenres", required = false) List<String> gameGenres){
        List<Game> filteredGames;

        if (gameGenres != null) {
            filteredGames = searchFilterService.findByGenres(gameGenres);
        } else if (minPrice != null || maxPrice != null) {
            filteredGames = searchFilterService.findByPriceRange(minPrice, maxPrice);
        } else {
            // Jika tidak ada parameter yang diberikan, tampilkan semua games
            filteredGames = searchFilterService.findAll();
        }
        
        model.addAttribute("games", filteredGames);
        return "FilteredGameList";
    }
    
}
