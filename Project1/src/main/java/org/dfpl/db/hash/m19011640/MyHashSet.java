package org.dfpl.db.hash.m19011640;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class MyHashSet implements Set<Integer> {
	//Field
	private MyThreeWayBTree[] hashTable;
	
	//Constructor
	public MyHashSet(){
		hashTable = new MyThreeWayBTree[3];
		for(int i = 0; i < hashTable.length; i++) {
			hashTable[i] = new MyThreeWayBTree();
		}
	}

	//Method
	//-------------------------------------
	//Getting size and consistent of key
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
		if(size() == 0) return true;
		return false;
	}

	@Override
	public boolean contains(Object o) {
		if(o instanceof Number) {
			Number key = (Number)o;
			return hashTable[key.intValue()%3].contains(key);
		}
		return false;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object val : c) {
			if(!contains(val)) return false;
		}
		return true;
	}
	//-------------------------------------
	//Returns Iterator and convertion to array
	//Internal class for iterator
	private class ItrHashSet<E> implements Iterator<E>{
		//Field
		Iterator<E>[] bTreeItr = new Iterator[hashTable.length];
		int index = 0;
		
		//Constructor
		public ItrHashSet(){
			for(int i = 0; i < hashTable.length; i++) {
				bTreeItr[i] = (Iterator<E>)hashTable[i].iterator();
			}
		}
		
		//Method
		@Override
		public boolean hasNext() {
			if(index > 2) return false;
			if(bTreeItr[index].hasNext()) return true;
			else {
				if(index + 1 < 3) {
					if(bTreeItr[++index].hasNext()) return true;
				}
			}
			return false;
		}

		@Override
		public E next() {
			return bTreeItr[index].next();
		}
		
		@Override
		public void remove() {
			bTreeItr[index].remove();
		}
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new ItrHashSet<Integer>();
	}

	@Override
	public Object[] toArray() {
		int size = 0;
		for(int i = 0; i < hashTable.length; i++) size += hashTable[i].size();
		
		Object[] tmp = new Object[size];
		for(int i = 0; i < hashTable.length; i++) {
			Object[] tmpBTree = hashTable[i].toArray();
			for(int j = 0; j < tmpBTree.length; i++) {
				tmp[tmp.length] = tmpBTree[j];
			}
		}
		return tmp;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		int size = 0;
		for(int i = 0; i < hashTable.length; i++) size += hashTable[i].size();
		
		for(int i = 0; i < hashTable.length; i++) {
			Object[] tmpBTree = hashTable[i].toArray();
			for(int j = 0; j < tmpBTree.length; i++) {
				a[a.length] = (T)tmpBTree[j];
			}
		}
		return a;
	}
	//-------------------------------------
	//Addition and removal
	@Override
	public boolean add(Integer e) {
		return hashTable[e%3].add(e);
	}

	@Override
	public boolean remove(Object o) {
		if(o instanceof Number) {
			Number key = (Number)o;
			return hashTable[key.intValue()%3].remove(key);
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		int cnt = 0;
		for(Integer val : c) {if(add(val)) cnt++;}
		return cnt > 0 ? true : false;
	}
	
	@Override
	public boolean removeAll(Collection<?> c) {
		int cnt = 0;
		for(Object val : c) {if(remove(val)) cnt++;}
		return cnt > 0 ? true : false;
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		List<Integer>[] keyList = new ArrayList[3];
		for(Object key : c) {
			if(key instanceof Number) {
				Number val = (Number)key;
				keyList[val.intValue()%3].add(val.intValue());
			}
			return false;
		}
		
		int i = 0, cnt = 0;
		for(MyThreeWayBTree bTree : hashTable) {
			if(bTree.retainAll(keyList[i++])) cnt++;
		}
		return cnt > 0 ? true : false;
	}

	@Override
	public void clear() {
		for(int i = 0; i < hashTable.length; i++)
			hashTable[i].clear();
	}
	//-------------------------------------
}
