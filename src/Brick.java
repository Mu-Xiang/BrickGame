import java.awt.Color;
import java.awt.Graphics;

public class Brick extends GameObject {
	private int health;
	
	public Brick() {
		
	}
	
	public Brick(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(this.getColor());
        g.fillRect(this.getX() + 1, this.getY() + 1, this.getWidth() - 1, this.getHeight() - 1);
	}
}