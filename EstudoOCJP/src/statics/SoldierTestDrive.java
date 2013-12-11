package statics;

public class SoldierTestDrive {
	public static void main(String[] args) {
		Soldier s1 = new Soldier("Like", "Espiao", 40, 19);

		System.out.println(s1.getSoldierDatas());
		System.out.println("Total of soldiers: " + s1.numberOfSoldiers); // o
																			// membro
																			// est�tico
																			// pode
																			// ser
																			// acessado
																			// atrav�s
																			// do
																			// objeto
																			// (s1)

		Soldier s2 = new Soldier("Gill", "Camper", 40, 39);

		System.out.println(s2.getSoldierDatas());
		System.out.println("Total of soldiers: " + Soldier.numberOfSoldiers); // ou
																				// atrav�s
																				// da
																				// classe
																				// (Soldier)

		Soldier s3 = new Soldier("Mike", "Major", 40, 19);

		System.out.println(s3.getSoldierDatas());
		System.out.println("Total of soldiers: " + s3.numberOfSoldiers); // o
																			// membro
																			// est�tico
																			// pode
																			// ser
																			// acessado
																			// atrav�s
																			// do
																			// objeto

	}
}
