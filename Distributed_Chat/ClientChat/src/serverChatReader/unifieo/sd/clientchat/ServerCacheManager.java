package serverChatReader.unifieo.sd.clientchat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ServerCacheManager {

	private final static String FILENAME = "servers_cache.db";
	
	private List<String> servers = new ArrayList<>();
	private int i = 0;
	
	public ServerCacheManager() {
		loadFile();
	}
	
	public boolean isEmpty() {
		return servers.size() == 0;
	}
	
	public void removeServer(String ipPort) {
		servers.remove(ipPort);
		i--;
	}
	
	public String nextServer() {
		if(i < servers.size()) {
			return servers.get(i++);
		}
		return null;
	}
	
	public void addServer(String ip, int port) {
		servers.add(String.format("%s:%d", ip, port));
	}
	
	private void loadFile() {
		String line = null;
		BufferedReader br = null;
		i = 0;
		try {
			File file = new File(FILENAME);
			br = new BufferedReader(new FileReader(file));
		
			while((line = br.readLine()) != null) {
				if(line.matches("\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}:\\d{1,5}")) {
					servers.add(line);
				}
			}
		} catch (IOException e) {
			System.out.println("[Cache Manager] Erro na leitura do cache: " + e.getMessage());
		} finally {
			if(br != null) {
				try {
					br.close();
				} catch (IOException e) {
				}
			}
		}
	}
	
	public void saveCache() {
		PrintWriter pw = null;
		
		try {
			File file = new File(FILENAME);
			pw = new PrintWriter(file);
			
			ListIterator<String> listIterator = servers.listIterator();
			while(listIterator.hasNext()) {
				pw.println(listIterator.next());
			}
		} catch (IOException e) {
			System.out.println("[Cache Manager] Erro ao salvar o cache: " + e.getMessage());
		} finally {
			if(pw != null) {
				pw.close();
			}
		}
		
	
	}
}
