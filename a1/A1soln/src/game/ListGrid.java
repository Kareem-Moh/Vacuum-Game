/**
 * 
 */
package game;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Kareem
 *
 */
public class ListGrid<T> extends Grid<T> {
	private List<List<T>> grid;
	private int numRows;
	private int numColumns;
	private T t;


	/**
	 * @param grid
	 * @param numRows
	 * @param numColumns
	 */
	public ListGrid(int numRows, int numColumns) {
		super();
		this.numRows = numRows;
		this.numColumns = numColumns;
		this.grid = new ArrayList<List<T>>();
		for (int i = 0; i<numRows; i++){
			List<T> row = new ArrayList<T>();
			for (int j = 0; j<numColumns; j++){
				row.add(j, t);
			}
			this.grid.add(i, row);
		}
	}

	/* (non-Javadoc)
	 * @see game.Grid#getCell(int, int)
	 */
	@Override
	public T getCell(int row, int column) {
		return (grid.get(row)).get(column);
	}

	/* (non-Javadoc)
	 * @see game.Grid#setCell(int, int, java.lang.Object)
	 */
	@Override
	public void setCell(int row, int column, T item) {
		(grid.get(row)).add(column, item);
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
}
