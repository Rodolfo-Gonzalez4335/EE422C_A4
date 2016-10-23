package assignment4;

public class Uknown extends Critter{

	private int dir; 
	
	public String toString() {return "?";}
	
	public Uknown(){
		dir = Critter.getRandomInt(8);
	}
	
	@Override
	public boolean fight(String not_used)
	{
		int x =Critter.getRandomInt(100)%2;
		if (x==0)
			return true;
		else 
		{
			walk(dir);
			return false;
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
				reproduce(child, Critter.getRandomInt(7));
			}
		}
		dir =  Critter.getRandomInt(8);
	}
	
	public static void runStats(java.util.List<Critter> Uknown)
	{
		System.out.println("" + Uknown.size() + " total Jeffs    ");
	}
}
