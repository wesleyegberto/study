import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
	public static void main(String[] args) throws Exception {
		File file = new File("D:\\projetos\\workspace\\ExtrairXml\\src\\Funcionarios.xml");
		FileInputStream fis = new FileInputStream(file);
		Scanner br = new Scanner(fis);

		StringBuilder sb = new StringBuilder();
		ArrayList<String> funcXml = new ArrayList<String>();
		ArrayList<Funcionario> funcionarios = new ArrayList<Funcionario>();

		while(br.hasNext()) {
			sb.append(br.next());
			// System.out.print(sb);
		}
		System.out.println(sb + "\n");

		Pattern p1 = Pattern.compile("<Funcionario>(.*?)</Funcionario>");
		Matcher m = p1.matcher(sb);

		while(m.find()) {
			funcXml.add(m.group());
			System.out.println(m.group());
		}

		Pattern p2 = Pattern.compile("<Nome>(.*?)</Nome>");
		Pattern p3 = Pattern.compile("<Salario>(.*?)</Salario>");

		Matcher mNome;
		Matcher mSal;
		System.out.println();
		for(int i = 0; i < funcXml.size(); i++) {
			String func = funcXml.get(i);
			mNome = p2.matcher(func);
			mSal = p3.matcher(func);

			while(mNome.find() && mSal.find()) {
				funcionarios.add(new Funcionario(mNome.group().replace("<Nome>", "").replace("</Nome>", ""), Double
						.parseDouble(mSal.group().replace("<Salario>", "").replace("</Salario>", ""))));
			}
		}

		for(Funcionario f : funcionarios) {
			System.out.println(f);
		}
	}
}

class Funcionario {
	private String nome;
	private double salario;

	public Funcionario() {
	}

	public Funcionario(String nome, double salario) {
		setNome(nome);
		setSalario(salario);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public double getSalario() {
		return salario;
	}

	@Override
	public String toString() {
		return "Nome: " + this.getNome() + "\nSalario: " + getSalario() + "\n";
	}
}
