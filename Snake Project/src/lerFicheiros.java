import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 
public class lerFicheiros {
 
	private static int highScore;
	
  public int exec (String fileName) {

	  
	  
    try {
      FileReader arq = new FileReader(fileName);
      BufferedReader lerArq = new BufferedReader(arq);
       
      highScore = lerArq.read();
      highScore -=48;
             
      System.out.println(highScore);
       
       
      lerArq.close();
      arq.close();
      
      
    } catch (IOException nfe) {
        System.out.println("Erro na abertura do arquivo: " +nfe.getMessage());
    }

    
    return highScore;
    
  }
}