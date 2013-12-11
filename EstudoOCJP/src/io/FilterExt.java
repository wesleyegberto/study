/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito 26/01/2013
 * 
 */
package io;

import java.io.File;
import java.io.FilenameFilter;

public class FilterExt implements FilenameFilter {
	private String ext;

	public FilterExt(String ext) {
		this.ext = ext;
	}

	public boolean accept(File dir, String filename) {
		return filename.endsWith(this.ext);
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("C:\\Users\\Wesley\\Pictures\\Geek\\");

		String[] list = f.list(new FilterExt(".jpg"));
		if(list == null)
			System.out.println("Nenhum arquivo encontrado.");
		else
			for(String s : list)
				System.out.println(s);
	}

}
