package net;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket sc;

		sc = new Socket(InetAddress.getLocalHost(), 6000);

		System.out.println("Conectado...");
	}
}
