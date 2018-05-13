import biuoop.DrawSurface;
/**
 *
 * @author Natalie.
 *
 */
public interface Sprite {
   /**
    * The function draws the sprite to the screen.
    * @param d a surface.
    */
   void drawOn(DrawSurface d);
   /**
    * The function notifies the sprite that time has passed.
    * @param dt is the time passed from previous frame.
    */
   void timePassed(double dt);
}