import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BrickGame extends JFrame {
	public static final String proName = "BrickGame";
	public boolean gameStart;
	
	Paddle paddle = new Paddle();
	ArrayList<Brick> bricks = new ArrayList<Brick>();

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrickGame frame = new BrickGame();
					frame.setVisible(true);
					frame.setTitle(proName);
					frame.setSize(720, 480);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BrickGame() {
		//initialize
		gameStart = false;
		
		//frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane paneGame = new JDesktopPane();
		contentPane.add(paneGame, BorderLayout.CENTER);
		paneGame.setBackground(Color.GRAY);
		
		JPanel panelInformation = new JPanel();
		panelInformation.setBounds(535, 10, 150, 24);
		paneGame.add(panelInformation);
		
		JLabel labelScoreText = new JLabel("Score : ");
		panelInformation.add(labelScoreText);
		
		JLabel labelScore = new JLabel("0");
		panelInformation.add(labelScore);
		
		JLabel labelTimeText = new JLabel("    Time");
		panelInformation.add(labelTimeText);
		
		JLabel labelTime = new JLabel("00 : 00");
		panelInformation.add(labelTime);
		
		JButton btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameStart = true;
				Graphics g = getGraphics();
				InitDraw(g);
			}
		});
		btnStart.setBounds(160, 340, 80, 30);
		paneGame.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				gameStart = false;
				Graphics g = getGraphics();
				g.setColor(Color.GRAY);
				g.fillRect(paddle.getX() , paddle.getY(), paddle.getWidth() + 1, paddle.getHeight() + 1);
			}
		});
		btnStop.setBounds(480, 340, 80, 30);
		paneGame.add(btnStop);
		
		new Thread(new PaintThread()).start();
	}
	
	public void InitDraw(Graphics g) {
		paddle = new Paddle(330, 390, 90, 10, Color.WHITE);
		paddle.draw(g);
//		bricks.add(new Brick(100, 50, 40, 10, Color.YELLOW));
//		bricks.get(0).draw(g);
	}
	
	public void Draw(Graphics g) {
		paddle.draw(g);
	}
	
	private class PaintThread implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (gameStart) {
					Graphics g = getGraphics();
					g.setColor(Color.GRAY);
					g.fillRect(paddle.getX() , paddle.getY(), paddle.getWidth() + 1, paddle.getHeight() + 1);
					//paddle.setX(paddle.getX() + 5);
					Draw(g);
					System.out.println(paddle.getX());
				}
			}
		}
	}
}
