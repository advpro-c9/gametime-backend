package id.ac.ui.cs.advprog.gametime.repository;

import id.ac.ui.cs.advprog.gametime.model.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class SearchFilterRepositoryTest {

    SearchFilterRepository searchFilterRepository;
    List<Game> games;

    @BeforeEach
    void setUp() {
        searchFilterRepository = new SearchFilterRepository();
        games = new ArrayList<>();

        Game game1 = new Game();
        game1.setGameId("1");
        game1.setGameName("Game A");
        game1.setGameDescription("Desc Game A");
        game1.setGamePrice(20.0);
        game1.setGameGenres(Arrays.asList("Action", "Adventure"));
        games.add(game1);

        Game game2 = new Game();
        game2.setGameId("2");
        game2.setGameName("Game B");
        game2.setGameDescription("Desc Game B");
        game2.setGamePrice(30.0);
        game2.setGameGenres(Arrays.asList("Strategy", "Action"));
        games.add(game2);

        Game game3 = new Game();
        game3.setGameId("3");
        game3.setGameName("Game C");
        game3.setGameDescription("Desc Game B");
        game3.setGamePrice(40.0);
        game3.setGameGenres(Arrays.asList("RPG", "Adventure"));
        games.add(game3);
    }

    @Test
    void testCreate() {
        Game game = games.get(1);
        Game result = searchFilterRepository.create(game);

        Game findResult = searchFilterRepository.findById(games.get(1).getGameId());
        assertEquals(game.getGameId(), result.getGameId());
        assertEquals(game.getGameId(), findResult.getGameId());
        assertEquals(game.getGameName(), findResult.getGameName());
        assertEquals(game.getGameDescription(), findResult.getGameDescription());
        assertEquals(game.getGamePrice(), findResult.getGamePrice());
        assertEquals(game.getGameGenres(), findResult.getGameGenres());
    }

    @Test
    void testCreateInvalid() {
        Game game0 = games.get(0);
        Game game1 = games.get(1);
        Game result = searchFilterRepository.create(game0);

        assertNotEquals(game1, result);
    }

    @Test
    void testFindAll() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        Iterator<Game> iterator = searchFilterRepository.findAll();
        int count = 0;
        while (iterator.hasNext()) {
            Game nextGame = iterator.next();
            assertTrue(games.contains(nextGame));
            count++;
        }

        assertEquals(games.size(), count);
    }

    @Test
    void testFindAllInvalid() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        Iterator<Game> iterator = searchFilterRepository.findAll();
        int count = 0;
        while (iterator.hasNext()) {
            Game nextGame = iterator.next();
            assertTrue(games.contains(nextGame));
            count++;
        }

        assertNotEquals(games.size()+1, count);
    }

    @Test
    void testFindById() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        Game findResult = searchFilterRepository.findById(games.get(1).getGameId());
        assertEquals(games.get(1).getGameId(), findResult.getGameId());
        assertEquals(games.get(1).getGameName(), findResult.getGameName());
        assertEquals(games.get(1).getGameDescription(), findResult.getGameDescription());
        assertEquals(games.get(1).getGamePrice(), findResult.getGamePrice());
        assertEquals(games.get(1).getGameGenres(), findResult.getGameGenres());
    }

    @Test
    void testFindByIdInvalid() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        assertNull(searchFilterRepository.findById("999"));
    }

    @Test
    void testFindByName() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        List <Game> findResult = searchFilterRepository.findByName(games.get(1).getGameName());
        assertEquals(games.get(1).getGameId(), findResult.getFirst().getGameId());
        assertEquals(games.get(1).getGameName(), findResult.getFirst().getGameName());
        assertEquals(games.get(1).getGameDescription(), findResult.getFirst().getGameDescription());
        assertEquals(games.get(1).getGamePrice(), findResult.getFirst().getGamePrice());
        assertEquals(games.get(1).getGameGenres(), findResult.getFirst().getGameGenres());
    }

    @Test
    void testFindByNameInvalid() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        assertTrue(searchFilterRepository.findByName("Nonexistent Game").isEmpty());
    }

    @Test
    void testFindByPriceRange() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        List<Game> findResult = searchFilterRepository.findByPriceRange(20.0, 35.0);
        assertEquals(games.get(1).getGameId(), findResult.get(1).getGameId());
        assertEquals(games.get(1).getGameName(), findResult.get(1).getGameName());
        assertEquals(games.get(1).getGameDescription(), findResult.get(1).getGameDescription());
        assertEquals(games.get(1).getGamePrice(), findResult.get(1).getGamePrice());
        assertEquals(games.get(1).getGameGenres(), findResult.get(1).getGameGenres());
    }

    @Test
    void testFindByPriceRangeInvalid() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        assertTrue(searchFilterRepository.findByPriceRange(10.0, 15.0).isEmpty());
    }

    @Test
    void testFindByGenres() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        List<Game> findResult = searchFilterRepository.findByGenres(Arrays.asList("Strategy", "Action"));
        assertEquals(games.get(1).getGameId(), findResult.get(1).getGameId());
        assertEquals(games.get(1).getGameName(), findResult.get(1).getGameName());
        assertEquals(games.get(1).getGameDescription(), findResult.get(1).getGameDescription());
        assertEquals(games.get(1).getGamePrice(), findResult.get(1).getGamePrice());
        assertEquals(games.get(1).getGameGenres(), findResult.get(1).getGameGenres());
    }

    @Test
    void testFindByGenresInvalid() {
        for (Game game : games) {
            searchFilterRepository.create(game);
        }

        assertTrue(searchFilterRepository.findByGenres(Arrays.asList("Puzzle", "Simulation")).isEmpty());
    }
}
