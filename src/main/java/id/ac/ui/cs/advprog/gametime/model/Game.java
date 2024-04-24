package id.ac.ui.cs.advprog.gametime.model;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Game {
    private String gameId;
    private String gameName;
    private List<String> gameGenres;
    private double gamePrice;
}