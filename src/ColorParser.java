import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Natalie.
 *
 */
public class ColorParser {
    private Map<String, Color> colorMap;
    /**
     * The function builds a color parser.
     */
    public ColorParser() {
        this.colorMap = new HashMap<String, Color>();
        colorMap.put("black", Color.black);
        colorMap.put("blue", Color.blue);
        colorMap.put("cyan", Color.cyan);
        colorMap.put("gray", Color.gray);
        colorMap.put("lightGray", Color.lightGray);
        colorMap.put("green", Color.green);
        colorMap.put("orange", Color.orange);
        colorMap.put("pink", Color.pink);
        colorMap.put("red", Color.red);
        colorMap.put("white", Color.white);
        colorMap.put("yellow", Color.yellow);
    }
    /**
     * The function creates a color according to a string.
     * @param s a string.
     * @return a color according to a string.
     */
    public Color colorFromString(String s) {
        String[] color = s.split(",");
        if (color.length >= 3) {
            return new Color(Integer.parseInt(color[0]), Integer.parseInt(color[1]),
                    Integer.parseInt(color[2]));
        } else {
            color[0] = s.substring(0, s.length() - 1);
            return colorMap.get(color[0]);
        }
    }
}
