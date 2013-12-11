package hash;

import java.util.Iterator;

public class Jogo {
	String titulo;
	String autor;

	public Jogo(String t, String a) {
		this.titulo = t;
		this.autor = a;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((autor == null) ? 0 : autor.hashCode());
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if(this == obj)
			return true;
		if(obj == null)
			return false;
		if(!(obj instanceof Jogo))
			return false;
		Jogo other = (Jogo) obj;
		if(autor == null) {
			if(other.autor != null)
				return false;
		} else if(!autor.equals(other.autor))
			return false;
		if(titulo == null) {
			if(other.titulo != null)
				return false;
		} else if(!titulo.equals(other.titulo))
			return false;
		return true;
	}

	@SuppressWarnings("rawtypes")
	public static void main(String... args) {
		Jogo g1 = new Jogo("1", "1");
		Jogo g2 = new Jogo("2", "2");
		Jogo g3 = new Jogo("2", "3");
		Jogo g4 = new Jogo("3", "2");

		java.util.HashSet<Jogo> lst = new java.util.HashSet<Jogo>();

		lst.add(g1);
		lst.add(g2);
		lst.add(g3);
		lst.add(g4);

		Iterator t = lst.iterator();
		while(t.hasNext())
			System.out.println(t.next().hashCode());

	}
}
