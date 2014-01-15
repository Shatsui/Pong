package fr.shatsui.pong;

import java.awt.Rectangle;

public abstract class Entity {
	
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected float speed;
	protected int dx;
	protected int dy;
	protected Rectangle hitbox;
	
	public Entity(int x, int y, int width, int  height){
		this.x = x;
		this.y = y;
        this.width = width;
        this.height = height;
        dx = 0;
        dy = 0;
        hitbox = new Rectangle(this.x, this.y, this.width, this.height);
	}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public float getSpeed() {
        return speed;
    }

    public int getDx() {
        return dx;
    }

    public int getDy() {
        return dy;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isIn(Entity other) {
        if (this.hitbox.intersects(other.getHitbox())){
            return true;
        } else {
            return false;
        }
    }
    
    public void update(){
        this.x += dx * this.speed;
        this.y += dy * this.speed;
    }

    public abstract void draw();
}
