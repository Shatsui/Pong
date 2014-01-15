package fr.shatsui.pong;

import org.apache.log4j.Logger;

/**
 * Created by Shatsui on 15/01/14.
 */
public class GameController {

    private Game game;
    private Logger logger;
    private GameState state;

    public GameController(Game game) {
        this.game = game;
        this.logger = Logger.getLogger(Game.class);
    }

    public void switchTo(GameState state) {
        this.state = state;
    }
}
