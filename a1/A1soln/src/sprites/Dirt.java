/**
 * 
 */
package sprites;

/**
 * @author Kareem
 *
 */
public abstract class Dirt extends Sprite {
	private int value;

	/**
	 * @param symbol
	 * @param row
	 * @param column
	 * @param value
	 */
	public Dirt(char symbol, int row, int column, int value) {
		super(symbol, row, column);
		this.value = value;
	}

	/**
	 * @return the value
	 */
	public int getValue() {
		return value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + value;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dirt other = (Dirt) obj;
		if (value != other.value)
			return false;
		return true;
	}
}
