package sound;

import javax.sound.midi.*;

public class Player {
	public static void main(String... args) {
		Player p = new Player();

		p.play();
	}

	public void play() {
		try {
			Sequencer player = MidiSystem.getSequencer();
			player.open();

			Sequence seq = new Sequence(Sequence.PPQ, 4);
			Track track = seq.createTrack();

			// MidiEvent event = null;
			/*
			 * ShortMessage first = new ShortMessage(); first.setMessage(192, 1,
			 * 12, 100);
			 * 
			 * MidiEvent changeInstrument = new MidiEvent(first, 1);
			 * track.add(changeInstrument);
			 */
			ShortMessage a = new ShortMessage();
			a.setMessage(144, 1, 120, 100);
			MidiEvent aMidi = new MidiEvent(a, 1);
			track.add(aMidi);

			ShortMessage b = new ShortMessage();
			b.setMessage(128, 1, 120, 100);
			MidiEvent bMidi = new MidiEvent(b, 16);
			track.add(bMidi);

			player.setSequence(seq);
			player.start();

			Thread.sleep(3000);

			player.close();
			System.exit(0);
		} catch(Exception ex) {
			ex.printStackTrace();
		}
	}
}
