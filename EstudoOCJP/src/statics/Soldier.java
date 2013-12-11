package statics;

// Utiliza um contador estático para compartilhar entre todos os objetos
public class Soldier {
	static int numberOfSoldiers;
	String name;
	String type;
	int hP;
	int pP;

	public Soldier(String name, String type, int pP, int hP) {
		this.name = name;
		this.type = type;
		this.hP = hP;
		this.pP = pP;
		numberOfSoldiers++; // incrementa a variável para indicar que mais um
							// soldado está pronto (um novo objeto criado)
	}

	public String getSoldierDatas() {
		return "\nNome: " + name + "\nType: " + type + "\nHP: " + hP + "\nPP: " + pP + "\n";
	}
}
