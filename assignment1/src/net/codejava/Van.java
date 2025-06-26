package net.codejava;

/**
 * Van class that implements the Vehicle interface:
 * Provides implementations for driving, starting, and stopping.
 */

public class Van implements Vehicle {
	
	@Override
	public void drive() {
		System.out.println("The VAN is driving.");		
	}

	@Override
	public void start() {
		System.out.println("The VAN started.");
	}

	@Override
	public void stop() {
		System.out.println("The VAN stopped.");
	}
}
