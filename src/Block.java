import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;

/**
 *
 * @author Natalie.
 *
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rectangle;
    private Map<Integer, Color> colors;
    private Map<Integer, Image> images;
    private Color borderColor;
    private boolean hasBorder;
    private int hits;
    private List<HitListener> hitListeners;
    /**
     * construct a basic block with default parameters,
     * located in the upper left corner of the screen (coordinates: x=0, y=0).
     */
    public Block() {
        this.rectangle = new Rectangle(0, 0, 1, 1);
        this.colors = new TreeMap<Integer, Color>();
        this.images = new TreeMap<Integer, Image>();
        this.borderColor = Color.BLACK;
        this.hasBorder = false;
        this.hits = 0;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * construct a new block from a given block according to its' parameters,
     * a new hitListener object is generated anyway.
     * @param other the given block.
     */
    public Block(Block other) {
        this.rectangle = other.rectangle;
        this.colors = other.colors;
        this.images = other.images;
        this.borderColor = other.borderColor;
        this.hasBorder = other.hasBorder;
        this.hits = other.hits;
        this.hitListeners = new ArrayList<HitListener>();
    }
    /**
     * this method returns the rectangle that defines the block.
     * @return the rectangle that defines the block.
     */
    public Rectangle getCollisionRectangle() {
        return this.rectangle;
    }
    /**
     * this method returns the block's current number of hits.
     * @return the block's current number of hits.
     */
    public int getHits() {
        return this.hits;
    }
    /**
     * this method gets a ball, a collision point and velocity and returns
     * a new velocity based on the hit's properties.
     * @param hitter the hitting ball.
     * @param collisionPoint the collision point.
     * @param currentVelocity the current velocity.
     * @return a new velocity based on the hit's properties.
     */
    public Velocity hit(Ball hitter, Point collisionPoint,
                        Velocity currentVelocity) {
        boolean flipX = false;
        boolean flipY = false;
        if (this.rectangle.getTop().isContainingPoint(collisionPoint)
             || this.rectangle.getBottom().isContainingPoint(collisionPoint)) {
            flipY = true;
        }
        if (this.rectangle.getLeft().isContainingPoint(collisionPoint)
             || this.rectangle.getRight().isContainingPoint(collisionPoint)) {
            flipX = true;
        }
        if ((flipX || flipY) && this.hits > 0) {
            this.hits--;
        }
        this.notifyHit(hitter);
        if (flipX && flipY) {
            return new Velocity(-currentVelocity.getDx(),
                                -currentVelocity.getDy());
        } else {
            if (flipX) {
                return new Velocity(-currentVelocity.getDx(),
                                     currentVelocity.getDy());
            }
            if (flipY) {
                return new Velocity(currentVelocity.getDx(),
                                   -currentVelocity.getDy());
            }
        }
        return currentVelocity;
    }
    /**
     * this method draws the block on given DrawSurface.
     * @param surface the DrawSurface to draw on.
     */
    public void drawOn(DrawSurface surface) {
        if (this.images.containsKey(this.hits)) {
            surface.drawImage(
                    (int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    this.images.get(this.hits));
        } else if (this.colors.containsKey(this.hits)) {
            surface.setColor(this.colors.get(this.hits));
            surface.fillRectangle(
                    (int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    (int) Math.round(this.rectangle.getWidth()),
                    (int) Math.round(this.rectangle.getHeight()));
        } else if (this.images.containsKey(0)) {
            surface.drawImage(
                    (int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    this.images.get(0));
        } else if (this.colors.containsKey(0)) {
            surface.setColor(this.colors.get(0));
            surface.fillRectangle(
                    (int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    (int) Math.round(this.rectangle.getWidth()),
                    (int) Math.round(this.rectangle.getHeight()));
        }
        if (this.hasBorder) {
            surface.setColor(this.borderColor);
            surface.drawRectangle(
                    (int) Math.round(this.rectangle.getUpperLeft().getX()),
                    (int) Math.round(this.rectangle.getUpperLeft().getY()),
                    (int) Math.round(this.rectangle.getWidth()),
                    (int) Math.round(this.rectangle.getHeight()));
        }
    }
    /**
     * this method notifies the block that a time unit has passed.
     * @param dt the dt value of this game.
     */
    public void timePassed(double dt) {
    }
    /**
     * this method adds the block to a game.
     * @param game the game.
     */
    public void addToGame(GameLevel game) {
        game.addCollidable(this);
        game.addSprite(this);
    }
    /**
     * this method removes the block from a game.
     * @param game the game.
     */
    public void removeFromGame(GameLevel game) {
        game.removeCollidable(this);
        game.removeSprite(this);
    }
    /**
     * this method adds a given hit listener to the block's hit listeners list.
     * @param hl the given hit listener.
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }
    /**
     * this method removes a given hit listener
     * from the block's hit listeners list.
     * @param hl the given hit listener.
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
    /**
     * this method sets the location of this block.
     * @param upperLeft the new location of the upper left corner of the block.
     */
    public void setUpperLeft(Point upperLeft) {
        this.rectangle = new Rectangle(upperLeft, this.rectangle.getWidth(),
                                                  this.rectangle.getHeight());
    }
    /**
     * this method sets the location of this block.
     * @param x the x coordinate of the upper left corner of the block.
     * @param y the y coordinate of the upper left corner of the block.
     */
    public void setUpperLeft(double x, double y) {
        Point upperLeft = new Point(x, y);
        this.setUpperLeft(upperLeft);
    }
    /**
     * this method sets the width of this block.
     * @param width the new width.
     */
    public void setWidth(int width) {
        this.rectangle = new Rectangle(this.rectangle.getUpperLeft(),
                                       width, this.rectangle.getHeight());
    }
    /**
     * this method sets the height of this block.
     * @param height the new height.
     */
    public void setHeight(int height) {
        this.rectangle = new Rectangle(this.rectangle.getUpperLeft(),
                                       this.rectangle.getWidth(), height);
    }
    /**
     * this method sets the number of hits of this block.
     * @param numberOfhits the number of hits.
     */
    public void setHits(int numberOfhits) {
        this.hits = numberOfhits;
    }
    /**
     * this method sets the color of this block's borders.
     * @param bordersColor the given color.
     */
    public void setBorderColor(Color bordersColor) {
        this.borderColor = bordersColor;
    }
    /**
     * this method sets the available colors of this block's filling.
     * @param filling the given colors.
     */
    public void setColors(Map<Integer, Color> filling) {
        this.colors = filling;
    }
    /**
     * this method sets the available images of this block's filling.
     * @param filling the given images.
     */
    public void setImages(Map<Integer, Image> filling) {
        this.images = filling;
    }
    /**
     * this method determines if this block has borders or not.
     * @param isHasBorder the wanted option.
     */
    public void setBorder(boolean isHasBorder) {
        this.hasBorder = isHasBorder;
    }
    /**
     * this method moves the ball's center according to the given velocity.
     * @param velocity the given velocity.
     * @param dt the dt value of this game.
     */
    public void move(Velocity velocity, double dt) {
        Velocity v = velocity.adjust(dt);
        double dx = Math.round(v.getDx());
        double dy = Math.round(v.getDy());
        int newX = (int) (this.rectangle.getUpperLeft().getX() + dx);
        int newY = (int) (this.rectangle.getUpperLeft().getY() + dy);
        this.setUpperLeft(newX, newY);
    }
}