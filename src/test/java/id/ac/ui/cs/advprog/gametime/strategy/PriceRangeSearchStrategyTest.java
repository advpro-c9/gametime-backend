package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PriceRangeSearchStrategyTest {

    @Mock
    private SearchFilterRepository searchFilterRepository;

    private PriceRangeSearchStrategy priceRangeSearchStrategy;

    private Game game;
    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        priceRangeSearchStrategy = new PriceRangeSearchStrategy(searchFilterRepository);

        game = new Game();
        game.setGameId(id);
        game.setGameName("Test Game");
        game.setGameDescription("Test Game Description");
        game.setGameGenre("Adventure");
        game.setGamePrice(49.99);
    }

    @Test
    void testSearch_HappyPath() {
        String priceRange = "30.00,60.00";
        double minPrice = 30.00;
        double maxPrice = 60.00;

        when(searchFilterRepository.findByGamePriceBetween(minPrice, maxPrice)).thenReturn(Arrays.asList(game));

        List<Game> foundGames = priceRangeSearchStrategy.search(priceRange);

        assertNotNull(foundGames);
        assertEquals(1, foundGames.size());
        assertEquals(game, foundGames.get(0));

        verify(searchFilterRepository, times(1)).findByGamePriceBetween(minPrice, maxPrice);
    }

    @Test
    void testSearch_UnhappyPath_NoResult() {
        String priceRange = "100.00,200.00";
        double minPrice = 100.00;
        double maxPrice = 200.00;

        when(searchFilterRepository.findByGamePriceBetween(minPrice, maxPrice)).thenReturn(Collections.emptyList());

        List<Game> foundGames = priceRangeSearchStrategy.search(priceRange);

        assertNotNull(foundGames);
        assertTrue(foundGames.isEmpty());

        verify(searchFilterRepository, times(1)).findByGamePriceBetween(minPrice, maxPrice);
    }

    @Test
    void testSearch_UnhappyPath_InvalidFormat() {
        String priceRange = "InvalidFormat";

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            priceRangeSearchStrategy.search(priceRange);
        });

        assertEquals("For input string: \"InvalidFormat\"", exception.getMessage());

        verify(searchFilterRepository, times(0)).findByGamePriceBetween(anyDouble(), anyDouble());
    }

    @Test
    void testSearch_UnhappyPath_Exception() {
        String priceRange = "30.00,60.00";
        double minPrice = 30.00;
        double maxPrice = 60.00;

        when(searchFilterRepository.findByGamePriceBetween(minPrice, maxPrice)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            priceRangeSearchStrategy.search(priceRange);
        });

        assertEquals("Database error", exception.getMessage());

        verify(searchFilterRepository, times(1)).findByGamePriceBetween(minPrice, maxPrice);
    }
}
