package negocio;

import java.util.ArrayList;

public class Pessoa {
	String nome;
	int idade;
	String sexo;
	ArrayList<String> telefone = new ArrayList<String>();

	public Pessoa() {
	}

	public void setTelefone(String telefone) {
		this.telefone.add(this.telefone.size(), telefone);
	}

	public String getSexo() {
		return sexo;
	}

	public String getNome() {
		return nome;
	}

	public int getIdade() {
		return idade;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public ArrayList<String> getTelefone() {
		return telefone;
	}

}
