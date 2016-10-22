package assignment4;

public class Jeff extends Critter{
	
	private int dir;
	
	
	public Jeff() {	
		dir = Critter.getRandomInt(8);
	}
	
	
	public String toString() { return "J"; }
	
	public boolean fight(String used){
		if (used.equals("@"))
		{
			return true;
		}
		else 
		{
			walk(dir);
			return false;
		}
	}
	
	@Override
	public void doTimeStep() {
		
		if (getEnergy() > Params.min_reproduce_energy)
		{
			Jeff child = new Jeff();
			//setting random 
			reproduce(child, Critter.getRandomInt(8));
		}
		
		dir = Critter.getRandomInt(8);
	}
	
	public static void runStats(java.util.List<Critter> Jeff)
	{
		System.out.println("" + Jeff.size() + " total Jeffs    ");
	}
}
