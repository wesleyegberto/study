/**
 * Copyright (c) 2010-2012, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 30/06/2012
 * 
 * @version 1.0
 * 
 *          Classe responsável pelo gerenciamento de arquivos e suas permissões.
 */
package br.com.bse.pattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public final class FileManager {
	private FileManager() {
	}

	/**
	 * Salva a String contida em value em um arquivo especificado pelo
	 * absolutePath. Caso não tenha permissão, é alterado as permissões da pasta
	 * para permitir a operação.
	 * 
	 * @param absolutePath
	 *            String contendo o caminho absoluto para o local do arquivo a
	 *            ser criado
	 * @param value
	 *            String contendo os dados a serem salvos
	 * @return true se foi salvo com sucesso
	 * @throws IOException
	 *             se ocorrer algum erro durante a operação
	 */
	public static boolean saveFile(String absolutePath, String value) throws IOException {
		File f = null;
		FileWriter fw = null;
		String[] path = absolutePath.replace(File.separatorChar, '/').split("/");
		int length = path.length;
		String paths = path[0];

		/*
		 * for(int i = 1; i < length; i++) { f = new File(paths);
		 * if(!f.exists()) { f.mkdir(); f.setWritable(true);
		 * f.setReadable(true); } paths += File.separator + path[i]; }
		 */

		try {
			f = new File(absolutePath);
			if(!f.exists())
				f.mkdirs();
			f.setWritable(true);
			f.setReadable(true);
			fw = new FileWriter(f);
			fw.write(value);
			fw.flush();
			fw.close();
			return true;
		} catch(FileNotFoundException e) {
			throw e;
		} catch(IOException e) {
			throw e;
		} catch(Exception e) {
			return false;
		}
	}

	/**
	 * Delete o arquivo referente ao caminho absoluto.
	 * 
	 * @param path
	 *            caminho absoluto até o arquivo que será deletado
	 * @return true se foi deletado com sucesso
	 */
	public static boolean delete(String path) {
		try {
			File f = new File(path);
			if(!f.exists())
				return true;
			return f.delete();
		} catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Verifica se o arquivo referenciado pela String recebida arquivo existe.
	 * 
	 * @param pathFile
	 *            caminho absolute até o arquivo
	 * @return true se o arquivo existe
	 */
	public static boolean exist(String pathFile) {
		if(pathFile == null)
			return false;

		File f = new File(pathFile);
		return f.exists();
	}

}
