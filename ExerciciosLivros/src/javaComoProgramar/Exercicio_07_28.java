// Exercício 7.28
// Corrida entre a tartaruga e a lebra

public class Exercicio_07_28
{
	double rnd;
	int posL = 1, posT = 1;
	
	void inicio()
	{
		for(int i = 0; posL <= 70 && posT <= 70; i++)
		{
			rnd = Math.random() * 10;
			
			moveT(rnd);
			if(posT < 1)
				posT = 1;
			
			rnd = Math.random() * 10;
			
			moveL(rnd);
			if(posL < 1)
				posL = 1;
				
			if(posL == posT)
				posL--;
			
			System.out.println("Tartaruga: " + posT + "\nLebre: " + posL);
			
			if(posL > 70)
				System.out.println("Finish!\nLabre venceu!\n");
			if(posT > 70)
				System.out.println("Finish!\nTartaruga venceu!\n");
		}
	}
	
	void moveT(double p)
	{
		if(p >= 0 && p < 5)
			posT += 3;
		else if(p >= 6 && p < 7)
			posT -= 3;
		else if(p >= 7 && p < 10)
			posT += 1;
	}
	
	void moveL(double p)
	{
		if(p >= 2 && p < 4)
			posL += 9;
		else if(p >= 4 && p < 5)
			posL -= 12;
		else if(p >= 5 && p < 8)
			posL++;
		else if(p >= 8 && p < 10)
			posL -= 2;
	}
	
	public static void main(String[] args)
	{
		Exercicio_07_28 c = new Exercicio_07_28();
		
		System.out.println("Ra-ta-ta-ta!!!\nA corrida comecou!!!");
		c.inicio();
	}
}