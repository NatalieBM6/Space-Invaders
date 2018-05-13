/**
 *
 * @author Natalie.
 *
 */
public interface Collidable {
    /**
     * The function finds the the "collision shape" of the object.
     * @return the "collision shape" of the object.
     */
   Rectangle getCollisionRectangle();

   /**
    * The function finds the new velocity of an object after the hit.
    * @param collisionPoint the collision point.
    * @param currentVelocity the current velocity of an object.
    * @param hitter the ball that hits the block.
    * @return the new velocity expected after the hit.
    */
   Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}