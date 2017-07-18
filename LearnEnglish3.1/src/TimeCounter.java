
public class TimeCounter {
	
	boolean flag = true;
	long startTime = 0L;
	long stopTime = 0L;
	long timeUsed = 0L;
	Integer sec = 0;
	Integer ms = 0;
	String showTime = null;
	
	public void start() {
		if (flag) {
			startTime = System.currentTimeMillis();
			flag = false;
		}
	}
	
	public void stop() {
		stopTime = System.currentTimeMillis();
		flag = true;
	}
	
	public String showTime() {
		
		timeUsed = stopTime - startTime;
		sec = (int) (timeUsed/1000);
		ms = (int) (timeUsed - sec*1000);
		if(sec/10 > 0) {
			showTime = sec.toString() + ":" + ms.toString();
		}else {
			showTime = "0" + sec.toString() + ":" + ms.toString();
		}

		return showTime;
	}
}
