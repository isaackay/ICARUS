// This example shows how to get the Analog pin sensor data from the Arduino.  It shows the value of every Analog pin once after connecting to the Arduino.
import rxtxrobot.*;

public class GetSensorData
{   
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
		
		
		//for (int x=0; x < USBSensor.NUM_ANALOG_PINS; ++x) //this is what I believe should be here, but I need to call & initialize the NUM_ANALOG_PINS here
		//for (int x=0; x < RXTXRobot.NUM_ANALOG_PINS; ++x) //just blocking this out for now
		
		
		//for (int x = 0; x < 6; x++) {
			AnalogPin temp = r.getAnalogPin(5);
			System.out.println("Ping Sensor, pin 5, has value: " + temp.getValue());
		//}
		
		//System.out.println("Ping Sensor pin 5 has value: " + temp.getValue(5));
		
		System.out.println("IR Sensor pin 7 has value: " + r.getDigitalPin(7));
		
		r.close();
    }
}
