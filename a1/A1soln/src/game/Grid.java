/**
 * 
 */
package game;

/**
 * @author Kareem
 *
 */
public abstract class Grid<T> {
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Grid [getNumRows()=" + getNumRows() + ", getNumColumns()=" + getNumColumns() + ", hashCode()="
				+ hashCode() + ", getClass()=" + getClass() + ", toString()=" + super.toString() + "]";
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj);
	}
	
	public abstract T getCell(int row, int column);
	public abstract void setCell(int row, int column, T item);
	public abstract int getNumRows();
	public abstract int getNumColumns();
	public abstract int hashCode();
	
}
