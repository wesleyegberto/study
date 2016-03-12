package br.unifieo.sd.serverchat;

/**
 * Centraliza as configurações do DNS.
 */
public class Settings {
	// Constantes da localização do DNS
	public static final String DNS_1_IP = "127.0.0.1";
	public static final short DNS_1_PORT = 12345;
	
	public static final String DNS_2_IP = "127.0.0.1";
	public static final short DNS_2_PORT = 12345;
	
	// Porta do Server Chat
	public static final short SERVER_CHAT_PORT = 14156;
	
	// Porta do Server para Sincronização
	public static final short SERVER_SYNC_PORT = 15610;

	public static final short SERVER_POWER = 5;
	
	// Quantidade de tentativas de se conectar no DNS
	public static final short SERVER_QTY_TRY = 3;
}
