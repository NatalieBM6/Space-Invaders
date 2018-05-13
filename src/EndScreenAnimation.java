import java.awt.Color;

import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class EndScreenAnimation implements Animation {
    private int score;
    private boolean isWin;
    /**
     * construct end screen object from a score counter, keyboard sensor,
     * and a boolean win variable.
     * @param score the score counter.
     * @param isWin the boolean variable.
     */
    public EndScreenAnimation(Counter score, boolean isWin) {
        this.score = score.getValue();
        this.isWin = isWin;
    }
    /**
     * this method draws each frame of the animation of the end screen.
     * and checks if user pressed SPACE key to close it.
     * @param surface the DrawSurface to draw on.
     * @param dt the dt value of this game.
     */
    public void doOneFrame(DrawSurface surface, double dt) {
        surface.setColor(Color.BLACK);
        surface.fillRectangle(0, 0, surface.getWidth(), surface.getHeight());
        surface.setColor(Color.CYAN);
        if (this.isWin) {
            surface.setColor(Color.PINK);
            surface.drawText(190, 200, "You Won!!", 100);
            surface.setColor(Color.MAGENTA);
            surface.drawText(192, 196, "You Won!!", 100);
        } else {
            surface.setColor(Color.CYAN);
            surface.drawText(190, 200, "You Lost:(", 100);
            surface.setColor(Color.BLUE);
            surface.drawText(192, 196, "You Lost:(", 100);
        }
        surface.setColor(Color.WHITE);
        surface.drawText(260, 450, "Press space to continue", 25);
        surface.setColor(Color.YELLOW);
        surface.drawText(50, 580, "Final score: " + this.score, 30);
    }
    /**
     * this method return true if
     * the screen has to be closed, false otherwise.
     * @return true if the screen has to be closed.
     * false otherwise.
     */
    public boolean shouldStop() {
        return false;
    }
}