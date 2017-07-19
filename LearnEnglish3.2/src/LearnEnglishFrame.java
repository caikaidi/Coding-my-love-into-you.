import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;


@SuppressWarnings("serial")
public class LearnEnglishFrame extends JFrame{
	
	//静态变量
	public static ImageIcon CORRECT_IMAGE = new ImageIcon("./Correct.jpg");
	public static ImageIcon WRONG_IMAGE = new ImageIcon("./TianZhuWenHao.jpg");
	public static ImageIcon PROBLEM_IMAGE = new ImageIcon("./Problem.jpg");

	//图形化元素
	public JLabel wordLabel = new JLabel("World");
	public JLabel meaningLabel = new JLabel("Meaning");
	public JTextField textField = new JTextField(46);
	public JTextArea log = new JTextArea(10,40);
	public JLabel imageLabel = new JLabel();
	public JButton button = new JButton("review");
	public JMenuBar menuBar = new JMenuBar();
	public JMenu wordlistMenu = new JMenu("Choose Wordlist");
	public JMenuItem CETMenuItem = new JMenuItem("CET-6");
	public JMenuItem reviewMenuItem = new JMenuItem("Learn History");
	
	//公共成员变量
	//public String word = null;
	public ArrayList<Words> wordArrayList = new ArrayList<Words>();
	public ArrayList<Words> reviewArrayList = new ArrayList<Words>();
	public ArrayList<Words> historyArrayList = new ArrayList<Words>();
	public ArrayList<Words> recentArrayList = new ArrayList<Words>();
	public ArrayList<Words> arrayListUsingNow = null;
	public WordReader wordReader = null;
	public TimeCounter timeCounter = new TimeCounter();
	
	//main方法
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		//new LearnEnglishFrame().lunchFrame();
		LearnEnglishFrame LEF = new LearnEnglishFrame();
		LEF.lunchFrame();
		LEF.startLearning();
	}
	
	public void lunchFrame() {
		
		//初始化Menu
		menuBar.add(wordlistMenu);
		wordlistMenu.add(CETMenuItem);
		wordlistMenu.add(reviewMenuItem);
		
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
		
		JPanel menuAndWordAndMeaning = new JPanel(new BorderLayout());
		menuAndWordAndMeaning.add(menuBar, BorderLayout.NORTH);
		menuAndWordAndMeaning.add(wordAndMeaning, BorderLayout.CENTER);

		//初始化显示Log和图片
		JScrollPane jsp = new JScrollPane(log);
		JPanel logAndImage = new JPanel();
		logAndImage.add(jsp);
		//imageLabel.setBounds(0, 0, 150, 150);
		CORRECT_IMAGE.setImage(CORRECT_IMAGE.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		WRONG_IMAGE.setImage(WRONG_IMAGE.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		PROBLEM_IMAGE.setImage(PROBLEM_IMAGE.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		imageLabel.setIcon(CORRECT_IMAGE);
		logAndImage.add(imageLabel);
		
		//初始化输入区
		JPanel textFieldAndButton = new JPanel();
		//JButton button = new JButton("review");
		textFieldAndButton.add(textField);
		textFieldAndButton.add(button);

		//添加WordReader
		wordReader = new WordReader(wordArrayList, reviewArrayList,historyArrayList, 
				recentArrayList, wordLabel, meaningLabel);
		try {
			wordReader.read("./WordList.txt", wordArrayList);
		} catch (IOException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		//wordReader.showWord(wordArrayList);

		//初始化Frame
		//this.add(menuBar, BorderLayout.NORTH);
		//this.add(wordAndMeaning, BorderLayout.NORTH);
		this.add(menuAndWordAndMeaning, BorderLayout.NORTH);
		this.add(logAndImage, BorderLayout.CENTER);
		this.add(textFieldAndButton, BorderLayout.SOUTH);
		
		//this.setLocationRelativeTo(null);
		this.setLocation(400,250);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		
		//初始化光标位置
		textField.grabFocus();
		
		//初始化Listener
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自动生成的方法存根
				try {
					wordReader.read("./HistoryList", historyArrayList);
				} catch (FileNotFoundException e3) {
					//第一次会没有文件，不处理FileNotFoundException
				} catch (IOException e2) {
					// TODO 自动生成的 catch 块
					e2.printStackTrace();
				}
				try {
					wordReader.writeHistory();
				} catch (IOException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				System.exit(0);
			}
			
		});
		//arrayListUsingNow = wordArrayList;
		textField.addActionListener(new TextFieldActionListener());
		textField.addKeyListener(new TextFieldKeyListener());
		button.addActionListener(new ButtonActionListener());
		CETMenuItem.addActionListener(new MenuActionListener());
		reviewMenuItem.addActionListener(new MenuActionListener());
		
		//debug
		//System.out.println(meaningLabel.getFont());
		
	}
	
	private class MenuActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			if(e.getSource() == CETMenuItem) {
				
			} else if (e.getSource() == reviewMenuItem) {
				
			}
		}
		
	}
	
	private class TextFieldActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			timeCounter.stop();
			if(textField.getText().trim().equals(arrayListUsingNow.get(wordReader.i).word.trim())) {
				 //Right
				 log.append(arrayListUsingNow.get(wordReader.i).toString()+"..."+timeCounter.showTime());
				 log.append("\n");
				 log.setCaretPosition(log.getText().length());
				 wordReader.showWord(arrayListUsingNow);
				 imageLabel.setIcon(CORRECT_IMAGE);
				 
			} else {
				//Wrong
				log.append("Wrong Spell:\n");
				log.append(arrayListUsingNow.get(wordReader.i).word + " NOT " + textField.getText());
				//timeCounter.showTime();
				log.append("\n");
				log.setCaretPosition(log.getText().length());
				imageLabel.setIcon(WRONG_IMAGE);
			}
			textField.setText("");
			textField.grabFocus();
		}
		
	}
	
	private class TextFieldKeyListener extends KeyAdapter {

		@Override
		public void keyPressed(KeyEvent e) {
			// TODO 自动生成的方法存根
			
			//System.out.println(System.currentTimeMillis());
			if(e.getKeyCode()>=KeyEvent.VK_A & e.getKeyCode()<=KeyEvent.VK_Z) {	
				//start
				timeCounter.start();
				//System.out.println(timeCounter.flag);
				//System.out.println(timeCounter.timeUsed);
			}else if (e.getKeyCode() == KeyEvent.VK_ENTER) {
				//stop
				//timeCounter.stop();
				
			}else {
				//change pic
				imageLabel.setIcon(PROBLEM_IMAGE);
			}
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
		arrayListUsingNow = wordArrayList;
	}
	
	
}
