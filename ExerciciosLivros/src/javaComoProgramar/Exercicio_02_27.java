public class Exercicio_02_27 {
	public static void main(String[] args) {
		
		for(int i = 1; i <= 8; i++) {
			for(int j = 1; j <= 8; j++) {
				if(i % 2 == 0) {
					if(j % 2 == 0) {
						System.out.print("*");
					}
					else {
						System.out.print(" ");
					}
				}
				else {
					if(j % 2 == 0) {
						System.out.print(" ");
					}
					else {
						System.out.print("*");
					}
				}
			}
			System.out.println();
		}
	}
}