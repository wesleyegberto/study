// Exerc�cio 7.36 - SimpletronMachineLanguage.java
// Computador Simpletron, e sua linguagem Simpletron Machine Language (SML)
import java.util.Scanner;

public class SimpletronMachineLanguage
{
	//palavra � um n�mero decimal com sinal. (Ex. +1055)
	// Onde os dois primeiro digitos s�o os comandos, e os dois �ltimo e a posi��o na mem�ria
	Scanner sc = new Scanner(System.in);
	int[] memory = new int[100]; // mem�ria
	int accumulator; // registrador de acumulador
	int instructionCounter; // monitora a posi��o da mem�ria que cont�m a instru��o sendo utilizada
	int operationCode; // indica a opera��o que est� sendo atualmente realizada
	int operand; // indica a posi��o da mem�ria em que a intru��o atual opera
	int instructionRegister; // armazena a instru��o a ser realizada
	int aux; // Ajuda no controle de entrada do usu�rio
	
	//Opera��o de Entreda/Sa�da
	final int READ = 10; // L� uma palavra do teclado para uma posi��o espec�fica da mem�ria
	final int WRITE = 11; // Escreve na tela uma palavra de uma posi��o espec�fica da mem�ria
	// Opera��es de Carregamento e Armazenamento
	final int LOAD = 20; // Carrega uma palavra de uma posi��o espec�fica na mem�ria para o acumulador
	final int STORE = 21; // Armazena uma palavra do acumulador para uma posi��o espec�fica da mem�ria
	// Opera��es Aritm�ticas, deixa o resultado no acumulador
	final int ADD = 30; // Adiciona uma palavra de uma posi��o espec�fica na mem�ria � palavra no acumulador
	final int SUBTRACT = 31; // Subtrai uma palavra de uma posi��o espec�fica na mem�ria da palavra no acumulador
	final int DIVIDE = 32; // Divide uma palavra de uma posi��o espec�fica na mem�ria da palavra no acumulador
	final int MULTIPLY = 33; // Multiplica uma palavra de uma posi��o espec�fica na mem�ria pela palavra no acumulador
	
	// Opera��es de Transfer�ncia de Controle
	final int BRANCH = 40; // Desvia para uma posi��o espec�fica na mem�ria
	final int BRANCHNEG = 41; // Desvia para uma posi��o espec�fica na mem�ria se o acumulador for negativo
	final int BRANCHZERO = 42; // Desvia para uma posi��o espec�fica na mem�ria se o acumulador for zero
	final int HALT = 43; // Pare. O programa completou sua tarefa.
	
	
	public void init()
	{
		System.out.println("*** Welcome to Simpletron! ***" + "\n" +
						   "*** Please enter your program one instruction ***" + "\n" +
						   "*** (or data word) at a time. I will display" +  "\n" +
						   "*** the location number and a question mark (?) ***" +  "\n" +
						   "*** You then type the word for that location. ***" + "\n" +
						   "*** Type -9999 to stop entering your program. ****\n\n\n");
	}
	
	public void start()
	{
		while(aux != -99999 && aux != HALT && instructionCounter < 100)
		{
			System.out.printf("\n%02d ? ", instructionCounter);
			aux = readCmd();
			if(aux == 0000) // Ser� 0000 caso a posi��o na mem�ria seja usada para uma vari�vel
			{
				instructionCounter++;
				continue;
			}
			else if(aux == -99999 && aux != HALT && instructionCounter < 100)
				break;
				
			memory[instructionCounter] = aux;
			System.out.printf("\t%+05d", memory[instructionCounter++]);
		}
		
		instructionCounter = 0; // Volta a posi��o inicial para poder executar
		
		if(aux == -99999)
			debbug();
		
		
	}
	
	
	public int readCmd()
	{
		int aux = 0;
		boolean valor = false;
		
		while(!valor)
		{
			aux = sc.nextInt();
			
			if(aux == -99999)
				valor = true;
			else if(aux < -9999 || aux > 9999)
				valor = false;
			else
				valor = true;
		}
		
		return aux;
	}
	
	public void debbug()
	{
		instructionCounter = 0;
		
		while(instructionCounter < 100 && operationCode != HALT)
		{
			instructionRegister = memory[instructionCounter++];
			if(instructionRegister == 0000) // Ser� 0000 caso a posi��o na mem�ria seja usada para uma vari�vel
				continue;
				
			operationCode = Math.abs(instructionRegister) / 100;
			operand = Math.abs(instructionRegister) % 100;
			//System.out.println(operationCode + " " + operand);
			if(operationCode == HALT)
				break;
			else
				operationCodeView(operationCode, operand);
			
		}
		
		dump();
	}
	
	public void operationCodeView(int operCode, int oper)
	{
		switch(operCode)
		{
			case READ:
				memory[oper] = readCmd();	break;
			case  WRITE:
				System.out.println(memory[oper]);	break;
			case LOAD:
				accumulator = memory[oper];	break;
			case STORE:
				memory[oper] = accumulator;	break;
			case ADD:
				accumulator += memory[oper];	break;
			case SUBTRACT:
				accumulator -= memory[oper];	break;
			case DIVIDE:
				if(memory[oper] == 0)
					System.out.println("\n*** Attempt to divide by zero. *** \n" + 
									   "*** Simpletron execution abnormally terminated. ***");
				else
					accumulator /= memory[oper];	break;
			case MULTIPLY:
				accumulator *= memory[oper];	break;
			case BRANCH:
				instructionCounter = oper;	break;
			case BRANCHNEG:
				if(accumulator < 0)
					instructionCounter = oper;
				break;
			case BRANCHZERO:
				if(accumulator == 0)
					instructionCounter = oper;
				break;
		}
	}
	
	
	public void dump() // Exibe o nome e o conte�do de cada resgistrador do Simpletron e sua mem�ria
	{
		int j = 0;
		
		System.out.printf("\n\nREGISTERS:" + 
						  "\naccumulator\t\t%+05d" +
						  "\ninstructionCounter\t%02d" +
						  "\ninstructionRegister\t%+05d" +
						  "\noperandCode\t\t%02d" +
						  "\noperand\t\t\t%02d", accumulator, instructionCounter, instructionRegister, operationCode, operand);
		
		System.out.println("\n\nMEMORY:");
		System.out.printf("\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\t%5s\n", 0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
		
		for(int i = 0; i < memory.length / 10; i++)
		{
			System.out.printf("%2s\t",i * 10);
			for(int x = 0; x < memory.length / 10; x++, j++)
			{
				System.out.printf("%+05d\t", memory[j]);
			}
			
			System.out.println();
		}
		
		System.out.println("\n\n");
	}
	
	
}