/**
 * 
 */
package net.codejava;

/**
 * A VehicleRental test/demo class
 * It initializes an instance of each type of Vehicle (Car, Van, Motorcycle)
 * It initializes a rental vehicle for each type and calls the rent() method of each rental vehicle
 */
public class VehicleRentalTest {

	/**
	 * @param args (not used)
	 */
	public static void main(String[] args) {
		
		Car myCar = new Car();
		Van myVan = new Van();
		MotorCycle myMotorcycle = new MotorCycle();
		
		VehicleRental<Car> rentalCar = new VehicleRental<Car>(myCar);
		rentalCar.rent();
		
		VehicleRental<Van> rentalVan = new VehicleRental<Van>(myVan);
		rentalVan.rent();
		
		VehicleRental<MotorCycle> rentalMotorcycle = new VehicleRental<MotorCycle>(myMotorcycle);
		rentalMotorcycle.rent();
		
	}

}
