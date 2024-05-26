package id.ac.ui.cs.advprog.gametime.service;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import id.ac.ui.cs.advprog.gametime.strategy.SearchFilterStrategy;
import id.ac.ui.cs.advprog.gametime.strategy.SearchFilterStrategyType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SearchFilterServiceImplTest {

    @Mock
    private SearchFilterStrategyType searchFilterStrategyType;

    @Mock
    private SearchFilterRepository searchFilterRepository;

    @Mock
    private SearchFilterStrategy searchFilterStrategy;

    @InjectMocks
    private SearchFilterServiceImpl searchFilterServiceImpl;

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
    void testSearch() throws ExecutionException, InterruptedException {
        String type = "genre";
        String input = "Adventure";

        when(searchFilterStrategyType.getStrategy(type, searchFilterRepository)).thenReturn(searchFilterStrategy);
        when(searchFilterStrategy.search(input)).thenReturn(CompletableFuture.completedFuture(Arrays.asList(game)));

        CompletableFuture<List<Game>> foundGamesFuture = searchFilterServiceImpl.search(type, input);
        List<Game> foundGames = foundGamesFuture.get();

        assertNotNull(foundGames);
        assertEquals(1, foundGames.size());
        assertEquals(game, foundGames.get(0));

        verify(searchFilterStrategyType, times(1)).getStrategy(type, searchFilterRepository);
        verify(searchFilterStrategy, times(1)).search(input);
    }

    @Test
    void testSearchNoResult() throws ExecutionException, InterruptedException {
        String type = "genre";
        String input = "NonExistingGenre";

        when(searchFilterStrategyType.getStrategy(type, searchFilterRepository)).thenReturn(searchFilterStrategy);
        when(searchFilterStrategy.search(input)).thenReturn(CompletableFuture.completedFuture(Collections.emptyList()));

        CompletableFuture<List<Game>> foundGamesFuture = searchFilterServiceImpl.search(type, input);
        List<Game> foundGames = foundGamesFuture.get();

        assertNotNull(foundGames);
        assertTrue(foundGames.isEmpty());

        verify(searchFilterStrategyType, times(1)).getStrategy(type, searchFilterRepository);
        verify(searchFilterStrategy, times(1)).search(input);
    }

    @Test
    void testSearchInvalidType() {
        String type = "invalidType";
        String input = "Test";

        when(searchFilterStrategyType.getStrategy(type, searchFilterRepository)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            searchFilterServiceImpl.search(type, input);
        });

        assertEquals("Tipe " + type + " tidak sesuai untuk dicari", exception.getMessage());

        verify(searchFilterStrategyType, times(1)).getStrategy(type, searchFilterRepository);
        verify(searchFilterStrategy, times(0)).search(input);
    }

    @Test
    void testSearchStrategyThrowsException() {
        String type = "genre";
        String input = "Adventure";

        when(searchFilterStrategyType.getStrategy(type, searchFilterRepository)).thenReturn(searchFilterStrategy);
        when(searchFilterStrategy.search(input)).thenThrow(new RuntimeException("Database error"));

        Exception exception = assertThrows(RuntimeException.class, () -> {
            CompletableFuture<List<Game>> foundGamesFuture = searchFilterServiceImpl.search(type, input);
            foundGamesFuture.join();
        });

        assertEquals("Database error", exception.getMessage());

        verify(searchFilterStrategyType, times(1)).getStrategy(type, searchFilterRepository);
        verify(searchFilterStrategy, times(1)).search(input);
    }
}
