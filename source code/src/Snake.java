import java.awt.EventQueue;
import java.awt.Toolkit;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;

public class Snake extends JFrame {

	public static JFrame ex;
	
    public Snake() {
        
            try {
                add(new Board());
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Snake.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        setResizable(false);
        pack();
        
        setTitle("Snake");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
   
    
    public static void exec(){
    	
    	EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {                
                ex = new Snake();
                ex.setVisible(true);
                
            }
        });
    	
    }
    
    public static void close (){
    	
    	ex.setVisible(false);
    	
    }
}