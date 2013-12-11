package MeuTesteEmpresa;

public class Funcionario extends Pessoa {
	private String codigo;

	// Construtor para inicializar os dados
	public Funcionario(String nome, String rg, String cpf, String codigo) {
		super(nome, rg, cpf);
		setCodigo(codigo);
	}

	// Getters e Setters
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	// Métodos
	public void imprimeDados() {
		System.out
				.printf("\n\n\n\n--------Dados do Funcionario--------\n"
						+ "Nome: %s\nRG: %s\nCPF: %s\nNumero da Carteira de Trabalho: %s\n------------------------------------\n\n\n\n",
						getNome(), getRg(), getCpf(), getCodigo());
	}
}
