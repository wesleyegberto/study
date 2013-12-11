package assertivas;

class A {
	void m1(int i) {
		int j = i % 3;
		switch(j) {
			case 0:
				System.out.print("0");
				break;
			case 1:
				System.out.print("1");
				break;
			default:
				assert j == 2;
				System.out.print(j);
		}
	}

	public static void main(String[] args) {
		A a = new A();
		for(int i = 5; i >= -1; i--) {
			a.m1(i);
		}
	}
}
