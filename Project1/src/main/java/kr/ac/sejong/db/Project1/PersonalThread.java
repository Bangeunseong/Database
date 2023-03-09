package kr.ac.sejong.db.Project1;

public class PersonalThread extends Thread {
	@Override
	public void run() {
		for(int i = 0; i < 10000; i++) {
			//Using Semaphore
			try {P3.sem.acquire();}
			catch(InterruptedException e) {e.printStackTrace();}
			
			//Critical
			int localFund = P3.fund;
			localFund += 1;
			P3.fund = localFund;
			//Release
			P3.sem.release(); 
			
			//Using synchronized
			//P3.addFund(1);
		}
	}
}
