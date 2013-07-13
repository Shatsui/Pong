package fr.shatsui.pong;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2i;

import java.awt.Rectangle;

public class Bat extends Entity{

	public Bat(int x, int y, int width, int height) {
		super(x, y, width, height);
		this.setSpeed(3f);
		this.setDx(0);
		this.setDy(0);
		hitbox = new Rectangle(this.x, this.y, this.width, this.height);
	}
	
	public void draw(){			
		glBegin(GL_QUADS);
			glVertex2i(this.getX(), this.getY());
			glVertex2i(this.getX()+this.getWidth(), this.getY());
			glVertex2i(this.getX()+this.getWidth(), this.getY()+this.getHeight());
			glVertex2i(this.getX(), this.getY()+this.getHeight());
		glEnd();
	}
	
	public void update(int dx, int dy){
		this.x += dx * this.speed;
		this.y += dy * this.speed;
	}
}