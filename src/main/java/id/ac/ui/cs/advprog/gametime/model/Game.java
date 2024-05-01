package id.ac.ui.cs.advprog.gametime.model;
import java.util.List;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @Entity @NoArgsConstructor @Table(name = "Game")
public class Game {
    @Id @Column(name = "game_id")
    private String gameId;
    @Column(name = "game_name")
    private String gameName;
    @ElementCollection
    @CollectionTable(name = "game_genres", joinColumns = @JoinColumn(name = "gameId"))
    @Column(name = "game_genre")
    private List<String> gameGenres;
    @Column(name = "game_price")
    private double gamePrice;
}