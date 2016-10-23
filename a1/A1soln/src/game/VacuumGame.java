package game;

import sprites.CleanHallway;
import sprites.Dirt;
import sprites.Dumpster;
import sprites.Dust;
import sprites.DustBall;
import sprites.Sprite;
import sprites.Vacuum;
import sprites.Wall;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * A class that represents the basic functionality of the vacuum game.
 * This class is responsible for performing the following operations:
 * 1. At creation, it initializes the instance variables used to store the
 *        current state of the game.
 * 2. When a move is specified, it checks if it is a legal move and makes the
 *        move if it is legal.
 * 3. It reports information about the current state of the game when asked.
 */
public class VacuumGame {

  private Random random;            // a random number generator to move the DustBalls
  private Grid<Sprite> grid;        // the grid
  private Vacuum vacuum1;           // the first player
  private Vacuum vacuum2;           // the second player
  private List<Dust> dusts;         // the dusts
  private List<DustBall> dustBalls; // the dust balls

  /**
   * Creates a new <code>VacuumGame</code> that corresponds to the given input text file. Assumes
   * that the input file has one or more lines of equal lengths, and that each character in it
   * (other than newline) is a character that represents one of the <code>Sprite</code>s in this
   * game. Uses gridType to implement the grid.
   * @param layoutFileName path to the input grid file
   * @param gridType the type of grid implementation to use
   */
  public VacuumGame(String layoutFileName, Constants.GridType gridType) throws IOException {
    dusts = new ArrayList<>();
    dustBalls = new ArrayList<>();
    random = new Random();

    // open the file, read the contents, and determine dimensions of the grid
    int[] dimensions = getDimensions(layoutFileName);
    int numRows = dimensions[0];
    int numColumns = dimensions[1];
        
    if (gridType.equals(Constants.GridType.LIST_GRID)) {
      grid = new ListGrid<>(numRows, numColumns);
    } else {
      grid = new MapGrid<>(numRows, numColumns);
    }

    // open the file again, read the contents, and store them in grid
    Scanner sc = new Scanner(new File(layoutFileName));
    for (int row = 0; row < numRows; row++) {
      String nextLine = sc.nextLine();

      /******** 
       * Initialize the grid
       ********/
      
    }
    sc.close();
  }

    /*********
     * Lots of methods
     ************/

  /**
 * @return the grid
 */
public Grid<Sprite> getGrid() {
	return grid;
}

/**
 * @return the vacuum1
 */
public Vacuum getVacuumOne() {
	return vacuum1;
}

/**
 * @return the vacuum2
 */
public Vacuum getVacuumTwo() {
	return vacuum2;
}

public int getNumRows(){
	return grid.getNumRows();
}

public int getNumColumns(){
	return grid.getNumColumns();
}

public Sprite getSprite(int row, int column){
	
}

public void move(char nextMove){

}

public boolean gameOver(){
	
}

public char getWinner(){
	
}
/**
   * Returns the dimensions of the grid in the file named layoutFileName.
   * @param layoutFileName path of the input grid file
   * @return an array [numRows, numCols], where numRows is the number
   *     of rows and numCols is the number of columns in the grid that
   *     corresponds to the given input grid file
   * @throws IOException if cannot open file layoutFileName
   */
  private int[] getDimensions(String layoutFileName) throws IOException {       

    Scanner sc = new Scanner(new File(layoutFileName));

    // find the number of columns
    String nextLine = sc.nextLine();
    int numCols = nextLine.length();

    // find the number of rows
    int numRows = 1;
    while (sc.hasNext()) {
      numRows++;
      nextLine = sc.nextLine();
    }

    sc.close();
    return new int[]{numRows, numCols};
  }  
}
