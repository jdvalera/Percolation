/*----------------------------------------------------------------
 *  Author:        John Valera
 *  Written:       3/3/2017
 *  Last updated:  3/3/2017
 *
 *  Compilation:   javac PercolationStats.java
 *  Execution:     java PercolationStats arg[0] arg[1]
 *  
 *  Does Monte Carlo simulation on percolation
 *
 *    
 *----------------------------------------------------------------*/
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
	
	private int gridSize;
	private Percolation p;
	private double [] trialRes;
	private int T;

	
	public PercolationStats(int n, int trials) {	
		checkBoundary(n,trials);
		
		int count = 0;
		
		gridSize = n*n;
		trialRes = new double[trials];
		T = trials;
		
		
		for(int i=0; i<trials; i++) {
			p = new Percolation(n);
			
			while(!p.percolates()) {
				int x;
				int y;
				
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
		return (T==1) ? Double.NaN : StdStats.stddev(trialRes);
		
	}
	
	// low endpoint of 95% confidence of percolation threshold
	public double confidenceLo() {
		return (mean()-((1.96*stddev())/Math.sqrt(T)));
	}
	
	// high endpoint of 95% confidence interval 
	public double confidenceHi() {
		return (mean()+((1.96*stddev())/Math.sqrt(T)));
	}
	
	private void checkBoundary(int x, int y) {
		if(x <= 0 || y <= 0 ) {
			throw new IndexOutOfBoundsException("out of bounds");
		}
		
	}

	public static void main(String[] args) {
		PercolationStats p = new PercolationStats(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
		System.out.println("mean = " + p.mean());
		System.out.println("stddev = " + p.stddev());
		System.out.println("95% confidence interval = " + "["+p.confidenceLo()+", "
				+ p.confidenceHi() + "]");
		

	}

}
