package fr.shatsui.pong;

import static org.lwjgl.opengl.GL11.*;

public class Line {

    private int x, y, width;

    public Line(int x, int z, int width) {
        this.x = x;
        this.y = z;
        this.width = width;
    }

    public void draw() {
        glBegin(GL_QUADS);
            glVertex2i(x, 0);
            glVertex2i(x + width, 0);
            glVertex2i(x + width, y);
            glVertex2i(x, y);
        glEnd();
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
}
