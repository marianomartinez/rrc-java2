package assignment6;

/**
 * A Runnable class, overrides the run() method to print the name of the thread
 */
public class ThreadRunnableExample implements Runnable {

	/**
	 * Constructor
	 */
	public ThreadRunnableExample() {
		
	}
	
	/**
	 * A method that prints the name of the thread
	 * @throws InterruptedException
	 */
	private void printThreadName() throws InterruptedException {
		
		for(int i=0; i < 3 ; i++) {
		
			System.out.println("The current thread name is: " + Thread.currentThread().getName());			
		
		}
	
	}

	/**
	 * Overridden run() method, tries to run the print method, catches the exception
	 */
	@Override
	public void run() {
		
		try {
			
			printThreadName();
			
		} catch (InterruptedException e) {
			
			e.printStackTrace();
			
		}
		
	}

}
