import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JLabel;
import javax.swing.JButton;

public class BrickGame extends JFrame {
	public static final String proName = "BrickGame";

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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 720, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JDesktopPane paneGame = new JDesktopPane();
		contentPane.add(paneGame, BorderLayout.CENTER);
		
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
		btnStart.setBounds(160, 340, 80, 30);
		paneGame.add(btnStart);
		
		JButton btnStop = new JButton("Stop");
		btnStop.setBounds(480, 340, 80, 30);
		paneGame.add(btnStop);
		
		
	}
}
