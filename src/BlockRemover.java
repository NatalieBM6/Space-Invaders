/**
 *
 * @author Natalie.
 *
 */
public class BlockRemover implements HitListener {
   private GameLevel game;
   private Counter numberOfBlocksToRemove;
   /**
    * construct a block remover from a game and and counter of blocks.
    * @param game a game to remove blocks from.
    * @param numberOfBlocksToRemove the number of blocks to remove.
    */
   public BlockRemover(GameLevel game, Counter numberOfBlocksToRemove) {
       this.game = game;
       this.numberOfBlocksToRemove = numberOfBlocksToRemove;
   }
   /**
    * this method is called whenever the beingHit object is being hit.
    * the method removes from the game blocks that reach 0 hit-points and
    * updates the number of blocks that left in the game.
    * @param beingHit the block that is being hit.
    * @param hitter the hitting ball.
    */
   public void hitEvent(Block beingHit, Ball hitter) {
       if (beingHit.getHits() == 0) {
           beingHit.removeHitListener(this);
           beingHit.removeFromGame(this.game);
           this.numberOfBlocksToRemove.decrease(1);
       }
   }
   /**
    * this method does nothing.
    * @param beingHit the paddle.
    * @param hitter the ball.
    */
   public void hitEvent(Paddle beingHit, Ball hitter) {
   }
}