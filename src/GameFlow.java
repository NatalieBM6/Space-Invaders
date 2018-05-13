import java.util.List;

import biuoop.DialogManager;
import biuoop.KeyboardSensor;

/**
 *
 * @author Natalie.
 *
 */
public class GameFlow {
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private int horizontalBound;
    private int verticalBound;
    private Counter lives;
    private Counter score;
    private DialogManager dialog;
    private HighScoresTable highScoresTable;
    private boolean isWin;
    /**
     * this method construct a gameFlow object.
     * @param ar an animation runner connected to a gui object.
     * @param ks a keyboard sensor connected to a gui object.
     * @param numOfLives the initial number of lives in this game.
     * @param horizontalBound the width of that gui.
     * @param verticalBound the height of that gui.
     * @param dialog a dialog manager.
     * @param table a table of high scores.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks,
                    int numOfLives, DialogManager dialog, HighScoresTable table,
                    int horizontalBound, int verticalBound) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.horizontalBound = horizontalBound;
        this.verticalBound = verticalBound;
        this.lives = new Counter(numOfLives);
        this.score = new Counter(0);
        this.dialog = dialog;
        this.highScoresTable = table;
        this.isWin = true;
    }
    /**
     * this method gets a list of levelInformation objects
     * and runs the appropriate levels.
     * @param levelsList the given list.
     */
    public void runLevels(List<LevelInformation> levelsList) {
        LevelInformation levelInfo = levelsList.get(0);
        int levelNumber = 1;
        int enemyPlatoonSpeed = 100;
        while (true) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor,
                                            this.animationRunner, this.score,
                                            this.lives, new Dimensions(
                                                    this.horizontalBound,
                                                    this.verticalBound),
                                            enemyPlatoonSpeed);
            level.initialize();
            LivesIndicator li = new LivesIndicator(this.lives);
            ScoreIndicator si = new ScoreIndicator(this.score);
            LevelNameIndicator lni = new LevelNameIndicator(
                                         levelInfo.levelName() + levelNumber);
            level.addSprite(li);
            level.addSprite(si);
            level.addSprite(lni);
            while (this.lives.getValue() > 0) {
                level.playOneTurn();
                if (level.isWin()) {
                    levelNumber++;
                    enemyPlatoonSpeed += 10;
                    break;
                } else {
                    this.lives.decrease(1);
                }
            }
            if (this.lives.getValue() == 0) {
                this.isWin = false;
                break;
            }
        }
        if (this.highScoresTable.getRank(this.score.getValue())
         <= this.highScoresTable.size()) {
            String name =
                    dialog.showQuestionDialog("Name", "What is your name?", "");
            ScoreInfo scoreInfo = new ScoreInfo(name, this.score.getValue());
            this.highScoresTable.add(scoreInfo);
        }
        this.animationRunner.run(new KeyPressStoppableAnimation(
                                 new EndScreenAnimation(this.score, this.isWin),
                                 this.keyboardSensor, "space"));
        this.animationRunner.run(new KeyPressStoppableAnimation(
                                 new HighScoresAnimation(this.highScoresTable),
                                 this.keyboardSensor, "space"));
    }
}