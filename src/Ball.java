import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Ball extends MoveableObject {
	private int delta; //ball speed
	private int directionX;
	private int directionY;
	
	public Ball() {
		
	}
	
	public Ball(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		delta = 2;
        this.setDeltaX(delta + 1);
        this.setDeltaY(delta);
        directionX = KeyEvent.VK_RIGHT;
        directionY = KeyEvent.VK_UP;
	}
	
	public void setDelta(int delta) {
		this.delta = delta;
	}
	
	public int getDelta() {
		return delta;
	}
	
	public void switchDirectionX() {
		if (directionX == KeyEvent.VK_RIGHT)
			directionX = KeyEvent.VK_LEFT;
		else if (directionX == KeyEvent.VK_LEFT)
			directionX = KeyEvent.VK_RIGHT;
	}
	
	public int getDirectionX() {
		return directionX;
	}
	
	public void switchDirectionY() {
		if (directionY == KeyEvent.VK_UP)
			directionY = KeyEvent.VK_DOWN;
		else if (directionY == KeyEvent.VK_DOWN)
			directionY = KeyEvent.VK_UP;
	}
	
	public int getDirectionY() {
		return directionY;
	}
	
	public void move() {
		//border detect
		if (this.getX() <= 12 + delta) {
			this.setX(12 + delta);
			switchDirectionX();
		}
		if (this.getX() + this.getWidth() >= 706 - delta) {
			this.setX(706 - this.getWidth() - delta);
			switchDirectionX();
		}
		if (this.getY() <= 36 + delta) {
			this.setY(36 + delta);
			switchDirectionY();
		}
		/*
		if (this.getY() >= 420 + delta) {
			this.setY(420 + delta);
			switchDirectionY();
		}
		*/
    	
		this.direction = directionX;
		super.move();
		this.direction = directionY;
		super.move();
	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
        g.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(this.getColor());
        g.fillOval(this.getX() + 1, this.getY() + 1, this.getWidth() - 1, this.getHeight() - 1);
	}
	
	void clearDraw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
		g.fillOval(this.getX() + 1, this.getY() + 1, this.getWidth() - 1, this.getHeight() - 1);
	}

}
