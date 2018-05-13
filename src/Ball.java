import java.awt.Color;
import biuoop.DrawSurface;

/**
 * @author Natalie.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private Color color;
    private Velocity velocity;
    private GameEnvironment gameEnvironment;
    /**
     * construct a ball from point, radius and color.
     * @param center the initial location of the ball's center.
     * @param r the ball's radius.
     * @param color the ball's color.
     */
    public Ball(Point center, int r, Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }
    /**
     * construct a ball from two coordinates, radius and color.
     * @param x the x coordinate of the initial location of the ball's center.
     * @param y the y coordinate of the initial location of the ball's center.
     * @param r the ball's radius.
     * @param color the ball's color.
     */
    public Ball(int x, int y, int r, Color color) {
        this.center = new Point((double) x, (double) y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = new GameEnvironment();
    }
    /**
     * construct a ball from point, radius ,color and a game environment.
     * @param center the initial location of the ball's center.
     * @param r the ball's radius.
     * @param color the ball's color.
     * @param gameEnvironment the game environment of the ball.
     */
    public Ball(Point center, int r, Color color,
                GameEnvironment gameEnvironment) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * construct a ball from two coordinates, radius,
     * color and a game environment.
     * @param x the x coordinate of the initial location of the ball's center.
     * @param y the y coordinate of the initial location of the ball's center.
     * @param r the ball's radius.
     * @param color the ball's color.
     * @param gameEnvironment the game environment of the ball.
     */
    public Ball(int x, int y, int r, Color color,
                GameEnvironment gameEnvironment) {
        this.center = new Point((double) x, (double) y);
        this.radius = r;
        this.color = color;
        this.velocity = new Velocity(0, 0);
        this.gameEnvironment = gameEnvironment;
    }
    /**
     * this method returns the rounded x coordinate of the ball's center.
     * @return the rounded x coordinate of the ball's center.
     */
    public int getX() {
        return (int) Math.round(this.center.getX());
    }
    /**
     * this method returns the rounded y coordinate of the ball's center.
     * @return the rounded y coordinate of the ball's center.
     */
    public int getY() {
        return (int) Math.round(this.center.getY());
    }
    /**
     * this method returns the ball's center point.
     * @return the ball's center point.
     */
    public Point getCenter() {
        return this.center;
    }
    /**
     * this method returns the ball's radius.
     * @return the ball's radius.
     */
    public int getSize() {
        return this.radius;
    }
    /**
     * this method returns the ball's color.
     * @return the ball's color.
     */
    public Color getColor() {
        return this.color;
    }
    /**
     * this method draws the ball on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(this.color);
        surface.fillCircle(this.getX(), this.getY(), this.radius);
        surface.setColor(Color.BLACK);
        surface.drawCircle(this.getX(), this.getY(), this.radius);
    }
    /**
     * this method sets the ball's velocity.
     * @param v Velocity object to apply on the ball.
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }
    /**
     * this method sets the ball's velocity.
     * @param dx the change in position on the x axis.
     * @param dy the change in position on the y axis.
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);
    }
    /**
     * this method returns the ball's velocity.
     * @return the ball's velocity.
     */
    public Velocity getVelocity() {
        return this.velocity;
    }
    /**
     * this method sets a new center for the ball.
     * @param x the x coordinate for the new center.
     * @param y the y coordinate for the new center.
     */
    public void setCenter(double x, double y) {
        this.center = new Point(x, y);
    }
    /**
     * this method returns the game environment of the ball.
     * @return the game environment of the ball.
     */
    public GameEnvironment getEnvironment() {
        return this.gameEnvironment;
    }
    /**
     * this method moves the ball's center according to it's velocity.
     * @param dt the dt value of this game.
     */
    public void moveOneStep(double dt) {
        Velocity v = this.velocity.adjust(dt);
        Point tmp = v.applyToPoint(this.center);
        Point startOfTrajectory = new Point(Math.round(this.center.getX()),
                                            Math.round(this.center.getY()));
        Point endOfTrajectory = new Point(Math.round(tmp.getX()),
                                          Math.round(tmp.getY()));
        Line trajectory = new Line(startOfTrajectory, endOfTrajectory);
        if (
          this.gameEnvironment.getClosestCollision(trajectory) != null) {
                this.setVelocity(this.gameEnvironment.getClosestCollision(
                trajectory).collisionObject().hit(this,
                this.gameEnvironment.getClosestCollision(
                trajectory).collisionPoint(),
                this.getVelocity()));
        }
        v = this.velocity.adjust(dt);
        this.center = v.applyToPoint(this.center);
    }
    /**
     * this method notifies the ball that a time unit has passed.
     * @param dt the dt value of this game.
     */
    public void timePassed(double dt) {
        this.gameEnvironment.stuckHandel(this);
        this.moveOneStep(dt);
    }
    /**
     * this method adds the ball to the game's sprite list.
     * @param game the game that is currently played.
     */
    public void addToGame(GameLevel game) {
        game.addSprite(this);
    }
    /**
     * this method removes the ball from the game's sprite list.
     * @param game the game that is currently played.
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }
 }