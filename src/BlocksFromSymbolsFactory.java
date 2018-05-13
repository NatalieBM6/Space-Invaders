import java.util.Map;

/**
 *
 * @author Natalie.
 *
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spaceWidths;
    private Map<String, BlockCreator> blockCreators;
    /**
     * The function builds a BlocksFromSymbolsFactory.
     * @param spaceWidths a map.
     * @param blockCreators a map.
     */
    public BlocksFromSymbolsFactory(Map<String, Integer> spaceWidths, Map<String, BlockCreator> blockCreators) {
        this.spaceWidths = spaceWidths;
        this.blockCreators = blockCreators;
    }
    /**
     * The function checks if a symbol exists.
     * @param s a symbol.
     * @return if a symbol exists.
     */
    public boolean isSpaceSymbol(String s) {
        if (spaceWidths.get(s) == null) {
            return false;
        }
        return true;
    }
    /**
     * The function checks if a symbol exists.
     * @param s a symbol.
     * @return if a symbol exists.
     */
    public boolean isBlockSymbol(String s) {
        if (blockCreators.get(s) == null) {
            return false;
        }
        return true;
    }
    /**
     * The function returns a block according to the factory.
     * @param s a symbol.
     * @param xpos x coordinate.
     * @param ypos y coordinate.
     * @return a block according to the factory.
     */
    public Block getBlock(String s, int xpos, int ypos) {
        return blockCreators.get(s).create(xpos, ypos);
    }
    /**
     * The function finds the space width.
     * @param s a symbol.
     * @return the space width.
     */
    public int getSpaceWidth(String s) {
        return spaceWidths.get(s);
    }
}
