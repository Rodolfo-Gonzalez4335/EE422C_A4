/* CRITTERS Main.java
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
package assignment4; // cannot be in default package
import java.util.ArrayList;
import java.util.Scanner;

import assignment4.Critter.TestCritter;

import java.io.*;
import java.lang.reflect.InvocationTargetException;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

    static Scanner kb;	// scanner connected to keyboard input, or input file
    private static String inputFile;	// input file, used instead of keyboard input if specified
    static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
    @SuppressWarnings("unused")
	private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
    @SuppressWarnings("unused")
	private static boolean DEBUG = false; // Use it or not, as you wish!
    static PrintStream old = System.out;	// if you want to restore output to console


    // Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
    static {
        myPackage = Critter.class.getPackage().toString().split(" ")[1];
    }

    /**
     * Main method.
     * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
     * and the second is test (for test output, where all output to be directed to a String), or nothing.
     * @throws IllegalAccessException 
     * @throws InstantiationException 
     * @throws InvalidCritterException 
     * @throws InvocationTargetException 
     * @throws NoSuchMethodException 
     * @throws IllegalArgumentException 
     * @throws SecurityException 
     */
    public static void main(String[] args) throws InvalidCritterException, InstantiationException, IllegalAccessException, SecurityException, IllegalArgumentException, NoSuchMethodException, InvocationTargetException { 
        if (args.length != 0) {
            try {
                inputFile = args[0];
                kb = new Scanner(new File(inputFile));			
            } catch (FileNotFoundException e) {
                System.out.println("USAGE: java Main OR java Main <input file> <test output>");
                e.printStackTrace();
            } catch (NullPointerException e) {
                System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
            }
            if (args.length >= 2) {
                if (args[1].equals("test")) { // if the word "test" is the second argument to java
                    // Create a stream to hold the output
                    testOutputString = new ByteArrayOutputStream();
                    PrintStream ps = new PrintStream(testOutputString);
                    // Save the old System.out.
                    old = System.out;
                    // Tell Java to use the special stream; all console output will be redirected here from now
                    System.setOut(ps);
                }
            }
        } else { // if no arguments to main
            kb = new Scanner(System.in); // use keyboard and console
        }

        /* Do not alter the code above for your submission. */
        /* Write your code below. */
        boolean stop=false;
        while (!stop){
        	ArrayList<String> commands = new ArrayList<String>();
        	
        	String input;
        	System.out.print("critters>");
        	input = kb.nextLine(); 
        	input =input.trim();
        	int begin=0;
        	boolean flag=false;
        	//saving each command inside a list
        	for (int i=0; i<input.length(); i++)
        	{
        		if (flag==true) 
        		{
        			if (input.charAt(i)!=' ') 
        				{
        					flag=false; 
        					begin=i;
        				}
        		}
        		if (input.charAt(i)==' ' )
        		{
        			commands.add(input.substring(begin, i));
        			flag=true;
        		}
        		else if (i+1==input.length()){
        			commands.add(input.substring(begin, i+1));
        		}
    			
        	}
        	
        	if(commands.size() <= 3 && commands.size() > 0){
        		if (commands.get(0).equals("quit"))
    				stop= true;
    			else if (commands.get(0).equals("show")){
    				if(commands.size()==1)
    					Critter.displayWorld();
    				else
    					Critter.processing(input);
    			}
    			else if (commands.get(0).equals("step")){
    				if(commands.size() == 2){
	    				int x = 0;
						try{
							x = Integer.parseInt(commands.get(1));//need to check if valid input
						}catch(NumberFormatException e){
							Critter.processing(input);
						}
						for (int i=0; i<x; i++)
							TestCritter.worldTimeStep();
	    				}
    				else if(commands.size() < 2)
    					TestCritter.worldTimeStep();
    				else
    					Critter.invalid(input);//call method from critter
	    			}
    			else if(commands.get(0).equals("make")){
    				if(commands.size() == 3){
    					if(Critter.getInstances(commands.get(1)).size() != 0){
	    					int x = 0;
	        				try{
	        					x= Integer.parseInt(commands.get(2));
	        				}catch(NumberFormatException e){
	        					Critter.processing(input);
	        				}
	        				for(int i = 0; i<x; i++)
	        					Critter.makeCritter(commands.get(1));
    					}
    					else
    						Critter.processing(input);
    				}
    				else if(commands.size() == 2){
    					if(Critter.getInstances(commands.get(1)).size() != 0){
    						Critter.makeCritter(commands.get(1));
    					}
    					else
    						Critter.processing(input);
    				}
    				else
    					Critter.invalid(input);
    			}
    			else if(commands.get(0).equals("seed")){
    				if(commands.size() == 2){
    					int x = 0;
        				try{
        					x = Integer.parseInt(commands.get(1));//need to check if valid input
        				}catch(NumberFormatException e){
        					Critter.processing(input);
        				}
        				Critter.setSeed(x);
    				}
    				else{
        	        	Critter.processing(input);
    				}
    			}
    			else if (commands.get(0).equals("stats"))
    			{
    				if(commands.size()==2){
    					if(Critter.getInstances(commands.get(1)).size() != 0)
    						Critter.runStats(Critter.getInstances(commands.get(1)));
    					else
    						Critter.processing(input);
    				}
    				else
    					Critter.invalid(input);
    			}	
    			else{
    	        	Critter.invalid(input);
    			}
        	}
        	else{
        		Critter.processing(input);
        	}
        }
        System.out.println("GLHF");
        
        /* Write your code above */
        System.out.flush();

    }
}