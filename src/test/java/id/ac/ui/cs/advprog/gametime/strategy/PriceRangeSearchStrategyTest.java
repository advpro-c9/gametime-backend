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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
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
    void testSearch() throws ExecutionException, InterruptedException {
        String priceRange = "30.00,60.00";
        double minPrice = 30.00;
        double maxPrice = 60.00;

        when(searchFilterRepository.findByGamePriceBetween(minPrice, maxPrice)).thenReturn(Arrays.asList(game));

        CompletableFuture<List<Game>> foundGamesFuture = priceRangeSearchStrategy.search(priceRange);
        List<Game> foundGames = foundGamesFuture.get();

        assertNotNull(foundGames);
        assertEquals(1, foundGames.size());
        assertEquals(game, foundGames.get(0));

        verify(searchFilterRepository, times(1)).findByGamePriceBetween(minPrice, maxPrice);
    }

    @Test
    void testSearchNoResult() throws ExecutionException, InterruptedException {
        String priceRange = "100.00,200.00";
        double minPrice = 100.00;
        double maxPrice = 200.00;

        when(searchFilterRepository.findByGamePriceBetween(minPrice, maxPrice)).thenReturn(Collections.emptyList());

        CompletableFuture<List<Game>> foundGamesFuture = priceRangeSearchStrategy.search(priceRange);
        List<Game> foundGames = foundGamesFuture.get();

        assertNotNull(foundGames);
        assertTrue(foundGames.isEmpty());

        verify(searchFilterRepository, times(1)).findByGamePriceBetween(minPrice, maxPrice);
    }

    @Test
    void testSearchInvalidFormat() {
        String priceRange = "InvalidFormat";

        Exception exception = assertThrows(NumberFormatException.class, () -> {
            priceRangeSearchStrategy.search(priceRange).join();
        });

        assertEquals("For input string: \"InvalidFormat\"", exception.getMessage());

        verify(searchFilterRepository, times(0)).findByGamePriceBetween(anyDouble(), anyDouble());
    }

    @Test
    void testSearchException() {
        String priceRange = "30.00,60.00";
        double minPrice = 30.00;
        double maxPrice = 60.00;

        when(searchFilterRepository.findByGamePriceBetween(minPrice, maxPrice)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            CompletableFuture<List<Game>> foundGamesFuture = priceRangeSearchStrategy.search(priceRange);
            foundGamesFuture.join();
        });

        assertEquals("Database error", exception.getMessage());

        verify(searchFilterRepository, times(1)).findByGamePriceBetween(minPrice, maxPrice);
    }
}
