import java.io.File;
import java.util.ArrayList;
import java.util.List;

import biuoop.GUI;
/**
 *
 * @author Natalie.
 *
 */
public class Ass7Game {
    /**
     * this is the main method.
     * @param args this array stores the user's input.
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Space Invaders", 800, 600);
        AnimationRunner animationRunner = new AnimationRunner(gui, 60);
        File highScoresFile = new File("highscores");
        HighScoresTable table = HighScoresTable.loadFromFile(highScoresFile);
        List<LevelInformation> level = new ArrayList<LevelInformation>();
        level.add(new SpaceInvadersLevelInfo());
        Menu<Task<Void>> menu = new MenuAnimation<Task<Void>>(
                "Space Invaders", gui.getKeyboardSensor(), animationRunner);
        menu.addSelection("s", "start game", new StartGameTask(
                gui, animationRunner, table,
                level, 3, highScoresFile));
        menu.addSelection("h", "High Scores", new ShowHiScoresTask(
                animationRunner, gui.getKeyboardSensor(), table));
        menu.addSelection("e", "Exit", new ExitTask(gui));
        while (true) {
            animationRunner.run(menu);
            Task<Void> task = menu.getStatus();
            task.run();
            menu.reset();
        }
    }
}
