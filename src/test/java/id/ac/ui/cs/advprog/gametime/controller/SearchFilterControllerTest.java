package id.ac.ui.cs.advprog.gametime.controller;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.service.SearchFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SearchFilterControllerTest {

    @Mock
    private SearchFilterService searchFilterService;

    @InjectMocks
    private SearchFilterController searchFilterController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateGame() {
        Game game = new Game();
        game.setGameId("1");
        game.setGameName("Game A");
        game.setGameDescription("Desc Game A");
        game.setGamePrice(20.0);
        game.setGameGenres(Arrays.asList("Action", "Adventure"));

        when(searchFilterService.create(any(Game.class))).thenReturn(game);

        ResponseEntity<Game> response = searchFilterController.createGame(game);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(game, response.getBody());
        verify(searchFilterService, times(1)).create(any(Game.class));
    }

    @Test
    void testGetAllGames() {
        List<Game> games = new ArrayList<>();

        Game game1 = new Game();
        game1.setGameId("1");
        game1.setGameName("Pokemon A");
        game1.setGameDescription("Desc Game A");
        game1.setGamePrice(20.0);
        game1.setGameGenres(Arrays.asList("Action", "Adventure"));
        games.add(game1);

        Game game2 = new Game();
        game2.setGameId("2");
        game2.setGameName("Pokemon B");
        game2.setGameDescription("Desc Game B");
        game2.setGamePrice(30.0);
        game2.setGameGenres(Arrays.asList("Strategy", "Action"));
        games.add(game2);

        when(searchFilterService.findAll()).thenReturn(games);

        ResponseEntity<List<Game>> response = searchFilterController.getAllGames();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(games, response.getBody());
        verify(searchFilterService, times(1)).findAll();
    }

    @Test
    void testGetGameById() {

        Game game = new Game();
        game.setGameId("1");
        game.setGameName("Game A");
        game.setGameDescription("Desc Game A");
        game.setGamePrice(20.0);
        game.setGameGenres(Arrays.asList("Action", "Adventure"));

        when(searchFilterService.findById("1")).thenReturn(game);

        ResponseEntity<Game> response = searchFilterController.getGameById("1");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(game, response.getBody());
        verify(searchFilterService, times(1)).findById("1");
    }

    @Test
    void testGetGamesByName() {
        List<Game> games = new ArrayList<>();

        Game game1 = new Game();
        game1.setGameId("1");
        game1.setGameName("Pokemon A");
        game1.setGameDescription("Desc Game A");
        game1.setGamePrice(20.0);
        game1.setGameGenres(Arrays.asList("Action", "Adventure"));
        games.add(game1);

        Game game2 = new Game();
        game2.setGameId("2");
        game2.setGameName("Pokemon B");
        game2.setGameDescription("Desc Game B");
        game2.setGamePrice(30.0);
        game2.setGameGenres(Arrays.asList("Strategy", "Action"));
        games.add(game2);

        when(searchFilterService.findByName("Pokemon")).thenReturn(games);

        ResponseEntity<List<Game>> response = searchFilterController.getGamesByName("Pokemon");

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(games, response.getBody());
        verify(searchFilterService, times(1)).findByName("Pokemon");
    }

    @Test
    void testGetGamesByPriceRange() {
        List<Game> games = new ArrayList<>();

        Game game1 = new Game();
        game1.setGameId("1");
        game1.setGameName("Pokemon A");
        game1.setGameDescription("Desc Game A");
        game1.setGamePrice(20.0);
        game1.setGameGenres(Arrays.asList("Action", "Adventure"));
        games.add(game1);

        Game game2 = new Game();
        game2.setGameId("2");
        game2.setGameName("Pokemon B");
        game2.setGameDescription("Desc Game B");
        game2.setGamePrice(30.0);
        game2.setGameGenres(Arrays.asList("Strategy", "Action"));
        games.add(game2);

        when(searchFilterService.findByPriceRange(0.0, 50.0)).thenReturn(games);

        ResponseEntity<List<Game>> response = searchFilterController.getGamesByPriceRange(0.0, 50.0);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(games, response.getBody());
        verify(searchFilterService, times(1)).findByPriceRange(0.0, 50.0);
    }

    @Test
    void testGetGamesByGenres() {
        List<Game> games = new ArrayList<>();

        Game game1 = new Game();
        game1.setGameId("1");
        game1.setGameName("Pokemon A");
        game1.setGameDescription("Desc Game A");
        game1.setGamePrice(20.0);
        game1.setGameGenres(Arrays.asList("Action", "Adventure"));
        games.add(game1);

        Game game2 = new Game();
        game2.setGameId("2");
        game2.setGameName("Pokemon B");
        game2.setGameDescription("Desc Game B");
        game2.setGamePrice(30.0);
        game2.setGameGenres(Arrays.asList("Strategy", "Action"));
        games.add(game2);

        List<String> genres = List.of("Action", "Adventure");
        when(searchFilterService.findByGenres(genres)).thenReturn(games);

        ResponseEntity<List<Game>> response = searchFilterController.getGamesByGenres(genres);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(games, response.getBody());
        verify(searchFilterService, times(1)).findByGenres(genres);
    }
}
