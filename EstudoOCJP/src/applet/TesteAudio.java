package applet;

import java.applet.AudioClip;
import java.net.URL;
import javax.swing.JApplet;

public class TesteAudio extends JApplet {
	@Override
	public void init() {
		try {
			AudioClip audio = getAudioClip(getCodeBase(), "headshot.wav");
			audio.loop();
		} catch(Exception e) {
			e.printStackTrace();
		}

	}

}
