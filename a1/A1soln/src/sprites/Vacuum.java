/**
 * Class to represent a Vacuum
 */
package sprites;

import game.Constants;

/**
 * @author Kareem
 *
 */
public class Vacuum extends Sprite {
	private int score;
	private int capacity;
	private int fullness;
	private Sprite under;
	private Sprite ch = new CleanHallway(Constants.CLEAN, this.getRow(), this.getColumn());

	/**
	 * @param symbol
	 * @param row
	 * @param column
	 * @param score
	 * @param capacity
	 * @param fullness
	 * @param under
	 */
	public Vacuum(char symbol, int row, int column, int score, int capacity, int fullness, Sprite under) {
		super(symbol, row, column);
		this.score = score;
		this.capacity = capacity;
		this.fullness = fullness;
		this.under = under;
		capacity = Constants.CAPACITY;
		fullness = Constants.EMPTY;

	}
	/**
	 * Switches the coordinates of the Vacuum object accordingly
	 * Cleans dust if possible
	 * @return void
	 */
	public void moveTo(int row, int column){
		this.updateCoordinates(row, column);
		if (this.getUnder() instanceof Dirt){
			this.clean();
		}
		else if (this.getUnder() instanceof Dumpster){
			this.empty();
		}
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Sets the score
	 */
	private void setScore(int score){
		this.score = score;
	}
	/**
	 * @return the under
	 */
	public Sprite getUnder() {
		return under;
	}
	/**
	 * @param under the under to set
	 */
	public void setUnder(Sprite under) {
		this.under = under;
	}
	/**
	 * @return Set the score privately
	 */
	private void setFullness(int fullness){
		this.fullness = fullness;
	}

	/**
	 * Empties the vacuum of its contents, reduces fullness to zero
	 */
	public void empty(){
		this.setFullness(Constants.EMPTY);
	}

	/**
	 * If the vacuum is not full, this method cleans the 
	 * dustball/dust and adds to the score of the player
	 * @return True if the vacuum successfully cleaned, False ow
	 */
	public boolean clean(){
		//Checks if the vacuum is already full
		if (this.fullness >= capacity){
			//If so do nothing and return false
			return false;
		}
		//Otherwise
		else{
			//Checks if the 
			if (this.getUnder().getSymbol() == Constants.DUST){
				this.setScore(this.score + Constants.DUST_SCORE);
				this.fullness++;
				this.setUnder(ch);
			}
			else if(this.getUnder().getSymbol() == Constants.DUST_BALL){
				this.setScore(this.score + Constants.DUST_BALL_SCORE);
				this.fullness++;
				this.setUnder(ch);
			}
			else if(this.getUnder().getSymbol() == Constants.DUMPSTER){
				this.setFullness(Constants.EMPTY);
			}
			return true;
		}
	}
}	

