import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Natalie.
 *
 */
public class GameEnvironment {
    private List<Collidable> collidableList;
    /**
     *  construct a game environment from a list of collidables.
     */
    public GameEnvironment() {
        this.collidableList = new ArrayList<Collidable>();
    }
    /**
     * this method adds a given collidable object to the collection.
     * @param c the given collidable object.
     */
    public void addCollidable(Collidable c) {
        this.collidableList.add(c);
    }
    /**
     * this method removes a given collidable object from the collection.
     * @param c the given collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.collidableList.remove(c);
    }
    /**
     * this method gets a trajectory line and
     * returns the closest collision information
     * (collision point & collision object).
     * if there is no collision, returns null.
     * @param trajectory the given line.
     * @return collision information, null if there isn't any.
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        if (this.collidableList.isEmpty()) {
            return null;
        }
        int i = 0;
        while (trajectory.closestIntersectionToStartOfLine(
               this.collidableList.get(i).getCollisionRectangle()) == null
            && i < this.collidableList.size() - 1) {
            i++;
        }
        Point closest = trajectory.closestIntersectionToStartOfLine(
                        this.collidableList.get(i).getCollisionRectangle());
        int collisionIndex = i;
        for (int j = i; j < this.collidableList.size(); j++) {
            if (trajectory.closestIntersectionToStartOfLine(
                this.collidableList.get(j).getCollisionRectangle()) != null) {
                if (closest.distance(trajectory.start())
                  > trajectory.closestIntersectionToStartOfLine(
                    this.collidableList.get(j).getCollisionRectangle()).
                    distance(trajectory.start())) {
                    closest = trajectory.closestIntersectionToStartOfLine(
                    this.collidableList.get(j).getCollisionRectangle());
                    collisionIndex = j;
                }
            }
        }
        return new CollisionInfo(closest,
                                 this.collidableList.get(collisionIndex));
    }
    /**
     * this method gets a ball and checks if it got stuck in
     * one of the collidable objects. if so, resets it's location
     * to the bottom of the screen.
     * @param ball the given ball.
     */
    public void stuckHandel(Ball ball) {
        if (this.collidableList.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.collidableList.size(); i++) {
            if (this.collidableList.get(i).getCollisionRectangle().
                isContainingPoint(ball.getCenter())) {
                Rectangle rec =
                        this.collidableList.get(i).getCollisionRectangle();
                Point inner = ball.getCenter();
                Line closestEdge =
                        this.collidableList.get(i).
                        getCollisionRectangle().closestEdge(inner);
                if (rec.getLeft().equals(closestEdge)) {
                    ball.setCenter(inner.getX() - 5, inner.getY());
                }
                if (rec.getRight().equals(closestEdge)) {
                    ball.setCenter(inner.getX() + 5, inner.getY());
                }
                if (rec.getTop().equals(closestEdge)) {
                    ball.setCenter(inner.getX(), inner.getY() - 5);
                }
                if (rec.getBottom().equals(closestEdge)) {
                    ball.setCenter(inner.getX(), inner.getY() + 5);
                }
            }
        }
    }
}