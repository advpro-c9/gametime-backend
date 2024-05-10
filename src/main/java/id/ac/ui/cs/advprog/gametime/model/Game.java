package id.ac.ui.cs.advprog.gametime.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "games")
@Getter @Setter
public class Game {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID gameId;

    @Column(nullable = false)
    private String gameName;

    @Column(nullable = false)
    private String gameDescription;

    @Column(nullable = false)
    private String gameGenre;

    @Column(nullable = false)
    private double gamePrice;
}