class TesteEntreTipos {
	public static void main(String[] args) {
		byte by = 3;
		short sh = 3;
		int in = 3;
		long lo = 3;
		float fl = 3f;
		double dou = 3d;
		
		dou = fl;
		fl = lo;
		lo = in;
		in = sh;
		sh = by;
		
		
		//Invalid attribuition
		by = sh;
		sh = in;
		in = lo;
		lo = fl;
		fl = dou;
	}
}