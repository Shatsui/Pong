package fr.shatsui.pong;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glVertex2i;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class Game {
	
	Bat bat = new Bat(10,480/2-75/2,12,75);
	Bat god_bat = new Bat(620,480/2-75/2,12,75);
	Ball ball = new Ball(640/2+15/2,480/2-12/2,12,12);
	
	public Game(){
		try {
			Display.setDisplayMode(new DisplayMode(640, 480));
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
		glOrtho(0, 640, 480, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);		
		
		bat.draw();
		god_bat.draw();
		ball.spawn(640/2+15/2,480/2-12/2,-1,-1);
		ball.setDx(-1);
		ball.setDy(1);
		
		while(!Display.isCloseRequested()) {
			
			glClear(GL_COLOR_BUFFER_BIT);
			
			glBegin(GL_QUADS);
				glVertex2i(640/2, 0);
				glVertex2i(640/2+15/2, 0);
				glVertex2i(640/2+15/2, 480);
				glVertex2i(640/2, 480);
			glEnd();	
			
			if(Keyboard.isKeyDown(Keyboard.KEY_DOWN) && (bat.getY()+bat.getHeight()) <= 480){
				bat.update(0,1);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_UP) && bat.getY() >= 0){
				bat.update(0,-1);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD5) && (god_bat.getY()+god_bat.getHeight()) <= 480){
				god_bat.update(0,1);
			}
			if(Keyboard.isKeyDown(Keyboard.KEY_NUMPAD8) && god_bat.getY() >= 0){
				god_bat.update(0,-1);
			}
			
			if(ball.getY() < 1)
				ball.setDy(1);
			if(ball.getY() > 479)
				ball.setDy(-1);
			
			if(bat.getX()+bat.getWidth() >= ball.getX() &&
					bat.getX() <= ball.getX() &&
					bat.getY() <= ball.getY() && 
					bat.getY()+bat.getHeight() >= ball.getY()){
				ball.setDx(1);
				if(ball.getY() < 480/2){
					ball.setDy(inverse(ball.getDy()));
				}
				
			}
			if(god_bat.getX()-god_bat.getWidth() <= ball.getX() &&
					god_bat.getY() <= ball.getY() &&
					god_bat.getY()+god_bat.getHeight() >= ball.getY()){
				ball.setDx(-1);
				if(ball.getY() < 480/2){6
					ball.setDy(inverse(ball.getDy()));
				}	
			}
			
			if(ball.getX() < -12)
				ball.kill();
			if(ball.getX() > 640+12)
				ball.kill();
			
			ball.update();
			
			bat.draw();
			god_bat.draw();
			ball.draw();
			
			Display.update();
			Display.sync(60);
		} 
		Display.destroy();
		System.exit(0);
	}
	
	public int inverse(int tar){
		if(tar < 0)
			return +tar;
		else if(tar > 0)
			return -tar;
		return 0;
	}

	public static void main(String[] args){
		new Game(); 
	}

}
