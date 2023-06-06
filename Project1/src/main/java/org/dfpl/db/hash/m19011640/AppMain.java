package org.dfpl.db.hash.m19011640;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

public class AppMain {

	public static void main(String[] args) {
		//1
		Set<Integer> set = new MyHashSet();
		System.out.println("[1] " + set.isEmpty());
		for(int i = 0; i < 30; i++) {
			set.add(i);
		}
		System.out.println("[2] " + set.contains(3));
		System.out.println("[3] " + set.contains(30));
		System.out.println("[4] " + set.isEmpty());
		System.out.println("[5] " + set.size());
		Iterator<Integer> iter = set.iterator();
		String join = "";
		while(iter.hasNext()) {
			join += iter.next() + ",";
		}
		join = join.substring(0,  join.length() - 1);
		System.out.println("[6] " + join);
		
		//2
		Set<Integer> set1 = new MyHashSet();
		Set<Integer> set2 = new MyHashSet();

		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			int next = r.nextInt(7000);
			set1.add(next);
			set2.add(next);
		}
		

		// true가 반환되어야만 합니다.
		System.out.println("[1] " + (set1.size() == set2.size()));

		boolean isDifferent = false;
		for (int set1Value : set1) {
			if (!set2.contains(set1Value)) {
				isDifferent = true;
				break;
			}
		}

		// true가 반환되어야만 합니다.
		System.out.println("[2] " + !isDifferent);

		Iterator<Integer> set2Iter = set2.iterator();
		int cnt = 0;
		while (set2Iter.hasNext()) {
			set2Iter.next();
			cnt++;
		}

		// true가 반환되어야만 합니다.
		System.out.println("[3] " + (set1.size() == cnt));

		ArrayList<Integer> removedList = new ArrayList<Integer>();
		Iterator<Integer> removeIter = set1.iterator();
		int rCount = 0;
		while (removeIter.hasNext()) {
			if (rCount == 1000)
				break;
			int valueToRemove = removeIter.next();
			removeIter.remove();
			removedList.add(valueToRemove);
			set2.remove(valueToRemove);
			rCount++;
		}

		boolean isNotRemoved = false;
		for (int removed : removedList) {
			if (set2.contains(removed)) {
				isNotRemoved = true;
				break;
			}
		}

		// true가 반환되어야만 합니다
		System.out.println("[4] " + !isNotRemoved);
	}

}

