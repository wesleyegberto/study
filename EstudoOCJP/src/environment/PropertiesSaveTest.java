package environment;

import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;

public class PropertiesSaveTest {
	public static void main(String[] args) {
		Properties myProp = new Properties();

		myProp.setProperty("resolution", "1280x960");
		myProp.setProperty("app.path", "C:\\BSE");
		myProp.setProperty("app.key", "ASE481DC11AA4799Q0");

		try {
			/* Efetua um dump das propriedades e os valores em um stream. */
			// myProp.list(System.out);
			/**
			 * Salva em um arquivo de texto com o formato: # [comment] # [full
			 * datetime saved] [property]=[value] (...)
			 */
			FileWriter fw = new FileWriter("prop.conf");
			myProp.store(fw, "Salvei 3");
			fw.close();

			/**
			 * Salva em um arquivo xml com o formato: <?xml version="1.0"
			 * encoding="UTF-8" standalone="no"?> <!DOCTYPE properties SYSTEM
			 * "http://java.sun.com/dtd/properties.dtd"> <properties>
			 * <comment>[comment]</comment> <entry
			 * key="[property]">[key]</entry> </properties>
			 */
			FileOutputStream fos = new FileOutputStream("prop.xml");
			myProp.storeToXML(fos, "Salvei 3 em XML");
			fos.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

		System.out.println("Properties:");
		for(Entry<Object, Object> property : myProp.entrySet()) {
			System.out.printf("[%s] = %s%n", property.getKey(), property.getValue());
		}
	}
}
