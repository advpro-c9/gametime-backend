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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

class SearchFilterControllerTest {

    @Mock
    private SearchFilterService searchFilterService;

    @InjectMocks
    private SearchFilterController searchFilterController;

    private MockMvc mockMvc;

    private Game game;
    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(searchFilterController).build();

        game = new Game();
        game.setGameId(id);
        game.setGameName("Test Game");
        game.setGameDescription("Test Game Description");
        game.setGameGenre("Adventure");
        game.setGamePrice(49.99);
    }

    @Test
    void testSearchGame_HappyPath() throws Exception {
        String type = "genre";
        String input = "Adventure";

        when(searchFilterService.search(type, input)).thenReturn(Arrays.asList(game));

        mockMvc.perform(get("/api/games/search/{type}/{input}", type, input))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].gameId").value(game.getGameId().toString()))
                .andExpect(jsonPath("$[0].gameName").value(game.getGameName()))
                .andExpect(jsonPath("$[0].gameDescription").value(game.getGameDescription()))
                .andExpect(jsonPath("$[0].gameGenre").value(game.getGameGenre()))
                .andExpect(jsonPath("$[0].gamePrice").value(game.getGamePrice()));

        verify(searchFilterService, times(1)).search(type, input);
    }

    @Test
    void testSearchGame_UnhappyPath_NoResult() throws Exception {
        String type = "genre";
        String input = "NonExistingGenre";

        when(searchFilterService.search(type, input)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/api/games/search/{type}/{input}", type, input))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(searchFilterService, times(1)).search(type, input);
    }
}
