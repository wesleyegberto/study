package javaComoProgramar;

public class Exercicio21_07
{
	static <T, V> boolean isEqualTo(T t, V v)
	{
		return t.equals(v);
	}
	
	public static void main(String[] args)
	{
		boolean resp;
		
		resp = isEqualTo(new Object(), new Object());
		
		System.out.println(resp);
	}
}
