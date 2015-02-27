package QueuesDeques;
import com.algs4.stdlib.StdIn;

public class Subset {
	
	public static void main(String[] args){
//		String s= StdIn.readString();
		int k=Integer.valueOf(args[0]);
		RandomizedQueue<Character> rd=new RandomizedQueue<Character>();
		while (!StdIn.hasNextChar()){ 
			char s = StdIn.readChar();
			if(rd.size()<k){
				rd.enqueue(s);
			}else{
				rd.dequeue();
				rd.enqueue(s);
			}		
		}
		for(Character c:rd){
			System.out.println(c.charValue());
		}
	}
}