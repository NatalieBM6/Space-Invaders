import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 *
 * @author Natalie.
 *
 */
public class GameLevel implements Animation {
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private AnimationRunner runner;
    private boolean running;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private KeyboardSensor keyboard;
    private int horizontalBound;
    private int verticalBound;
    private int margin;
    private Paddle paddle;
    private List<Block> blocks;
    private Counter score;
    private BlockGroup enemyPlatoon;
    private Counter paddleHitCounter;
    private List<Ball> shots;
    /**
     * this method constructs a gameLevel object.
     * @param level the levelInfornation object for the current level.
     * @param keyboard a keyboard sensor connected to a gui object.
     * @param animationRunner an animation runner connected to a gui object.
     * @param score a score counter holding the current score.
     * @param lives a lives counter holding the current lives.
     * @param dimentions the available width and height.
     * @param enemyPlatoonSpeed the available enemyPlatoon speed.
     */
    public GameLevel(LevelInformation level,
                     KeyboardSensor keyboard,
                     AnimationRunner animationRunner,
                     Counter score,
                     Counter lives,
                     Dimensions dimentions,
                     int enemyPlatoonSpeed) {
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
        this.sprites.addSprite(level.getBackground());
        this.runner = animationRunner;
        this.remainingBlocks = new Counter(level.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(0);
        this.keyboard = keyboard;
        this.horizontalBound = dimentions.horizontalBound();
        this.verticalBound = dimentions.verticalBound();
        this.margin = 1;
        this.enemyPlatoon = new BlockGroup(this, enemyPlatoonSpeed);
        this.blocks = new ArrayList<Block>();
        for (Block b : level.blocks()) {
            Block block = new Block(b);
            this.blocks.add(block);
            this.enemyPlatoon.addBlock(block);
        }
        this.sprites.addSprite(enemyPlatoon);
        this.paddle = new Paddle(horizontalBound / 2 - level.paddleWidth() / 2,
                                 verticalBound - level.paddleHeight() - 10,
                                 level.paddleWidth(),
                                 level.paddleHeight(),
                                 Color.YELLOW,
                                 keyboard,
                                 this.margin);
        this.paddle.setBounds(horizontalBound, verticalBound);
        this.paddle.setSpeed(level.paddleSpeed());
        this.score = score;
        this.running = true;
        this.paddleHitCounter = new Counter(1);
        this.shots = new ArrayList<Ball>();
    }
    /**
     * this method adds a given collidable object to the game.
     * @param c the given collidable object.
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }
    /**
     * this method removes a given collidable object from the game.
     * @param c the given collidable object.
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }
    /**
     * this method adds a given sprite object to the game.
     * @param s the given sprite object.
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }
    /**
     * this method removes a given sprite object from the game.
     * @param s the given sprite object.
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }
    /**
     * this method initializes the game - creates Blocks, Balls and Paddle.
     */
    public void initialize() {
        Map<Integer, Color> marginColor = new TreeMap<Integer, Color>();
        Map<Integer, Image> marginImage = new TreeMap<Integer, Image>();
        marginColor.put(0, Color.GRAY);

        Block topMargin = new Block();
        topMargin.setUpperLeft(0, 15);
        topMargin.setWidth(this.horizontalBound);
        topMargin.setHeight(this.margin);
        topMargin.setColors(marginColor);
        topMargin.setImages(marginImage);
        topMargin.setBorderColor(Color.BLACK);
        topMargin.setHits(0);

        Block bottomMargin = new Block();
        bottomMargin.setUpperLeft(this.margin, this.verticalBound);
        bottomMargin.setWidth(this.horizontalBound - 2 * this.margin);
        bottomMargin.setHeight(this.margin);
        bottomMargin.setColors(marginColor);
        bottomMargin.setImages(marginImage);
        bottomMargin.setBorderColor(Color.BLACK);
        bottomMargin.setHits(0);

        Block leftMargin = new Block();
        leftMargin.setUpperLeft(0, this.margin + 15);
        leftMargin.setWidth(this.margin);
        leftMargin.setHeight(this.verticalBound - this.margin - 15);
        leftMargin.setColors(marginColor);
        leftMargin.setImages(marginImage);
        leftMargin.setBorderColor(Color.BLACK);
        leftMargin.setHits(0);

        Block rightMargin = new Block();
        rightMargin.setUpperLeft(
                this.horizontalBound - this.margin, this.margin + 15);
        rightMargin.setWidth(this.margin);
        rightMargin.setHeight(this.verticalBound - this.margin - 15);
        rightMargin.setColors(marginColor);
        rightMargin.setImages(marginImage);
        rightMargin.setBorderColor(Color.BLACK);
        rightMargin.setHits(0);

        HitListener br = new BlockRemover(this, this.remainingBlocks);
        HitListener blr = new BallRemover(this, this.remainingBalls);
        HitListener stl = new ScoreTrackingListener(this.score);
        HitListener shbr = new BlockRemover(this, new Counter(0));

        leftMargin.addToGame(this);
        rightMargin.addToGame(this);
        topMargin.addToGame(this);
        bottomMargin.addToGame(this);
        leftMargin.addHitListener(blr);
        rightMargin.addHitListener(blr);
        topMargin.addHitListener(blr);
        bottomMargin.addHitListener(blr);

        Block shieldTemplate = new Block();
        Map<Integer, Color> shieldColor = new TreeMap<Integer, Color>();
        shieldColor.put(1, Color.CYAN);
        shieldTemplate.setColors(shieldColor);
        shieldTemplate.setHits(1);
        shieldTemplate.setWidth(5);
        shieldTemplate.setHeight(5);
        Block[][] leftShield = new Block[3][30];
        Block[][] middleShield = new Block[3][30];
        Block[][] rightShield = new Block[3][30];

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 30; j++) {
                leftShield[i][j] = new Block(shieldTemplate);
                leftShield[i][j].setUpperLeft(100 + j * 5, 500 + i * 5);
                leftShield[i][j].addHitListener(blr);
                leftShield[i][j].addHitListener(shbr);
                leftShield[i][j].addToGame(this);

                middleShield[i][j] = new Block(shieldTemplate);
                middleShield[i][j].setUpperLeft(300 + j * 5, 500 + i * 5);
                middleShield[i][j].addHitListener(blr);
                middleShield[i][j].addHitListener(shbr);
                middleShield[i][j].addToGame(this);

                rightShield[i][j] = new Block(shieldTemplate);
                rightShield[i][j].setUpperLeft(500 + j * 5, 500 + i * 5);
                rightShield[i][j].addHitListener(blr);
                rightShield[i][j].addHitListener(shbr);
                rightShield[i][j].addToGame(this);
            }
        }

        for (Block i: this.blocks) {
            i.addToGame(this);
            i.addHitListener(br);
            i.addHitListener(blr);
            i.addHitListener(stl);
            i.addHitListener(this.enemyPlatoon);
        }
    }
    /**
     * this method tells if the animation drawing should stop.
     * @return true if the animation drawing should stop, false otherwise.
     */
    public boolean shouldStop() {
       return !this.running;
    }
    /**
     * this method sets the paddle and the balls to the
     * initial location before the level starts.
     */
    public void setBallsAndPaddle() {
        this.paddle.putInMiddle();
        this.paddleHitCounter = new Counter(1);
        HitListener pblr = new BallRemover(this, this.paddleHitCounter);
        this.paddle.addHitListener(pblr);
        this.paddle.addToGame(this);
    }
    /**
     * this method draws the current state of
     * the animation object to the screen.
     * @param d a draw surface to draw on.
     * @param dt the dt value of this game.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed(dt);
        this.paddle.shoot(this, dt);
        if (this.keyboard.isPressed("p")) {
            this.runner.run(new KeyPressStoppableAnimation(
                    new PauseScreenAnimation(), this.keyboard, "space"));
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.running = false;
        }
        if (this.paddleHitCounter.getValue() == 0) {
            this.running = false;
            this.enemyPlatoon.reset(dt);
            this.removeShotsLeftover();
        }
        if (this.enemyPlatoon.reachedShields()) {
            this.running = false;
            this.enemyPlatoon.reset(dt);
            this.removeShotsLeftover();
        }
    }
    /**
     * this method tells if the player win the game.
     * @return true if the player wins, false otherwise.
     */
    public boolean isWin() {
        return this.remainingBlocks.getValue() == 0;
    }
    /**
     * this method starts the animation loop.
     */
    public void playOneTurn() {
        this.setBallsAndPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
        this.paddle.removeFromGame(this);
        return;
    }
    /**
     * this method generates an enemy shot object at the given point.
     * @param point the given point.
     * @return a player shot object.
     */
    public Ball getEnemyShot(Point point) {
        Ball ball = new Ball(point, 5, Color.RED, this.environment);
        this.shots.add(ball);
        return ball;
    }
    /**
     * this method generates a player shot object at the given point.
     * @param point the given point.
     * @return a player shot object.
     */
    public Ball getPlayerShot(Point point) {
        Ball ball = new Ball(point, 4, Color.WHITE, this.environment);
        this.shots.add(ball);
        return ball;
    }
    /**
     * this method removes all the shots from the screen.
     */
    public void removeShotsLeftover() {
        for (Ball ball : this.shots) {
            ball.removeFromGame(this);
        }
    }
}