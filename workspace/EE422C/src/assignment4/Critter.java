/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */
package assignment4;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();
	private int cnt = 0;

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}
	
	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}
	
	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}
	
	
	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }
	
	private int energy = 0;
	protected int getEnergy() { return energy; }
	
	private int x_coord;
	private int y_coord;
	//added this variable in order to keep track of which critters have moved
	private boolean moved = false; //?????????????
	
	protected final void walk(int direction) {   //TODO
		//checking if it already moved since it can only move once per time step
		if (!moved){
			moved = true;
			change(direction);
			if(cnt == 1 && !moved){
				change(direction);
			}
		}
		moved=true;
		energy= energy-Params.walk_energy_cost;
	}
	
	protected final void change(int direction) {		//what are you?
		//not sure whether to use this or leave it as is
		switch (direction)
		{
		case 0 :
			if(!moved || checkPosition((x_coord+1)%Params.world_width, y_coord) || cnt == 0){
				if(!checkPosition((x_coord+1)%Params.world_width, y_coord))
					moved = false;
				else
					moved = true;
				x_coord= (x_coord+1)%Params.world_width;
			}
			else{
				moved = false;
				change(4);
			}
			break;
		case 1 :
			if(!moved || checkPosition((x_coord+1)%Params.world_width, (y_coord+1)%Params.world_height) || cnt == 0){
				if(checkPosition((x_coord+1)%Params.world_width, (y_coord+1)%Params.world_height))
					moved = false;
				else
					moved = true;
				x_coord =(x_coord+1)%Params.world_width;
				y_coord = (y_coord+1)%Params.world_height;
			}
			else{
				moved = false;
				change(5);
			}
			break;
		case 2 :
			if(!moved || checkPosition(x_coord, (y_coord+1)%Params.world_height) || cnt == 0){
				if(checkPosition(x_coord, (y_coord+1)%Params.world_height))
					moved = false;
				else
					moved = true;
				y_coord = (y_coord+1)%Params.world_height;
			}
			else{
				moved = false;
				change(6);
			}
			break;
		case 3:
			if(!moved || checkPosition((Params.world_width+x_coord-1)%Params.world_width, (y_coord+1)%Params.world_height) || cnt == 0){
				if(checkPosition((Params.world_width+x_coord-1)%Params.world_width, (y_coord+1)%Params.world_height))
					moved = false;
				else
					moved = true;
				x_coord = (Params.world_width+x_coord-1)%Params.world_width;
				y_coord = (y_coord+1)%Params.world_height;
			}
			else{
				moved = false;
				change(7);
			}
			break;
		case 4:
			if(!moved || checkPosition((Params.world_width+x_coord-1)%Params.world_width, y_coord) || cnt == 0){
				if(checkPosition((Params.world_width+x_coord-1)%Params.world_width, y_coord))
					moved = false;
				else
					moved = true;
				x_coord = (Params.world_width+x_coord-1)%Params.world_width;
			}
			else{
				moved = false;
				change(0);
			}
			break;
		case 5:
			if(!moved || checkPosition((Params.world_width+x_coord-1)%Params.world_width, (Params.world_height+y_coord-1)%Params.world_height) || cnt == 0){
				if(checkPosition((Params.world_width+x_coord-1)%Params.world_width, (Params.world_height+y_coord-1)%Params.world_height))
					moved = false;
				else
					moved = true;
				x_coord = (Params.world_width+x_coord-1)%Params.world_width;
				y_coord = (Params.world_height+y_coord-1)%Params.world_height;
			}
			else{
				moved = false;
				change(1);
			}
			break;
		case 6:
			if(!moved || checkPosition(x_coord, (Params.world_height+y_coord-1)%Params.world_height) || cnt == 0){
				if(checkPosition(x_coord, (Params.world_height+y_coord-1)%Params.world_height))
					moved = false;
				else
					moved = true;
				y_coord=(Params.world_height+y_coord-1)%Params.world_height;
			}
			else{
				moved = false;
				change(2);
			}
			break;
		case 7:
			if(!moved || checkPosition((x_coord+1)%Params.world_width, (Params.world_height+y_coord-1)%Params.world_height) || cnt == 0){
				if(checkPosition((x_coord+1)%Params.world_width, (Params.world_height+y_coord-1)%Params.world_height))
					moved = false;
				else
					moved = true;
				x_coord = (x_coord+1)%Params.world_width;
				y_coord = (Params.world_height+y_coord-1)%Params.world_height;
			}
			else{
				moved = false;
				change(3);
			}
			break;
		}
		
	}
	
	protected final void run(int direction) {    //TODO
		//checking if it already moved since it can only move once per time step
		if (!moved) {
			change(direction);
			moved = true;
			change(direction);
		}
		moved = true;
		energy = energy - Params.run_energy_cost;
	}
	
	protected final void reproduce(Critter offspring, int direction) {  //TODO
		//moving the baby to the desire location
		
		//checking if it has enough energy to reproduce
		if (this.energy<Params.min_reproduce_energy) return;
		
		int[] x = directiontomove(offspring, direction);
		offspring.energy= this.energy/2;//rounding down the energy of the child
		this.energy= this.energy/2 + this.energy%2; //rounding up the energy of parent
		offspring.x_coord= x[0];
		offspring.y_coord = x[1];
		
		babies.add(offspring);
	}
	
	//method to change
	protected final void changedirection(Critter offspring,int direction) //??????????
	{
		int[] x = directiontomove(offspring, direction);
		offspring.x_coord= x[0];
		offspring.y_coord = x[1];
		
	}
	//i made this moves the direction of the critter
	protected final int[] directiontomove(Critter offspring, int direction) //??????
	{
		int[] x = new int[2];
				switch (direction) 
				{
					case 0 :
						x[0]= (this.x_coord+1)%Params.world_width;
						x[1] = this.y_coord;
						break;
					case 1 :
						x[0] =(this.x_coord+1)%Params.world_width;
						x[1]= (this.y_coord+1)%Params.world_height;
						break;
					case 2 :
						x[0] = this.x_coord;
						x[1]= (this.y_coord+1)%Params.world_height;
						break;
					case 3:
						x[0]= (Params.world_width+this.x_coord-1)%Params.world_width;
						x[1]= (this.y_coord+1)%Params.world_height;
						break;
					case 4:
						x[0]= (Params.world_width+this.x_coord-1)%Params.world_width;
						x[1] = this.y_coord;
						break;
					case 5:
						x[0]= (Params.world_width+this.x_coord-1)%Params.world_width;
						x[1]=(Params.world_height+this.y_coord-1)%Params.world_height;
						break;
					case 6:
						x[0] = this.x_coord;
						x[1]=(Params.world_height+this.y_coord-1)%Params.world_height;
						break;
					case 7:
						x[0]= (this.x_coord+1)%Params.world_width;
						x[1]=(Params.world_height+this.y_coord-1)%Params.world_height;
						break;
				}
				return x;
	}
	
	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);
	
	
	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 */
	@SuppressWarnings("unused")
	public static void makeCritter(String critter_class_name) throws InvalidCritterException, InstantiationException, IllegalAccessException {
		//ignore when they are both in the same 
		String critter_class_name1 = myPackage + "." + critter_class_name;
		Class<?> c = "foo".getClass();
		try {
			Class<?> x = Class.forName(critter_class_name1);
			c=x;
			Critter check = (Critter)x.newInstance();
		} catch(NoClassDefFoundError e){
			return;
		}catch (ClassNotFoundException e1) {
			return;
		}catch (InstantiationException e2) {
			return;
		}catch(ClassCastException e3){
			return;
		}
		//System.out.println(c);
		Critter z = (Critter) c.newInstance();
		z.x_coord= getRandomInt(Params.world_width-1);
		z.y_coord =  getRandomInt(Params.world_height-1);
		z.energy = Params.start_energy;
		population.add(z);

	}
	
	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	@SuppressWarnings("unused")
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException, InstantiationException, IllegalAccessException {
		List<Critter> result = new java.util.ArrayList<Critter>();
		String critter_class_name1 = myPackage + "." + critter_class_name;
		Class<?> c = "foo".getClass();
		try {
			Class<?> x = Class.forName(critter_class_name1);
			c=x;
			Critter check = (Critter)x.newInstance();
		}catch(NoClassDefFoundError e){
			return result;//thinking...
		}catch (ClassNotFoundException e1) {
			return result;//thinking....
		}catch (InstantiationException e2) {
			return result;//thinking....
		}
		Critter check1 = (Critter)c.newInstance();
		result.add(check1);
		for(Critter e : population){
			if(e.getClass().isInstance(check1))
				result.add(e);
		}
		return result;
	}
	
	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * @throws NoSuchMethodException 
	 * @throws SecurityException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 */
	public static void runStats(List<Critter> critters) throws InstantiationException, IllegalAccessException, SecurityException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {

		/*if(critters.get(0).getClass().isInstance(null)){
			System.out.print("" + critters.size() + " critters as follows -- ");
			java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
			for (Critter crit : critters) {
				String crit_string = crit.toString();
				Integer old_count = critter_count.get(crit_string);
				if (old_count == null) {
					critter_count.put(crit_string,  1);
				} else {
					critter_count.put(crit_string, old_count.intValue() + 1);
				}
			}
			String prefix = "";
			for (String s : critter_count.keySet()) {
				System.out.print(prefix + s + ":" + critter_count.get(s));
				prefix = ", ";
			}
			System.out.println();
		}*/
		//else{
			//fix this part(have to call the runStats of each respective class given a list of elements of that class)
		int index = myPackage.length()+7;	
		String buche;
		buche = critters.get(0).getClass().toString().substring(index);
		buche = "stats " + buche;
			try{
				Class<?> c = critters.get(0).getClass();
				Method method =  c.getDeclaredMethod("runStats", List.class);
				critters.remove(0);
				method.invoke(null, critters);
			}catch(NoSuchMethodException e){
				processing(buche);
			}
	}
	
	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}
		
		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}
		
		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}
		
		protected int getX_coord() {
			return super.x_coord;
		}
		
		protected int getY_coord() {
			return super.y_coord;
		}
		

		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}
		
		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		for(Critter e: population){
			population.remove(e);
		}
	}
	/**invoke time step 
	 * 
	 * 
	 * */
	public static void worldTimeStep() {
		//adding babies first since the first time it will be empty
		//therefore new babies will be added at the second time
		for (int i=0; i< population.size(); i++)
		{
			Critter x = population.get(i);
			x.doTimeStep();		//we can make them fight here
			x.cnt++;	//increasing count to 1
		}
		
		//do the fights
		for (int i=0; i<population.size()-1; i++)
		{
			for (int j=i+1; j<population.size(); j++)
			{
				Critter x = population.get(i);
				Critter y = population.get(j);
				//checking if critters are at the same position
				if (x.x_coord==y.x_coord && x.y_coord==y.y_coord && x.energy > 0 && y.energy > 0) //otherwise opponent is dead
				{
					int fight_number_1 = Critter.getRandomInt(x.energy);
					int fight_number_2 = Critter.getRandomInt(y.energy);
					//seeing if both want to fight
					if (x.fight(y.toString())==true){
						if (y.fight(x.toString())==true)
						{
							fightdecision(fight_number_1,fight_number_2,x,y);
						}
						else//y failed to move(run away)
						{
							if (x.x_coord==y.x_coord && x.y_coord==y.y_coord && x.energy > 0 && y.energy > 0){ //otherwise opponent is dead
								fight_number_2 = 0;
								fightdecision(fight_number_1,fight_number_2,x,y);
							}
						}
					}
					else //both failed to move (run away)
					{
						if (x.x_coord==y.x_coord && x.y_coord==y.y_coord && x.energy > 0 && y.energy > 0){ //otherwise opponent is dead
							fight_number_1 = 0;
							fightdecision(fight_number_1,fight_number_2,x,y);
						}
					}
					
				}
			}
		}
		 //substracting resting energy of critters
		for (int i=0; i<population.size(); i++)
		{					
			Critter x = population.get(i);
			x.energy= x.energy-Params.rest_energy_cost;
			x.cnt = 0;		//setting count back to 0
		}
		// deleting Critter
		for (int i=0; i<population.size(); i++)
		{
			Critter x = population.get(i);
			x.moved=false; 
			if (x.energy<1){
				population.remove(i);
			}
	
		}
		//generating algae
		for (int i=0; i<Params.refresh_algae_count; i++)
		{
			Critter x = new Algae();
			x.energy= Params.start_energy;
			x.x_coord= Critter.getRandomInt(Params.world_width-1);
			x.y_coord= Critter.getRandomInt(Params.world_height-1);
			population.add(x);
		}
		
		//new to see the encounter
		for (int i=0; i<babies.size();i++)
		{
			population.add(babies.get(i));
		}
		babies.clear();
		
	}
	public static void fightdecision(int fight_number_1,int fight_number_2,Critter x, Critter y)
	{
		if (fight_number_1<fight_number_2)
		{
			y.energy+=x.energy/2;
			population.remove(x);
		}
		else if (fight_number_2<fight_number_1)
		{
			x.energy+=y.energy/2;
			population.remove(y);
		}
		else 
		{
			x.energy+=y.energy/2;
			population.remove(y);
		}
	}
	public static void displayWorld() {
		List<Critter> copy_population= new java.util.ArrayList<Critter>();
		List<Critter> copy_babies= new java.util.ArrayList<Critter>();
		for(Critter e : population)
			copy_population.add(e);
		for(Critter m: babies)
			copy_babies.add(m);
		
		for(int i=0; i<Params.world_width+2; i++)
		{
			for(int j=0; j<Params.world_height+2; j++)
			{
				if ((i==0 &&j==0)||(i==0 && j==Params.world_height+1)|| (i==Params.world_width+1 && j==0)|| (i==Params.world_width+1 && j==Params.world_height+1))
					System.out.print("+");
				else if (i==0 || i==Params.world_width+1)
					System.out.print("-");
				else if (j==0 || j==Params.world_height+1)
					System.out.print("|");
				else 
				{
					boolean flag=false;
					for (int k=0; k<copy_population.size(); k++)
					{
						Critter to_check = copy_population.get(k);
						if (to_check.x_coord==i-1 && to_check.y_coord==j-1)
						{
							System.out.print(to_check);
							copy_population.remove(k);
							flag=true;
							break;
						}
					}//not sure if we have to print babies
					if (flag==false)
						for (int l=0; l<copy_babies.size(); l++)
						{
							Critter to_check = copy_babies.get(l);
							if (to_check.x_coord==i-1 && to_check.y_coord==j-1)
							{
								System.out.print(to_check);
								copy_babies.remove(l);
								flag=true;
								break;
							}
						}
					if (flag==false) System.out.print(" ");
				}
			}
			System.out.println();
		}
		
	}

	public static void processing(String input){
		System.out.println("critter>error processing: " + input);
	}
	public static void invalid(String input){
		System.out.println("critter>invalid command: " + input);
	}
	public static boolean checkPosition(int x, int y){
		for(Critter e : population){
			if(e.x_coord == x && e.y_coord == y)
				return false;
		}
		return true;
	}
}