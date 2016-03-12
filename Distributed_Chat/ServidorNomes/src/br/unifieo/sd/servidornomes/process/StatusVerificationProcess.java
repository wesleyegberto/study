package br.unifieo.sd.servidornomes.process;

import java.io.IOException;
import java.net.Socket;

public class StatusVerificationProcess extends SocketProcess {

	// Lista de servidores de serviço de Chat e FTP
	private ServiceServerProcess servidoresChat;
	private ServiceServerProcess servidoresFtp;
	
	public StatusVerificationProcess(ServiceServerProcess servidoresChat, ServiceServerProcess servidoresFtp) throws IOException {
		this.servidoresChat = servidoresChat;
		this.servidoresFtp = servidoresFtp;
	}
	
	public void run() {
		while(true) {
			servidoresChat.sync();
			//servidoresFtp.sync();
			try {
				Thread.sleep(30000);
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	protected void processRequest(Socket socketClient) {
	}
}
