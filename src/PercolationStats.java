
public class PercolationStats {
	
	int gridSize;
	Percolation p;
	double [] trialRes;
	
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials) {
		
		gridSize = n*n;
		trialRes = new double[gridSize];
		p = new Percolation(gridSize);
		
		for(int i=0; i<trials; i++) {
			
		}
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return 0;
		
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return 0;
		
	}
	
	// low endpoint of 95% confidence of percolation threshold
	public double confidenceLo() {
		return 0;
	}
	
	// high endpoint of 95% confidence interval 
	public double confidenceHi() {
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
