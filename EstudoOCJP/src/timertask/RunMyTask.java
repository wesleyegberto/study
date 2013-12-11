package timertask;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class RunMyTask {

	public static void main(String[] args) {
		final long timerToExec = 5000; // * 60 * 60 * 24;

		Timer t = new Timer();
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MINUTE, 00);
		c.set(Calendar.SECOND, 10);
		System.out.println(c.getTime());

		t.schedule(new MyTimerTask(), c.getTime(), timerToExec);
	}
}

class MyTimerTask extends TimerTask {
	@Override
	public void run() {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("C:\\myservice" + System.currentTimeMillis() + ".txt"));
			bw.write("Hora: " + new Date() + "\r\n");
			bw.flush();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			if(bw != null) {
				try {
					bw.close();
				} catch(IOException e) {
					e.printStackTrace();
				}
			}
		}
		// System.out.println("I'm alive");
	}
}
