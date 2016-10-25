/**
 * 
 */
package sprites;

/**
 * @author Kareem
 *
 */
public class DustBall extends Sprite {
	/**
	 * @param symbol
	 * @param row
	 * @param column
	 */
	public DustBall(char symbol, int row, int column) {
		super(symbol, row, column);
	}

	/**
	 * Randomly moves the dust ball one unit across the ball
	 */
	public void moveTo(int row, int column){
		this.updateCoordinates(row, column);
	}
}
