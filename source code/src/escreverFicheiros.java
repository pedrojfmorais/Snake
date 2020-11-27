import java.io.*;

public class escreverFicheiros {

	  public void exec(String FileName, int highScore) {

		  try {
		  
		  FileWriter arq = new FileWriter(FileName, false);
		  BufferedWriter escArq = new BufferedWriter(arq);
		      
		  System.out.print(highScore);
		      
		  String linha = Integer.toString(highScore, 999);
		      
		  
			escArq.write(linha);
		
		  escArq.flush();
		      
		  escArq.close();
		  arq.close();
		  
		  } catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		  }
	
}
