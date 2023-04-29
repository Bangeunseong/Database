package org.dfpl.db.hash.m19011640;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MyThreeWayBTreeNode {
	//Field
	private MyThreeWayBTreeNode parent;
	private List<Integer> keyList;
	private List<MyThreeWayBTreeNode> children;
	
	//Constructor
	MyThreeWayBTreeNode(){
		parent = null; 
		children = new ArrayList<MyThreeWayBTreeNode>();
		keyList = new ArrayList<Integer>();
	}
	
	//Method -> Getter, Setter
	//keys
	public void setKey(Integer key) {
		keyList.add(key);
		keyList.sort(new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				if(o1.intValue() > o2.intValue()) return 1;
				else if(o1.intValue() == o2.intValue()) return 0;
				else return -1;
			}
		});
	}
	public List<Integer> getKeyList() {return keyList;}
	public Integer getKeyListSize() {return keyList.size();}
	
	//parent
	public void setParent(MyThreeWayBTreeNode parent) {this.parent = parent;}
	public MyThreeWayBTreeNode getParent() {return parent;}
	
	//children
	public void setChildren(MyThreeWayBTreeNode children) {this.children.add(children);}
	public List<MyThreeWayBTreeNode> getChildrenList(){return children;}
}
