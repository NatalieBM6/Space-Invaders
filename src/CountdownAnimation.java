import java.awt.Color;
import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class CountdownAnimation implements Animation {
    private boolean running;
    private long numOfMillis;
    private int countFrom;
    private int initialCount;
    private SpriteCollection gameScreen;
    private long initiationTime;
    /**
     * construct a count down animation object from time of running
     * of the animation, number to count down from,
     * and the game's sprite.
     * @param numOfSeconds time of displaying the count down animation.
     * @param countFrom the number to count down from.
     * @param gameScreen the game's sprites.
     */
    public CountdownAnimation(double numOfSeconds,
            int countFrom, SpriteCollection gameScreen) {
        this.running = true;
        this.numOfMillis = (long) (numOfSeconds * 1000);
        this.countFrom = countFrom;
        this.initialCount = countFrom;
        this.gameScreen = gameScreen;
        this.initiationTime = System.currentTimeMillis();
    }
    /**
     * this method gets a color and a DrawSurface
     * and draws the background on the given DrawSurface.
     * @param d the given DrawSurface.
     * @param color the given color.
     */
    public void setBackground(DrawSurface d, Color color) {
        d.setColor(color);
        d.fillRectangle(0, 0, d.getWidth(), d.getHeight());
    }
    /**
     * this method draws each frame of the animation
     * of the count down animation on a given DrawSurface.
     * when count down reaches 0 it changes the running member to false,
     * so the animation will stop.
     * @param surface the DrawSurface to draw on.
     * @param dt the dt value of this game.
     */
    public void doOneFrame(DrawSurface surface, double dt) {
        if (this.countFrom == 0) {
            this.running = false;
        }
        this.setBackground(surface, Color.BLUE);
        this.gameScreen.drawAllOn(surface);
        surface.setColor(Color.decode("#fec04c"));
        surface.drawText(385, 450, Integer.toString(this.countFrom), 65);
        if (System.currentTimeMillis() - this.initiationTime
          > this.numOfMillis / this.initialCount) {
            this.initiationTime = System.currentTimeMillis();
            this.countFrom--;
        }
    }
    /**
     * this method returns true if count down animation has to stop,
     * false otherwise.
     * @return true if count down animation has to stop, false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}