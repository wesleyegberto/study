/**
 * @author Wesley Egberto de Brito
 * @version v1.0
 */
package tool;

public enum TypeMessage {
	NOTE_ON(144), // Inicia a reprodução da nota
	NOTE_OFF(128), // Interrompe a reprodução da nota
	CHANGE_INSTRUMENT(192), // Altera o instrumento
	CONTROL_CHANGE(176);

	private int typeMessage;

	private TypeMessage(int type) {
		typeMessage = type;
	}

	public int getTypeMessage() {
		return typeMessage;

	}

}
