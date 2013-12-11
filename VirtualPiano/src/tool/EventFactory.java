/**
 * @author Wesley Egberto de Brito
 * @version v1.0
 * 
 *          A classe EventFactory � respons�vel por criar as MidiEvent
 */
package tool;

import javax.sound.midi.MidiEvent;
import javax.sound.midi.ShortMessage;

public final class EventFactory {
	private EventFactory() {
	}

	/**
	 * @param typeMsg
	 *            � o tipo da mensagem que s�o as constantes da enum Typemessage
	 * @param channel
	 *            � o canal como se fosse um m�sico (1 para baterista, 2 para
	 *            guitarrista, etc.)
	 * @param note
	 *            � a nota a ser reproduzida (de 0 a 127)
	 * @param v
	 *            � a velocidade (0 para suave, muito baixo, e 100 alto, � um
	 *            bom padr�o)
	 * @param moment
	 *            � o momento da batida em que ser� reproduzido
	 * @return MidiEvent com o som j� configurado
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
