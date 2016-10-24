/**
 * 
 */
package sprites;

/**
 * @author Kareem
 *A class to represent Sprites
 */
public abstract class Sprite {
	
	private char symbol;
	private int row;
	private int column;
	
	/**
	 * @param symbol
	 * @param row
	 * @param column
	 */
	public Sprite(char symbol, int row, int column) {
		super();
		this.symbol = symbol;
		this.row = row;
		this.column = column;
	}

	/**
	 * @return the symbol
	 */
	public char getSymbol() {
		return symbol;
	}

	/**
	 * @return the row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column
	 */
	public int getColumn() {
		return column;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + column;
		result = prime * result + row;
		result = prime * result + symbol;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj){
		// See if the user inputs anything other than a Sprite object
		try{
			//Check if obj and self share the same character representation adn coordinates
			boolean symbolMatch = (this.getSymbol() == ((Sprite) obj).getSymbol());
			boolean rowMatch = (this.getRow() == ((Sprite) obj).getRow());
			boolean colMatch = (this.getColumn() == ((Sprite) obj).getColumn());
			
			return ((rowMatch == colMatch) & symbolMatch);
		}
		// If obj can't use .getSymbol(), therefore isn't a Sprite
		catch (Exception e){
			return false;
		}
	}

	/**
	 * @param row the row to set
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * @param column the column to set
	 */
	public void setColumn(int column) {
		this.column = column;
	}
}
