package nachos.threads;

public class WaitThread implements Comparable<WaitThread>{
	
	private KThread current;
	private long waitTime;

	public WaitThread(KThread thread, long wait){
		current = thread;
		waitTime = wait;
	}

	public long getWait(){
		return waitTime;
	}

	public KThread getThread(){
		return current;
	}

	@Override
	public int compareTo(WaitThread otherWaitThread){
		if(otherWaitThread.waitTime < this.waitTime){
			return 1;
		} else if(otherWaitThread.waitTime == this.waitTime){
			return 0;
		} else {
			return -1;
		}
	}
}
		
