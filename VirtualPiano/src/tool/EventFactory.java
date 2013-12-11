/**
 * @author Wesley Egberto de Brito
 * @version v1.0
 * 
 *          A classe EventFactory é responsável por criar as MidiEvent
 */
package tool;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

public final class EventFactory {
	private EventFactory() {
	}

	/**
	 * @param typeMsg
	 *            é o tipo da mensagem que são as constantes da enum Typemessage
	 * @param channel
	 *            é o canal como se fosse um músico (1 para baterista, 2 para
	 *            guitarrista, etc.)
	 * @param note
	 *            é a nota a ser reproduzida (de 0 a 127)
	 * @param v
	 *            é a velocidade (0 para suave, muito baixo, e 100 alto, é um
	 *            bom padrão)
	 * @param moment
	 *            é o momento da batida em que será reproduzido
	 * @return MidiEvent com o som já configurado
	 */
	public static MidiEvent createEvent(TypeMessage typeMsg, Channel channel, int note, int v, int moment) {
		MidiEvent event = null;

		try {
			ShortMessage a = new ShortMessage();
			a.setMessage(typeMsg.getTypeMessage(), channel.getChannel(), note, v);
			event = new MidiEvent(a, moment);
		} catch(Exception e) {
			e.printStackTrace();
		}

		return event;
	}
}
