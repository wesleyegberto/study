package piano;

import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import tool.*;

@SuppressWarnings("serial")
public class GUIPiano extends JFrame {
	Player player;

	public GUIPiano() {
		super("Virtual Piano");
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		player = new Player();

		addKeyListener(new KeyListener());
		setVisible(true);
	}

	class KeyListener implements java.awt.event.KeyListener {
		@Override
		public void keyPressed(KeyEvent k) {

		}

		@Override
		public void keyReleased(KeyEvent k) {
		}

		@Override
		public void keyTyped(KeyEvent k) {
			int note = 0;

			switch(k.getKeyChar()) {
				case '=':
					note = 23;
					break;
				case '-':
					note = 24;
					break;
				case '0':
					note = 25;
					break;
				case '9':
					note = 26;
					break;
				case '8':
					note = 27;
					break;
				case '7':
					note = 28;
					break;
				case '6':
					note = 29;
					break;
				case '5':
					note = 30;
					break;
				case '4':
					note = 31;
					break;
				case '3':
					note = 32;
					break;
				case '2':
					note = 33;
					break;
				case '1':
					note = 34;
					break;

				case '[':
					note = 35;
					break;
				case 'p':
					note = 36;
					break;
				case 'o':
					note = 37;
					break;
				case 'i':
					note = 38;
					break;
				case 'u':
					note = 39;
					break;
				case 'y':
					note = 40;
					break;
				case 't':
					note = 41;
					break;
				case 'r':
					note = 42;
					break;
				case 'e':
					note = 43;
					break;
				case 'w':
					note = 44;
					break;
				case 'q':
					note = 45;
					break;

				case ']':
					note = 46;
					break;
				case 'ç':
					note = 47;
					break;
				case 'l':
					note = 48;
					break;
				case 'k':
					note = 49;
					break;
				case 'j':
					note = 50;
					break;
				case 'h':
					note = 51;
					break;
				case 'g':
					note = 52;
					break;
				case 'f':
					note = 53;
					break;
				case 'd':
					note = 54;
					break;
				case 's':
					note = 55;
					break;
				case 'a':
					note = 56;
					break;

				case '/':
					note = 57;
					break;
				case ';':
					note = 58;
					break;
				case '.':
					note = 59;
					break;
				case ',':
					note = 60;
					break;
				case 'm':
					note = 61;
					break;
				case 'n':
					note = 62;
					break;
				case 'b':
					note = 63;
					break;
				case 'v':
					note = 64;
					break;
				case 'c':
					note = 65;
					break;
				case 'x':
					note = 66;
					break;
				case 'z':
					note = 67;
					break;
				case '\\':
					note = 68;
					break;

				case 'P':
					note = 69;
					break;
				case 'O':
					note = 70;
					break;
				case 'I':
					note = 71;
					break;
				case 'U':
					note = 72;
					break;
				case 'Y':
					note = 73;
					break;
				case 'T':
					note = 74;
					break;
				case 'R':
					note = 75;
					break;
				case 'E':
					note = 76;
					break;
				case 'W':
					note = 77;
					break;
				case 'Q':
					note = 78;
					break;

				case 'Ç':
					note = 79;
					break;
				case 'L':
					note = 80;
					break;
				case 'K':
					note = 81;
					break;
				case 'J':
					note = 82;
					break;
				case 'H':
					note = 83;
					break;
				case 'G':
					note = 84;
					break;
				case 'F':
					note = 85;
					break;
				case 'D':
					note = 86;
					break;
				case 'S':
					note = 87;
					break;
				case 'A':
					note = 88;
					break;

				case 'M':
					note = 89;
					break;
				case 'N':
					note = 90;
					break;
				case 'B':
					note = 91;
					break;
				case 'V':
					note = 92;
					break;
				case 'C':
					note = 93;
					break;
				case 'X':
					note = 94;
					break;
				case 'Z':
					note = 95;
					break;

			}
			System.out.println((k.getKeyChar() + 0) + " => " + note);
			playNote(note);
		}

		public void playNote(int note) {
			if(note < 21)
				return;

			player.createTrack();
			// player.addMidiEvent(EventFactory.createEvent(TypeMessage.CONTROL_CHANGE,
			// Channel.TECLA, note, 0, 1));
			player.addMidiEvent(EventFactory.createEvent(TypeMessage.NOTE_ON, Channel.NOTHING, note, 60, 0));
			player.addMidiEvent(EventFactory.createEvent(TypeMessage.NOTE_OFF, Channel.NOTHING, note, 60, 10));
			player.play();
		}
	}

	public static void main(String[] args) {
		new GUIPiano();
	}
}
