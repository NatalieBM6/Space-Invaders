/**
 *
 * @author Natalie.
 *
 */
public class ScoreTrackingListener implements HitListener {
   private Counter currentScore;
   /**
    * construct a score tracking listener from a score counter object.
    * @param scoreCounter the given score counter object.
    */
   public ScoreTrackingListener(Counter scoreCounter) {
      this.currentScore = scoreCounter;
   }
   /**
    * this method updates the game score when a block is being hit.
    * if the block that is being hit is removed,
    * score is increased by 10 points, 5 otherwise.
    * @param beingHit the block that is being hit.
    * @param hitter the hitting ball.
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHits() > 0) {
           this.currentScore.increase(100);
       }
       if (beingHit.getHits() == 0) {
           this.currentScore.increase(100);
       }
   }
   /**
    * this method does nothing.
    * @param beingHit the Paddle that is being hit.
    * @param hitter the hitting ball.
    */
   public void hitEvent(Paddle beingHit, Ball hitter) {
   }
}