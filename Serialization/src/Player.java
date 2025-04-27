import java.io.Serializable;

public class Player implements Serializable {
    private String name;
    private int score;

    public String getName() {
        return name;
    }

    public Player setName(String name) {
        this.name = name;
        return this;
    }

    public int getScore() {
        return score;
    }

    public Player setScore(int score) {
        this.score = score;
        return this;
    }

    public Player(String name, int score) {
        this.name = name;
        this.score = score;
    }
}
