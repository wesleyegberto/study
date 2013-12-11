package collection;

import java.util.Arrays;
import java.util.List;

public class AnimalListTest {
	public static void castra1(List<? extends Animal> animals) {
		for(Animal a : animals)
			a.eat();
	}

	public static void castra2(Animal[] animals) {
		for(Animal a : animals)
			a.eat();
	}

	public static void main(String... args) {
		Animal[] ans = new Animal[3];

		ans[0] = new Animal("Lebre");
		ans[1] = new Animal("Rato");
		ans[2] = new Animal("Ganço");

		castra1(Arrays.asList(ans));
	}
}
