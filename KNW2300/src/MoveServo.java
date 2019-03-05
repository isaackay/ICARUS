// This example moves each servo individually.  While this method should be used to move one servo individually, it is recommended to use moveBothServos if both servos must be moved simultaneously
import rxtxrobot.*;

public class MoveServo
{
	public static void main(String[] args)
	{
		RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object
		r.setPort("/dev/tty.usbmodem14401"); // Set the port to COM3
		r.setVerbose(true); // Turn on debugging messages
		r.connect();
		
		//Move 180 Servo on channel 0 to 50 degrees
		//r.runPCAServo(8, 50);

		//Move Continuous Servo on channel 1 at 90 speed	
		//r.runPCAContServo(1, 90); 

		//Move 180 Servo on channel 2 to 50 degrees for 1000 milliseconds
		//r.runPCAServo(8, 5);
		r.getPing(5);
		r.close();
	}
}

