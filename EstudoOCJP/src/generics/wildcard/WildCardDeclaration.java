package generics.wildcard;

import java.util.ArrayList;
import java.util.List;

public class WildCardDeclaration {
	public static void main(String[] args) {
		List<?> wildList1 = new ArrayList<Object>(); // Permite receber qualquer
														// tipo de List
		List wildList2 = new ArrayList<Object>(); // É utilizado rawtype -
													// Object

		wildList1 = new ArrayList<Integer>(); // O wildcard permite atribuir
												// outro tipo de List
	}
}
