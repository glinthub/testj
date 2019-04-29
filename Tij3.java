import java.util.*;

class Timeout extends Timer {
	public Timeout(int delay, final String msg) {
		super(true); // Daemon thread
		schedule(new TimerTask() {
			public void run() {
				System.out.println(msg);
				System.exit(0);
			}
		}, delay);
	}
} ///:~

public class Tij3  {
	private static int i;
	
	public Tij3() {
		i=10;
		System.out.println("Tij3: ");
		i=11;
	}
	public static void main(String[] args) throws Exception {
		//new Tij3();
		System.out.println("Tij3 main: " + i);
		new Timeout(1,"123");
	}
} 