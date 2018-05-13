import java.io.Serializable;

/**
 *
 * @author Natalie.
 *
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;
    /**
     * The function builds a scoreInfo.
     * @param n a name.
     * @param score1 a score.
     */
    public ScoreInfo(String n, int score1) {
        this.name = n;
        this.score = score1;
    }
    /**
     * The function finds the name of the ScoreInfo.
     * @return the name of the ScoreInfo.
     */
    public String getName() {
        return this.name;
    }
    /**
     * The function finds the score of the ScoreInfo.
     * @return the score of the ScoreInfo.
     */
    public int getScore() {
        return this.score;
    }
}
