package criando;

import negocio.Pessoa;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/*
 * import com.thoughtworks.xstream.io.xml.StaxDriver; Apresenta tudo em uma
 * única Linha pacman.blog.br blog dicas
 * xstream-simplicidade-ao-lidar-com-xml-em-java //DomDriver("ISO-8859-1")
 */
public class gravar {

	public gravar() {
	}

	public boolean ini() {
		boolean retorno = true;
		if(!gerarXml001().equals("")) {
			retorno = false;
		}
		return retorno;
	}

	private static void salvarArquivo(String documento, String file) {
		File path = new File("C:\\" + file);
		try {
			PrintWriter writer = new PrintWriter(path);
			writer.println("<?xml version=\"1.0\" encoding=\"ISO-8859-1\" standalone=\"no\"?>");
			writer.println(documento);
			writer.flush();
			writer.close();
		} catch(FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static String gerarXml001() {
		String retorno = "";
		try {
			Pessoa p = new Pessoa();
			p.setNome("William");
			p.setIdade(19);
			p.setSexo("M");
			for(int x = 0; x <= 9; x++) {
				p.setTelefone("(11)4191-189" + x);
			}
			// Criamos o objeto que fará o trabalho de gerar o xml
			XStream xStream = new XStream(new DomDriver());
			// O método toXML() transforma nosso objeto
			// contato em um padrão de saída no formato XML.
			String documento = xStream.toXML(p);
			// A chamada ao método salvaArquivo passando por parâmetro
			// a String com o XML gerado e o nome do arquivo
			salvarArquivo(documento, "gerarXml001.xml");
		} catch(Exception e) {
			retorno = e.toString();
		}
		return retorno;
	}
}
