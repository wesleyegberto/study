package exceptions;

import java.util.*;
import java.io.*;

public class MinhaExcecaoParaOverloading {
	public void meuMetodo() throws Error, IllegalAccessError, InterruptedException // Exception
																					// que
																					// s�o
																					// checadas
	{

	}

	public void meuMetodo(int x) throws IOException, ClassNotFoundException // Na
																			// sobrecarga
																			// permite
																			// a
																			// declara��o
																			// de
																			// mais
																			// exce��es
	{

	}
}
