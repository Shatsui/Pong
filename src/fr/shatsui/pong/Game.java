package fr.shatsui.pong;

import org.apache.log4j.Logger;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

public class Game {

    private static int WIDTH = 640;
    private static int HEIGHT = 480;

    private Logger logger = GameController.getLogger();

	Bat bat = new Bat(10,480/2-75/2,12,75);
	Bat god_bat = new Bat(620,480/2-75/2,12,75);
	Ball ball = new Ball(640/2+15/2,480/2-12/2,12,12);
    GodBat ai1;
    GodBat ai2;
	
	public Game(int width, int height) {
        WIDTH = width;
        HEIGHT = height;
	}
	
	public int inverse(int tar){
		if(tar < 0)
			return +tar;
		else if(tar > 0)
			return -tar;
		return 0;
	}

    public void setUpGl() {
        /** Create & Show a display frame
         * @since v0.1
         * @see Game#launch()
         */
        try {
            Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
            Display.setTitle("Pong, You vs God");
            Display.create();
        } catch (LWJGLException e) {
            e.printStackTrace();
            Display.destroy();
            System.exit(1);
        }
		/* Init */
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, WIDTH, HEIGHT, 0, 1, -1);
        glMatrixMode(GL_MODELVIEW);
        logger.info("Displayed the Game frame");
    }

    public void setUpObjects() {
        bat.draw();
        god_bat.draw();
        ball.spawn(WIDTH / 2 + 15 / 2, HEIGHT / 2 - 12 / 2,-1,-1);
        ball.setDx(-1);
        ball.setDy(1);
        ai1 = new GodBat(god_bat, ball);
        ai2 = new GodBat(bat, ball);
        logger.info("Created the objects");
    }

    private void input() {
        if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && (bat.getY() + bat.getHeight()) <= HEIGHT){
            bat.update(0,1);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_UP) && bat.getY() >= 0){
            bat.update(0,-1);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5) && (god_bat.getY() + god_bat.getHeight()) <= HEIGHT){
            god_bat.update(0,1);
        }
        if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8) && god_bat.getY() >= 0){
            god_bat.update(0,-1);
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
            ball.spawn(WIDTH / 2 + 15 / 2, HEIGHT / 2 - 12 / 2,-1,-1);
        }
    }

    public void update() {
        input();

        if(ball.getY() < 1)
            ball.setDy(1);
        if(ball.getY() > HEIGHT - 1)
            ball.setDy(-1);

        if(bat.getX() + bat.getWidth() >= ball.getX() &&
                bat.getX() <= ball.getX() &&
                bat.getY() <= ball.getY() &&
                bat.getY()+bat.getHeight() >= ball.getY()){
            ball.setDx(1);
            if(ball.getY() < HEIGHT / 2){
                ball.setDy(inverse(ball.getDy()));
            }

        }
        if(god_bat.getX()-god_bat.getWidth() <= ball.getX() &&
                god_bat.getY() <= ball.getY() &&
                god_bat.getY()+god_bat.getHeight() >= ball.getY()){
            ball.setDx(-1);
            if(ball.getY() < HEIGHT / 2){
                ball.setDy(inverse(ball.getDy()));
            }
        }

        if(ball.getX() < -12)
            ball.kill();
        if(ball.getX() > WIDTH + 12)
            ball.kill();

        ball.update();

        bat.draw();
        god_bat.draw();
        ball.draw();
    }

    public void launch() {
        setUpGl();
        setUpObjects();
        logger.info("Launching the game (" + WIDTH + "x" + HEIGHT + ")");
        while(!Display.isCloseRequested()) {
            glClear(GL_COLOR_BUFFER_BIT);
            update();
            Display.update();
            Display.sync(60);
        }
        Display.destroy();
        logger.info("Exiting the game with 0 exit code");
        System.exit(0);
    }
}
