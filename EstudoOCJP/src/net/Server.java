package net;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;

public class Server {
	public static void main(String[] args) throws IOException {
		ArrayList<Socket> clients = new ArrayList<Socket>();

		ServerSocket ss = new ServerSocket(6000, 2);

		while(true) {
			Socket s = ss.accept();
			clients.add(s);
			System.out.println("Client " + s.getPort() + "...");
		}
	}
}
