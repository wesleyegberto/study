package pegando;

import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JOptionPane;
import negocio.Pessoa;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class ler {
	public ler() {

	}

	public void ini() {
		lerXml();
	}

	private static void lerXml() {
		FileReader reader = null;
		try {
			// carrega o arquivo XML para um objeto reader
			reader = new FileReader("C:\\gerarXml001.xml");
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
		// Cria o objeto xstream
		XStream xStream = new XStream(new DomDriver());
		// informamos as tags que serão lidas
		// como foi feito no método gerarXml002
		xStream.alias("Pessoa", Pessoa.class);
		// xStream.aliasField("Endereco", Contato.class, "endereco");
		// xStream.aliasField("Telefones", Contato.class, "telefones");
		// xStream.alias("Telefone", Telefone.class);
		Pessoa pessoa = (Pessoa) xStream.fromXML(reader);
		// Exibimos no console o resultado
		// String telefones = "";
		// for(String telefone : pessoa.getTelefone()){
		// telefones += telefone + "\n";
		// }

		JOptionPane.showMessageDialog(null, xStream.toXML(pessoa));
	}
}
