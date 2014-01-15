package fr.shatsui.pong;

import org.apache.log4j.Logger;
import static org.lwjgl.opengl.GL11.*;

public class GameController {

    private Game game;
    private Logger logger;
    private GameState state;

    private static final String WINDOW_NAME = "Snake";
    private static final String VERSION = "0.1";

    public GameController(Game game) {
        this.game = game;
        this.logger = Logger.getLogger(Game.class);
        logger.info("OS : " + System.getProperty("os.name") + " (" + System.getProperty("os.version") + ") " + System.getProperty("os.arch"));
        logger.info("Java : " + System.getProperty("java.version") + " / " + System.getProperty("java.vendor"));
        logger.info("Started new Game v" + VERSION + " with OpenGL : " + GL_VERSION);
        game.launch();
    }

    public void switchTo(GameState state) {
        this.state = state;
    }

    public static void main(String[] args) {
        Game game = new Game();
        GameController app = new GameController(game);
        app.switchTo(GameState.MENU);
    }
}
