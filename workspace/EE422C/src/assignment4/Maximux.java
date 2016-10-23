package assignment4;

public class Maximux extends Critter{
	
	private int dir;
	private boolean wonfight=false;
	
	public String toString(){return "^";}
	
	public Maximux(){

		dir = Critter.getRandomInt(8);
	}
	
	public boolean fight (String used)
	{
		if (used.equals("^")){
			walk(dir);
			return false;
		}
		else 
		{
			wonfight=true;
			return true;
		}
	}
	
	@Override
	public void doTimeStep(){
		if (wonfight==true )
		{
			if (getEnergy()>Params.min_reproduce_energy)
			{
				Maximux child = new Maximux();
				reproduce(child , Critter.getRandomInt(8));
			}
			else//means he is to tired to move so he has to conserve energy
				return;
		}
		if (Critter.getRandomInt(20)%2 ==1) {run(dir);}
			dir = Critter.getRandomInt(8);
	}
	
	public static void runStats(java.util.List<Critter> Maximux)
	{
		System.out.println("" + Maximux.size() + " total Maximuxs    ");
	}
}
