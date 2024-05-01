package id.ac.ui.cs.advprog.gametime.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;


public class GameTest {
    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.setGameId("123");
        game.setGameName("Test Game");
        game.setGameDescription("Test Game Description");
        List<String> genres = new ArrayList<>();
        genres.add("Adventure");
        genres.add("Action");
        game.setGameGenres(genres);
        game.setGamePrice(49.99);
    }

    @Test
    public void testGameId() {
        assertEquals("123", game.getGameId());
    }

    @Test
    public void testGameName() {
        assertEquals("Test Game", game.getGameName());
    }

    @Test
    public void testGameDescription() {
        assertEquals("Test Game Description", game.getGameDescription());
    }

    @Test
    public void testGameGenres() {
        List<String> expectedGenres = new ArrayList<>();
        expectedGenres.add("Adventure");
        expectedGenres.add("Action");
        assertEquals(expectedGenres, game.getGameGenres());
    }

    @Test
    public void testGamePrice() {
        assertEquals(49.99, game.getGamePrice(), 0.001);
    }
}
