/**
 * @author Wesley Egberto de Brito
 * 
 *         Operadores para o SML
 */

package version1;

public final class Instructions {
	private Instructions() {
	}

	// Opera��o de Entreda/Sa�da
	public static final int READ = 10; // L� uma palavra do teclado para uma
										// posi��o espec�fica da mem�ria
	public static final int WRITE = 11; // Escreve na tela uma palavra de uma
										// posi��o espec�fica da mem�ria
	public static final int READSTR = 12; // Come�a a ler uma string
	public static final int WRITESTR = 13; // Termina a leitura de uma string

	// Opera��es de Carregamento e Armazenamento
	public static final int LOAD = 20; // Carrega uma palavra de uma posi��o
										// espec�fica na mem�ria para o
										// acumulador
	public static final int STORE = 21; // Armazena uma palavra do acumulador
										// para uma posi��o espec�fica da
										// mem�ria
	public static final int LOADSTR = 22; // Inicia a leitura de um string a
											// partir da posi��o que indica o
											// tamanho dela

	// Opera��es Aritm�ticas, deixa o resultado no acumulador
	public static final int ADD = 30; // Adiciona uma palavra de uma posi��o
										// espec�fica na mem�ria � palavra no
										// acumulador
	public static final int SUBTRACT = 31; // Subtrai uma palavra de uma posi��o
											// espec�fica na mem�ria da palavra
											// no acumulador
	public static final int DIVIDE = 32; // Divide uma palavra de uma posi��o
											// espec�fica na mem�ria da palavra
											// no acumulador
	public static final int MULTIPLY = 33; // Multiplica uma palavra de uma
											// posi��o espec�fica na mem�ria
											// pela palavra no acumulador
	public static final int POW = 34; // Eleva a palavra do acumulador por outra
										// palavra em uma posi��o espec�fica na
										// mem�ria
	public static final int MOD = 35; // Retorna o resto da divis�o do
										// acumulador por outra palavra em uma
										// posi��o espec�fica na mem�ria

	// Opera��es de Transfer�ncia de Controle
	public static final int BRANCH = 90; // Desvia para uma posi��o espec�fica
											// na mem�ria
	public static final int BRANCHNEG = 91; // Desvia para uma posi��o
											// espec�fica na mem�ria se a
											// palavra do acumulador for
											// negativo
	public static final int BRANCHZERO = 92; // Desvia para uma posi��o
												// espec�fica na mem�ria se a
												// palavra do acumulador for
												// zero
	public static final int BRANCHEQ = 93; // Desvia para uma posi��o espec�fica
											// na mem�ria se a palavra do
											// acumulador for igual a palavra de
											// uma posi��o espec�fica da mem�ria
	public static final int BRANCHNEQ = 94; // Desvia para uma posi��o
											// espec�fica na mem�ria se a
											// palavra do acumulador for
											// diferente da palavra de uma
											// posi��o espec�fica da mem�ria
	public static final int BRANCHGT = 95; // Desvia para uma posi��o espec�fica
											// na mem�ria se a palavra do
											// acumulador for maior que a
											// palavra de uma posi��o espec�fica
											// da mem�ria
	public static final int BRANCHLT = 96; // Desvia para uma posi��o espec�fica
											// na mem�ria se a palavra do
											// acumulador for menor que a
											// palavra de uma posi��o espec�fica
											// da mem�ria
	public static final int BRANCHGTE = 97; // Desvia para uma posi��o
											// espec�fica na mem�ria se a
											// palavra do acumulador for maior
											// ou igual que a palavra de uma
											// posi��o espec�fica da mem�ria
	public static final int BRANCHLTE = 98; // Desvia para uma posi��o
											// espec�fica na mem�ria se a
											// palavra do acumulador for menor
											// ou igual que a palavra de uma
											// posi��o espec�fica da mem�ria

	public static final int HALT = 99; // Pare. O programa completou sua tarefa.
	public static final int DEBBUG = 00; // Para e debbug as instru��es.

}
