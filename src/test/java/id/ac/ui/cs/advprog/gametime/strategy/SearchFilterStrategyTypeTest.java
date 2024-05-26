package id.ac.ui.cs.advprog.gametime.strategy;

import id.ac.ui.cs.advprog.gametime.repository.SearchFilterRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SearchFilterStrategyTypeTest {

    @Mock
    private SearchFilterRepository searchFilterRepository;

    private SearchFilterStrategyType searchFilterStrategyType;

    @BeforeEach
    public void setUp() {
        searchFilterStrategyType = new SearchFilterStrategyType();
    }

    @Test
    void testGetNameStrategy() {
        SearchFilterStrategy strategy = searchFilterStrategyType.getStrategy("NAME", searchFilterRepository);
        assertNotNull(strategy);
        assertTrue(strategy instanceof NameSearchStrategy);
    }

    @Test
    void testGetGenresStrategy() {
        SearchFilterStrategy strategy = searchFilterStrategyType.getStrategy("GENRES", searchFilterRepository);
        assertNotNull(strategy);
        assertTrue(strategy instanceof GenresSearchStrategy);
    }

    @Test
    void testGetPriceRangeStrategy() {
        SearchFilterStrategy strategy = searchFilterStrategyType.getStrategy("PRICE RANGE", searchFilterRepository);
        assertNotNull(strategy);
        assertTrue(strategy instanceof PriceRangeSearchStrategy);
    }

    @Test
    void testGetStrategyInvalidType() {
        SearchFilterStrategy strategy = searchFilterStrategyType.getStrategy("INVALID", searchFilterRepository);
        assertNull(strategy);
    }
}
