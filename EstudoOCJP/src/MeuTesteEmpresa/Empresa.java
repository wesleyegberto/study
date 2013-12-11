package MeuTesteEmpresa;

import java.util.ArrayList;

public class Empresa {
	private String nome;
	private String cnpj;
	private ArrayList<Funcionario> empregados = new ArrayList<Funcionario>();

	// Construtor para inicializar os dados
	public Empresa(String nome, String cnpj) {
		setNome(nome);
		setCnpj(cnpj);
	}

	// Getters e Setters
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	// Métodos
	public boolean addFuncionario(Funcionario f) {
		empregados.add(f);
		return true;
	}

	public boolean remFuncionario(String dado) {
		for(Funcionario emp : empregados) {
			if(emp.getNome().equals(dado) || emp.getRg().equals(dado) || emp.getCpf().equals(dado)
					|| emp.getCodigo().equals(dado)) {
				empregados.remove(emp);
				return true;
			}
		}
		return false;
	}

	public Funcionario getFuncionario(String dado) {
		for(Funcionario emp : empregados) {
			if(emp.getNome().equals(dado) || emp.getRg().equals(dado) || emp.getCpf().equals(dado)
					|| emp.getCodigo().equals(dado)) {
				return emp;
			}
		}
		return null;
	}

	public void imprimeEmpregados() {
		if(empregados.isEmpty() == false) {
			System.out.println("Funcionarios empregados na " + getNome() + ":");
			for(Funcionario emp : empregados) {
				System.out.println(emp.getCodigo() + " - " + emp.getNome());
			}
			System.out.println("\n\n");
		} else
			System.out.println("Nenhum empregado");
	}
}
