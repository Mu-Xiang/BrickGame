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

public class BrickGame extends JFrame implements KeyListener {
	public static final String proName = "BrickGame";
	public boolean gameStart;
	public int timerMin;
	public int timerSec;
	public String timerText = "00 : 00";
	
	Paddle paddle = new Paddle();
	ArrayList<Brick> bricks = new ArrayList<Brick>();

	public JLabel labelTime;
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
		panelInformation.setBounds(535, 400, 150, 24);
		paneGame.add(panelInformation);
		
		JLabel labelScoreText = new JLabel("Score : ");
		panelInformation.add(labelScoreText);
		
		JLabel labelScore = new JLabel("0");
		panelInformation.add(labelScore);
		
		JLabel labelTimeText = new JLabel("    Time");
		panelInformation.add(labelTimeText);
		
		labelTime = new JLabel(timerText);
		panelInformation.add(labelTime);
		
		JButton btnStart = new JButton("Start");
		btnStart.setBounds(160, 340, 80, 30);
		paneGame.add(btnStart);
		btnStart.setVisible(false);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(480, 340, 80, 30);
		paneGame.add(btnStop);
		btnStop.setVisible(false);
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				startGame();
				System.out.println("Game start.");
			}
		});
		
		btnStop.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				stopGame();
			}
		});
		
		this.addKeyListener(this);
		new Thread(new PaintThread()).start();
		new Thread(new TimerThread()).start();
	}
	
	public void startGame() {
		gameStart = true;
		
		Graphics g = getGraphics();
		initDraw(g);
	}
	
	public void stopGame() {
		gameStart = false;
		
		Graphics g = getGraphics();
		g.setColor(Color.LIGHT_GRAY);
		g.fillRect(paddle.getX() , paddle.getY(), paddle.getWidth() + 1, paddle.getHeight() + 1);
	}
	
	public void initDraw(Graphics g) {
		//Paddle
		paddle = new Paddle(330, 390, 90, 10, Color.WHITE);
		paddle.draw(g);
		
		//Bricks
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
					//Area
					g.setColor(Color.LIGHT_GRAY);
					g.fillRect(12, 36, 696, 386);
					//Paddle
					paddle.draw(g);
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
				if (gameStart) {
					if (++timerSec == 60) {
						timerSec = 0;
						timerMin++;
					}
					timerText = ((timerMin < 10) ? "0" : "") + timerMin + " : " + ((timerSec < 10) ? "0" : "") + timerSec;
					labelTime.setText(timerText);
					//System.out.println(timerText);
				}
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
				if (gameStart) stopGame();
				else startGame();
				break;
			default:
				paddle.processKeyPressed(e);
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
}
