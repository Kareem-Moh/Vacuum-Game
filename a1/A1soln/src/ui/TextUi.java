/**
 * 
 */
package ui;
import game.VacuumGame;

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
	}


	public void launchGame(){
		System.out.println(game.getGrid());
	}

	public void displayWinner(){

	}
}
