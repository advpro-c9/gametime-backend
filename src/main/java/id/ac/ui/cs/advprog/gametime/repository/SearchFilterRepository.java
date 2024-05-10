package id.ac.ui.cs.advprog.gametime.repository;

import id.ac.ui.cs.advprog.gametime.model.Game;
import org. springframework.stereotype.Repository;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SearchFilterRepository extends JpaRepository<Game, UUID>{

    List<Game> findByGameNameContaining(String name);
    List<Game> findByGameGenreIn(List<String> genres);
    List<Game> findByGamePriceBetween(double minPrice, double maxPrice);
}