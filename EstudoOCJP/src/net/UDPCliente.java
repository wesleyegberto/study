/**
 * Classe que ser� o cliente na comunica��o UDP. -A conex�o UDP n�o exige um
 * canal de comunica��o fixo (estabelecimento de comunica��o entre duas
 * m�quinas). -Envia os pacotes para o destinat�rio, n�o h� garantias de que o
 * pacote chegou ou se est� inteiro ou na ordem. -Ideal para aplica��es que
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

		DatagramSocket ds = new DatagramSocket(4545); // Passa a porta que ser�
														// usada para receber os
														// dados
		System.out.println("Endere�o usado: " + InetAddress.getLocalHost().getHostAddress() + ":" + ds.getLocalPort());

		DatagramPacket dp = new DatagramPacket(buff, buff.length, InetAddress.getByName(InetAddress.getLocalHost()
				.getHostAddress()), ds.getLocalPort()); // Porta que ser� usada
														// para receber os dados

		System.out.println("Aguardando dados...");
		// Aguarda o recebimento do pacote
		ds.receive(dp);
		System.out.println("Dados recebido.");
		String pack = new String(dp.getData());
		System.out.println(pack);
	}
}
