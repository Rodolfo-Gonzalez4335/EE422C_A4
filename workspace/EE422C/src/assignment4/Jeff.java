package assignment4;

public class Jeff extends Critter{
	
	public String toString() { return "J"; }
	
	private int dir;
	
	
	public Jeff() {	
		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight(String used){
		if (used.equals("Algae"))
		{
			return true;
		}
		else 
		{
			
			// check if it can run away
			return false;
		}
	}
	
	@Override
	public void doTimeStep() {
		
		if (getEnergy() > 150)
		{
			Jeff child = new Jeff();
			//setting random 
			reproduce(child, Critter.getRandomInt(8));
		}
		
		dir = Critter.getRandomInt(8);
	}
	
	public static
}
