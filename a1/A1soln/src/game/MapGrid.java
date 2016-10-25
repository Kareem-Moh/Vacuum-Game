/**
 * 
 */
package game;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Kareem
 *
 */
public class MapGrid<T> extends Grid<T> {
	private Map<Integer, Map<Integer,T>> grid;
	private int numRows;
	private int numColumns;
	private T t;

	/**
	 * @param grid
	 * @param numRows
	 * @param numColumns
	 */
	public MapGrid(int numRows, int numColumns) {
		super();
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.grid = new HashMap<Integer, Map<Integer, T>>();
		for (int i = 0; i<numRows; i++){
			Map<Integer, T> row = new HashMap<Integer, T>();
			for (int j = 0; j<numColumns; j++){
				row.put(j, t);
			}
			this.grid.put(i, row);
		}
	}

	/* (non-Javadoc)
	 * @see game.Grid#getCell(int, int)
	 */
	@Override
	public T getCell(int row, int column) {
		return this.grid.get(row).get(column);

	}

	/* (non-Javadoc)
	 * @see game.Grid#setCell(int, int, java.lang.Object)
	 */
	@Override
	public void setCell(int row, int column, T item) {
		grid.get(row).put(column, item);
	}

	/* (non-Javadoc)
	 * @see game.Grid#getNumRows()
	 */
	@Override
	public int getNumRows() {
		return numRows;
	}

	/* (non-Javadoc)
	 * @see game.Grid#getNumColumns()
	 */
	@Override
	public int getNumColumns() {
		return numColumns;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grid == null) ? 0 : grid.hashCode());
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
		MapGrid other = (MapGrid) obj;
		if (grid == null) {
			if (other.grid != null)
				return false;
		} else if (!grid.equals(other.grid))
			return false;
		return true;
	}

}
