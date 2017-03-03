import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	
	WeightedQuickUnionUF uf;
	WeightedQuickUnionUF uf2;
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
	int vTop;
	int vBot;
	
	// create n-by-n grid, with all sites blocked
	public Percolation(int n) {
		
		gridSize = (n*n) + virtualSites;
		length = n;
		vTop = gridSize - 2;
		vBot = gridSize - 1;
		
		uf = new WeightedQuickUnionUF(gridSize);
		uf2 = new WeightedQuickUnionUF(gridSize);
		
		openSites = new byte[gridSize];
		
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
		boolean isFUll = isFull(row,col);
		
		if (openSites[cell] != open) {
			  
			openSites[cell] = open;
			   
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
		return uf.count();
		
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
		// TODO Auto-generated method stub
		Percolation p = new Percolation(3);
		p.open(1,1);
		p.open(2,1);
		p.open(3,1);
		System.out.println(p.percolates());

	}

}
