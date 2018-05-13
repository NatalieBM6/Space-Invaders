import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

/**
 *
 * @author Natalie.
 *
 */
public class AnimationRunner {
    private GUI gui;
    private Sleeper sleeper;
    private int framesPerSecond;
    private double dt;
    /**
     * construct an animation runner from a given gui object.
     * @param gui the given gui.
     * @param framesPerSecond number of frames per second.
     */
    public AnimationRunner(GUI gui,  int framesPerSecond) {
        this.gui = gui;
        this.sleeper = new Sleeper();
        this.framesPerSecond = framesPerSecond;
        this.dt = 1 / (double) framesPerSecond;
    }
    /**
     * this function contains the main graphic loop of the game.
     * the function gets an animation object and uses the gui to show it.
     * @param animation the animation to show.
     */
    public void run(Animation animation) {
        while (!animation.shouldStop()) {
            long startTime = System.currentTimeMillis();
            DrawSurface d = this.gui.getDrawSurface();
            animation.doOneFrame(d, this.dt);
            this.gui.show(d);
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep =
                    1000 / this.framesPerSecond - usedTime;
            if (milliSecondLeftToSleep > 0) {
                this.sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}