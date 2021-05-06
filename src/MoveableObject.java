import java.awt.Color;
import java.awt.event.KeyEvent;

public abstract class MoveableObject extends GameObject {
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

    public int collidesWith(GameObject g) {
        if (this.y + this.height + this.deltaY >= g.getY() && this.y + this.height + this.deltaY <= g.getY() + g.getHeight() &&
        	this.getCenterX() + this.deltaX >= g.getX() && this.getCenterX() + this.deltaX <= g.getX() + g.getWidth()) {
        		return KeyEvent.VK_UP;
        }
    	if (this.y - this.deltaY <= g.getY() + g.getHeight() && this.y + this.deltaY >= g.getY() &&
    		this.getCenterX() + this.deltaX >= g.getX() && this.getCenterX() + this.deltaX <= g.getX() + g.getWidth()) {
    			return KeyEvent.VK_DOWN;
    	}
    	if (this.x + this.width + this.deltaX >= g.getX() && this.x + this.width + this.deltaX <= g.getX() + g.getWidth() &&
    		this.getCenterY() + this.deltaY >= g.getY() && this.getCenterY() + this.deltaY <= g.getY() + g.getHeight()) {
    			return KeyEvent.VK_LEFT;
    	}
    	if (this.x - this.deltaX <= g.getX() + g.getWidth() && this.x + this.deltaX >= g.getX() &&
    		this.getCenterY() + this.deltaY >= g.getY() && this.getCenterY() + this.deltaY <= g.getY() + g.getHeight()) {
    			return KeyEvent.VK_RIGHT;
    	}
    	
    	return 0;
    }
    
    public String toString() {
    	return "x=" + x + ",y=" + y;
    }
}
