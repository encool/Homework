package Percolate;
//import WeightedQuickUnionUF;

public class Percolation {
	int[][] sites;							//mark isOPEN or not
	WeightedQuickUnionUF wq;
	int N;
	int vUp,vBot;							//Virual dot for convenience
	int openedCount;
	public Percolation(int N){              // create N-by-N grid, with all sites blocked
		openedCount=0;
		sites=new int[N][N];
		for(int i=0;i<N*N-1;i++){
			sites[i/N][i%N]=0;
		}
		this.N=N;
		wq=new WeightedQuickUnionUF(N*N+2);
		vUp=N*N;
		vBot=N*N+1;
		for(int i=0;i<N;i++){
			wq.union(vUp, i);
			wq.union(vBot, N*(N-1)+i);
		}
	}
	// open site (row i, column j) if it is not open already
	public void open(int i, int j){
		if(sites[i][j]==1)
			return;
		sites[i][j]=1;
		openedCount++;
		int n=N*j+i;
		int up=j>0?N*(j-1)+i:-1;
		int bot=j<N-2?N*(j+1)+i:-1;
		int lf=i>0?N*j+i-1:-1;
		int rt=i<N-2?N*j+i+1:-1;
		if(up!=-1&&isOpen(i,j-1)){
			wq.union(n, up);
		}
		if(lf!=-1&&isOpen(i-1,j)){
			wq.union(n, lf);
		}
		if(bot!=-1&&isOpen(i,j+1)){
			wq.union(n, bot);
		}
		if(rt!=-1&&isOpen(i+1,j)){
			wq.union(n, rt);
		}
	}  
	// is site (row i, column j) open?
	public boolean isOpen(int i, int j){
		return sites[i][j]==1?true:false;
	}   
	// is site (row i, column j) full?
	public boolean isFull(int i, int j){
	   return wq.connected(N*j+i, vUp);
	}  
	// does the system percolate?
	public boolean percolates(){
	   return wq.connected(vUp, vBot);
	}             
	public double generateFraction(){
		int unOpened=N*N-openedCount;
		Percolation percolation=new Percolation(N);
		while(unOpened>0){
			int i=(int) (Math.random()*N*N);
			if(sites[i/N][i%N]==1)
				continue;
			percolation.open(i/N,i%N);
			if(percolation.percolates()){
//				System.out.println((double)percolation.openedCount/(double)(N*N));
				return (double)percolation.openedCount/(double)(N*N);
				
			}
		}
		return -1;
	}
	// test client (optional)
	public static void main(String[] args ){
		
		int N=Integer.valueOf(args[0]);
		Percolation percolation=new Percolation(N);
		for(int j=0;j<10000;j++){
			int i=(int) (Math.random()*N*N);
			percolation.open(i/N,i%N);
			if(percolation.percolates()){
				System.out.println("threshold is "+percolation.openedCount);
				System.out.println("ratio is"+(double)percolation.openedCount/(double)(N*N));
				break;
			}
			
		}

	}  
}