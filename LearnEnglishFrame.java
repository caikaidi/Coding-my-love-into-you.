import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class LearnEnglishFrame extends JFrame{

	public JLabel word = new JLabel("World");
	public JLabel meaning = new JLabel("Meaning");
	//public JPanel wordAndMeaning = new JPanel();
	//public JPanel logAndImage = new JPanel();
	public JTextField textFild = new JTextField(50);
	public JTextArea log = new JTextArea(10,40);
	public JLabel imageLabel = new JLabel();
	public ImageIcon image = new ImageIcon("./correct.jpg");
	
	
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		new LearnEnglishFrame().lunchFrame();

	}
	
	public void lunchFrame() {
		
		JPanel wordAndMeaning = new JPanel(new GridLayout(1, 2));
		//wordAndMeaning.setBackground(Color.YELLOW);
		//word.setBackground(Color.RED);
		word.setForeground(Color.RED);
		word.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		//meaning.setBackground(Color.RED);
		wordAndMeaning.add(word);
		wordAndMeaning.add(meaning);

		
		JScrollPane jsp = new JScrollPane(log);
		
		JPanel logAndImage = new JPanel();
		logAndImage.add(jsp);
		//imageLabel.setBounds(0, 0, 150, 150);
		image.setImage(image.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT));
		imageLabel.setIcon(image);
		logAndImage.add(imageLabel);
		
		JPanel textFieldAndButton = new JPanel();
		JButton button = new JButton("...");
		textFieldAndButton.add(textFild);
		textFieldAndButton.add(button);
		


		this.add(wordAndMeaning, BorderLayout.NORTH);
		this.add(logAndImage, BorderLayout.CENTER);
		this.add(textFieldAndButton, BorderLayout.SOUTH);
		
		this.setLocation(400,250);
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.addWindowListener(new WindowAdapter(){

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO 自动生成的方法存根
				System.exit(0);
			}
			
		});
		
		
	}
	
	

}
