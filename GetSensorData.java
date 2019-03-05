// This example shows how to get the Analog pin sensor data from the Arduino.  It shows the value of every Analog pin once after connecting to the Arduino.
import rxtxrobot.*;

public class GetSensorData
{   
	//static RXTXRobot r = new ArduinoUno(); //Create RXTXRobot object
	
    public static void main(String[] args)
    {    
	    // All sensor data will be read from the analog pins
		
	    RXTXRobot r = new ArduinoUno(); //Create RXTXRobot object
	    //USBSensor s = new USBSensor(); //I believe we need this object in order to use the NUM_ANALOG_PINS public field in USBSensor.class
	    
	    boolean v = true;
		r.setVerbose(v); // Turn on/off the full detailed output of the program, 
						 //shows you more detail about what the Arduino is doing.
	    
		r.setPort("/dev/tty.usbmodem14401"); // Sets the port to COM5
		//s.setPort("/dev/tty.usbmodem14401"); // Sets the port to /dev/tty.usbmodem14401
		
			
 
		r.connect();
		//s.connect();
		
		r.refreshAnalogPins(); // Cache the Analog pin information
		//s.refreshAnalogPins(); // Cache the Analog pin information - this doesn't work, b/c the USBSensor class doesn't have a .refreshAnalogPins() method
		
		r.refreshDigitalPins(); // Cache the Digital pin information
		
		//Get the average thermistor reading
		
		int sum = 0;
  		int readingCount = 10; //Read the analog pin values ten times, adding to sum each time 
  		for (int i = 0; i < readingCount; i++) 
  		{
  			//Refresh the analog pins so we get new readings
  			r.refreshAnalogPins(); 
  			int reading = r.getAnalogPin(0).getValue();
  			sum += reading;
  		} 
  		
  		
  		int thermistorReading = sum / readingCount;

		//Return the average reading
		//Print the results
		System.out.println("The probe read the value: " + thermistorReading);
		System.out.println("In volts: " + (thermistorReading * (5.0/1023.0)));
		
		AnalogPin temp = r.getAnalogPin(0);
		System.out.println("Temp Sensor, pin 0, has value: " + temp.getValue());
		
		
		//System.out.println("Ping Sensor pin 5 has value: " + temp.getValue(5));
		
		System.out.println("IR Sensor pin 7 has value: " + r.getIRChar());
		
		r.close();
    }
    
}
