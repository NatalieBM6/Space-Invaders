/**
 *
 * @author Natalie.
 *
 */
public interface BlockCreator {
    /**
     * The function creates a block at a specific location.
     * @param xpos x coordinate.
     * @param ypos y coordinate.
     * @return a block at a specific location.
     */
    Block create(int xpos, int ypos);
}
