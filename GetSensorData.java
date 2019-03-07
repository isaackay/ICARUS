// This example shows how to get the Analog pin sensor data from the Arduino.  It shows the value of every Analog pin once after connecting to the Arduino.
import rxtxrobot.*;

public class GetSensorData
{   
	

	
	
   // public static void main(String[] args)
	void takeTemperature(RXTXRobot robot) 
	{    
	    // All sensor data will be read from the analog pins
		
	    ////////// RXTXRobot r = new ArduinoUno(); //Create RXTXRobot object
	    
	    
//	    boolean v = true;
//		r.setVerbose(v); // Turn on/off the full detailed output of the program, 
//						 //shows you more detail about what the Arduino is doing.
//	    
//		r.setPort("/dev/tty.usbmodem14401"); // Sets the port to COM5
//		//s.setPort("/dev/tty.usbmodem14401"); // Sets the port to /dev/tty.usbmodem14401
//		
//			
// 
//		r.connect();
//		//s.connect();
//		
//		r.refreshAnalogPins(); // Cache the Analog pin information
//		//s.refreshAnalogPins(); // Cache the Analog pin information - this doesn't work, b/c the USBSensor class doesn't have a .refreshAnalogPins() method
//		
//		r.refreshDigitalPins(); // Cache the Digital pin information
		
		//Get the average thermistor reading
		
		int sum = 0;
  		int readingCount = 10; //Read the analog pin values ten times, adding to sum each time 
  		for (int i = 0; i < readingCount; i++) 
  		{
  			//Refresh the analog pins so we get new readings
  			robot.refreshAnalogPins(); 
  			int reading = robot.getAnalogPin(0).getValue();
  			sum += reading;
  		} 
  		
  		
  		int thermistorReading = sum / readingCount;

		//Return the average reading
		//Print the results
		System.out.println("The Temp Probe read a byte value of: " + thermistorReading);
		System.out.println("Volts across Analog Pin 0:		" + (thermistorReading * (5.0/1023.0)) + "V");
		
		AnalogPin temp = robot.getAnalogPin(0);
		System.out.println("Temp Probe, A0, has a byte value of: " + temp.getValue() + "\n\n\n");
		
		
		
		System.out.println("\n\nTemperature readings from our method will now follow...\n\n\n");
		
		
		
		
		
//		// This code is to be used to read thermistor data.
//		// Get the average thermistor reading
		
		sum = 0;
	  	readingCount = 10; //Read the analog pin values ten times, adding to sum each time 
	  	for (int i = 0; i < readingCount; i++) {
	  		//Refresh the analog pins so we get new readings
	  		robot.refreshAnalogPins(); 
	  		int reading = robot.getAnalogPin(0).getValue();
	  		//System.out.printf("%d", reading);
	  		sum += reading;
	  	} 
	  	
	  	thermistorReading = sum / readingCount;
	  	
	  	// When the temperature is 7 degrees Celsius, we measured an ADC code of 753.
	  	// When the temperature is 30 degrees Celsius, we measured an ADC code of 464.
	  	// When the temperature is 21 degrees Celsius, we measured an ADC code of 567.
	    // When the temperature is 23 degrees Celsius, we measured an ADC code of 553.
	  	// See GitHub for a Jupyter notebook, .pdf, and .png file containing our regression
	  	// analysis. Our computed slope was -12.58206278 and our intercept was 839.03677.
	  	
	  	double estimatedtemp = 0;
	  	estimatedtemp = (thermistorReading - 839.03677)/(-12.58206278);
	  	
	  	System.out.printf("Thermistor Reading ADC code: %d\n", thermistorReading);
	  	System.out.printf("Estimated Temperature: %f Celsius", estimatedtemp);
		
		/* Close connection to ArduinoUno. */
		
		//r.close();
		
		}
}
