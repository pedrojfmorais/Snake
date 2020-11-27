import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

    private final int B_WIDTH = 520;
    private final int B_HEIGHT = 520;
    private final int DOT_SIZE = 10;
    private final int ALL_DOTS = 900;
    private final int RAND_POS = 50;
    private static int score=0;

    private final int x[] = new int[ALL_DOTS];
    private final int y[] = new int[ALL_DOTS];

    private int dots;
    private int apple_x;
    private int apple_y;

    private boolean leftDirection = false;
    private boolean rightDirection = true;
    private boolean upDirection = false;
    private boolean downDirection = false;
    private boolean inGame = true;
    private boolean restart = false;
    private boolean quit = false;

    private Timer timer;
    private Image ball;
    private Image apple;
    private Image head;
    
    private static int highest_score;

    Chronometer c1 = new Chronometer();
    _delay_ d1 = new _delay_();

    public Board() throws UnsupportedAudioFileException, IOException, LineUnavailableException {

        addKeyListener(new TAdapter()); 
        setBackground(Color.black);
        setFocusable(true);

        setPreferredSize(new Dimension(B_WIDTH, B_HEIGHT));
        loadImages();
        initGame();
        
        
    }

    protected static void playSound( File soundFile) throws UnsupportedAudioFileException, IOException, LineUnavailableException{
        
        AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
        DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
        final Clip clip = (Clip) AudioSystem.getLine(info);
        clip.open(sound);
        clip.start();
        
    }
    
    private void loadImages() {

        ImageIcon iid = new ImageIcon("data\\dot.png");
        ball = iid.getImage();

        ImageIcon iia = new ImageIcon("data\\apple.png");
        apple = iia.getImage();

        ImageIcon iih = new ImageIcon("data\\head.png");
        head = iih.getImage();
        
        c1.start();
    }

    private void initGame() {

        dots = 1;
        
        for (int z = 0; z < dots; z++) {
            x[z] = 50 - z * 10;
            y[z] = 50;
        }

        locateApple();
        

        timer = new Timer(d1.getDelay(), this);
        timer.start();

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        doDrawing(g);
        if (inGame==true){
        	seeScore(g);
        }else ;
    }
    
    
    
    private void doDrawing(Graphics g) {
        
        if (inGame) {

            g.drawImage(apple, apple_x, apple_y, this);

            for (int z = 0; z < dots; z++) {
                if (z == 0) {
                    g.drawImage(head, x[z], y[z], this);
                } else {
                    g.drawImage(ball, x[z], y[z], this);
                }
            }

            Toolkit.getDefaultToolkit().sync();

        } else {

            try {
                gameOver(g);
            } catch (Exception ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }        
    }

    private void gameOver(Graphics g) {

    	ficheiros f1 = new ficheiros();
    
        try {
                    
            this.highest_score=f1.exec();
                    
        } catch (Exception ex) {
            Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
        }
    	
    	c1.stop();
    	double x = c1.getSeconds();
    	int min=0;
    	
    	int sec = (int)x;
    	
    	while (sec>=60){
    		
    		sec -=60;
    		min++;
    	}
    	
    	String time = "Time Played: ";
        String msg = "Game Over";
        String score = "Your score: "+this.score;
        String highest_score = "High Score: "+this.highest_score;
        String restart = "Restart (r)";
        String quit = "Quit to Menu(q)";
        
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);
        g.setColor(Color.white);
        g.setFont(small);
        
        g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2, (B_HEIGHT / 2)-80);
        g.drawString(highest_score, (B_WIDTH - metr.stringWidth(highest_score)) / 2, (B_HEIGHT / 2)-20);
        g.drawString(score, (B_WIDTH - metr.stringWidth(score)) / 2, (B_HEIGHT / 2));
        g.drawString(time+min+":"+sec, (B_WIDTH - metr.stringWidth(time)-18)/2 , (B_HEIGHT / 2)+20);
        g.drawString(restart, (B_WIDTH - metr.stringWidth(restart)) / 2, (B_HEIGHT / 2)+80);
        g.drawString(quit, (B_WIDTH - metr.stringWidth(quit)) / 2, (B_HEIGHT / 2)+100);
 
        this.score=0;
        
    }

    private void checkApple() {

        if ((x[0] == apple_x) && (y[0] == apple_y)) {

            dots++;
            locateApple();
            this.score += 1;
            
            try {
                                    
                Board.playSound(new File("data\\\\nice_yeah.wav"));
            } catch (UnsupportedAudioFileException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            } catch (LineUnavailableException ex) {
                Logger.getLogger(Board.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void move() {

        for (int z = dots; z > 0; z--) {
            x[z] = x[(z - 1)];
            y[z] = y[(z - 1)];
        }

        if (leftDirection) {
            x[0] -= DOT_SIZE;
        }

        if (rightDirection) {
            x[0] += DOT_SIZE;
        }

        if (upDirection) {
            y[0] -= DOT_SIZE;
        }

        if (downDirection) {
            y[0] += DOT_SIZE;
        }
    }

    private void checkCollision() {

        for (int z = dots; z > 0; z--) {

            if ((z > 4) && (x[0] == x[z]) && (y[0] == y[z])) {
                inGame = false;
            }
        }

        if (y[0] >= B_HEIGHT) {
            inGame = false;
        }

        if (y[0] < 0) {
            inGame = false;
        }

        if (x[0] >= B_WIDTH) {
            inGame = false;
        }

        if (x[0] < 0) {
            inGame = false;
        }
        
        if(!inGame) {
            timer.stop();
        }
    }

    private void locateApple() {

        int r = (int) (Math.random() * RAND_POS);
        apple_x = ((r * DOT_SIZE));

        r = (int) (Math.random() * RAND_POS);
        apple_y = ((r * DOT_SIZE));
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (inGame) {

            checkApple();
            checkCollision();
            move();
        }

        repaint();
    }

    private void seeScore(Graphics g){

    	String score = "Your score: "+this.score;
        Font small = new Font("Helvetica", Font.BOLD, 14);
        FontMetrics metr = getFontMetrics(small);

        g.setColor(Color.white);
        g.setFont(small);
        g.drawString(score, (B_WIDTH - metr.stringWidth(score)-10), 20);
   
    }

    private void checkHighScore (){
    	
    	//check file for HighScore, and change "this.highest_score" to the score in the file
    	
    	if (this.score > this.highest_score){
    		
    		this.highest_score = this.score;
    		//change highScore in file to the new one
    	}
    	
    	//close file
    	
    }
    
    private void restart(){

    	if(restart==true){
    		
    		Snake s = new Snake();
    		s.close();
    		s.exec();
 		
    	}
      
        restart = false;
    }
    
    private void quit(){

    	if(quit==true){
    		
    		Snake s = new Snake();
    		s.close();
    		
    		StartMenu sm = new StartMenu();
    		sm.showUP();
    		
    	}
      
        restart = false;
    }
    
    private class TAdapter extends KeyAdapter {

    	
        @Override
        public void keyPressed(KeyEvent e) {
        	
            int key = e.getKeyCode();

            if ((key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) && (!rightDirection)) {
                leftDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) && (!leftDirection)) {
                rightDirection = true;
                upDirection = false;
                downDirection = false;
            }

            if ((key == KeyEvent.VK_UP || key == KeyEvent.VK_W) && (!downDirection)) {
                upDirection = true;
                rightDirection = false;
                leftDirection = false;
            }

            if ((key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) && (!upDirection)) {
                downDirection = true;
                rightDirection = false;
                leftDirection = false;
            }
 
            if ((key == KeyEvent.VK_R)) {
            	restart = true;
            	restart();
            }
            
            if ((key == KeyEvent.VK_Q) || (key == KeyEvent.VK_ESCAPE)) {
            	quit = true;
            	quit();
            }
        }
    }  
    
    public int getScore(){
    	
    	return score;
    	
    }
}