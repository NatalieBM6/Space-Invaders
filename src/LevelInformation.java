import java.util.List;

/**
 *
 * @author Natalie.
 *
 */
public interface LevelInformation {
    /**
     * this method returns a string of the level's name.
     * @return string of the level's name.
     */
    String levelName();
    /**
     * this method returns the level's background.
     * @return the level's background.
     */
    Sprite getBackground();
    /**
     * this method returns number of balls of a specific level.
     * @return number of balls.
     */
    Integer numberOfBalls();
    /**
     * this method returns the number of blocks left on the screen.
     * @return number of blocks left on the screen.
     */
    Integer numberOfBlocksToRemove();
    /**
     * this method returns a list of level's blocks.
     * @return a list of the level's blocks.
     */
    List<Block> blocks();
    /**
     * this method returns a list of balls' velocities.
     * @return list list of balls' velocities.
     */
    List<Velocity> initialBallVelocities();
    /**
     * this method returns the paddle's speed.
     * @return paddle's speed.
     */
    Integer paddleSpeed();
    /**
     * this method returns the paddle's width.
     * @return paddle's width.
     */
    Integer paddleWidth();
    /**
     * this method returns the paddle's height.
     * @return paddle's height.
     */
    Integer paddleHeight();
}