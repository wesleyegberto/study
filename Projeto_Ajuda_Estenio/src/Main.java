import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

public class Main {
	public static void main(String[] args) throws Exception {
		new Main().go();
	}
	
	public void go()  throws Exception {
		/*
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		pessoas.add(new Pessoa("Odair", "odair@gmail.com"));
		pessoas.add(new Pessoa("Jose", "jose@gmail.com"));
		pessoas.add(new Pessoa("Odair 2", "odair@gmail.com"));
		pessoas.add(new Pessoa("Jose 2", "jose@gmail.com"));
		pessoas.add(new Pessoa("Odair 3", "odair@gmail.com"));
		pessoas.add(new Pessoa("Jose 3", "jose@gmail.com"));
		pessoas.add(new Pessoa("Odair 4", "odair@gmail.com"));
		pessoas.add(new Pessoa("Jose 4", "jose@gmail.com"));
		pessoas.add(new Pessoa("Odair 5", "odair@gmail.com"));
		pessoas.add(new Pessoa("Jose 5", "jose@gmail.com"));
		pessoas.add(new Pessoa("Odair 6", "odair@gmail.com"));
		pessoas.add(new Pessoa("Jose 6", "jose@gmail.com"));
		pessoas.add(new Pessoa("Beto Meira", "beto.meira@gmail.com"));
		pessoas.add(new Pessoa("Beto Meira 2", "beto.meira@gmail.com"));
		salvaPessoas(pessoas);
		*/

		salvaPessoa(new Pessoa("Beto Meira 13", "beto.meira@gmail.com"));
		
		// Carrega
		List<Pessoa> carregaPessoas = carregaPessoas();
		int indice = 0;
		for(Pessoa pessoa : carregaPessoas) {
			indice++;
			System.out.print(indice + ": ");
			System.out.println(pessoa);
		}
		// Pegou o indice do txt e subtraiu 1 para equalizar o indice do List
		// indice = Integer.parseInt(txtIndice.getText()) - 1;
		// setName e setEmail
		// atualiza todo mundo
		// salvaPessoas(carregaPessoas);
		
		geraGrupos();
	}
	
	public void geraGrupos() throws Exception {
		// Carrega as pessoas no List que é indexado de 0 até o size()
		final List<Pessoa> pessoasDisponiveis = carregaPessoas();
		
		int qtdePessoasDisponiveis = pessoasDisponiveis.size();
		
		// Calcula a qtde de grupos
		int qtdeGrupos = (int) (qtdePessoasDisponiveis / 4);
		if(qtdePessoasDisponiveis % 4 != 0) {
			qtdeGrupos = qtdeGrupos + 1;
		}
		
		// Lista de grupos (onde cada grupo é uma Lista de Pessoas)
		List<List<Pessoa>> grupos = new ArrayList<List<Pessoa>>(qtdeGrupos);
		
		List<Pessoa> grupo = null;
		int posicaoSortiada = 0;
		// Para cada grupo
		for(int i = 0; i < qtdeGrupos; i++) {
			if(qtdePessoasDisponiveis > 4) {
				grupo = new ArrayList<Pessoa>(4);
				for(int j = 0; j < 4; j++) {
					posicaoSortiada = ((int) (Math.random() * qtdePessoasDisponiveis)); // Gera entre 0 e total (se o total é 40 vai até 39)
					grupo.add(pessoasDisponiveis.get(posicaoSortiada));
					pessoasDisponiveis.remove(posicaoSortiada);
					qtdePessoasDisponiveis--;
				}
				grupos.add(grupo);
			} else if(qtdePessoasDisponiveis == 1) { // Se restar apenas 1 pessoa
				grupos.get(qtdeGrupos - 2).add(pessoasDisponiveis.get(0));
			} else { // Se restar 2 ou 3 pessoas
				grupos.add(pessoasDisponiveis);
			}
		}
		
		salvaGruposGerados(grupos);
	}

	public void salvaPessoa(Pessoa pessoa) throws Exception {
		File f = null;
		FileWriter fw = null;
		Formatter fmt = null;
		
		try {
			f = new File("teste.csv");
			fw = new FileWriter(f, true);
			fmt = new Formatter(fw);
			fmt.format("%s;%s;\n", pessoa.getNome(), pessoa.getEmail());
		} catch(IOException ex) {
			new Exception("Erro ao salvar pessoa no arquivo.");
		} finally {
			fmt.close();
		}
	}
	
	
	public void salvaPessoas(final List<Pessoa> pessoas) throws Exception {
		File f = null;
		FileWriter fw = null;
		Formatter fmt = null;
		
		try {
			f = new File("teste.csv");
			fw = new FileWriter(f, true);
			fmt = new Formatter(fw);
			
			for(Pessoa pessoa : pessoas) {
				fmt.format("%s;%s;\n", pessoa.getNome(), pessoa.getEmail());
			}
		} catch(IOException ex) {
			new Exception("Erro ao salvar pessoas no arquivo.");
		} finally {
			fmt.close();
		}
	}
	
	public void salvaGruposGerados(final List<List<Pessoa>> grupos) {
		File f = null;
		FileWriter fw = null;
		Formatter fmt = null;
		try {
			f = new File("grupos.csv");
			fw = new FileWriter(f);
			fmt = new Formatter(fw);
			
			int numGrupo = 0;
			fmt.format("%s;%s;%s;\n", "N# grupo", "Nome do integrante", "E-mail do integrante");
			for(List<Pessoa> grupo : grupos) {
				numGrupo++;
				for(Pessoa pessoa : grupo) {
					fmt.format("%d;%s;%s;\n", numGrupo, pessoa.getNome(), pessoa.getEmail());
				}
				fmt.format("\n");
			}
		} catch(Exception ex) {
			
		} finally {
			fmt.close();
		}
	}
	
	public List<Pessoa> carregaPessoas() throws Exception {
		List<Pessoa> pessoas = new ArrayList<Pessoa>();
		BufferedReader br = null;
		try {
			File f = new File("teste.csv");
			
			br = new BufferedReader(new FileReader(f));
			
			String line = null;
			while((line = br.readLine()) != null) {
				if(line.contains(";")) {
					String data[] = line.split(";");
					pessoas.add(new Pessoa(data[0], data[1]));
				}
			}
		} catch(FileNotFoundException ex) {
			throw new Exception("Arquivo não existe.");
		} catch(IOException ex) {
			throw new Exception("Erro ao ler arquivo.");
		} finally {	
			if(br != null) {
				try {
					br.close();
				} catch(IOException e) {
				}
			}
		}
		
		return pessoas;
	}
}
