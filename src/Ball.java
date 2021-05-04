import java.awt.Color;
import java.awt.Graphics;

public class Ball extends MoveableObject {
	private int delta = 2; //ball speed
	
	public Ball() {
		
	}
	
	public Ball(int x, int y, int width, int height, Color color) {
		super(x, y, width, height, color);
        this.setDeltaX(delta);
        this.setDeltaY(delta);
	}

	void draw(Graphics g) {
		g.setColor(Color.BLACK);
        g.drawOval(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(this.getColor());
        g.fillOval(this.getX() + 1, this.getY() + 1, this.getWidth() - 1, this.getHeight() - 1);
	}

}
