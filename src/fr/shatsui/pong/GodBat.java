package fr.shatsui.pong;

/**
 * Created by Shatsui on 11/01/14.
 */
public class GodBat extends Thread {

    private Bat bat;
    private Ball ball;

    public GodBat(Bat bat, Ball ball) {
        this.bat = bat;
        this.ball = ball;
        this.start();
    }

    @Override
    public void run() {
        while (ball.isAlive()) {
            if (ball.getY() > bat.getY() && bat.getDy() != 1) {
                bat.update(0, 1);
            } else if (bat.getDy() != -1) {
                bat.update(0, -1);
            }
        }
    }
}
