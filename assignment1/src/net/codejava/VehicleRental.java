/**
 * 
 */
package net.codejava;

/**
 * A generic class that only takes Objects that implement the Vehicle interface.
 * This class accepts any and only type of Vehicle and calls its drive method.
 */

public class VehicleRental<T extends Vehicle> {
	
	T obj;
	
	/**
	 * Constructor that takes an object of any type of Vehicle
	 * @param obj A vehicle object.
	 */	 
	public VehicleRental(T obj) {
		this.obj = obj;
	};
	
	/**
	 * A simple object getter
	 * @return The vehicle type object
	 */
	public T getMyVehicle() {
		return obj;	
	}
	
	/**
	 * Rent method
	 * It calls the drive() method of the vehicle type object
	 */
	public void rent() {
		obj.drive();
	}
	
}
