import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
/**
 *
 * @author Natalie.
 *
 */
public class SpriteCollection {
    private List<Sprite> spriteList;
    /**
     * this method constructs a new sprite collection.
     */
    public SpriteCollection() {
        this.spriteList = new ArrayList<Sprite>();
    }
    /**
     * this method adds a given sprite object to the collection.
     * @param s the sprite object to add.
     */
    public void addSprite(Sprite s) {
        this.spriteList.add(s);
    }
    /**
     * this method removes a sprite object from collection.
     * @param s the given sprite object.
     */
    public void removeSprite(Sprite s) {
        this.spriteList.remove(s);
    }
    /**
     * this method notifies all the sprite objects that a time unit has passed.
     * @param dt the dt value of this game.
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < spriteList.size(); ++i) {
            this.spriteList.get(i).timePassed(dt);
        }
    }
    /**
     * this method draws all the sprite objects on a given draw surface.
     * @param d the DrawSurface to draw on.
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < spriteList.size(); ++i) {
            this.spriteList.get(i).drawOn(d);
        }
    }
}