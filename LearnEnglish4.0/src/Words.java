
public class Words {


	String word;
	String meaning;
	Boolean read;
	
	Words() {}
	
	Words(String word, String meaning, Boolean read) {
		this.word = word;
		this.meaning = meaning;
		this.read = read;
	}
	
	public void read() {
		this.read = true;
	}
	

	@Override
	public String toString() {
		// TODO 自动生成的方法存根
		return word + meaning + (read==true ? "---learned" : "---unlearned");
	}
	public String toString(boolean b) {
		// TODO 自动生成的方法存根
		return word + meaning + "\n";
	}
}
