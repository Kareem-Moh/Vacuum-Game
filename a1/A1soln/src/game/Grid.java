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
		if (obj instanceof Grid){
			boolean numRowsBool = (this.getNumRows() == ((Grid) obj).getNumRows());
			boolean numColsBool = (this.getNumColumns() == ((Grid) obj).getNumColumns());
			boolean sameSprites = true;
			for (int i = 1; i<this.getNumRows(); i++){
				for (int j = 1; j<this.getNumColumns(); j++){
					if (this.getCell(i, j) != ((Grid) obj).getCell(i,j)){
						sameSprites = false;
						break;
					}
				}
			}
			return (numRowsBool && numColsBool && sameSprites);
		}
		else {
			return false;
		}
	}
	
	public abstract T getCell(int row, int column);
	public abstract void setCell(int row, int column, T item);
	public abstract int getNumRows();
	public abstract int getNumColumns();
	public abstract int hashCode();
	
}
