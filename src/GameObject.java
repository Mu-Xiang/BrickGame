import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;

public abstract class GameObject extends Component {
	protected int x;
	protected int y;
	protected int width;
	protected int height;
	protected Color color;

	public GameObject() {
		super();
		this.x = 0;
		this.y = 0;
		this.width = 0;
		this.height = 0;
		this.color = Color.WHITE;
	}

	public GameObject(int x, int y, int width, int height, Color color) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = color;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getX() {
		return x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return y;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getWidth() {
		return width;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getHeight() {
		return height;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public int getCenterX() {
		return x + width / 2;
	}
	
	public int getCenterY() {
		return y + height / 2;
	}

	abstract void draw(Graphics g);
}
