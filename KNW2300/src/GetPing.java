// This example shows how to get the response from the Ping sensor since it is different than other Analog sensors.  It MUST be connected to digital pin 13.
import rxtxrobot.*;

public class GetPing
{
	//private static final int PING_PIN = 10;

	//final private static PING_PIN = 5; //this is what was given - it was the WRONG SYNTAX

	public static void main(String[] args)
	{
		RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object
		r.setPort("/dev/tty.usbmodem14401"); // Set the port to /dev/tty.usbmodem14401
		r.connect();
		r.refreshDigitalPins();
		for (int x=0; x < 10; x++)
		{
			//Read the ping sensor value, which is connected to pin 12
			System.out.println("Response: " + r.getPing(8) + " cm");
			r.sleep(300);
		}
		r.close();
	}
}

