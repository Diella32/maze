package droidPD;

import java.util.ArrayList;


import mazePD.Coordinates;
import mazePD.DroidInterface;
import mazePD.Maze;
import mazePD.Maze.Content;
import mazePD.Maze.Direction;
import stackPD.StackUsed;

public class Droid implements DroidInterface{

	private Coordinates droidLocation;
	private String droidName; 
	LinkedStackMaze<Coordinates> droidPath = new LinkedStackMaze(); 
	ArrayList <Coordinates> visitedCells = new ArrayList(); 
	Content adjacentArray[] = new Content [4];
	
	@Override
	public String getName() {
		return droidName; 
	}
	
	public Droid(String Name) {
		droidName = Name;
		
	}
	
	public Coordinates explorationMaze(Maze mazeUsed) {
		mazeUsed.enterMaze(this);
		Coordinates startLocation = mazeUsed.getCurrentCoordinates(this);  
        droidPath.push(startLocation);
        visitedCells.add(startLocation); 
        
        while(!droidPath.isEmpty()&& !mazeUsed.scanCurLoc(this).equals(Maze.Content.END) )	{
        	adjacentArray = mazeUsed.scanAdjLoc(this); 
        	Coordinates nextPlace = moveToNextLocation(mazeUsed, adjacentArray); 
        	
        	if(nextPlace == null) {
        		droidPath.pop(); 
        		moveTheDroid(mazeUsed.getCurrentCoordinates(this), mazeUsed);
        	}
        	else {
        		System.out.println(mazeUsed.getCurrentCoordinates(this));
        		for (int i = 0; i <4; i++) {
        			System.out.print(" " + adjacentArray[i] + " ");
        		}
        		System.out.println();
        		droidPath.push(nextPlace);
        		visitedCells.add(nextPlace); 
        	}
        		
        	if(mazeUsed.scanCurLoc(this).equals(Maze.Content.PORTAL_DN)){
        		mazeUsed.usePortal(this, Direction.DN);
        		droidPath.push(mazeUsed.getCurrentCoordinates(this));
        		visitedCells.add(nextPlace); 

        	}

        }

        if(droidPath.isEmpty())
	        {
	        	return null; 
	        }
        else 
	        {
	        return droidPath.top();
	        }
        
	}
	
	public Coordinates moveToNextLocation (Maze mazeUsed, Maze.Content[] adjacentArray) {
		
		for(int i =0; i<4; i++) {
			if (!adjacentArray[i].equals(Maze.Content.NA) && !adjacentArray[i].equals(Maze.Content.BLOCK)) {
				if( i == 0) {
					mazeUsed.move(this, Direction.D00); 
				}
				if( i == 1) {
					mazeUsed.move(this, Direction.D90); 
				}
				if( i == 2) {
					mazeUsed.move(this, Direction.D180); 
				}
				if( i == 3) {
					mazeUsed.move(this, Direction.D270); 
				}
				if(isVisited(mazeUsed.getCurrentCoordinates(this))) {
					if(i == 0) {
						mazeUsed.move(this, Direction.D180);
					}
					if(i == 1) {
						mazeUsed.move(this, Direction.D270);
					}
					if(i == 2) {
						mazeUsed.move(this, Direction.D00);
					}
					if(i == 3) {
						mazeUsed.move(this, Direction.D90);
					}
				}
				else
					return mazeUsed.getCurrentCoordinates(this);
			}
			
		}
		return null; 
		
	}
	
	
	public boolean isVisited(Coordinates droidLocation ) {
		for (Coordinates i: visitedCells) {
			if(droidLocation.equals(i)) {
				return true;
			}
		}
		return false; 
	}
	
	public void moveTheDroid(Coordinates droidLocation, Maze mazeUsed ) {
		
		if(mazeUsed.getCurrentCoordinates(this).getX() == droidPath.top().getX()) {
			if(mazeUsed.getCurrentCoordinates(this).getY() > droidPath.top().getY()) {
				mazeUsed.move(this, Direction.D00);
			}
			else {
				mazeUsed.move(this, Direction.D180); 
			}
		}
		
		else if(mazeUsed.getCurrentCoordinates(this).getY() == droidPath.top().getY()) {
			if(mazeUsed.getCurrentCoordinates(this).getX() > droidPath.top().getX()) {
				mazeUsed.move(this, Direction.D270);
			}
			else {
				mazeUsed.move(this, Direction.D90); 
			}
		}
	}
}
