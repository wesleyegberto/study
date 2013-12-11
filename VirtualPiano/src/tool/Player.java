/**
 * @author Wesley Egberto de Brito
 * @version v1.0
 * 
 *          A classe Player é responsável pela execução
 */
package tool;

import javax.sound.midi.*;

public final class Player {
	private Sequencer sequencer;
	private Sequence seq;
	private Track track;

	public Player() {
		try {
			sequencer = MidiSystem.getSequencer();
			// sequencer.setLoopCount(2); // Reproduz 2 vezes
			sequencer.open();
			createTrack();
		} catch(MidiUnavailableException e) {
			e.printStackTrace();
		}
	}

	public void addMidiEvent(MidiEvent midi) {
		if(midi == null) {
			return;
		}
		if(track == null) {
			System.out.println("Track inexistente.");
			return;
		}
		track.add(midi);
	}

	/**
	 * @return true se foi criado com sucesso e false caso ocorra algum erro
	 */
	public boolean createTrack() {
		try {
			seq = new Sequence(Sequence.PPQ, 16);
			track = seq.createTrack();
			return true;
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public void play() {
		setSequence(seq);
		sequencer.start();
		// sequencer.setTempoInBPM(120);
	}

	/**
	 * @param seq
	 *            é o novo objeto para ser utilizado na reprodução
	 * @return true se foi inserido com sucesso e false caso ocorra algum erro
	 */
	public boolean setSequence(Sequence seq) {
		try {
			this.seq = seq;
			sequencer.setSequence(this.seq);
			return true;
		} catch(InvalidMidiDataException e) {
			e.printStackTrace();
			return false;
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Player p = new Player();

		p.addMidiEvent(EventFactory.createEvent(TypeMessage.CHANGE_INSTRUMENT, Channel.TECLA, 3, 0, 1));
		p.addMidiEvent(EventFactory.createEvent(TypeMessage.NOTE_ON, Channel.TECLA, 80, 127, 1));

		p.play();
		Thread.sleep(1000);
		System.exit(0);

	}
}
