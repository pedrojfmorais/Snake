import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class StartMenu extends JFrame {

	private JPanel contentPane;
	private final JButton btnPlay = new JButton("PLAY!");
	private JLabel txtSnake;
	
	static StartMenu frame = new StartMenu();

	public static void showUP(){
		
		frame.setVisible(true);
		
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	/**
	 * Create the frame.
	 */
	public StartMenu() {
            
            
            
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 500, 500);
		contentPane = new JPanel();
		setTitle("Snake");
		setLocationRelativeTo(null);
		contentPane.setBackground(new Color(204, 204, 204));
		contentPane.setForeground(new Color(0, 0, 0));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		btnPlay.setFont(new Font("Tahoma", Font.BOLD, 38));
		btnPlay.setForeground(new Color(255, 0, 0));
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				StartMenu.frame.setVisible(false);
				Snake s1 = new Snake();
				s1.exec();
                                
                                try {
                                    
                                    Board.playSound(new File("data\\\\Mesmo_a_Bacanzz_Curtam.wav"));
                                } catch (UnsupportedAudioFileException ex) {
                                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (IOException ex) {
                                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                } catch (LineUnavailableException ex) {
                                    Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
                                }
							
                        }
		});
		btnPlay.setBounds(163, 161, 175, 100);
		contentPane.add(btnPlay);
		
		JButton btnOptions = new JButton("Options");
		btnOptions.setFont(new Font("Tahoma", Font.BOLD, 21));
		btnOptions.setForeground(Color.GREEN);
		btnOptions.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				frame.setVisible(false);
				
				OptionsMenu om = new OptionsMenu();
				om.execOptions();
				
			}
		});
		btnOptions.setBounds(192, 296, 115, 55);
		contentPane.add(btnOptions);
		
		JButton btnQuit = new JButton("Quit");
		btnQuit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnQuit.setForeground(Color.BLUE);
		btnQuit.setBounds(212, 382, 75, 25);
		btnQuit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
				
			}
		});
		contentPane.add(btnQuit);
		
		txtSnake = new JLabel();
		txtSnake.setForeground(new Color(255, 255, 153));
		txtSnake.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 61));
		txtSnake.setHorizontalAlignment(SwingConstants.CENTER);
		txtSnake.setText("SNAKE");
		txtSnake.setBounds(100, 25, 300, 125);
		contentPane.add(txtSnake);
	}
}
