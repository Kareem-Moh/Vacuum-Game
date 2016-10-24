/**
 * 
 */
package game;
import java.util.Map;

/**
 * @author Kareem
 *
 */
public class MapGrid<T> extends Grid<T> {
	private Map<Integer, Map<Integer,T>> grid;
	
	
	/**
	 * @param grid
	 */
	public MapGrid(Map<Integer, Map<Integer, T>> grid) {
		super();
		this.grid = grid;
	}

	/* (non-Javadoc)
	 * @see game.Grid#getCell(int, int)
	 */
	@Override
	public T getCell(int row, int column) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see game.Grid#setCell(int, int, java.lang.Object)
	 */
	@Override
	public void setCell(int row, int column, T item) {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see game.Grid#getNumRows()
	 */
	@Override
	public int getNumRows() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see game.Grid#getNumColumns()
	 */
	@Override
	public int getNumColumns() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see game.Grid#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

}
