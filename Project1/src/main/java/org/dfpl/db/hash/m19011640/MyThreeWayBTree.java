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
	private MyThreeWayBTreeNode root;
	
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
		for(Integer val : base.getKeyList()) {
			try {tmp[index++] = (T)val;}
			catch (ClassCastException e) {throw new ClassCastException("Unable to cast!");}
		}
		int ind = index;
		if(base.getChildrenList().isEmpty()) return ind;
		for(MyThreeWayBTreeNode node : base.getChildrenList()) {ind = getKeyValue(node, tmp, ind);}
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
		if(o instanceof Number) {
			Number key = (Number)o;
			MyThreeWayBTreeNode base = root;
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
			
			//TODO Make remove function below
			if(base.getChildrenList().isEmpty()) {
				if(base.getKeyListSize() > Math.floor(3/2)) base.getKeyList().remove(key);
				else {
					MyThreeWayBTreeNode parent = base.getParent();
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
							
						}
					}
					else {
						int index = base.getKeyList().indexOf(key);
						base.getKeyList().remove(key);
						base.setKey(parent.getKeyList().get(index - 1));
						sibling.getParent().setKey(sibling.getKeyList().remove(sibling.getKeyListSize() - 1));
					}
				}
			}
			else {
				
			}
			return true;
		}
		else return false;
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
	
	@Override
	public boolean retainAll(Collection<?> c) {
		// TODO Auto-generated method stub
		return false;
	}
	//-----------------------------------------
	//Finding value
	@Override
	public Integer lower(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer floor(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer ceiling(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer higher(Integer e) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollFirst() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer pollLast() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public NavigableSet<Integer> descendingSet() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<Integer> descendingIterator() {
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

	@Override
	public Comparator<? super Integer> comparator() {
		// TODO Auto-generated method stub
		return null;
	}

}
