/**
 * 
 */
package ui;
import game.Constants;
import game.VacuumGame;

import java.util.Scanner;

/**
 * @author Kareem
 *
 */
public class TextUi implements Ui{

	private VacuumGame game;

	/**
	 * 
	 */
	public TextUi(VacuumGame game) {
		super();
		this.game = game;
	}


	public void launchGame(){
		// Prints the starting grid
		System.out.println(game.getGrid());
		// Opens a Scanner object to read user input
		Scanner reader = new Scanner(System.in);
		// Initialize the exit condition
		boolean gameOver = false;
		// Set a while loop that ends when the game is over
		while (!gameOver){
			// Tells the users to move
			System.out.println("Make the next Move!");
			// Reads the user's next input
			char userInput = reader.next().charAt(0);
			// Move the vacuum accordingly
			VacuumGame.move(userInput);
			// Print the new gird
			System.out.println(game.getGrid());
			// Check if the game is over
			gameOver = game.gameOver();
			}
		// Close the open Scanner
		reader.close();
		
	}

	public void displayWinner(){
		char winner;
		String Score;
		if (game.getWinner() == Constants.P1){
			winner = Constants.P1;
			Integer score = new Integer(game.getVacuumOne().getScore());
			Score = score.toString();
		}
		else {
			winner = Constants.P2;
			Integer score = new Integer(game.getVacuumTwo().getScore());
			Score = score.toString();
		}
		System.out.println("Player " + winner + " is the winner! You finished with " + Score + " points!");
	}
}
