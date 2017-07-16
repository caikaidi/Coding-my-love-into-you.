import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;


@SuppressWarnings("serial")
public class LearnEnglishFrame extends JFrame{
	
	public static ImageIcon CORRECT_IMAGE = new ImageIcon("./Correct.jpg");
	public static ImageIcon WRONG_IMAGE = new ImageIcon("./TianZhuWenHao.jpg");

	public JLabel wordLabel = new JLabel("World");
	public JLabel meaningLabel = new JLabel("Meaning");
	//public JPanel wordAndMeaning = new JPanel();
	//public JPanel logAndImage = new JPanel();
	public JTextField textField = new JTextField(50);
	public JTextArea log = new JTextArea(10,40);
	public JLabel imageLabel = new JLabel();
	//public ImageIcon image = new ImageIcon("./correct.jpg");
	JButton button = new JButton("review");
	
	//public String word = null;
	public ArrayList<Words> wordArrayList = new ArrayList<Words>();
	public ArrayList<Words> reviewArrayList = new ArrayList<Words>();
	public ArrayList<Words> arrayListUsingNow = null;
	public WordReader wordReader = null;
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//new LearnEnglishFrame().lunchFrame();
		LearnEnglishFrame LEF = new LearnEnglishFrame();
		LEF.lunchFrame();
		LEF.startLearning();
	}
	
	public void lunchFrame() {
		
		//初始化显示单词和释义的Label
		//JPanel wordAndMeaning = new JPanel(new GridLayout(1, 2));
		JPanel wordAndMeaning = new JPanel(new FlowLayout(FlowLayout.LEFT));
		//wordAndMeaning.setBackground(Color.YELLOW);
		//word.setBackground(Color.RED);
		wordLabel.setForeground(Color.RED);
		wordLabel.setFont(new Font("Times New Roman", Font.PLAIN, 32));
		//meaning.setBackground(Color.RED);
		meaningLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 25));
		wordAndMeaning.add(wordLabel);
		wordAndMeaning.add(meaningLabel);

		//初始化显示Log和图片的区域
		JScrollPane jsp = new JScrollPane(log);
		JPanel logAndImage = new JPanel();
		logAndImage.add(jsp);
		//imageLabel.setBounds(0, 0, 150, 150);
		CORRECT_IMAGE.setImage(CORRECT_IMAGE.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		WRONG_IMAGE.setImage(WRONG_IMAGE.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		imageLabel.setIcon(CORRECT_IMAGE);
		logAndImage.add(imageLabel);
		
		//初始化输入区
		JPanel textFieldAndButton = new JPanel();
		//JButton button = new JButton("review");
		textFieldAndButton.add(textField);
		textFieldAndButton.add(button);

		//添加WordReader
		wordReader = new WordReader(wordArrayList, reviewArrayList, wordLabel, meaningLabel);
		try {
			wordReader.read();
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		//wordReader.showWord(wordArrayList);

		//初始化Frame
		this.add(wordAndMeaning, BorderLayout.NORTH);
		this.add(logAndImage, BorderLayout.CENTER);
		this.add(textFieldAndButton, BorderLayout.SOUTH);
		
		this.setLocation(400,250);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		
		//初始化Listener
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自动生成的方法存根
				System.exit(0);
			}
			
		});
		arrayListUsingNow = wordArrayList;
		textField.addActionListener(new TextFieldActionListener());
		button.addActionListener(new ButtonActionListener());
		
		//debug
		//System.out.println(meaningLabel.getFont());
		
	}
	
	private class TextFieldActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if(textField.getText().trim().equals(arrayListUsingNow.get(wordReader.i).word.trim())) {
				//Right
				 log.append(arrayListUsingNow.get(wordReader.i).toString());
				 log.append("\n");
				 log.setCaretPosition(log.getText().length());
				 wordReader.showWord(arrayListUsingNow);
				 imageLabel.setIcon(CORRECT_IMAGE);
				 
			} else {
				//Wrong
				log.append("Wrong Spell:\n");
				log.append(arrayListUsingNow.get(wordReader.i).word + " NOT " + textField.getText());
				log.append("\n");
				log.setCaretPosition(log.getText().length());
				imageLabel.setIcon(WRONG_IMAGE);
			}
			textField.setText("");
		}
		
	}
	
	private class ButtonActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if (e.getActionCommand()=="review") {
				arrayListUsingNow = reviewArrayList;
				wordReader.reviewWord();
				button.setText("go on ");
			}else if (e.getActionCommand() == "go on ") {
				arrayListUsingNow = wordArrayList;
				wordReader.showWord(arrayListUsingNow);
				button.setText("review");
			}else {
				log.append(e.getActionCommand());
			}
		}
		
	}
	
	public void startLearning () {
		
		wordReader.showWord(wordArrayList);
		
	}

}
