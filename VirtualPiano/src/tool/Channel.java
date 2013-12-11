/**
 * @author Wesley Egberto de Brito
 * @version v1.0
 */
package tool;

public enum Channel {
	NOTHING(0), TECLA(1), BATIDA(9);

	private int channel;

	private Channel(int channel) {
		this.channel = channel;
	}

	public int getChannel() {
		return this.channel;
	}

}
