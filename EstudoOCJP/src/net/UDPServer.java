/**
 * Classe que será o cliente na comunicação UDP. -A conexão UDP não exige um
 * canal de comunicação fixo (estabelecimento de comunicação entre duas
 * máquinas). -Envia os pacotes para o destinatário, não há garantias de que o
 * pacote chegou ou se está inteiro ou na ordem. -Ideal para aplicações que
 * enviam mtos dados.
 * 
 */
package net;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Date;

public class UDPServer {
	public static void main(String[] args) throws Exception {
		DatagramSocket ds = new DatagramSocket(4546); // Porta que será usada
														// para enviar os dados
		DatagramPacket dp;

		// Broadcast da class C -> IP's no intervalo de 192 a 223
		InetAddress ia = InetAddress.getByName("255.255.0.0");
		byte[] buff;

		for(;;) {
			Thread.sleep(500);
			String s = (new Date()).toString();
			buff = s.getBytes();
			dp = new DatagramPacket(buff, buff.length, ia, 4545); // Porta de
																	// destino
																	// dos dados
			System.out.println("Enviando dados para " + dp.getAddress().getHostAddress() + "...");
			// Envia o pacote
			ds.send(dp);
			System.out.println("Dados enviado.");
		}
	}
}
