import java.awt.Color;
import java.awt.Graphics;

public class Brick extends GameObject {
	private int health;
	public static int count = 0;
	
	public Brick() {
		
	}
	
	public Brick(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
		count++;
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
	
	void clearDraw(Graphics g) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(this.getX(), this.getY(), this.getWidth() + 1, this.getHeight() + 1);
	}
}
