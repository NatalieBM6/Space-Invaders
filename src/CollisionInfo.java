/**
 *
 * @author Natalie.
 *
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collisionObject;
    /**
     * The function builds CollisionInfo.
     * @param collisionPoint the point at which the collision occurs.
     * @param collisionObject the collidable object involved in the collision.
     */
    public CollisionInfo(Point collisionPoint, Collidable collisionObject) {
        this.collisionPoint = collisionPoint;
        this.collisionObject = collisionObject;
    }

    /**
     * The function finds the point at which the collision occurs.
     * @return the point at which the collision occurs.
     */
   public Point collisionPoint() {
       return this.collisionPoint;
   }

   /**
    * The function finds the collidable object involved in the collision.
    * @return the collidable object involved in the collision.
    */
   public Collidable collisionObject() {
       return this.collisionObject;
   }
}