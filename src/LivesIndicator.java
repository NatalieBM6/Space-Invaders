import java.awt.Color;

import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class LivesIndicator implements Sprite {
    private Counter lives;
    private Rectangle rectangle;
    /**
     * construct a lives indicator from
     * a given lives counter object.
     * @param lives the given lives counter.
     */
    public LivesIndicator(Counter lives) {
       this.lives = lives;
       this.rectangle = new Rectangle(0, 0, 200, 15);
    }
    /**
     * this method draws the lives indicator on given DrawSurface.
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
                          "Lives: "
                        + Integer.toString(this.lives.getValue()), 13);
    }
    /**
     * this method notifies the lives indicator that a time unit has passed.
     * @param dt the dt value of this game.
     */
    public void timePassed(double dt) {
    }
    /**
     * this method adds the lives indicator to a game.
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
       game.addSprite(this);
       // what about removing all the indicators????
    }
}