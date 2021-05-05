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
        this.setDeltaX(delta);
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
	
	public void setDirectionX(int directionX) {
		this.directionX = directionX;
	}
	
	public int getDirectionX() {
		return directionX;
	}
	
	public void setDirectionY(int directionY) {
		this.directionY = directionY;
	}
	
	public int getDirectionY() {
		return directionY;
	}
	
	public void move() {
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

}
