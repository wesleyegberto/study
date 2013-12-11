/**
 * Copyright (c) 2010-2013, BSE Technology. All rights reserved. BSE Technology.
 * Use is subject to license terms.
 * 
 * @author Wesley Egberto de Brito May 25, 2013
 * 
 *         Objetivo: Testar a definiçã ode classe dentro de um enum.
 */
package enums;

/**
 * EnumInnerClass
 */
public enum EnumInnerClass {
	A(1), B(2), C(3);

	public int n;

	EnumInnerClass(int n) {
		this.n = n;
	}

	// É possível criar classes abstratas ou não, interfaces e o método
	// main(String[]) dentro de um Enum.
	public class Teste {
	}

	public interface ITeste {
	}

	public abstract class ATeste {
	}

	public enum ETeste {
		T();
	}
}
