import java.io.File;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class ficheiros {

	_delay_ d1 = new _delay_();
	
        lerFicheiros lf1 = new lerFicheiros();
	escreverFicheiros ef1 = new escreverFicheiros();
	
	private String FileName;
	private int toReturn;
	
	public int exec() throws IOException, UnsupportedAudioFileException, LineUnavailableException {
		
            Board b1 = new Board();
            
		fileName();
		
		System.out.println(FileName);
		
		int highScore;
		int fileScore;
		
		highScore = b1.getScore();
		
		fileScore = lf1.exec(FileName);
		System.out.println(fileScore);
		
		if(fileScore<highScore){
			
                    Board.playSound(new File("data\\\\toma_la_dentro.wav"));
                    
                    ef1.exec(FileName, highScore);
			
                    toReturn = highScore;
			
			
		}else if(fileScore>=highScore){
			
                    Board.playSound(new File("data\\\\a_serioo.wav"));
                    
                    toReturn = fileScore;
			
		}

		return toReturn;
		
	}

	private void fileName(){
		
		int delay;
		
		delay=d1.getDelay();
		
		switch(delay){
		case 125:FileName="data\\HighScoreDifficulty1.txt";break;
		case 100:FileName="data\\HighScoreDifficulty2.txt";break;
		case 75:FileName="data\\HighScoreDifficulty3.txt";break;
		case 50:FileName="data\\HighScoreDifficulty4.txt";break;
		case 25:FileName="data\\HighScoreDifficulty5.txt";break;
                case 5:FileName="data\\HighScoreDifficulty6.txt";break;

		}
		
	}
	
}
