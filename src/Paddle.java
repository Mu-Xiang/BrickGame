import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class Paddle extends MoveableObject {
	private final int delta = 5;
	
	public Paddle() {
		
	}
	
	public Paddle(int x, int y, int width, int height, Color color) {
        super(x, y, width, height, color);
        this.setDeltaX(delta);
        this.setDeltaY(delta);
    }

    public void draw(Graphics g) {
    	if (this.getX() < 12 + delta) this.setX(12 + delta);
    	if (this.getX() + this.getWidth() > 706 - delta) this.setX(706 - delta - this.getWidth());
        g.setColor(Color.BLACK);
        g.drawRect(this.getX(), this.getY(), this.getWidth(), this.getHeight());
        g.setColor(this.getColor());
        g.fillRect(this.getX() + 1, this.getY() + 1, this.getWidth() - 1, this.getHeight() - 1);
    }

    public void move(int dir) {
        this.setDirection(dir);
        super.move();
    }

    public void processKeyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		switch (keyCode) {
		/*
		case KeyEvent.VK_UP:
			this.setDirection(KeyEvent.VK_UP);
			break;
		*/
		case KeyEvent.VK_D:
		case KeyEvent.VK_RIGHT:
			this.setDirection(KeyEvent.VK_RIGHT);
			break;
		/*
		case KeyEvent.VK_DOWN:
			this.setDirection(KeyEvent.VK_DOWN);
			break;
		*/
		case KeyEvent.VK_A:
		case KeyEvent.VK_LEFT:
			this.setDirection(KeyEvent.VK_LEFT);
			break;
		default:
			break;
		}
		super.move();
        //System.out.println(this.getDirection());
	}
}
