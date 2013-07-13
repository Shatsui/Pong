package fr.shatsui.pong;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Rectangle;

public class Ball extends Entity{
	
	private boolean isAlive = false; 
	
	public Ball(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setSpeed(2f);
		this.setDx(0);
		this.setDy(0);
		hitbox = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public void draw(){			
		if(isAlive){
			glBegin(GL_QUADS);
				glVertex2i(this.getX(), this.getY());
				glVertex2i(this.getX()+this.getWidth(), this.getY());
				glVertex2i(this.getX()+this.getWidth(), this.getY()+this.getHeight());
				glVertex2i(this.getX(), this.getY()+this.getHeight());
			glEnd();
		}
	}
	
	public void update(int dx, int dy){
		this.x += dx * this.speed;
		this.y += dy * this.speed;
	}
	
	public void spawn(int x, int y,int dx, int dy){
		isAlive = true;
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}
	
	public void kill(){
		isAlive = false;
		this.dx = 0;
		this.dy = 0;
		this.x = 0;
		this.y = 0;
	}
	
	public boolean isAlive(){
		return this.isAlive;
	}
}
