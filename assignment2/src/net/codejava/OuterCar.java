/**
 * 
 */
package net.codejava;

/**
 * OuterCar outer class with an InnerEngine inner class
 * Initializes an instance of the InnerEngine inner class and calls its methods.
 */
public class OuterCar {
	
	public void drive() {
				
		InnerEngine engine = new InnerEngine();
		engine.start();
		engine.shift();
		engine.stop();
		
	}
		
	/**
	 * InnerEngine inner class defines the methods for a car's mechanicals
	 */
	class InnerEngine {
		
		void start() {
			System.out.println("The car started");
		};
		void shift() {
			System.out.println("Shifting gear");
		};
		void stop() {
			System.out.println("The car stopped");
		};

	}
	
}
