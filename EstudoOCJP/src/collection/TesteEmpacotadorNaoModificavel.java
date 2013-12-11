package collection;

import java.util.*;

public class TesteEmpacotadorNaoModificavel {
	public static void main(String[] args) {
		List<Animal> lst = new ArrayList<Animal>();

		lst.add(new Cat());
		lst.add(new Dog());
		lst.add(new Animal("Rato"));
		lst.add(new Animal("Cavalo"));

		List<Animal> vFinal = Collections.unmodifiableList(lst);

		vFinal.add(new Animal("Macaco"));
	}
}
