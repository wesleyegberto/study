// Exercicio 5.23

public class Exercicio_05_23 {
	public static void main(String[] args) {
		int x = 4, y = 3, a = 4, b = 9, g = 6;
		//Leis de Morgan
		/*
			Equivalencia
				!(<cond1> && <cond2>) == (!<cond1> || !<cond2>)
				!(<cond1> || <cond2>) == (!<cond1> && !<cond2>)
		*/
		
		/*	Faça o Teste
			a)	!(x < 5) && !(y >= 7)
			b)	!( a == b) || !(g != 5)
			c)	!((x <= 8) && (y > 4))
			d)	!((i > 4) || (j <= 6))
		*/
		System.out.printf("x = %d, y = %d, a = %d, b = %d, g = %d\n\n\n", x, y, a, b, g);
		System.out.println("a)\t!(x < 5) && !(y >= 7) <--> !((x < 5) || (y >= 7))");
		System.out.println("\t" + (!(x < 5) && !(y >= 7)) + "<--> " + (!((x < 5) || (y >= 7))));
		System.out.println("a)\t!((x < 5) && (y >= 7)) <--> (!(x < 5) || !(y >= 7))");
		System.out.println("\t" + !((x < 5) && (y >= 7)) + " <--> " + (!(x < 5) || !(y >= 7)));
		
		System.out.println("\n\nb)\t(!(a == b) && !(g != 5)) <--> !((a == b) || (g != 5)))");
		System.out.println("\t" + (!(a == b) && !(g != 5)) + " <--> " + ((a == b) || (g != 5)));
	}
}