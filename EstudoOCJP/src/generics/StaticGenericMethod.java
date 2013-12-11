package generics;

public class StaticGenericMethod {
	// Generic static method
	public static <K, V> boolean compare(Pair<K, V> p1, Pair<K, V> p2) {
		return p1.getKey().equals(p2.getKey()) && p1.getValue().equals(p2.getValue());
	}

	public static void main(String[] args) {
		Pair<Integer, String> p1 = new Pair<Integer, String>(1, "apple");
		Pair<Integer, String> p2 = new Pair<Integer, String>(2, "pear");
		boolean same = StaticGenericMethod.<Integer, String> compare(p1, p2);
		System.out.println(same);
	}
}

class Pair<K, V> {
	private K key;
	private V value;

	// Generic constructor
	public Pair(K key, V value) {
		this.key = key;
		this.value = value;
	}

	// Generic methods
	public void setKey(K key) {
		this.key = key;
	}

	public void setValue(V value) {
		this.value = value;
	}

	public K getKey() {
		return key;
	}

	public V getValue() {
		return value;
	}
}
