package id.ac.ui.cs.advprog.gametime.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.UUID;

public class GameTest {
    private Game game;
    private final UUID id = UUID.randomUUID();

    @BeforeEach
    public void setUp() {
        game = new Game();
        game.setGameId(id);
        game.setGameName("Test Game");
        game.setGameDescription("Test Game Description");
        game.setGameGenre("Adventure");
        game.setGamePrice(49.99);
    }

    @Test
    public void testGameId() {
        assertEquals(id, game.getGameId());
    }

    @Test
    public void testGameIdInvalid() {
        UUID anotherId = UUID.randomUUID();
        assertNotEquals(anotherId, game.getGameId());
    }

    @Test
    public void testGameName() {
        assertEquals("Test Game", game.getGameName());
    }

    @Test
    public void testGameNameInvalid() {
        assertNotEquals("Invalid Game", game.getGameName());
    }

    @Test
    public void testGameDescription() {
        assertEquals("Test Game Description", game.getGameDescription());
    }

    @Test
    public void testGameDescriptionInvalid() {
        assertNotEquals("Invalid Game Description", game.getGameDescription());
    }

    @Test
    public void testGameGenres() {
        assertEquals("Adventure", game.getGameGenre());
    }

    @Test
    public void testGameGenresInvalid() {
        assertNotEquals("Action", game.getGameGenre());
    }

    @Test
    public void testGamePrice() {
        assertEquals(49.99, game.getGamePrice(), 0.001);
    }

    @Test
    public void testGamePriceInvalid() {
        assertNotEquals(59.99, game.getGamePrice(), 0.001);
    }
}
