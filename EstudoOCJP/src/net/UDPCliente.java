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

public class UDPCliente {
	public static void main(String[] args) throws Exception {
		byte[] buff = new byte[64];

		DatagramSocket ds = new DatagramSocket(4545); // Passa a porta que será
														// usada para receber os
														// dados
		System.out.println("Endereço usado: " + InetAddress.getLocalHost().getHostAddress() + ":" + ds.getLocalPort());

		DatagramPacket dp = new DatagramPacket(buff, buff.length, InetAddress.getByName(InetAddress.getLocalHost()
				.getHostAddress()), ds.getLocalPort()); // Porta que será usada
														// para receber os dados

		System.out.println("Aguardando dados...");
		// Aguarda o recebimento do pacote
		ds.receive(dp);
		System.out.println("Dados recebido.");
		String pack = new String(dp.getData());
		System.out.println(pack);
	}
}
