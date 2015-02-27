package QueuesDeques;

import java.util.Iterator;
import java.util.Random;



public class RandomizedQueue<Item> implements Iterable<Item> {

	private int size;
	Itemhold<Item>[] items;
	
	private class Myiterator implements Iterator{
		
		Itemhold<Item>[] its=new Itemhold[size*2];		//double size 
		int mark=0;										//num already pulled
		public Myiterator(){
			
			for(int i=0;i<size*2;i++)
				its[i]=null;
			for(int i=0;i<size;i++){
				int tar=(int) (Math.random()*size*2);
				while(its[tar]!=null){
					tar=(int) (Math.random()*size*2);
				}
				its[tar]=items[i];
			}
			
				
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return mark==size?false:true;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
			int i=mark;
			while(its[i]==null)
				i++;		
			mark++;
			return its[i++].item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
			
		}
		
	}
	@Override
	public Iterator<Item> iterator() {
		// TODO Auto-generated method stub
		return new Myiterator();
	}
	public RandomizedQueue(){
		items=new Itemhold[1];
		size=0;
	}
	public boolean isEmpty(){
		return size==0?true:false;
	}
	public int size(){
		return size;
	}
	public void enqueue(Item item){
		Itemhold<Item> ih=new Itemhold<Item>();
		ih.item=item;
		if(size==items.length)
			doubleCapacity();
		size++;
		items[size-1]=ih;
		
	}
	public Item dequeue(){
		//remove and return a random item
//		int tar=randomIndependent(size);
		int tar=(int) (Math.random()*size);
		Itemhold<Item> item=items[tar];
		if(size<items.length/4)		//quarter?
			reduceCapacity();
		items[tar]=items[size-1];
		size--;
		return item.item;
	}
	public Item sample(){
		//return (do not remove) a random item
//		return items[randomIndependent(size)].item;
		return items[(int) (Math.random()*size)].item;
	}

	public class Itemhold<Item>{

		Itemhold<Item> next;
		Item item;
		int mark;
	}
	public void doubleCapacity(){
		Itemhold[] items1=new Itemhold[items.length*2];
		for(int i=0;i<items.length;i++)
			items1[i]=items[i];	
		items=items1;
	}
	public void reduceCapacity(){
		Itemhold[] items1=new Itemhold[items.length/2];
		for(int i=0;i<items.length;i++)
			items1[i]=items[i];	
		items=items1;
	} 
//	public int randomIndependent(int size){
//		Random ran=new Random();
//		ran.setSeed(System.currentTimeMillis());
//		return (int) (ran.nextDouble()*size);
//	}
	public static void main(String[] args){
		RandomizedQueue<Integer> rq=new RandomizedQueue<Integer>();
		rq.enqueue(1);
		rq.enqueue(2);
		rq.enqueue(3);
		rq.enqueue(4);
		rq.enqueue(5);
		
//		rq.dequeue();
		for(Integer i:rq)
			System.out.println(rq.sample());
//			System.out.println(i.intValue());
	}
}
