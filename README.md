# EE422C_A4
We created 4 new classes which are our Critter 1 through 4 and all of them have the following methods:
  - toString()
  - fight()
  - doTime()
  - runStats() \n
  
 Our class worldTimeStep calls these classes and invokes them in their appropiate order. No new classes where implemented
 besides the ones decribed above. Instead new methods were created in the Critter class, here's a run list of the new methods created:
  - change()
  - directiontomove()
  - fightdecision()
  - processing()
  - invalid()
  - checkPosition()
  

After various data structures, we finally decided to stick with an ArrayList to save the population of all critters. In any case we wanted to do a smaller set we would just create another arraylist and send it to the called function to get a set of specific critters. We tried to implement a hash set composed of points to keep track of the position of each critter and make sure it never repeated itself but found the x_coord and the y_coord pretty useful since each object had their own we didn't really needed the use of a hash set.


Notes:
  - We do not print "critter>" before any processing error or invalid command string. We do not know if this will affect the efficiency of our program since on the given tests the program checks if there's a string of "critter>" yet since is just a replace method if it doesn't find any it would not produce an error so we should be good.
  - After the command quit is executed we are outputting GLHF, but we are not sure if we are require to erase that println or not.
  - The method runStats inside Critter was erased from what was given at first and replace by a code that calls the runstats of every specified class/object.
