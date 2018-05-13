import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public interface Animation {
    /**
     * this method draws the current state of
     * the animation object to the screen.
     * @param d a draw surface to draw on.
     * @param dt the dt value of this game.
     */
    void doOneFrame(DrawSurface d, double dt);
    /**
     * this method tells if the animation drawing should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    boolean shouldStop();
}