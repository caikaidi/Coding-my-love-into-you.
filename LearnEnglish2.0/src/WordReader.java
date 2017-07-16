import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class WordReader {
	
	public ArrayList<Words> wordArrayList = null;
	public JLabel wordLabel = null;
	public JLabel meaningLabel = null;
	public int i = 0;
	
	Random random = new Random();
	
	
	
	WordReader(ArrayList<Words> wordArrayList, JLabel wordLabel, JLabel meaningLabel) {
		this.wordArrayList = wordArrayList;
		this.wordLabel = wordLabel;
		this.meaningLabel = meaningLabel;
	}
	
	public void read() throws IOException {
		
		String tempString = null;
		String[] temp = null;
		
		FileReader fileReader = new FileReader("./WordList.txt");
		BufferedReader reader = new BufferedReader(fileReader);
		
		
		while((tempString = reader.readLine()) != null) {
			
			temp = tempString.split("/");
			//System.out.println(temp[1]);
			wordArrayList.add(new Words(temp[0],"/"+temp[1]+"/"+temp[2], false));
			
		}
		
		reader.close();
		
	}
	
	public void showWord() {
		
		i = random.nextInt(wordArrayList.size());
		
		wordLabel.setText(wordArrayList.get(i).word);
		meaningLabel.setText(wordArrayList.get(i).meaning);
		
		wordArrayList.get(i).read();
	}

}

