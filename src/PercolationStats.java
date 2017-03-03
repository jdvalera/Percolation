import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	int gridSize;
	Percolation p;
	double [] trialRes;
	double mean;
	double stddev;
	double cL;
	double cH;
	int T;
	int length;
	
	// perform trials independent experiments on an n-by-n grid
	public PercolationStats(int n, int trials) {	
		int count = 0;
		
		gridSize = n*n;
		trialRes = new double[gridSize];
		trials = T;
		p = new Percolation(gridSize);
		
		for(int i=0; i<trials; i++) {
			
			while(!p.percolates()) {
				int x; 
				int y;
				
				do {
					x = StdRandom.uniform(1, n);
					y = StdRandom.uniform(1, n);
				} while(p.isOpen(x, y));
				
				p.open(x, y);
				count++;
			}
			
			trialRes[i] = count;
			count = 0;
		}
	}
	
	// sample mean of percolation threshold
	public double mean() {
		return StdStats.mean(trialRes);
		
	}
	
	// sample standard deviation of percolation threshold
	public double stddev() {
		return (T==1) ? 1 : StdStats.stddev(trialRes);
		
	}
	
	// low endpoint of 95% confidence of percolation threshold
	public double confidenceLo() {
		return (mean-((1.96*stddev)/Math.sqrt(T)));
	}
	
	// high endpoint of 95% confidence interval 
	public double confidenceHi() {
		return 0;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
