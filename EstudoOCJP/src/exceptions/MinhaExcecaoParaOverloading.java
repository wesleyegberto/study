package exceptions;

import java.util.*;
import java.io.*;

public class MinhaExcecaoParaOverloading {
	public void meuMetodo() throws Error, IllegalAccessError, InterruptedException // Exception
																					// que
																					// são
																					// checadas
	{

	}

	public void meuMetodo(int x) throws IOException, ClassNotFoundException // Na
																			// sobrecarga
																			// permite
																			// a
																			// declaração
																			// de
																			// mais
																			// exceções
	{

	}
}
