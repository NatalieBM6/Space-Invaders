/**
 *
 * @author Natalie.
 *
 */
public interface HitListener {
    /**
     * this method is called whenever the beingHit object is being hit.
     * @param beingHit the block that is being hit.
     * @param hitter the hitting ball.
     */
    void hitEvent(Block beingHit, Ball hitter);
    /**
     * this method is called whenever the beingHit object is being hit.
     * @param beingHit the paddle that is being hit.
     * @param hitter the hitting ball.
     */
    void hitEvent(Paddle beingHit, Ball hitter);
}
