package br.unifieo.sd.serverchat.process;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import br.unifieo.sd.serverchat.IllegalDataException;
import br.unifieo.sd.serverchat.Settings;
import br.unifieo.sd.serverchat.data.GroupManager;

/**
 * Status: Aguarda solicitações do DNS sobre o status, retorna OK e quantos clientes estão online.
 *  - Receberá e enviará uma mensagem no formato: <STATUS>|<CLIENT_QTDE>
 *    . STATUS: 0 - Ok e 1 - Erro
 *    . CLIENT_QTDE: Quantidade de clientes ativos no servidor 
 */
public class SyncProcess extends SocketProcess {

	private ServerSocket serverSync;
	
	private GroupManager groupManager;
	
	public SyncProcess(GroupManager groupManager) throws IOException {
		// Inicia o server
		serverSync = new ServerSocket(Settings.SERVER_SYNC_PORT);
		
		this.groupManager = groupManager;
	}
	
	@Override
	public void run() {
		Socket socketDns = null;
		
		while(true) {
			// Aguarda requisições dos DNS para sincronização
			try {
				//debug("Sync Server", "Aguardando sincronização");
				socketDns = serverSync.accept();
				
				if(socketDns == null)
					continue;
				
				processRequest(socketDns);
			} catch (IOException e) {
				debug("Sync Server", "Erro na solicitação de sincronização: " + e.getMessage());
			}
		}
	}

	@Override
	protected void processRequest(Socket socketDns) {
		BufferedReader br = null;
		PrintWriter pw = null;
		try {
			debug("Sync Server", "Inicializando sincronização");
			// Cria as streams para comunicação
			br = new BufferedReader(new InputStreamReader(socketDns.getInputStream()));
			pw = new PrintWriter(socketDns.getOutputStream());
			
			// Delay para aguardar sinal de transmissão
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
			}
			
			// Le a entrada para formatar e converter os dados
			String inputClient = br.readLine();
			if(inputClient == null) {
				throw new IllegalDataException("Entrada vazia");
			}
			
			String[] splittedData = inputClient.split("\\|");
			if(splittedData.length != 2) {
				throw new IllegalDataException("Formato invalido");
			}
			// Extrai os dados da mensagem recebida
			short action = Short.parseShort(splittedData[0]);
	
			// Valida e adiciona de acordo com o tipo de serviço
			if(action != 0) {
				throw new IllegalDataException("Açao invalida");
			}
			
			groupManager.verifyUsers();
			pw.print("0|");
			pw.println(groupManager.getChatUsers().size());
			
			debug("Sync Server", "Sincronização finalizada");
		} catch (IllegalDataException ex) {
			sendErrorMessage(socketDns, pw, ex.getMessage());
		} catch (IOException e) {
			debug("Sync Server", "Erro na solicitação de sincronização: " + e.getMessage());
		} finally {
			closeResources(socketDns, br, pw);
		}
	}

}
