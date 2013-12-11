package exceptions;

import java.util.*;
import java.io.*;

public class MinhaExcecaoParaOverride {
	public void meuMetodo() throws Error, IllegalAccessError, InterruptedException // Exception
																					// que
																					// são
																					// checadas
	{

	}

	public void meuMetodo2() throws IOException, SecurityException {

	}
}

class MinhaExcecaoParaOverriding extends MinhaExcecaoParaOverride {
	@Override
	public void meuMetodo()
	// throws IOException, Exception, ClassNotFoundException //Inválido pois as
	// exceções declaradas aqui
	// são mais do que na superclasse, pois estes são superclasse de outras
	// exception sendo assim lançam mais.
			throws LinkageError // Permitido porque essa exceção é uma
								// especialização de Error, então os dois
								// métodos podem lançar
	{

	}

	@Override
	public void meuMetodo2() throws FileNotFoundException, // é permitido pois é
															// subclasse de
															// IOException
			NullPointerException // é permitido pois é uma exceção não checada
									// (é uma subclasse de RuntimeException)
									// e o Java permite na sobre escrita
									// declarar exceções unchecked a mais.
	{

	}
}
