package net.codejava;

/**
 * Van class that implements the Vehicle interface:
 * Provides implementations for driving, starting, and stopping.
 */

public class Car implements Vehicle {

	@Override
	public void drive() {
		System.out.println("The CAR is driving.");		
	}

	@Override
	public void start() {
		System.out.println("The CAR started.");
	}

	@Override
	public void stop() {
		System.out.println("The CAR stopped.");
	}

}
