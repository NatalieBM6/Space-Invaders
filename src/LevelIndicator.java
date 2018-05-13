import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class LevelIndicator {
    private String name;
    /**
     * The function builds a LevelIndicator.
     * @param n the name of the level.
     */
    public LevelIndicator(String n) {
        this.name = n;
    }
    /**
     * The function draws the level's name.
     * @param d a surface.
     */
    public void drawOn(DrawSurface d) {
        d.drawText(600, 10, "Level Name: " + this.name, 10);
    }
    /**
     * The function doesn't do anything.
     * @param dt the time difference.
     */
    public void timePassed(double dt) {
    }
}
