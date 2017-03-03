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
		trialRes = new double[trials];
		T = trials;
		
		
		for(int i=0; i<trials; i++) {
			p = new Percolation(n);
			
			while(!p.percolates()) {
				int x;
				int y;
				
				//int x = StdRandom.uniform(n)+1; 
				//int y = StdRandom.uniform(n)+1;
				
				do {
					x = StdRandom.uniform(n)+1; 
					y = StdRandom.uniform(n)+1; 
				} while(p.isOpen(x, y) && !p.percolates());
				
				
				p.open(x, y);
				count++;
				
			}
			
			trialRes[i] = (double) count/gridSize;
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
		return (mean()-((1.96*stddev())/Math.sqrt(T)));
	}
	
	// high endpoint of 95% confidence interval 
	public double confidenceHi() {
		return (mean()+((1.96*stddev())/Math.sqrt(T)));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		PercolationStats p = new PercolationStats(200, 100);
		System.out.println(p.mean());
		System.out.println(p.stddev());
		System.out.println(p.confidenceLo());
		System.out.println(p.confidenceHi());

	}

}
