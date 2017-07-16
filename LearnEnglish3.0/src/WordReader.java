import java.awt.Color;
import java.io.*;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;

public class WordReader {
	
	public ArrayList<Words> wordArrayList = null;
	public ArrayList<Words> reviewArrayList = null;
	public JLabel wordLabel = null;
	public JLabel meaningLabel = null;
	public int i = 0;
	
	Random random = new Random();
	
	
	
	WordReader(ArrayList<Words> wordArrayList, ArrayList<Words> reviewArrayList, JLabel wordLabel, JLabel meaningLabel) {
		this.wordArrayList = wordArrayList;
		this.reviewArrayList = reviewArrayList;
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
	
	
	public void showWord(ArrayList<Words> someArrayList) {
		
		i = random.nextInt(someArrayList.size());
		
		wordLabel.setText(someArrayList.get(i).word);
		meaningLabel.setText(someArrayList.get(i).meaning);
		
		if(someArrayList.get(i).read == true) {
			wordLabel.setForeground(Color.GREEN);
		} else {
			wordLabel.setForeground(Color.RED);
		}
		
		someArrayList.get(i).read();
		
		//System.out.println(someArrayList.indexOf(someArrayList.get(i)));
		if(reviewArrayList.indexOf(someArrayList.get(i)) == -1) {
			reviewArrayList.add(someArrayList.get(i));
		}
	}
	
	/*
	public void showWord() {
		
		i = random.nextInt(wordArrayList.size());
		
		wordLabel.setText(wordArrayList.get(i).word);
		meaningLabel.setText(wordArrayList.get(i).meaning);
		
		if(wordArrayList.get(i).read == true) {
			wordLabel.setForeground(Color.GREEN);
		} else {
			wordLabel.setForeground(Color.RED);
		}
		
		wordArrayList.get(i).read();
		reviewArrayList.add(wordArrayList.get(i));

	}
	*/
	
	public void reviewWord() {
		
		//System.out.println(reviewArrayList.size());
		i = random.nextInt(reviewArrayList.size());
		
		wordLabel.setText(reviewArrayList.get(i).word);
		meaningLabel.setText(reviewArrayList.get(i).meaning);
		
		if(reviewArrayList.get(i).read == true) {
			wordLabel.setForeground(Color.GREEN);
		} else {
			wordLabel.setForeground(Color.RED);
		}
		
		reviewArrayList.get(i).read();
		
	}

}

