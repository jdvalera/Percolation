/*----------------------------------------------------------------
 *  Author:        John Valera
 *  Written:       3/2/2017
 *  Last updated:  3/3/2017
 *
 *  Compilation:   javac Percolation.java
 *  Execution:     java Percolation arg[0]
 *  
 *  Creates a NxN grid and allows for opening cells and checking for
 *  percolation, if cell is open, and if cell is full
 *
 *    
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	private WeightedQuickUnionUF uf;
	private WeightedQuickUnionUF uf2;
	private int gridSize;
	private int length;
	private boolean[] openSites;
	private int virtualSites = 2;
	private int offset = 1;
	private boolean closed = false;
	private boolean open = true;
	private int vTop;
	private int vBot;
	private int openCount;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		
		if(n <= 0) throw new IllegalArgumentException("out of bounds");
		
		gridSize = (n*n) + virtualSites;
		length = n;
		vTop = gridSize - 2;
		vBot = gridSize - 1;
		
		uf = new WeightedQuickUnionUF(gridSize);
		uf2 = new WeightedQuickUnionUF(gridSize);
		
		openSites = new boolean[gridSize];
		
		for (int i=0; i<gridSize; i++) {
			openSites[i] = closed;
		}
		
		openSites[vTop] = open;
		openSites[vBot] = open;
			
	}
	
	// open site (row, col) if it is not open already
	public void open(int row, int col) {
		
		
		checkBoundary(row, col);
		
		int cell = xyTo1D(row,col);
		int cellL = xyTo1D(row, col-1);
		int cellR = xyTo1D(row, col+1);
		int cellT = xyTo1D(row-1, col);
		int cellB = xyTo1D(row+1, col);
		int top = row-1;
		int bot = row+1;
		int left = col-1;
		int right = col+1;
		
		if (openSites[cell] != open) {
			  
			openSites[cell] = open;
			openCount++;
			   
		   // if Top
		   if(row == 1) {
			   uf.union(cell, vTop);
			   uf2.union(cell, vTop);
			   //openSites[cell] = full;
		   } else {
		      if(isOpen(top,col)) {
		    	  uf.union(cellT, cell);
		    	  uf2.union(cellT, cell);
		      }
		   }
		    
		   // If not Left
		   if(col != 1) {
		      if(isOpen(row,left)) {
		    	  uf.union(cellL, cell);
		    	  uf2.union(cellL, cell);
		      }
		   }
		    
		   //if not Right
		   if(col != length) {
		      if(isOpen(row,right)) {
			    	  uf.union(cellR, cell);
			    	  uf2.union(cellR, cell);
			      }
		   }
		   
		   //if Bottom
		   if(row == length) {
			   uf.union(cell, vBot);
		   } else {
			   if(isOpen(bot,col)) {
			    	  uf.union(cellB, cell);
			    	  uf2.union(cellB, cell);
			      }
		   }
		}
		
	}
	
	// is site (row, col) open?
	public boolean isOpen(int row, int col) {
		checkBoundary(row, col);
		return openSites[xyTo1D(row,col)] == open;
		
	}
	
	// is site (row, col) full?
	public boolean isFull(int row, int col) {
		checkBoundary(row, col);
		//return openSites[xyTo1D(row,col)] == full;
		int cell = xyTo1D(row,col);
		return uf2.connected(cell, vTop);
		
	}
	
	// number of open sites
	public int numberOfOpenSites() {
		return openCount;
		
	}
	
	// does the system percolate?
	public boolean percolates() {
		return uf.connected(vTop, vBot);
		
	}
	
	private void checkBoundary(int x, int y) {
		if(x <= 0 || x > length || y <= 0 || y > length) {
			throw new IndexOutOfBoundsException("out of bounds");
		}
		
	}
	
	private int xyTo1D(int x, int y) {
		return ((x-offset)%length)+(length*(y-offset));
	}

	public static void main(String[] args) {
		Percolation p = new Percolation(3);
		System.out.println(p.numberOfOpenSites());
		p.open(1,1);
		System.out.println(p.numberOfOpenSites());
		p.open(2,1);
		p.open(3,1);
		System.out.println(p.percolates());

	}

}
