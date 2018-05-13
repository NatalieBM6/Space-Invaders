import java.awt.Color;
import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class PauseScreenAnimation implements Animation {
    /**
     * this method draws each frame of the animation of the pause screen.
     * and checks if user pressed SPACE key to close it.
     * @param d the DrawSurface to draw on.
     * @param dt the dt value of this game.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLACK);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
        d.setColor(Color.decode("#1e7f00"));
        d.fillRectangle(0, d.getHeight() / 2 - 160, d.getWidth(), 150);
        d.setColor(Color.GREEN);
        d.fillRectangle(0, d.getHeight() / 2 - 160, d.getWidth(), 3);
        d.drawText(290, d.getHeight() / 2 - 100, "Paused", 70);
        d.drawText(270, d.getHeight() / 2 - 30, "press space to continue", 25);
        d.fillRectangle(0, d.getHeight() / 2 - 10, d.getWidth(), 3);
    }
    /**
     * this method tells if the animation drawing should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}
