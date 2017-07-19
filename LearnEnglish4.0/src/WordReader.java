import java.awt.Color;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import javax.swing.JLabel;

public class WordReader {
	
	public ArrayList<Words> wordArrayList = null;
	public ArrayList<Words> reviewArrayList = null;
	public ArrayList<Words> historyArrayList = null;
	public ArrayList<Words> recentArrayList = null;
	public JLabel wordLabel = null;
	public JLabel meaningLabel = null;
	public int i = 0;
	
	Random random = new Random();
	
	
	
	WordReader(ArrayList<Words> wordArrayList, ArrayList<Words> reviewArrayList, ArrayList<Words> historyArrayList,
			ArrayList<Words> recentArrayList, JLabel wordLabel, JLabel meaningLabel) {
		this.wordArrayList = wordArrayList;
		this.reviewArrayList = reviewArrayList;
		this.historyArrayList = historyArrayList;
		this.recentArrayList = recentArrayList;
		this.wordLabel = wordLabel;
		this.meaningLabel = meaningLabel;
	}
	
	public void read(String file, ArrayList<Words> relatedArrayList) throws IOException {
		
		String tempString = null;
		String[] temp = null;
		
		FileReader fileReader = new FileReader(file);
		BufferedReader reader = new BufferedReader(fileReader);
		
		
		while((tempString = reader.readLine()) != null) {
			
			temp = tempString.split("/");
			//System.out.println(temp[1]);
			relatedArrayList.add(new Words(temp[0],"/"+temp[1]+"/"+temp[2], false));
			
		}
		
		reader.close();
		
	}
	
	public void writeHistory() throws IOException {
		
		FileWriter filewriter = new FileWriter("./HistoryList.txt", true);
		BufferedWriter writer = new BufferedWriter(filewriter);
		Words temp = new Words();
		
		for(Iterator<Words> i = reviewArrayList.iterator();i.hasNext();) {
			temp = i.next();
			if(historyArrayList.indexOf(temp) == -1) {
				writer.write(temp.toString(true));
			}
		}
		
		writer.flush();
		writer.close();
		filewriter.close();
	}
	
	public void writeRecent() throws IOException {
		SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy.MM.dd-HH.mm.ss");
		Date date = new Date();
		//String recentFileName = "./" + dateFormater.format(date);
		
		FileWriter filewriterRecent = new FileWriter("./" + dateFormater.format(date)+".txt");
		BufferedWriter writerRecent = new BufferedWriter(filewriterRecent);
		
		for(Iterator<Words> i = reviewArrayList.iterator();i.hasNext();) {
			writerRecent.write(i.next().toString(true));
		}
		
		writerRecent.flush();
		writerRecent.close();
		filewriterRecent.close();
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

