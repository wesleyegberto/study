package MeuTesteEmpresa;

public class TestaEmpresa {
	public static void main(String[] args) {
		String resp, dado;
		boolean teste;
		EntradaSaidaDados esd = new EntradaSaidaDados();

		Empresa emp1 = new Empresa("Microsoft", "158741200551");
		Funcionario f1;

		f1 = new Funcionario("Wesley", "198455105", "15848951121", "1");
		emp1.addFuncionario(f1);
		f1 = new Funcionario("Raul", "194201105", "15757551121", "2");
		emp1.addFuncionario(f1);
		f1 = new Funcionario("Fernando", "14277555105", "15875757521", "3");
		emp1.addFuncionario(f1);
		f1 = new Funcionario("Raphael", "198455105", "15857757521", "4");
		emp1.addFuncionario(f1);
		f1 = new Funcionario("Axel", "192454242", "15842442121", "5");
		emp1.addFuncionario(f1);

		f1.imprimeDados();

		emp1.imprimeEmpregados();

		// Teste para remover funcionario
		teste = true;
		while(teste) {
			resp = esd.entrada("Deseja remover funcionario? (s/n)\n");
			if(resp.toUpperCase().equals("S") || resp.toUpperCase().equals("N")) {
				if(resp.toUpperCase().equals("S")) {
					dado = esd.entrada("Entre com o nome, RG, CPF ou o codigo: ");

					if(emp1.remFuncionario(dado))
						System.out.println("\n\n\nEmpregado removido com sucesso!\n\n");
					else
						System.out.println("\n\n\nEmpregado nao esta contratado!\n\n");

					emp1.imprimeEmpregados();
				}
				teste = false;
			} else
				System.out.println("Digite s para sim ou n para nao.\n\n");
		}

		// Teste para procurar funcionario
		teste = true;
		while(teste) {
			resp = esd.entrada("Deseja procurar funcionario? (s/n)\n");

			if(resp.toUpperCase().equals("S") || resp.toUpperCase().equals("N")) {
				if(resp.toUpperCase().equals("S")) {
					dado = esd.entrada("Entre com o nome, RG, CPF ou o codigo: ");

					f1 = emp1.getFuncionario(dado);
					if(f1 != null)
						f1.imprimeDados();
					else
						System.out.println("Nenhum funcionario encontrado!");
				}
				teste = false;
			} else
				System.out.println("Digite s para sim ou n para nao.\n\n");
		}

	}
}
