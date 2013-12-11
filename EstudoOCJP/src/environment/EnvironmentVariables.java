package environment;

import java.util.Map;
import java.util.Map.Entry;

public class EnvironmentVariables {
	public static void main(String[] args) {
		Map<String, String> env = System.getenv();

		System.out.println("System's properties (read only):");
		for(Entry<String, String> property : env.entrySet()) {
			System.out.printf("[%s] = %s%n", property.getKey(), property.getValue());
		}

		System.out.println("\n\nEnvironment's properties:");
		for(Entry<Object, Object> property : System.getProperties().entrySet()) {
			System.out.printf("[%s] = %s%n", property.getKey(), property.getValue());
		}

	}
}
