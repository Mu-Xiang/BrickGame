import java.awt.Color;
import java.awt.event.KeyEvent;

public abstract class MoveableObject extends GameObject {
	protected boolean live = true;
    protected int deltaX;
    protected int deltaY;
    protected int strength;
    protected int direction;

    public MoveableObject() {
        super();
    }

    public MoveableObject(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
    }

    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public int getDeltaX() {
        return deltaX;
    }

    public void setDeltaX(int deltaX) {
        this.deltaX = deltaX;
    }

    public int getDeltaY() {
        return deltaY;
    }

    public void setDeltaY(int deltaY) {
        this.deltaY = deltaY;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void move() {
        switch (direction) {
            case KeyEvent.VK_UP:
                y -= deltaY;
                break;
            case KeyEvent.VK_DOWN:
                y += deltaY;
                break;
            case KeyEvent.VK_LEFT:
                x -= deltaX;
                break;
            case KeyEvent.VK_RIGHT:
                x += deltaX;
                break;
            default:
                break;
        }
        direction = 0;
    }

    public boolean collidesWith(GameObject g) {
        if (this.contains(g.x, g.y) || 
            this.contains(g.x, g.y + g.getHeight()) ||
            this.contains(g.x + g.getWidth(), g.y) ||
            this.contains(g.x + g.getWidth(), g.y + g.getHeight())) {
                return true;
        }
        return false;
    }
}
