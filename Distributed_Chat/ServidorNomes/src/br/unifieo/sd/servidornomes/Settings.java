package br.unifieo.sd.servidornomes;

/**
 * Centraliza as configurações do DNS.
 */
public class Settings {
	public static final int PORT_BINDER = 12345;
	public static final int PORT_NAME_RESOLUTION = 65156;

	// IP e Porta do DNS Principal
	public static final String DNS_1_IP = "10.2.10.130";
	public static final int DNS_1_PORT = 23132;
	
	// IP e Porta do DNS Secundário
	public static final String DNS_2_IP = "10.2.10.128";
	public static final int DNS_2_PORT = 22602;
}
