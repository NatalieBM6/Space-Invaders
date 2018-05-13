import java.awt.Color;
import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class HighScoresAnimation implements Animation {
    private HighScoresTable table;
    /**
     * construct a high score animation object from a given high score table.
     * @param table the given high score table.
     */
    public HighScoresAnimation(HighScoresTable table) {
        this.table = table;
    }
    /**
     * this method draws each frame of the animation of the pause screen.
     * and checks if user pressed SPACE key to close it.
     * @param d the DrawSurface to draw on.
     * @param dt the dt value of this game.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.MAGENTA);
        d.drawText(100, 150, "High Scores Table", 80);
        for (int i = 0; i < this.table.getHighScores().size(); i++) {
            d.setColor(Color.YELLOW);
            d.drawText(280, 230 + 32 * i,
                   this.table.getHighScores().get(i).getName(), 30);
            d.drawText(470, 230 + 32 * i,
            Integer.toString(this.table.getHighScores().get(i).getScore()), 30);
        }
        d.setColor(Color.WHITE);
        d.drawText(240, 500, "Press space to continue", 30);
    }
    /**
     * this method tells if the animation drawing should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}