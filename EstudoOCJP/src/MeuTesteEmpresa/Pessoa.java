package MeuTesteEmpresa;

public class Pessoa {
	private String nome;
	private String rg;
	private String cpf;

	// Construtor para inicializar os dados
	public Pessoa(String nome, String rg, String cpf) {
		setNome(nome);
		setRg(rg);
		setCpf(cpf);
	}

	// Getters e Setters
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getRg() {
		return rg;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getCpf() {
		return cpf;
	}
}
