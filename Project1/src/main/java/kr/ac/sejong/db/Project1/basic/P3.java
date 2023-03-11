package kr.ac.sejong.db.Project1.basic;

import java.util.concurrent.Semaphore;

public class P3 {
	public static int fund = 0;
	public static Semaphore sem = new Semaphore(1);
	public static synchronized void addFund(int x) {fund += x;}
	
	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new PersonalThread();
		t1.start();
		Thread t2 = new PersonalThread();
		t2.start();
		Thread t3 = new PersonalThread();
		t3.start();
		
		Thread.sleep(3000);
		System.out.println(fund);
	}
}
