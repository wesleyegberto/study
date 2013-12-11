package generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class VarargsErros {
	public static <T> void addToList(List<T> listArg, T... elements) {
		for(T x : elements) {
			listArg.add(x);
		}
	}

	public static void faultyMethod(List<String>... l) {
		Object[] objectArray = l; // Valid
		objectArray[0] = Arrays.asList(20);
		@SuppressWarnings("unused")
		String s = l[0].get(0); // ClassCastException thrown here
	}

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		List<String> stringListA = new ArrayList<String>();
		List<String> stringListB = new ArrayList<String>();

		VarargsErros.addToList(stringListA, "Seven", "Eight", "Nine");
		VarargsErros.addToList(stringListA, "Ten", "Eleven", "Twelve");
		List<List<String>> listOfStringLists = new ArrayList<List<String>>();
		VarargsErros.addToList(listOfStringLists, stringListA, stringListB);
		VarargsErros.faultyMethod(Arrays.asList("Hello!"), Arrays.asList("World!"));
	}

}
