package Percolate;
public class PercolationStats {
	int N,T;
	double[] x;
	double sum=0; 
	double u;
	double sd;
	double confidenceLo;
	double confidenceHi;
	Percolation percolation;
	public PercolationStats(int N, int T){	// perform T independent experiments on an N-by-N grid
		this.N=N;
		this.T=T;
		x=new double[T];
		for(int i=0;i<T;i++){
			percolation=new Percolation(N);
			x[i]=percolation.generateFraction();
		}
	}    	 
	public double mean(){					// sample mean of percolation threshold	
		for(int i=0;i<x.length;i++){
		   sum+=x[i];
		}
		u=sum/(double)T;
		return u;
	}                      
	public double stddev() {					// sample standard deviation of percolation threshold
		double sum2=0;
		for(int i=0;i<x.length;i++){
		   sum2+=((x[i]-u)*(x[i]-u));
		}
		sd=Math.sqrt(sum2/(double)(T-1));
		return sd;
	}                  
	public double confidenceLo(){			// low  endpoint of 95% confidence interval
		confidenceLo=u-1.96*sd/Math.sqrt((double)T);
		return confidenceLo;
	}              
	public double confidenceHi(){			// high endpoint of 95% confidence interval
		confidenceHi=u+1.96*sd/Math.sqrt((double)T);
		return confidenceHi;
	}              

	public static void main(String[] args){	// test client (described below)
		int N=Integer.valueOf(args[0]);
		int T=Integer.valueOf(args[1]);
		PercolationStats percolationStats = new PercolationStats(N,T);
		System.out.printf("mean     = %.16f\n",percolationStats.mean());
		System.out.printf("stddev   = %.16f\n",percolationStats.stddev());
		System.out.printf("95%% confidence interval = %.16f, %.16f\n",percolationStats.confidenceLo(),percolationStats.confidenceHi());
	}    
}