public class SimpleDotComTesteDrive {
	public static void main(String[] args) {
		int numOfGuess = 0;
		
		SimpleDotCom dot = new SimpleDotCom();
		GameHelper gm = new GameHelper();
		
		int randomNum = (int) (Math.random() * 5);
		
		int[] locations = {randomNum, randomNum + 1, randomNum + 2};
		
		dot.setLocationCells(locations);
		
		boolean isAlive = true;
		while(isAlive == true)
		{
			String userGuess = gm.getUserInput("Insira um palpite:");
			
			String result = dot.checkYourself(userGuess);
			
			numOfGuess++;
			
			if(result.equals("kill")) {
				isAlive = false;
				
				System.out.println("Você usou " + numOfGuess + " palpites");
			}
		}
		
	}
}	