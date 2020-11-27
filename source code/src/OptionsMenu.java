import java.awt.*;
import java.awt.event.*;

import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class OptionsMenu extends JFrame implements ActionListener,ItemListener{

	static OptionsMenu frame = new OptionsMenu();
	static _delay_ d1 = new _delay_();
	
	private JPanel contentPane;
	private JLabel textField;
	int data;
	public int DELAY;
	
	Choice choice = new Choice();
	
	/**
	 * Launch the application.
	 */
	public static void execOptions() {
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
	public OptionsMenu() {
            
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(500, 500, 500, 500);
		contentPane = new JPanel();
		setTitle("Options");
		setLocationRelativeTo(null);
		contentPane.setBackground(new Color(192, 192, 192));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnReturnToMenu = new JButton("Return to Menu");
		btnReturnToMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				OptionsMenu.frame.setVisible(false);
				StartMenu sm = new StartMenu();
				sm.frame.setVisible(true);
				
			}
		});
		btnReturnToMenu.setBounds(75, 388, 128, 23);
		contentPane.add(btnReturnToMenu);

		choice.setFont(new Font("Arial Rounded MT Bold", Font.PLAIN, 12));
		choice.setBounds(165, 189, 140, 113);
		
		choice.add("Super Easy");
		choice.add("Easy");
		choice.add("Normal");
		choice.add("Hard");
		choice.add("NightMare");
                choice.add("IMPOSSIBLE");    
		
	      JButton btnApply = new JButton("Apply");
  		btnApply.addActionListener(new ActionListener() {
  			public void actionPerformed(ActionEvent e) {
  				
  				data = choice.getSelectedIndex();

   		  		switch(data){
		  		  case 0: data= 0;DELAY = 125;break;
		  		  case 1: data= 1;DELAY = 100;break;
		  		  case 2: data= 2;DELAY = 75;break;
		  		  case 3: data= 3;DELAY = 50;break;
		  		  case 4: data= 4;DELAY = 25;break;
                                  case 5: data= 5;DELAY = 5;break;

   		  		}
   		  	
   		 d1.setDelay(DELAY);
  		  		
  			}
  		});

  		btnApply.setBounds(191, 216, 89, 23);
  		contentPane.add(btnApply);
 
		contentPane.add(choice);

		textField = new JLabel();
		textField.setForeground(new Color(220, 20, 60));
		textField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 63));
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setText("Difficulty");
		textField.setBounds(80, 29, 324, 141);
		contentPane.add(textField);
		
		try {
			
			File soundFile = new File("data\\music1.wav");
                        final AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
                        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
                        final Clip clip = (Clip) AudioSystem.getLine(info);
                        clip.open(sound);

           
            
                JButton btnMusicOn = new JButton("Start");
                    btnMusicOn.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				
    				clip.start();
    				
    			}
    		});
    		btnMusicOn.setBounds(296, 326, 113, 23);
    		contentPane.add(btnMusicOn);
    		
    		JButton btnMusicOff = new JButton("Pause");
    		btnMusicOff.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent e) {
    				clip.stop();
    			}
    		});
    		btnMusicOff.setBounds(296, 350, 113, 23);
    		contentPane.add(btnMusicOff);
                
                JButton btnMusicRes = new JButton("Stop");
                    btnMusicRes.addActionListener(new ActionListener() {
    			public void actionPerformed(ActionEvent arg0) {
    				
                                    clip.setMicrosecondPosition(0);
                               
    			}
    		});
    		btnMusicRes.setBounds(296, 374, 113, 23);
    		contentPane.add(btnMusicRes);

		 } catch (Exception e) {
	            JOptionPane.showMessageDialog(this, e);
	     }

	}
	
	@Override
	public void itemStateChanged(ItemEvent arg0) {
		
		arg0.getStateChange();
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		e.getActionCommand();
		
	}
}
