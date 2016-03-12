package br.unifieo.sd.serverchat.data;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import br.unifieo.sd.serverchat.ServerChat;

/**
 * Gerencia os usuários do chat: - Quando um usuário entra ou sai notifica todos
 * os outros.
 * 
 * - Formato da mensagem: <ACTION>|<TYPE>|<CONTENT>
 * - Action:
 *   - 1: Conectou
 *   - 2: Desconectou
 *   - 3: Broadcast
 *   - 4: PVT - privado
 * - Type:
 *   - 1: Mensagem de texto
 *   - 2: Bytes de arquivo
 *   - 3: Nome do usuário destinatário no PVT
 * - Content: mensagem ou bytes de um arquivo
 */
public class GroupManager {
	private List<UserChatListenerProcess> chatUsers;

	public GroupManager() {
		chatUsers = new LinkedList<>();
	}
	
	public List<UserChatListenerProcess> getChatUsers() {
		return chatUsers;
	}

	public synchronized void addUser(UserChatListenerProcess newUser) {
		newUser.setListener(this);
		broadcastConnection(newUser);
		chatUsers.add(newUser);
		ServerChat.getExecutorService().execute(newUser);
	}
	
	public synchronized void removeUser(UserChatListenerProcess user) {
		chatUsers.remove(user);
	}

	public synchronized void verifyUsers() {
		ListIterator<UserChatListenerProcess> listIterator = chatUsers.listIterator();
		UserChatListenerProcess userChat = null;
		while(listIterator.hasNext()) {
			userChat = listIterator.next();
			
			if(!userChat.isConnected()) {
				listIterator.remove();
				userChat.disconnect();
				broadcastDisconnection(userChat);
			}
		}
	}
	
	private void broadcastConnection(UserChatListenerProcess newUser) {
		ListIterator<UserChatListenerProcess> listIterator = chatUsers.listIterator();
		UserChatListenerProcess user = null;
		
		while(listIterator.hasNext()) {
			user = listIterator.next();
			user.sendMessage(String.format("1|%1$s|%1$s entrou do chat", newUser.getUsername()));
		}
	}
	
	private void broadcastDisconnection(UserChatListenerProcess userAction) {
		if(chatUsers.contains(userAction)) {
			chatUsers.remove(userAction);
		}
		ListIterator<UserChatListenerProcess> listIterator = chatUsers.listIterator();
		UserChatListenerProcess user = null;
		
		while(listIterator.hasNext()) {
			user = listIterator.next();
			user.sendMessage(String.format("2|%1$s|%1$s saiu do chat", userAction.getUsername()));
		}
	}
	
	private void broadcastMessage(UserChatListenerProcess userAction, String message) {
		ListIterator<UserChatListenerProcess> listIterator = chatUsers.listIterator();
		UserChatListenerProcess user = null;
		
		while(listIterator.hasNext()) {
			user = listIterator.next();
			
			if(userAction == user) {
				continue;
			}
			
			user.sendMessage(String.format("3|%s|%s", userAction.getUsername(), message));
		}
	}
	
	private void sendPrivateMessage(UserChatListenerProcess userAction, String message, String usernamePvtDest) {
		ListIterator<UserChatListenerProcess> listIterator = chatUsers.listIterator();
		UserChatListenerProcess user = null;
		
		while(listIterator.hasNext()) {
			user = listIterator.next();
			if(user.getUsername().equals(usernamePvtDest)) {
				user.sendMessage(String.format("4|%s|%s", userAction.getUsername(), message));
				return;
			}
		}
	}
	
	public synchronized void fireEvent(UserEvent userEvent) {
		System.out.println("[Group Manager] Evento: " + userEvent);
		
		switch (userEvent.getEvent()) {
			case UserEvent.EVENT_CONNECTION:
				broadcastDisconnection(userEvent.getUserEvent());
				break;
			case UserEvent.EVENT_DISCONNECTION:
				broadcastDisconnection(userEvent.getUserEvent());
				break;
			case UserEvent.EVENT_BROADCAST:
				broadcastMessage(userEvent.getUserEvent(), userEvent.getMessage());
				break;
			case UserEvent.EVENT_PRIVATE:
				sendPrivateMessage(userEvent.getUserEvent(), userEvent.getMessage(), userEvent.getUsernamePvtDest());
				break;
		}
	}
}
