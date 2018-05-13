import java.awt.Color;

import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class ErrorAnimation implements Animation {
    private String generalError;
    private String reason;
    private String details;
    private String exit;
    /**
     * construct an error animation object from four error messages.
     * @param generalError the general Error.
     * @param reason the reason of the error.
     * @param details details.
     * @param exit the key that closes the animation.
     */
    public ErrorAnimation(String generalError, String reason,
                          String details, String exit) {
        this.generalError = generalError;
        this.reason = reason;
        this.details = details;
        this.exit = exit;
    }
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
        d.drawText(310, d.getHeight() / 2 - 90, "Error", 70);
        d.drawText(290, d.getHeight() / 2 - 40, this.generalError, 25);
        if (this.reason.length() < 70) {
            d.drawText(20, d.getHeight() / 2 + 60, this.reason, 25);
            d.drawText(20, d.getHeight() / 2 + 90,
                       "Details: " + this.details, 25);
        } else {
            String reason1 = this.reason.substring(0, 70);
            String reason2 = this.reason.substring(70);
            d.drawText(20, d.getHeight() / 2 + 60, reason1, 25);
            d.drawText(20, d.getHeight() / 2 + 90, reason2, 25);
            d.drawText(20, d.getHeight() / 2 + 120,
                       "Details: " + this.details, 25);
        }
        d.drawText(290, d.getHeight() - 40,
                   "Press \"" + this.exit + "\" to exit...", 25);
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
