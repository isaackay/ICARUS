// This example shows how to move the DC motors.

import rxtxrobot.*;

public class RunOneMotor
{
	public static void main(String[] args)
	{
		RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object
		
		boolean v = true;
		r.setVerbose(v); // Turn on/off the full detailed output of the program, 
							 //shows you more detail about what the Arduino is doing.
		r.setPort("/dev/tty.usbmodem14401"); // Set port to /dev/tty.usbmodem14401
		r.connect();
		
		
		
		
		
		//r.refreshAnalogPins(); // Cache the Analog pin information
								 // Should be ran before calling the values from any analog pins.


		//s.refreshAnalogPins(); // Cache the Analog pin information - this doesn't work, b/c the USBSensor class doesn't have a .refreshAnalogPins() method
		
		//r.getAnalogPin(analogPinsAvailable); // Returns the value from the specific pin called.
		
		for (int x = 0; x < 6; x++) {
			AnalogPin temp = r.getAnalogPin(x);
			//AnalogPin[] temp = new AnalogPin[r.getAnalogPin(x)];
			System.out.println("Sensor " + x + " has value: " + temp.getValue());
		}
		
		
		
		
		
		/////////////Run motor on channel 4 at speed 400 for 5000 milliseconds
//		r.runPCAMotor(4, -400, 5000); //THIS RUNS the RIGHT MOTOR

		//Run motor on channel 5 at speed 400 for 5000 milliseconds
//		r.runPCAMotor(5, 400, 2000); //THIS RUNS the LEFT MOTOR
		
//		//Run LEFT motor on channel 5 at the same speed relative to the RIGHT motor for 5000 milliseconds
//		r.runPCAMotor(5, 800, 5000); //THIS WORKS!!!!

		
//		//STRAIGHT (Attempt 3)
		r.runTwoPCAMotor(4, -100, 5, 800, 2000);


//		COUNTER-CLOCKWISE (FAST) ≤720 degrees
//		//Runs a R. motor on channel 4 at speed -400 and a L. motor on channel 5 at speed -400 for 5000 milliseconds
//		r.runTwoPCAMotor(4, -400, 5, -400, 5000);
		
//		CLOCKWISE (SLOW) ≤180 degrees
//		Runs a motor on channel 0 at speed 400 and a motor on channel 1 at speed -400 for 2000 milliseconds
//		r.runTwoPCAMotor(4, 400, 5, 400, 5000); // This function with it's parameters will rotate

		r.close();
	}
}
