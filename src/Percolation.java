import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	WeightedQuickUnionUF uf;
	int gridSize;
	// 0 - closed, 1 - open
	byte[] openSites;
	int offset = 1;
	byte closed = 0;
	byte open = 1;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		
		uf = new WeightedQuickUnionUF(n+offset);
		openSites = new byte[n+offset];
		
		for (int i=0; i<(n+offset); i++) {
			openSites[i] = closed;
		}
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
