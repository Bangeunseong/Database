package org.dfpl.db.hash.m19011640;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class MyHashSet implements Set<Integer> {
	//Field
	private MyThreeWayBTree[] hashTable;
	
	//Constructor
	public MyHashSet(){
		hashTable = new MyThreeWayBTree[3];
	}

	//Method
	@Override
	public int size() {
		int size = 0;
		for(MyThreeWayBTree tree : hashTable) {
			size += tree.size();
		}
		return size;
	}

	@Override
	public boolean isEmpty() {
		if(size() == 0) return false;
		return true;
	}

	@Override
	public boolean contains(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object[] toArray() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Integer e) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean remove(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}

}
