package org.dfpl.db.hash.m19011640;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NavigableSet;
import java.util.SortedSet;

@SuppressWarnings("unused")
public class MyThreeWayBTree implements NavigableSet<Integer> {
	//Field
	public MyThreeWayBTreeNode root;
	
	//Constructor
	MyThreeWayBTree(){root = new MyThreeWayBTreeNode();}
	
	//Method
	//-----------------------------------
	//Getting first and last key of BTree
	@Override
	public Integer first() {
		MyThreeWayBTreeNode base = root;
		while(!base.getChildrenList().isEmpty()) {
			base = base.getChildrenList().get(0);
		}
		return base.getKeyList().get(0);
	}

	@Override
	public Integer last() {
		MyThreeWayBTreeNode base = root;
		while(!base.getChildrenList().isEmpty()) {
			base = base.getChildrenList().get(base.getChildrenList().size() - 1);
		}
		return base.getKeyList().get(base.getKeyListSize() - 1);
	}
	//------------------------------------
	//Getting BTreeSize
	public int getSize(MyThreeWayBTreeNode base) {
		if(base.getChildrenList().isEmpty()) return base.getKeyListSize();
		
		int cnt = base.getKeyListSize();
		for(MyThreeWayBTreeNode node : base.getChildrenList()) {
			cnt += getSize(node);
		}
		return cnt;
	}
	
	@Override
	public int size() {return getSize(root);}
	//-------------------------------------
	//Getting true value when BTree is empty
	@Override
	public boolean isEmpty() {
		if(root.getKeyList().isEmpty()) return true;
		return false;
	}
	//-------------------------------------
	//Getting true value when BTree has Object o(which is Key)
	@Override
	public boolean contains(Object o) {
		MyThreeWayBTreeNode base = root;
		if(o instanceof Number) {
			Number ins = (Number)o;
			while(true) {
				int i = 0;
				for(Integer key : base.getKeyList()) {
					if(ins.intValue() < key) break;
					else if(ins.intValue() == key) return true;
					else i++;
				}
				try {base = base.getChildrenList().get(i);}
				catch(IndexOutOfBoundsException e) {return false;}
			}
		}
		return false;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		for(Object val : c) {if(!contains(c)) return false;}
		return true;
	}
	//---------------------------------------
	//Getting Array value from BTree
	public <T> int getKeyValue(MyThreeWayBTreeNode base, T[] tmp, int index) throws ClassCastException{
		//If base is leaf, get all keys
		if(base.getChildrenList().isEmpty()) {
			int ind = index;
			for(Integer val : base.getKeyList()) {
				try {tmp[ind++] = (T)val;}
				catch (ClassCastException e) {throw new ClassCastException("Unable to cast!");}
				catch (IndexOutOfBoundsException e) {return ind;}
			}
			return ind;
		}
		
		//Goes to first children
		int ind = getKeyValue(base.getChildrenList().get(0), tmp, index);
		
		//Get firstKey of base keyList
		tmp[ind++] = (T)base.getKeyList().get(0);
		
		//Goes to second children
		ind = getKeyValue(base.getChildrenList().get(1), tmp, ind);
		
		//Get secondKey if it exists and Goes to third children
		try {
			tmp[ind++] = (T)base.getKeyList().get(1);
			ind = getKeyValue(base.getChildrenList().get(2), tmp, ind);
		}
		catch(IndexOutOfBoundsException e) {return ind - 1;}
		
		return ind;
	}
	
	@Override
	public Object[] toArray() {
		Object[] tmp = new Object[size()];
		try {
			getKeyValue(root, tmp, 0);
			return tmp;
		}
		catch(ClassCastException e) {return null;}
	}

	@Override
	public <T> T[] toArray(T[] a) {
		try {
			getKeyValue(root, a, 0);
			return a;
		}
		catch (ClassCastException e) {return null;}
	}
	//-----------------------------------------
	//Add element
	@Override
	public boolean add(Integer e) {
		if(contains(e)) return false;
		
		MyThreeWayBTreeNode base = root;
		while(!base.getChildrenList().isEmpty()) {
			int i = 0;
			for(Integer key : base.getKeyList()) {
				if(e.intValue() < key.intValue()) break;
				i++;
			}
			try {base = base.getChildrenList().get(i);}
			catch(IndexOutOfBoundsException exp) {break;}
		}
		
		base.setKey(e);
		
		if(base.getKeyListSize() > 2) {
			while(base.getKeyListSize() > 2) {
				MyThreeWayBTreeNode newRoot = new MyThreeWayBTreeNode();
				newRoot.setKey(base.getKeyList().get(1));
				newRoot.setParent(base.getParent());
				
				MyThreeWayBTreeNode node_1 = new MyThreeWayBTreeNode();
				node_1.setKey(base.getKeyList().get(0));
				node_1.setParent(newRoot);
				
				MyThreeWayBTreeNode node_2 = new MyThreeWayBTreeNode();
				node_2.setKey(base.getKeyList().get(2));
				node_2.setParent(newRoot);
				
				if(!base.getChildrenList().isEmpty()) {
					node_1.setChildren(base.getChildrenList().get(0));
					node_1.setChildren(base.getChildrenList().get(1));
					node_1.getChildrenList().stream().forEach(node->{
						node.setParent(node_1);
					});
					node_2.setChildren(base.getChildrenList().get(2));
					node_2.setChildren(base.getChildrenList().get(3));
					node_2.getChildrenList().stream().forEach(node->{
						node.setParent(node_2);
					});
				}
				
				newRoot.setChildren(node_1);
				newRoot.setChildren(node_2);
				
				MyThreeWayBTreeNode parent = base.getParent();
				if(parent == null){root = newRoot; break;}
				else {
					parent.setKey(newRoot.getKeyList().get(0));
					node_1.setParent(parent);
					node_2.setParent(parent);
					parent.setChildren(node_1);
					parent.setChildren(node_2);
					
					int j = 0;
					for(MyThreeWayBTreeNode node : parent.getChildrenList()) {
						if(node.equals(base)) break;
						j++;
					}
					parent.getChildrenList().remove(j);
				}
				base = parent;
			}
		}
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		for(Integer val : c) {if(!add(val)) return false;}
		return true;
	}
	//-----------------------------------------
	//Remove element
	@Override
	public boolean remove(Object o) {
		if(!contains(o)) return false;
		
		MyThreeWayBTreeNode base = root;
		Number key = (Number)o;
		
		Outter:while(true) {
			int i = 0;
			for(Integer val : base.getKeyList()) {
				if(key.intValue() < val) break;
				else if(val == key.intValue()) break Outter;
				else i++;
			}
			try {base = base.getChildrenList().get(i);}
			catch(IndexOutOfBoundsException e) {return false;}
		}
		
		do {
			MyThreeWayBTreeNode parent = base.getParent();
			
			if(base.getChildrenList().isEmpty()) {
				if(base.getKeyListSize() > Math.floor(3/2)) base.getKeyList().remove(key);
				else {
					MyThreeWayBTreeNode sibling = null;
					for(MyThreeWayBTreeNode node : parent.getChildrenList()) {
						if(base.isSibling(node) && node.getKeyListSize() > Math.floor(3/2)) {
							sibling = node; break;
						}
					}
					if(sibling == null) {
						if(parent.getKeyListSize() > Math.floor(3/2)) {
							Integer removedKey = base.getKeyList().get(0);
							parent.getChildrenList().remove(base);
							int i = 0;
							for(Integer val : parent.getKeyList()) {
								if(removedKey < val) {
									parent.getChildrenList().get(i).setKey(val);
									parent.getKeyList().remove(val);
									break;
								}
								i++;
							}
						}
						else {
							parent.getChildrenList().remove(base);
							parent.setKey(parent.getChildrenList().get(0).getKeyList().get(0));
							parent.getChildrenList().remove(parent.getChildrenList().get(0));
						}
					}
					else {
						int index = base.getKeyList().indexOf(key);
						base.getKeyList().remove(key);
						if(index > 0) {
							base.setKey(parent.getKeyList().get(index - 1));
							parent.getKeyList().remove(index - 1);
							parent.setKey(sibling.getKeyList().remove(sibling.getKeyListSize() - 1));
						}
						else {
							base.setKey(parent.getKeyList().get(index));
							parent.getKeyList().remove(0);
							parent.setKey(sibling.getKeyList().remove(0));
						}
					}
				}
			}
			else {
				//TODO Make remove function when node is internal
				MyThreeWayBTreeNode lessBase = base;
				MyThreeWayBTreeNode bigBase = base;
				while(true) {
					int i = 0;
					for(Integer val : lessBase.getKeyList()) {
						if(val >= key.intValue()) break;
						i++;
					}
					try {lessBase = lessBase.getChildrenList().get(i);}
					catch (IndexOutOfBoundsException e) {break;}
				}
				while(true) {
					int i = 0;
					for(Integer val : lessBase.getKeyList()) {
						if(val <= key.intValue()) break;
						i++;
					}
					try {bigBase = bigBase.getChildrenList().get(i);}
					catch (IndexOutOfBoundsException e) {break;}
				}
				
				if(lessBase.getKeyListSize() > Math.floor(3/2)) {
					
				}
				else if(bigBase.getKeyListSize() > Math.floor(3/2)) {
					
				}
				else {
					if(base.getKeyListSize() > Math.floor(3/2)) {
						
					}
					else {
						
					}
				}
			}
			base = parent;
		}while(base.getKeyListSize() <= Math.floor(3/2) && base != null);
		
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		for(Object val : c) {if(!remove(val)) return false;}
		return true;
	}

	@Override
	public void clear() {
		while(!isEmpty()) {remove(root.getKeyList().get(0));}
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		return false;
	}
	//-----------------------------------------
	//Finding value
	@Override
	public Integer lower(Integer e) {
		MyThreeWayBTreeNode base = root;
		Integer result = null;
		while(true) {
			int i = 0;
			for(Integer val : base.getKeyList()) {
				if(val < e) {result = val; i++;}
				else break;
			}
			try {base = base.getChildrenList().get(i);}
			catch(IndexOutOfBoundsException exp) {return result;}
		}
	}
	
	@Override
	public Integer higher(Integer e) {
		MyThreeWayBTreeNode base = root;
		Integer result = null;
		while(true) {
			int i = 0;
			for(Integer val : base.getKeyList()) {
				if(val > e) {result = val; i++;}
				else break;
			}
			try {base = base.getChildrenList().get(i);}
			catch(IndexOutOfBoundsException exp) {return result;}
		}
	}
	
	@Override
	public Integer floor(Integer e) {
		MyThreeWayBTreeNode base = root;
		Integer result = null;
		while(true) {
			int i = 0;
			for(Integer val : base.getKeyList()) {
				if(val < e) {result = val; i++;}
				else if(val.equals(e)) return val;
				else break;
			}
			try {base = base.getChildrenList().get(i);}
			catch(IndexOutOfBoundsException exp) {return result;}
		}
	}

	@Override
	public Integer ceiling(Integer e) {
		MyThreeWayBTreeNode base = root;
		Integer result = null;
		while(true) {
			int i = 0;
			for(Integer val : base.getKeyList()) {
				if(val > e) {result = val; i++;}
				else if(val.equals(e)) return val;
				else break;
			}
			try {base = base.getChildrenList().get(i);}
			catch(IndexOutOfBoundsException exp) {return result;}
		}
	}

	@Override
	public Integer pollFirst() {
		if(isEmpty()) return null;
		Integer firstKey = first();
		remove(firstKey);
		return firstKey;
	}

	@Override
	public Integer pollLast() {
		if(isEmpty()) return null;
		Integer lastKey = last();
		remove(lastKey);
		return lastKey;
	}
	//-----------------------------------------
	//Returns iterator
	//Internal class of iterator
	public class ItrAscending<E> implements Iterator<E>{
		private E[] tmp = null;
		private int index = 0;
		
		public ItrAscending(Class<E> klass){
			tmp = toArray((E[])Array.newInstance(klass, size()));
		}
		
		@Override
		public boolean hasNext() {
			if(index >= size()) return false;
			return true;
		}

		@Override
		public E next() {
			return tmp[index++];
		}
	}
	//Internal class of descendingiterator
	public class ItrDescending<E> implements Iterator<E>{
		private E[] tmp = null;
		private int index = size();
		
		public ItrDescending(Class<E> klass){
			tmp = toArray((E[])Array.newInstance(klass, size()));
		}
		
		@Override
		public boolean hasNext() {
			if(index <= 0) return false;
			return true;
		}

		@Override
		public E next() {
			return tmp[--index];
		}
	}
	
	@Override
	public Iterator<Integer> iterator() {
		return new ItrAscending<Integer>(Integer.class);
	}
	
	@Override
	public Iterator<Integer> descendingIterator() {
		return new ItrDescending<Integer>(Integer.class);
	}
	//-----------------------------------------
	//Returns sets
	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public NavigableSet<Integer> subSet(Integer fromElement, boolean fromInclusive, Integer toElement,
			boolean toInclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> headSet(Integer toElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> tailSet(Integer fromElement, boolean inclusive) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> subSet(Integer fromElement, Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> headSet(Integer toElement) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public SortedSet<Integer> tailSet(Integer fromElement) {
		// TODO Auto-generated method stub
		return null;
	}
	//-----------------------------------------
	//Comparator
	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}
	//-----------------------------------------
}
