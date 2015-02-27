package QueuesDeques;


import java.util.Iterator;


public class Deque<Item> implements Iterable<Item> {
	
	Itemhold first;
	Itemhold end;
	int size;
	public Deque(){
		//construct an empty deque
		Itemhold firstnode=new Itemhold();
		firstnode.next=null;
		first=firstnode;
		end=null;
		size=0;
	}
	public class Itemhold{
		Itemhold previous;
		Itemhold next;
		Item item;
	}
	public boolean isEmpty(){
		return size==0?true:false;
	}
	
	public int size(){
		return size;
	}

	public void addFirst(Item item){
		//add the item to the front
//		if(item==null) new NullPointerException().
		Itemhold item1=new Itemhold();
		item1.item=item;
		if(size==0) end=item1;
		item1.previous=first;
		item1.next=first.next;
		first.next=item1;
		size++;
	}
	
	public void addLast(Item item){
		//add the item to the end
		Itemhold item1=new Itemhold();
		item1.item=item;
		if(size==0){
			addFirst(item);
		}else{
			end.next=item1;
			item1.previous=end;
			item1.next=null;
			end=item1;
			size++;
		}
		
	}
	
	public Item removeFirst(){
		//remove and return the item from front
//		if(size==0) 				//throwException
		
		Itemhold item=first.next;
		first.next=item.next;
		item.next.previous=first;
		size--;
		return item.item;
	}
	public Item removeLast(){
		//remove and return the item from the end
//		if(size==0)					//throwException
		Itemhold item=end;
		end.previous.next=null;
		end=item.previous;
		size--;
		return item.item;
	}

	@Override
	public Iterator<Item> iterator() {
		//return an iterator over items in order from front to end
		// TODO Auto-generated method stub
	
		return new MyIterator();
	}
	class MyIterator implements Iterator<Item>{
		
		Itemhold cur;
		public MyIterator(){
			cur=first;
		}
		@Override
		public boolean hasNext() {
			// TODO Auto-generated method stub
			return cur.next==null?false:true;
		}

		@Override
		public Item next() {
			// TODO Auto-generated method stub
//			if(cur==null)				//throwException
			cur=cur.next;
			Item item=cur.item;
			
			return item;
		}

		@Override
		public void remove() {
			// TODO Auto-generated method stub
										//throwUnSorportedOperationException
		}
		
	}
	public static void main(String[] args){
		Deque<Integer> t=new Deque<Integer>();
		t.addFirst(1);
		t.addFirst(2);
		t.addFirst(3);
		t.addLast(9);
		t.addFirst(8);
		t.removeLast();
		t.removeFirst();
		t.addFirst(8);
		t.addLast(9);
		t.removeFirst();
//		t.removeFirst();
//		t.removeFirst();
//		t.removeFirst();
//		t.removeFirst();
//		Iterator it=t.iterator();
		for(Integer i : t){
			System.out.println(i.intValue());
		}
		System.out.println(t.size());
	}
}
