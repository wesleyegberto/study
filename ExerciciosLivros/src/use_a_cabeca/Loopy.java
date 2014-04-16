public class Loopy
{
	public static void main(string[] args)
	{
		int x = 1;
		System.out.println("Antes do Loop");
		while (x < 4)
		{
			System.out.println("No loop");
			System.out.println("O valor de x é " + x);
			x = x + 1;
		}
		System.out.println("Esse é o fim do loop");
	}
}