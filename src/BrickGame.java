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
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class BrickGame extends JFrame implements KeyListener {
	public static final String proName = "BrickGame";
	public boolean gameStart;
	public boolean gamePause;
	
	public int score;
	public int timerMin;
	public int timerSec;
	public String timerText = "00 : 00 ";
	
	Paddle paddle = new Paddle();
	Ball ball = new Ball();
	ArrayList<Brick> bricks = new ArrayList<Brick>();

	public JLabel labelScore;
	public JLabel labelTime;
	public JLabel labelTip;
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
		gamePause = false;
		score = 0;
		timerMin = 0;
		timerSec = 0;
		
		//frame
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.setBackground(Color.GRAY);
		
		JDesktopPane paneGame = new JDesktopPane();
		contentPane.add(paneGame, BorderLayout.CENTER);
		paneGame.setBackground(Color.LIGHT_GRAY);
		
		JPanel panelInformation = new JPanel();
		panelInformation.setBounds(530, 400, 155, 24);
		paneGame.add(panelInformation);
		
		JLabel labelScoreText = new JLabel(" Score : ");
		panelInformation.add(labelScoreText);
		
		labelScore = new JLabel(score + " ");
		panelInformation.add(labelScore);
		
		JLabel labelTimeText = new JLabel("  Time");
		panelInformation.add(labelTimeText);
		
		labelTime = new JLabel(timerText);
		panelInformation.add(labelTime);
		
		JDesktopPane paneTip = new JDesktopPane();
		paneTip.setBackground(Color.LIGHT_GRAY);
		paneTip.setBounds(10, 400, 400, 24);
		paneGame.add(paneTip);
		
		JLabel labelTipText = new JLabel("Tip : ");
		labelTipText.setFont(new Font("Times New Roman", Font.BOLD, 13));
		labelTipText.setForeground(Color.BLUE);
		labelTipText.setBounds(10, 5, 30, 16);
		paneTip.add(labelTipText);
		
		labelTip = new JLabel("Press space to new a game.");
		labelTip.setForeground(Color.BLUE);
		labelTip.setFont(new Font("Times New Roman", Font.BOLD, 13));
		labelTip.setBounds(40, 5, 350, 16);
		paneTip.add(labelTip);
		
		this.addKeyListener(this);
		new Thread(new PaintThread()).start();
		new Thread(new TimerThread()).start();
	}
	
	public void newGame() {
		gameStart = true;
		gamePause = true;
		timerMin = 0;
		timerSec = 0;
		
		Graphics g = getGraphics();
		initDraw(g);
		labelTip.setText("Press space to start the game.");
	}
	
	public void startGame() {
		gamePause = false;
		
		Graphics g = getGraphics();
		
	}
	
	public void stopGame() {
		gamePause = true;
		
		Graphics g = getGraphics();
		
	}
	
	public String tipSelect() {
		if (gameStart) {
			if (gamePause)
				return "Press space to start the game or press N to new a game.";
			else
				return "Press space to pause the game.";
		} else
			return "Press space to new a game.";
	}
	
	public void initDraw(Graphics g) {
		//Paddle
		paddle = new Paddle(300, 390, 90, 10, Color.WHITE);
		paddle.draw(g);
		
		//Ball
		ball = new Ball(340, 380, 10, 10, Color.CYAN);
		ball.draw(g);
		
		//Bricks
	}
	
	private class PaintThread implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(40);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (gameStart) {
					Graphics g = getGraphics();
					//Area
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(12, 36, 696, 386);
					//Paddle
					paddle.draw(g);
					//Ball
					if (!gamePause) ball.move();
					ball.draw(g);
				}
			}
		}
	}
	
	private class TimerThread implements Runnable {
		public void run() {
			while (true) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (gameStart && !gamePause) {
					if (++timerSec == 60) {
						timerSec = 0;
						timerMin++;
					}
				}
				timerText = ((timerMin < 10) ? "0" : "") + timerMin + " : " + ((timerSec < 10) ? "0" : "") + timerSec + " ";
				labelTime.setText(timerText);
				//System.out.println(timerText);
			}
		}
	}

	//Interface KeyListener
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		//System.out.println(e.getKeyCode());
		switch (e.getKeyCode()) {
			case KeyEvent.VK_SPACE:
				if (gameStart) {
					if (gamePause) startGame();
					else stopGame();
				} else newGame();
				labelTip.setText(tipSelect());
				break;
			case KeyEvent.VK_N:
				if (gamePause) newGame();
				break;
			default:
				if (!gamePause) paddle.processKeyPressed(e);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
