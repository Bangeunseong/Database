package org.dfpl.db.hash.m19011640;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
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
	public void preorder(MyThreeWayBTreeNode base) {
		System.out.println(base.getKeyList());
		for(MyThreeWayBTreeNode node : base.getChildrenList()) {
			preorder(node);
		}
	}
	
	
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
		
		//Find the base node to add e
		MyThreeWayBTreeNode base = root;
		while(true) {
			int i = 0;
			for(Integer key : base.getKeyList()) {
				if(e.intValue() < key.intValue()) break;
				i++;
			}
			if(base.getChildrenList().isEmpty()) break;
			else base = base.getChildrenList().get(i);
		}
		
		//Add e to base keylist
		base.setKey(e);
		
		//Refactoring when base keysize exceeds floor(3/2)
		if(base.getKeyListSize() > 2) {
			//Refactoring base manipulates parent
			//Use loop from base to root to change parent node
			while(base.getKeyListSize() > 2) {
				//Make subBtree
				//Subtree Root
				MyThreeWayBTreeNode newRoot = new MyThreeWayBTreeNode();
				newRoot.setKey(base.getKeyList().get(1));
				newRoot.setParent(base.getParent());
				
				//Subtree Child_1
				MyThreeWayBTreeNode node_1 = new MyThreeWayBTreeNode();
				node_1.setKey(base.getKeyList().get(0));
				node_1.setParent(newRoot);
				
				//Subtree Child_2
				MyThreeWayBTreeNode node_2 = new MyThreeWayBTreeNode();
				node_2.setKey(base.getKeyList().get(2));
				node_2.setParent(newRoot);
				
				//Children that need to be attached to subtree children if base does have children
				if(!base.getChildrenList().isEmpty()) {
					//Setting Child_1 children
					node_1.setChildren(base.getChildrenList().get(0));
					node_1.setChildren(base.getChildrenList().get(1));
					node_1.getChildrenList().stream().forEach(node->{
						node.setParent(node_1);
					});
					//Setting Child_2 children
					node_2.setChildren(base.getChildrenList().get(2));
					node_2.setChildren(base.getChildrenList().get(3));
					node_2.getChildrenList().stream().forEach(node->{
						node.setParent(node_2);
					});
				}
				
				//Attach Child_1, Child_2 to subtree root
				newRoot.setChildren(node_1);
				newRoot.setChildren(node_2);
				
				//Rebase the parent of base
				MyThreeWayBTreeNode parent = base.getParent();
				if(parent == null){root = newRoot; break;}
				else {
					parent.getChildrenList().remove(base);
					parent.setKey(newRoot.getKeyList().get(0));
					node_1.setParent(parent);
					node_2.setParent(parent);
					parent.setChildren(node_1);
					parent.setChildren(node_2);
				}
				base = parent;
			}
		}
		return true;
	}
	
	@Override
	public boolean addAll(Collection<? extends Integer> c) {
		int cnt = 0;
		for(Integer val : c) {if(add(val)) cnt++;}
		return cnt > 0 ? true : false;
	}
	//-----------------------------------------
	//Remove element
	@Override
	public boolean remove(Object o) {
		if(!contains(o)) return false;
		
		MyThreeWayBTreeNode base = root;
		Number key = (Number)o;
		
		//Find the base that needs to be removed
		Outter:while(true) {
			int i = 0;
			for(Integer val : base.getKeyList()) {
				if(key.intValue() < val) break;
				else if(val == key.intValue()) break Outter;
				else i++;
			}
			if(base.getChildrenList().isEmpty()) break;
			else base = base.getChildrenList().get(i);
		}
		
		//Remove the key and change the base if it is internal
		if(base.getChildrenList().isEmpty()) {
			base.getKeyList().remove(key);
		}
		else {
			//Get the index of base
			int index = base.getKeyList().indexOf(key);
			
			//Get lower value of base, Get higher value of base
			MyThreeWayBTreeNode lessBase = base.getChildrenList().get(index);
			MyThreeWayBTreeNode bigBase = base.getChildrenList().get(index + 1);
			while(!lessBase.getChildrenList().isEmpty()) {
				lessBase = lessBase.getChildrenList().get(lessBase.getChildrenListSize() - 1);
			}
			while(!bigBase.getChildrenList().isEmpty()) {
				bigBase = bigBase.getChildrenList().get(bigBase.getChildrenListSize() - 1);
			}
			
			//Remove key from base
			base.getKeyList().remove(key);
			
			//Switch base key to lower val or higher val
			if(lessBase.getKeyListSize() > Math.floor(3/2)) {
				base.setKey(lessBase.getKeyList().get(lessBase.getKeyListSize() - 1));
				lessBase.getKeyList().remove(lessBase.getKeyListSize() - 1);
				base = lessBase;
			}
			else if(bigBase.getKeyListSize() > Math.floor(3/2)) {
				base.setKey(bigBase.getKeyList().get(0));
				bigBase.getKeyList().remove(0);
				base = bigBase;
			}
			else {
				base.setKey(lessBase.getKeyList().get(lessBase.getKeyListSize() - 1));
				lessBase.getKeyList().remove(lessBase.getKeyListSize() - 1);
				base = lessBase;
			}
		}
		
		//Refactor the base and its parent
		//Use loop to change the parent of base
		while(base != null) {
			MyThreeWayBTreeNode parent = base.getParent();
			
			//When base violates keysize refactor base
			if(base.getKeyListSize() < 1) {
				//When base is root relocate the root to base
				if(parent == null) {
					if(!base.getChildrenList().isEmpty()) {
						base.getChildrenList().get(0).setParent(base.getParent());
						root = base.getChildrenList().get(0);
					}
					break;
				}
				
				//Find sibling node if exists
				MyThreeWayBTreeNode sibling = null;
				for(MyThreeWayBTreeNode node : parent.getChildrenList()) {
					if(base.isSibling(node) && (node.getKeyListSize() > Math.floor(3/2))) {
						sibling = node; break;
					}
				}
				
				int indexBase = parent.getChildrenList().indexOf(base);
				
				//If sibling does not exist do merge operation
				if(sibling == null) {
					//System.out.println("Merge operation engaged!");
					if(indexBase >= 1) {
						//Set key which is the left node of base with last key of parent keylist
						parent.getChildrenList().get(indexBase - 1).setKey(parent.getKeyList().get(indexBase - 1));
						//Remove left key of keylist based on index of base
						parent.getKeyList().remove(indexBase - 1);
						//If base had children, connect to parent node
						if(!base.getChildrenList().isEmpty()) {
							base.getChildrenList().stream().forEach(node->{
								node.setParent(parent.getChildrenList().get(indexBase - 1));
								parent.getChildrenList().get(indexBase - 1).setChildren(node);
							});
						}
						//Remove base from parent
						parent.getChildrenList().remove(base);
					}
					else {
						//Set key which is the right node of base with last key of parent keylist
						parent.getChildrenList().get(indexBase + 1).setKey(parent.getKeyList().get(indexBase));
						//Remove right key of keylist based on index of base
						parent.getKeyList().remove(indexBase);
						//If base had children, connect to parent node
						if(!base.getChildrenList().isEmpty()) {
							base.getChildrenList().stream().forEach(node->{
								node.setParent(parent.getChildrenList().get(indexBase + 1));
								parent.getChildrenList().get(indexBase + 1).setChildren(node);
							});
						}
						//Remove base from parent
						parent.getChildrenList().remove(base);
					}
				}
				//If sibling does exist do switch operation
				else {
					//System.out.println("Switch operation engaged!");
					
					//Find index of sibling
					int indexSibling = parent.getChildrenList().indexOf(sibling);
					
					//Switch key of nodes based on diff between index of sibling and index of base
					if(indexBase > indexSibling) {
						//Switching keys from parent to base, sibling to parent
						base.setKey(parent.getKeyList().get(indexSibling));
						parent.getKeyList().remove(indexSibling);
						parent.setKey(sibling.getKeyList().get(sibling.getKeyListSize() - 1));
						sibling.getKeyList().remove(sibling.getKeyListSize() - 1);
						
						//Stealing child of sibling and attach to base (Reason : removed key from sibling violation in children count)
						if(!sibling.getChildrenList().isEmpty()) {
							MyThreeWayBTreeNode children = sibling.getChildrenList().get(sibling.getChildrenListSize() - 1);
							children.setParent(base);
							base.setChildren(children);
							sibling.getChildrenList().remove(sibling.getChildrenListSize() - 1);
						}
					}
					else {
						//Switching keys from parent to base, sibling to parent
						base.setKey(parent.getKeyList().get(indexBase));
						parent.getKeyList().remove(indexBase);
						parent.setKey(sibling.getKeyList().get(0));
						sibling.getKeyList().remove(0);
						
						//Stealing child of sibling and attach to base (Reason : removed key from sibling violation in children count)
						if(!sibling.getChildrenList().isEmpty()) {
							MyThreeWayBTreeNode children = sibling.getChildrenList().get(0);
							children.setParent(base);
							base.setChildren(children);
							sibling.getChildrenList().remove(0);
						}
					}
				}
			}
			base = parent;
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		int cnt = 0;
		for(Object val : c) {if(remove(val)) cnt++;}
		return cnt > 0 ? true : false; 
	}

	@Override
	public void clear() {
		List<Object> tmp = Arrays.asList(toArray());
		removeAll(tmp);
	}
	
	@Override
	public boolean retainAll(Collection<?> c) {
		List<Object> tmp = Arrays.asList(toArray());
		tmp.removeAll(c);
		if(removeAll(tmp)) return true;
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
		
		@Override
		public void remove() {
			if(index > 0) {
				Object key = tmp[index - 1];
				MyThreeWayBTree.this.remove(key);
			}
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
		
		@Override
		public void remove(){
			if(index < size()) {
				Object key = tmp[index];
				MyThreeWayBTree.this.remove(key);
			}
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
