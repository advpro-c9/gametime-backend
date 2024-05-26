package id.ac.ui.cs.advprog.gametime.controller;

import id.ac.ui.cs.advprog.gametime.model.Game;
import id.ac.ui.cs.advprog.gametime.service.SearchFilterService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import java.util.Arrays;
import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(SearchFilterController.class)
class SearchFilterControllerTest {

    @MockBean
    private SearchFilterService searchFilterService;

    @Autowired
    private MockMvc mockMvc;

    private Game game;
    private final UUID id = UUID.randomUUID();

    @BeforeEach
    void setUp() {
        game = new Game();
        game.setGameId(id);
        game.setGameName("Test Game");
        game.setGameDescription("Test Game Description");
        game.setGameGenre("Adventure");
        game.setGamePrice(49.99);
    }

    @Test
    void testSearchGame() throws Exception {
        String type = "genre";
        String input = "Adventure";

        when(searchFilterService.search(type, input)).thenReturn(CompletableFuture.completedFuture(Arrays.asList(game)));

        MvcResult mvcResult = mockMvc.perform(get("/games/search/{type}/{input}", type, input))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
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
    void testSearchGameNoResult() throws Exception {
        String type = "genre";
        String input = "NonExistingGenre";

        when(searchFilterService.search(type, input)).thenReturn(CompletableFuture.completedFuture(Collections.emptyList()));

        MvcResult mvcResult = mockMvc.perform(get("/games/search/{type}/{input}", type, input))
                .andExpect(request().asyncStarted())
                .andReturn();

        mockMvc.perform(asyncDispatch(mvcResult))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(0));

        verify(searchFilterService, times(1)).search(type, input);
    }
}
