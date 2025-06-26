package assignment6;

/**
 * A demo class.
 * Calls the 
 */
public class MultipleThreads {

	/**
	 * Main method to run the demo
	 * @param args (not used)
	 */
	public static void main(String[] args) {

		/**
		 * Initializes an instance of the ThreadRunnableExample class 
		 */
		ThreadRunnableExample tre = new ThreadRunnableExample();
		
		/**
		 * Initializes 2 instances of Thread, based on ThreadRunnableExample, with a name
		 */
		Thread t1 = new Thread(tre, "thread1");
		Thread t2 = new Thread(tre, "thread2");
		
		/**
		 * Starts the run() method of each Thread
		 */
		t1.start();
		t2.start();
		
	}

}
