package game;

import sprites.CleanHallway;
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

	private static Random random;            // a random number generator to move the DustBalls
	private static Grid<Sprite> grid;        // the grid
	private static Vacuum vacuum1;           // the first player
	private static Vacuum vacuum2;           // the second player
	private static List<Dust> dusts;         // the dusts
	private static List<DustBall> dustBalls; // the dust balls

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
			for (int col = 0; col < numColumns; col++){
				if (nextLine.charAt(col) == Constants.WALL){
					Sprite wall = new Wall (Constants.WALL, row, col);
					grid.setCell(row, col, wall);
				}
				else if (nextLine.charAt(col) == Constants.CLEAN){
					Sprite ch = new CleanHallway (Constants.CLEAN, row, col);
					grid.setCell(row, col, ch);
				}
				else if (nextLine.charAt(col) == Constants.DUMPSTER){
					Sprite dumpster = new Dumpster (Constants.DUMPSTER, row, col);
					grid.setCell(row, col, dumpster);
				}
				else if (nextLine.charAt(col) == Constants.DUST){
					Dust dust = new Dust (Constants.DUST, row, col);
					grid.setCell(row, col, dust);
					dusts.add(dust);
				}
				else if (nextLine.charAt(col) == Constants.DUST_BALL){
					DustBall db = new DustBall (Constants.DUST_BALL, row, col);
					grid.setCell(row, col, db);
					dustBalls.add(db);
				}
				else if (nextLine.charAt(col) == Constants.P1){
					Sprite ch2 = new CleanHallway(Constants.CLEAN, row, col);
					vacuum1 = new Vacuum (Constants.P1, row, col, Constants.INIT_SCORE, Constants.CAPACITY, Constants.EMPTY, ch2);
					grid.setCell(row, col, vacuum1);
					if (vacuum1.getUnder() instanceof DustBall){
						dustBalls.add(new DustBall (Constants.DUST_BALL, row, col));
					}
				}
				else if (nextLine.charAt(col) == Constants.P2){
					Sprite ch3 = new CleanHallway(Constants.CLEAN, row, col);
					vacuum2 = new Vacuum (Constants.P2, row, col, Constants.INIT_SCORE, Constants.CAPACITY, Constants.EMPTY, ch3);
					grid.setCell(row, col, vacuum2);
					if (vacuum2.getUnder() instanceof DustBall){
						dustBalls.add(new DustBall (Constants.DUST_BALL, row, col));
					}
				}
			}
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
		return grid.getCell(row, column);
	}

	public static void move(char nextMove){
		if (nextMove == Constants.P1_LEFT){
			//Check if the left is safe to move to
			boolean valid1 = (grid.getCell(vacuum1.getRow(), vacuum1.getColumn()-1) instanceof Dust);
			boolean valid2 = (grid.getCell(vacuum1.getRow(), vacuum1.getColumn()-1) instanceof DustBall);
			boolean valid3 = (grid.getCell(vacuum1.getRow(), vacuum1.getColumn()-1) instanceof CleanHallway);
			boolean valid4 = (grid.getCell(vacuum1.getRow(), vacuum1.getColumn()-1) instanceof Dumpster);
			if (valid1 || valid2 || valid3 || valid4){
				//Move the vacuum
				grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), vacuum1.getUnder());
				vacuum1.moveTo(vacuum1.getRow(), (vacuum1.getColumn())-1);
				vacuum1.setUnder(grid.getCell(vacuum1.getRow(), vacuum1.getColumn()));
				//Set the new location of the vacuum to the same vacuum sprite
				grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), vacuum1);

				int prevScore = vacuum1.getScore();
				vacuum1.clean();
				if (vacuum1.getScore() == (prevScore + Constants.DUST_BALL_SCORE)){
					for (int i=0; i<dustBalls.size();i++){
						if ((dustBalls.get(i).getRow() == vacuum1.getRow())&&(dustBalls.get(i).getColumn() ==vacuum1.getColumn())){
							dustBalls.remove(i);
						}
					}
				}
			}
		}
		else if (nextMove == Constants.P2_LEFT){
			//Check if the left is safe to move to
			boolean valid1 = (grid.getCell(vacuum2.getRow(), vacuum2.getColumn()-1) instanceof Dust);
			boolean valid2 = (grid.getCell(vacuum2.getRow(), vacuum2.getColumn()-1) instanceof DustBall);
			boolean valid3 = (grid.getCell(vacuum2.getRow(), vacuum2.getColumn()-1) instanceof CleanHallway);
			boolean valid4 = (grid.getCell(vacuum2.getRow(), vacuum2.getColumn()-1) instanceof Dumpster);
			if (valid1 || valid2 || valid3 || valid4){
				//Move the vacuum
				grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), vacuum2.getUnder());
				vacuum2.moveTo(vacuum2.getRow(), (vacuum2.getColumn())-1);
				vacuum2.setUnder(grid.getCell(vacuum2.getRow(), vacuum2.getColumn()));
				//Set the new location of the vacuum to the same vacuum sprite
				grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), vacuum2);			
				int prevScore = vacuum2.getScore();
				vacuum2.clean();
				if (vacuum2.getScore() == (prevScore + Constants.DUST_BALL_SCORE)){
					for (int i=0; i<dustBalls.size();i++){
						if ((dustBalls.get(i).getRow() == vacuum2.getRow())&&(dustBalls.get(i).getColumn() ==vacuum2.getColumn())){
							dustBalls.remove(i);
						}
					}
				}
			}
		}
		else if (nextMove == Constants.P1_UP){
			//Check if the left is safe to move to
			boolean valid1 = (grid.getCell(vacuum1.getRow()-1, vacuum1.getColumn()) instanceof Dust);
			boolean valid2 = (grid.getCell(vacuum1.getRow()-1, vacuum1.getColumn()) instanceof DustBall);
			boolean valid3 = (grid.getCell(vacuum1.getRow()-1, vacuum1.getColumn()) instanceof CleanHallway);
			boolean valid4 = (grid.getCell(vacuum1.getRow()-1, vacuum1.getColumn()) instanceof Dumpster);
			if (valid1 || valid2 || valid3 || valid4){
				//Move the vacuum
				grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), vacuum1.getUnder());
				vacuum1.moveTo(vacuum1.getRow()-1, (vacuum1.getColumn()));
				vacuum1.setUnder(grid.getCell(vacuum1.getRow(), vacuum1.getColumn()));
				//Set the new location of the vacuum to the same vacuum sprite
				grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), vacuum1);
				int prevScore = vacuum1.getScore();
				vacuum1.clean();
				if (vacuum1.getScore() == (prevScore + Constants.DUST_BALL_SCORE)){
					for (int i=0; i<dustBalls.size();i++){
						if ((dustBalls.get(i).getRow() == vacuum1.getRow())&&(dustBalls.get(i).getColumn() ==vacuum1.getColumn())){
							dustBalls.remove(i);
						}
					}
				}
			}
		}
		else if (nextMove == Constants.P2_UP){
			//Check if the left is safe to move to
			boolean valid1 = (grid.getCell(vacuum2.getRow()-1, vacuum2.getColumn()) instanceof Dust);
			boolean valid2 = (grid.getCell(vacuum2.getRow()-1, vacuum2.getColumn()) instanceof DustBall);
			boolean valid3 = (grid.getCell(vacuum2.getRow()-1, vacuum2.getColumn()) instanceof CleanHallway);
			boolean valid4 = (grid.getCell(vacuum2.getRow()-1, vacuum2.getColumn()) instanceof Dumpster);
			if (valid1 || valid2 || valid3 || valid4){
				//Move the vacuum
				grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), vacuum2.getUnder());
				vacuum2.moveTo(vacuum2.getRow()-1, (vacuum2.getColumn()));
				vacuum2.setUnder(grid.getCell(vacuum2.getRow(), vacuum2.getColumn()));
				//Set the new location of the vacuum to the same vacuum sprite
				grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), vacuum2);			
				int prevScore = vacuum2.getScore();
				vacuum2.clean();
				if (vacuum2.getScore() == (prevScore + Constants.DUST_BALL_SCORE)){
					for (int i=0; i<dustBalls.size();i++){
						if ((dustBalls.get(i).getRow() == vacuum2.getRow())&&(dustBalls.get(i).getColumn() ==vacuum2.getColumn())){
							dustBalls.remove(i);
						}
					}
				}
			}
		}
		else if (nextMove == Constants.P1_RIGHT){
			//Check if the left is safe to move to
			boolean valid1 = (grid.getCell(vacuum1.getRow(), vacuum1.getColumn()+1) instanceof Dust);
			boolean valid2 = (grid.getCell(vacuum1.getRow(), vacuum1.getColumn()+1) instanceof DustBall);
			boolean valid3 = (grid.getCell(vacuum1.getRow(), vacuum1.getColumn()+1) instanceof CleanHallway);
			boolean valid4 = (grid.getCell(vacuum1.getRow(), vacuum1.getColumn()+1) instanceof Dumpster);
			if (valid1 || valid2 || valid3 || valid4){
				//Move the vacuum
				grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), vacuum1.getUnder());
				vacuum1.moveTo(vacuum1.getRow(), (vacuum1.getColumn()+1));
				vacuum1.setUnder(grid.getCell(vacuum1.getRow(), vacuum1.getColumn()));
				//Set the new location of the vacuum to the same vacuum sprite
				grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), vacuum1);
				int prevScore = vacuum1.getScore();
				vacuum1.clean();
				if (vacuum1.getScore() == (prevScore + Constants.DUST_BALL_SCORE)){
					for (int i=0; i<dustBalls.size();i++){
						if ((dustBalls.get(i).getRow() == vacuum1.getRow())&&(dustBalls.get(i).getColumn() ==vacuum1.getColumn())){
							dustBalls.remove(i);
						}
					}
				}
			}
		}
		else if (nextMove == Constants.P2_RIGHT){
			//Check if the left is safe to move to
			boolean valid1 = (grid.getCell(vacuum2.getRow(), vacuum2.getColumn()+1) instanceof Dust);
			boolean valid2 = (grid.getCell(vacuum2.getRow(), vacuum2.getColumn()+1) instanceof DustBall);
			boolean valid3 = (grid.getCell(vacuum2.getRow(), vacuum2.getColumn()+1) instanceof CleanHallway);
			boolean valid4 = (grid.getCell(vacuum2.getRow(), vacuum2.getColumn()+1) instanceof Dumpster);
			if (valid1 || valid2 || valid3 || valid4){
				//Move the vacuum
				grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), vacuum2.getUnder());
				vacuum2.moveTo(vacuum2.getRow(), (vacuum2.getColumn()+1));
				vacuum2.setUnder(grid.getCell(vacuum2.getRow(), vacuum2.getColumn()));
				//Set the new location of the vacuum to the same vacuum sprite
				grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), vacuum2);			
				int prevScore = vacuum2.getScore();
				vacuum2.clean();
				if (vacuum2.getScore() == (prevScore + Constants.DUST_BALL_SCORE)){
					for (int i=0; i<dustBalls.size();i++){
						if ((dustBalls.get(i).getRow() == vacuum2.getRow())&&(dustBalls.get(i).getColumn() ==vacuum2.getColumn())){
							dustBalls.remove(i);
						}
					}
				}
			}
		}
		else if (nextMove == Constants.P1_DOWN){
			//Check if the left is safe to move to
			boolean valid1 = (grid.getCell(vacuum1.getRow()+1, vacuum1.getColumn()) instanceof Dust);
			boolean valid2 = (grid.getCell(vacuum1.getRow()+1, vacuum1.getColumn()) instanceof DustBall);
			boolean valid3 = (grid.getCell(vacuum1.getRow()+1, vacuum1.getColumn()) instanceof CleanHallway);
			boolean valid4 = (grid.getCell(vacuum1.getRow()+1, vacuum1.getColumn()) instanceof Dumpster);
			if (valid1 || valid2 || valid3 || valid4){
				//Move the vacuum
				grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), vacuum1.getUnder());
				vacuum1.moveTo(vacuum1.getRow()+1, (vacuum1.getColumn()));
				vacuum1.setUnder(grid.getCell(vacuum1.getRow(), vacuum1.getColumn()));
				//Set the new location of the vacuum to the same vacuum sprite
				grid.setCell(vacuum1.getRow(), vacuum1.getColumn(), vacuum1);
				int prevScore = vacuum1.getScore();
				vacuum1.clean();
				if (vacuum1.getScore() == (prevScore + Constants.DUST_BALL_SCORE)){
					for (int i=0; i<dustBalls.size();i++){
						if ((dustBalls.get(i).getRow() == vacuum1.getRow())&&(dustBalls.get(i).getColumn() ==vacuum1.getColumn())){
							dustBalls.remove(i);
						}
					}
				}
			}
		}
		else if (nextMove == Constants.P2_DOWN){
			//Check if the left is safe to move to
			boolean valid1 = (grid.getCell(vacuum2.getRow()+1, vacuum2.getColumn()) instanceof Dust);
			boolean valid2 = (grid.getCell(vacuum2.getRow()+1, vacuum2.getColumn()) instanceof DustBall);
			boolean valid3 = (grid.getCell(vacuum2.getRow()+1, vacuum2.getColumn()) instanceof CleanHallway);
			boolean valid4 = (grid.getCell(vacuum2.getRow()+1, vacuum2.getColumn()) instanceof Dumpster);
			if (valid1 || valid2 || valid3 || valid4){
				//Move the vacuum
				grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), vacuum2.getUnder());
				vacuum2.moveTo(vacuum2.getRow()+1, (vacuum2.getColumn()));
				vacuum2.setUnder(grid.getCell(vacuum2.getRow(), vacuum2.getColumn()));
				//Set the new location of the vacuum to the same vacuum sprite
				grid.setCell(vacuum2.getRow(), vacuum2.getColumn(), vacuum2);			
				int prevScore = vacuum2.getScore();
				vacuum2.clean();
				if (vacuum2.getScore() == (prevScore + Constants.DUST_BALL_SCORE)){
					for (int i=0; i<dustBalls.size();i++){
						if ((dustBalls.get(i).getRow() == vacuum2.getRow())&&(dustBalls.get(i).getColumn() ==vacuum2.getColumn())){
							dustBalls.remove(i);
						}
					}
				}
			}
		}
	moveDustBalls();
	}

	public boolean gameOver(){
		boolean noDirt = true;
		for (int i = 0; i<grid.getNumRows(); i++){
			for (int j = 0; j<grid.getNumColumns(); j++){
				if ((grid.getCell(i, j) instanceof Dust)||(grid.getCell(i, j) instanceof DustBall)){
					noDirt = false;
					break;
				}
			}
		}
		return noDirt;
	}

	public char getWinner(){
		if (vacuum1.getScore() > vacuum2.getScore()){
			return Constants.P1;
		}
		else if (vacuum1.getScore() < vacuum2.getScore()){
			return Constants.P2;
		}
		else{
			return Constants.TIE;
		}
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

	private static void moveDustBalls(){
		for (int k = 0; k < dustBalls.size(); k++){
			boolean valid1 = (dustBalls.get(k).getCoordinates().equals(vacuum1.getCoordinates()));
			boolean valid2 = (dustBalls.get(k).getCoordinates().equals(vacuum2.getCoordinates()));
			if  (!valid1&&!valid2){
				int randomNum = random.nextInt((5 - 1) + 1) + 1;
				Dust dust = new Dust(Constants.DUST, dustBalls.get(k).getRow(), dustBalls.get(k).getColumn());
				if (randomNum == 1){
					if ((grid.getCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn()-1) instanceof CleanHallway)||
							(grid.getCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn()-1) instanceof Dust)){
						grid.setCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn(), dust);
						dustBalls.get(k).moveTo(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn()-1);
						grid.setCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn(), dustBalls.get(k));
					}
				}
				else if (randomNum == 2){
					if ((grid.getCell(dustBalls.get(k).getRow()-1, dustBalls.get(k).getColumn()) instanceof CleanHallway)||
							(grid.getCell(dustBalls.get(k).getRow()-1, dustBalls.get(k).getColumn()) instanceof Dust)){
						grid.setCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn(), dust);
						dustBalls.get(k).moveTo(dustBalls.get(k).getRow()-1, dustBalls.get(k).getColumn());
						grid.setCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn(), dustBalls.get(k));

					}
				}
				else if (randomNum == 3){
					if ((grid.getCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn()+1) instanceof CleanHallway)||
							(grid.getCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn()+1) instanceof Dust)){
						grid.setCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn(), dust);
						dustBalls.get(k).moveTo(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn()+1);
						grid.setCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn(), dustBalls.get(k));
					}
				}
				else if (randomNum == 4){
					if ((grid.getCell(dustBalls.get(k).getRow()+1, dustBalls.get(k).getColumn()) instanceof CleanHallway)||
							(grid.getCell(dustBalls.get(k).getRow()+1, dustBalls.get(k).getColumn()) instanceof Dust)){
						grid.setCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn(), dust);
						dustBalls.get(k).moveTo(dustBalls.get(k).getRow()+1, dustBalls.get(k).getColumn());
						grid.setCell(dustBalls.get(k).getRow(), dustBalls.get(k).getColumn(), dustBalls.get(k));
					}
				}
			}

		}
	}
}
