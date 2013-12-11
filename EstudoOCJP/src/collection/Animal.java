package collection;

public class Animal implements Comparable {
	String especie;

	public Animal() {

	}

	public Animal(String e) {
		this.especie = e;
	}

	public void eat() {
		System.out.println(especie + " is eating.");
	}

	@Override
	public String toString() {
		return especie;
	}

	@Override
	public int compareTo(Object o) {
		if(!(o instanceof Animal))
			return -1;
		else
			return this.especie.compareTo(((Animal) o).especie);
	}

}
