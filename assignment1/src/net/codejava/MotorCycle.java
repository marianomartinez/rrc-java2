package net.codejava;

/**
 * Motorcycle class that implements the Vehicle interface:
 * Provides implementations for driving, starting, and stopping.
 */

public class MotorCycle implements Vehicle {
	
	@Override
	public void drive() {
		System.out.println("The MOTORCYCLE is driving.");		
	}

	@Override
	public void start() {
		System.out.println("The MOTORCYCLE started.");
	}

	@Override
	public void stop() {
		System.out.println("The MOTORCYCLE stopped.");
	}
}
