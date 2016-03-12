package br.unifieo.sd.serverchat.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.unifieo.sd.serverchat.data.ServerConstants;

/**
 * Representa um processo que utiliza socket.
 */
public abstract class SocketProcess implements Runnable {

	public SocketProcess() {
	}
	
	protected abstract void processRequest(Socket socketClient);
	
	protected void debug(String process, String message) {
		System.out.print("[");
		System.out.print(process);
		System.out.print("] ");
		System.out.println(message);
	}
	
	protected void sendErrorMessage(Socket socketClient, PrintWriter pw, String message) {
		if(socketClient != null && pw != null && socketClient.isConnected()) {
			pw.print(ServerConstants.FLAG_ERROR);
			pw.print("|");
			pw.println(message);
		}
	}

	protected void closeResources(ServerSocket socket) {
		if(socket != null && !socket.isClosed()) {
			try {
				socket.close();
			} catch (IOException e) {
			}
		}
	}

	protected void closeResources(Socket socketClient) {
		if(socketClient != null && !socketClient.isClosed()) {
			try {
				socketClient.close();
			} catch (IOException e) {
			}
		}
	}
	
	protected void closeResources(Socket socketClient, BufferedReader br, PrintWriter pw) {
		if(pw != null) {
			pw.flush();
			pw.close();
		}
		if(br != null) {
			try {
				br.close();
			} catch (IOException e) {
			}
		}
		closeResources(socketClient);
	}

}