import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	WeightedQuickUnionUF uf;
	int gridSize;
	int length;
	// 0 - Closed,
	// 1 - Open
	// 2 - Full
	byte[] openSites;
	int virtualSites = 2;
	int offset = 1;
	byte closed = 0;
	byte open = 1;
	byte full = 2;
	int vTop = gridSize - 1;
	int vBot = gridSize;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		
		gridSize = (n*n) + virtualSites;
		length = n;
		
		uf = new WeightedQuickUnionUF(gridSize);
		openSites = new byte[gridSize];
		
		for (int i=0; i<gridSize - virtualSites; i++) {
			openSites[i] = closed;
		}
		
		openSites[vTop] = open;
		openSites[vBot] = open;
			
	}
	
	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		return false;
		
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		return false;
		
	}
	
	// number of open sites
	public int numberOfOpenSites() {
		return 0;
		
	}
	
	// does the system percolate?
	public boolean percolates() {
		return false;
		
	}
	
	private int xyTo1D(int x, int y) {
		return ((x-offset)%length)+(length*(y-offset));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
