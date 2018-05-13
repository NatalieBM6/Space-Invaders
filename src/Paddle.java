import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *
 * @author Natalie.
 *
 */
public class Paddle implements Sprite, Collidable, HitNotifier {
    private Rectangle rectangle;
    private Color color;
    private KeyboardSensor keyboard;
    private int horizontalBound;
    private int verticalBound;
    private int margin;
    private int speed;
    private long lastTimeShot;
    private List<HitListener> hitListeners;
    /**
     * construct a paddle from a rectangle, color,
     * keyboard sensor,and a margin size.
     * @param rec the given rectangle.
     * @param color the paddle's color.
     * @param keyboard the keyboard sensor.
     * @param margin the margin size.
     */
    public Paddle(Rectangle rec, Color color,
                  KeyboardSensor keyboard, int margin) {
        this.rectangle = rec;
        this.color = color;
        this.margin = margin;
        this.keyboard = keyboard;
        this.lastTimeShot = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * construct a paddle from a upper left point,
     * width, height, color,
     * keyboard sensor,and a margin size.
     * @param upperLeft the upper left point.
     * @param width the paddle's width.
     * @param height the paddle's height.
     * @param color the paddle's color.
     * @param keyboard the keyboard sensor.
     * @param margin the margin size.
     */
    public Paddle(Point upperLeft, double width, double height, Color color,
                  KeyboardSensor keyboard, int margin) {
        this.rectangle = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.margin = margin;
        this.keyboard = keyboard;
        this.lastTimeShot = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * construct a paddle from two coordinates,
     * width, height, color,
     * keyboard sensor,and a margin size.
     * @param x the x coordinate of the initial location
     * of the paddle's upper left corner.
     * @param y the y coordinate of the initial location
     * of the paddle's upper left corner.
     * @param width the paddle's width.
     * @param height the paddle's height.
     * @param color the paddle's color.
     * @param keyboard the keyboard sensor.
     * @param margin the margin size.
     */
    public Paddle(double x, double y, double width, double height,
                  Color color, KeyboardSensor keyboard, int margin) {
        this.rectangle = new Rectangle(x, y, width, height);
        this.color = color;
        this.margin = margin;
        this.keyboard = keyboard;
        this.lastTimeShot = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * this method sets the paddle's boundaries.
     * @param hBound the horizontal boundary.
     * @param vBound the vertical boundary.
     */
    public void setBounds(int hBound, int vBound) {
        this.horizontalBound = hBound;
        this.verticalBound = vBound;
    }
    /**
     * this method sets the paddle speed.
     * @param s the paddle's speed.
     */
    public void setSpeed(int s) {
        this.speed = s;
    }
    /**
     * this method moves the paddle to the left.
     * @param dt the dt value of this game.
     */
    public void moveLeft(double dt) {
        int spd = (int) (Math.floor(this.speed * dt));
        if (this.keyboard.isPressed(KeyboardSensor.LEFT_KEY)
         && this.rectangle.getUpperLeft().getX() > this.margin) {
            this.rectangle = new Rectangle(
                    this.rectangle.getUpperLeft().getX() - spd,
                    this.rectangle.getUpperLeft().getY(),
                    this.rectangle.getWidth(),
                    this.rectangle.getHeight());
        }
    }
    /**
     * this method moves the paddle to the right.
     * @param dt the dt value of this game.
     */
    public void moveRight(double dt) {
        int spd = (int) (Math.floor(this.speed * dt));
        if (this.keyboard.isPressed(KeyboardSensor.RIGHT_KEY)
         && this.rectangle.getUpperLeft().getX()
         + this.rectangle.getWidth()
          < this.horizontalBound - this.margin) {
            this.rectangle = new Rectangle(
                    this.rectangle.getUpperLeft().getX() + spd,
                    this.rectangle.getUpperLeft().getY(),
                    this.rectangle.getWidth(),
                    this.rectangle.getHeight());
        }
    }
    /**
     * this method shoots.
     * @param game the gameLevel object.
     * @param dt the dt value of this game.
     */
    public void shoot(GameLevel game, double dt) {
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            if (System.currentTimeMillis() - lastTimeShot > 350) {
                this.lastTimeShot = System.currentTimeMillis();
                Ball shot = game.getPlayerShot(new Point(
                        this.getCollisionRectangle().getTop().middle().getX(),
                        this.getCollisionRectangle().getTop().middle().getY()));
                shot.setVelocity(0, -300);
                shot.addToGame(game);
            }
        }
    }
    /**
     * this method notifies the paddle that a time unit has passed.
     * @param dt the dt value of this game.
     */
    public void timePassed(double dt) {
        this.moveLeft(dt);
        this.moveRight(dt);
    }
    /**
     * this method draws the paddle on given DrawSurface.
     * @param d the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle(
                (int) Math.round(this.rectangle.getUpperLeft().getX()),
                (int) Math.round(this.rectangle.getUpperLeft().getY()),
                (int) Math.round(this.rectangle.getWidth()),
                (int) Math.round(this.rectangle.getHeight()));
        d.setColor(Color.BLACK);
        d.drawRectangle(
                (int) Math.round(this.rectangle.getUpperLeft().getX()),
                (int) Math.round(this.rectangle.getUpperLeft().getY()),
                (int) Math.round(this.rectangle.getWidth()),
                (int) Math.round(this.rectangle.getHeight()));
    }
    /**
     * this method returns the rectangle that defines the paddle.
     * @return the rectangle that defines the paddle.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * this method gets collision point and velocity
     * and returns a new velocity according to the hit location on the block.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return the velocity according to the hit location on the block
     */
    public Velocity hitBySegment(Point collisionPoint,
                                 Velocity currentVelocity) {
        double segmentLength = this.rectangle.getWidth() / 5;
        Line leftSegment = new Line(this.rectangle.getUpperLeft().getX(),
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + segmentLength,
                this.rectangle.getUpperLeft().getY());
        Line middleLeftSegment = new Line(
                this.rectangle.getUpperLeft().getX() + segmentLength,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + 2 * segmentLength,
                this.rectangle.getUpperLeft().getY());
        Line middleSegment = new Line(
                this.rectangle.getUpperLeft().getX() + 2 * segmentLength,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + 3 * segmentLength,
                this.rectangle.getUpperLeft().getY());
        Line middleRightSegment = new Line(
                this.rectangle.getUpperLeft().getX() + 3 * segmentLength,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + 4 * segmentLength,
                this.rectangle.getUpperLeft().getY());
        Line rightSegment = new Line(
                this.rectangle.getUpperLeft().getX() + 4 * segmentLength,
                this.rectangle.getUpperLeft().getY(),
                this.rectangle.getUpperLeft().getX() + 5 * segmentLength,
                this.rectangle.getUpperLeft().getY());
        if (leftSegment.isContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(300, currentVelocity.getSpeed());
        }
        if (middleLeftSegment.isContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(330, currentVelocity.getSpeed());
        }
        if (middleSegment.isContainingPoint(collisionPoint)) {
            return new Velocity(currentVelocity.getDx(),
                               -currentVelocity.getDy());
        }
        if (middleRightSegment.isContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(30, currentVelocity.getSpeed());
        }
        if (rightSegment.isContainingPoint(collisionPoint)) {
            return Velocity.fromAngleAndSpeed(60, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }
    /**
     * this method gets collision point and velocity
     * and returns a new velocity according to the hit properties.
     * @param hitter the hitting ball.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return the velocity according to the hit properties.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        if (this.rectangle.getTop().isContainingPoint(collisionPoint)) {
            this.notifyHit(hitter);
            return hitBySegment(collisionPoint, currentVelocity);
        }
        if (this.rectangle.getLeft().isContainingPoint(collisionPoint)) {
            this.notifyHit(hitter);
            return Velocity.fromAngleAndSpeed(290, currentVelocity.getSpeed());
        }
        if (this.rectangle.getRight().isContainingPoint(collisionPoint)) {
            this.notifyHit(hitter);
            return Velocity.fromAngleAndSpeed(80, currentVelocity.getSpeed());
        }
        if (this.rectangle.getBottom().isContainingPoint(collisionPoint)) {
            this.notifyHit(hitter);
            return Velocity.fromAngleAndSpeed(0, currentVelocity.getSpeed());
        }
        return currentVelocity;
    }
    /**
     * this method adds the paddle to a game.
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    /**
     * this method removes the paddle from the game.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * this method moves the paddle to the middle bottom of the screen.
     */
    public void putInMiddle() {
        double x = this.horizontalBound / 2 - this.rectangle.getWidth() / 2;
        double y = this.verticalBound - this.rectangle.getHeight() - 10;
        Point middle = new Point(x, y);
        this.rectangle = new Rectangle(middle, this.rectangle.getWidth(),
                                               this.rectangle.getHeight());
    }
    /**
     * this method adds a given hit listener object
     * to a list of hit listeners in this hit notifier.
     * @param hl the hit listener object.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * this method removes a given hit listener object
     * from the list of hit listeners in this hit notifier.
     * @param hl the hit listener object.
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }
    /**
     * this method gets a ball that is hitting the block and notify
     * all the hit listeners about the hit.
     * @param hitter the hitting ball.
     */
    private void notifyHit(Ball hitter) {
        List<HitListener> listeners =
                new ArrayList<HitListener>(this.hitListeners);
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }
}