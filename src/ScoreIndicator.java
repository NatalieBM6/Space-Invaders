import java.awt.Color;
import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class ScoreIndicator implements Sprite {
    private Counter score;
    private Rectangle rectangle;
    /**
     * Construct a score indicator from a score counter object.
     * @param score the given score counter.
     */
    public ScoreIndicator(Counter score) {
        this.score = score;
        this.rectangle = new Rectangle(200, 0, 400, 15);
    }
    /**
     * this method draws the score indicator on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(Color.LIGHT_GRAY);
        surface.fillRectangle((int) this.rectangle.getUpperLeft().getX(),
                              (int) this.rectangle.getUpperLeft().getY(),
                              (int) this.rectangle.getWidth(),
                              (int) this.rectangle.getHeight());
        surface.setColor(Color.BLACK);
        surface.drawText((int) (this.rectangle.getUpperLeft().getX()
                              + this.rectangle.getWidth() / 2 - 20),
                         (int) (this.rectangle.getUpperLeft().getY()
                              + this.rectangle.getHeight() / 2 + 5),
                         "Score: "
                              + Integer.toString(this.score.getValue()), 13);
    }
    /**
     * this method notifies the score indicator that a time unit has passed.
     * @param dt the dt value of this game.
     */
    public void timePassed(double dt) {
    }
    /**
     * this method adds the score indicator to a game.
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
}