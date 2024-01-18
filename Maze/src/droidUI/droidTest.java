package droidUI;

import droidPD.Droid;
import mazePD.Coordinates;
import mazePD.Maze;
import mazePD.Maze.MazeMode;

public class droidTest {

		public static void main(String[] args) {
			int levels = 5; 
			int dim = 6; 
			System.out.println("Maze Test");
			Maze maze = new Maze(dim,levels,MazeMode.TEST);
			System.out.println("Maze - "+maze.toString());
			
			for (int z=0;z<levels;z++)
			{
			  System.out.println("Level - "+z );
			  String[] mazeArray = maze.toStringLevel(z);
			  for (int y=0;y<dim;y++) 	{					     
				  System.out.println(y+" "+ mazeArray[y]);
			}

			}
			
			Droid droid;
			droid= new Droid("Roboto");
			Coordinates result= droid.explorationMaze(maze);

			if(result== null)
			{
				System.out.println("No Path Found");
			}

			else

			{
				System.out.println("Found End : "+ result.toString());
			}

		}
}