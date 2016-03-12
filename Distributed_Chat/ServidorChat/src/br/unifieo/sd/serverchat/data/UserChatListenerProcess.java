package br.unifieo.sd.serverchat.data;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Gerenciar a conexão com um cliente.
 */
public class UserChatListenerProcess implements Runnable {
	private String username;
	private Socket socketUser;
	private BufferedReader reader;
	private PrintWriter writer;

	private GroupManager listener;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Socket getSocket() {
		return socketUser;
	}

	public void setSocket(Socket socket) {
		this.socketUser = socket;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public PrintWriter getWriter() {
		return writer;
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((socketUser == null) ? 0 : socketUser.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserChatListenerProcess other = (UserChatListenerProcess) obj;
		if (socketUser == null) {
			if (other.socketUser != null)
				return false;
		} else if (!socketUser.equals(other.socketUser))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public boolean isConnected() {
		if (socketUser == null || writer == null || reader == null) {
			return false;
		}
		
		return socketUser.isConnected();
	}

	public void setListener(GroupManager groupManager) {
		listener = groupManager;
	}

	public void fireDisconnectionEvent() {
		listener.fireEvent(new UserEvent(UserEvent.EVENT_DISCONNECTION, this, null));
	}
	
	public void disconnect() {
		if(writer != null) {
			writer.flush();
			writer.close();
			writer = null;
		}
		if(reader != null) {
			try {
				reader.close();
			} catch (IOException e) {
			}
			reader = null;
		}
		if(socketUser != null && !socketUser.isClosed()) {
			try {
				socketUser.close();
			} catch (IOException e) {
			}
			socketUser = null;
		}
	}

	@Override
	public void run() {
		String message = null;
		Scanner sc = new Scanner(reader);
		try {
			while((message = sc.nextLine()) != null) {
				System.out.println(username + " sent: " + message);
				if(message.length() > 0 && message.contains("|")) {
					String[] data = message.split("\\|");
					
					if("3".equals(data[0])) { // Broadcast
						listener.fireEvent(new UserEvent(UserEvent.EVENT_BROADCAST, this, data[2]));
					} else if("4".equals(data[0])) { // Privado
						listener.fireEvent(new UserEvent(UserEvent.EVENT_PRIVATE, this, data[1], data[2]));
					}
				}
			}
		} catch (Exception ex) {
			System.out.println("[User Listener] Erro: " + ex.getMessage());
		} finally {
			sc.close();
			disconnect();
			fireDisconnectionEvent();
		}
		
	}

	public void sendMessage(String message) {
		if(writer == null) {
			fireDisconnectionEvent();
			return;
		}
		System.out.println("Sending to " + username + ": " + message);
		writer.println(message);
		writer.flush();
	}
}
