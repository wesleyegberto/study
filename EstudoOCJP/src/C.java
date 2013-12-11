public class C {
	public static void main(String[] args) {
		int k = 2;
		outer:
		while(true) {
			++k;
			inner:
			for(int j = 6; j > 2; j--) {
				k = 8 - j;
				if(j == 3)
					continue inner;
				break outer;
			}
			continue outer;
		}
		System.out.println(k);
	}
}
