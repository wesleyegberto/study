package environment;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Map.Entry;

public class PropertiesReadTest {
	public static void main(String[] args) {
		Properties myProp = new Properties();
		try {
			FileReader fr = new FileReader("prop.conf");
			myProp.load(fr);
			fr.close();

			FileInputStream fis = new FileInputStream("prop.xml");
			myProp.loadFromXML(fis);
			fis.close();

		} catch(IOException e) {
			e.printStackTrace();
		}

		System.out.println("Properties loaded:");
		for(Entry<Object, Object> property : myProp.entrySet()) {
			System.out.printf("[%s] = %s%n", property.getKey(), property.getValue());
		}
	}
}
