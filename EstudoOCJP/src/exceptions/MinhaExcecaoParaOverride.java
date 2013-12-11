package exceptions;

import java.util.*;
import java.io.*;

public class MinhaExcecaoParaOverride {
	public void meuMetodo() throws Error, IllegalAccessError, InterruptedException // Exception
																					// que
																					// s�o
																					// checadas
	{

	}

	public void meuMetodo2() throws IOException, SecurityException {

	}
}

class MinhaExcecaoParaOverriding extends MinhaExcecaoParaOverride {
	@Override
	public void meuMetodo()
	// throws IOException, Exception, ClassNotFoundException //Inv�lido pois as
	// exce��es declaradas aqui
	// s�o mais do que na superclasse, pois estes s�o superclasse de outras
	// exception sendo assim lan�am mais.
			throws LinkageError // Permitido porque essa exce��o � uma
								// especializa��o de Error, ent�o os dois
								// m�todos podem lan�ar
	{

	}

	@Override
	public void meuMetodo2() throws FileNotFoundException, // � permitido pois �
															// subclasse de
															// IOException
			NullPointerException // � permitido pois � uma exce��o n�o checada
									// (� uma subclasse de RuntimeException)
									// e o Java permite na sobre escrita
									// declarar exce��es unchecked a mais.
	{

	}
}
