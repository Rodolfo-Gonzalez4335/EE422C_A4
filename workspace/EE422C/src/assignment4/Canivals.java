package assignment4;

public class Canivals extends Critter{
	
	private int dir;
	
	public Canivals(){
		dir = Critter.getRandomInt(8);
	}
	
	public String toString() { return "!"; }
	
	public boolean fight(String used )
	{
		if (used.equals("!") || used.equals("@"))
			return true;
		else 
		{
			
			walk(dir);
			return  false;
		}
	}
	
	public void doTimeStep(){
		
		int x = Critter.getRandomInt(100)%3;
		
		if (x==0)
		{
			walk(dir);
		}
		else if (x==1)
		{
			run(dir);
		}
		else
		{
			if (getEnergy()>Params.min_reproduce_energy)
			{
				Uknown child = new Uknown();
				reproduce(child, Critter.getRandomInt(8));
			}
		}
		dir =  Critter.getRandomInt(8);
	}
	
	public static void runStats(java.util.List<Critter> Canivals)
	{
		System.out.println("" + Canivals.size() + " total Jeffs    ");
	}
}
