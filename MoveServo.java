// This example moves each servo individually.  While this method should be used to move one servo individually, it is recommended to use moveBothServos if both servos must be moved simultaneously
import rxtxrobot.*;
//import java.util.Scanner;

public class MoveServo
{
	private static final int desiredangle = 45; 
	private static int portNum = 8;
	private static final int servo_speed = 18;

	public void runServo(RXTXRobot robot, String servo_name) {
		if (servo_name == "IR") {
			portNum = 8;
		} 

		if (servo_name == "Temp") {
			portNum = 9;
		}

		/* Set parameters. */

		//r.run



		/* Set parameters. */

		// This angle is relative to the reference angle pointing 
		// directly rightward from the robot's forward-facing perspective.

		//int desiredangle = 180; //see above for this variable's declaration/initialization

		/* We need to convert the desired angle to the numerical value that needs to 
		 * be inputted to the .runPCAServo() function in order to actually turn to motor 
		 * towards this angle. Experimentally, the two are not equal. */

		double actualangle = 0;
		actualangle = (70.0/90.0)*(desiredangle + ((90.0*90.0)/70.0) - 90.0); //This closely approximates to 160deg
		int newangle = (int) actualangle;
		System.out.printf("New Angle ! %d\n\n\nNew funtion runs below with IR Angle data\n\n\n", newangle);

		/* Move to zero degrees to begin. */

		robot.runPCAServo(portNum, servo_speed);
		robot.sleep(4000);
		robot.runPCAServo(portNum, newangle);




		//Move 180 Servo on channel 0 to 50 degrees
		//r.runPCAServo(8, 50);

		//Move Continuous Servo on channel 1 at 90 speed	
		//r.runPCAContServo(1, 90); 

		//Move 180 Servo on channel 2 to 50 degrees for 1000 milliseconds
		//r.runPCAServo(8, 5);

		//r.close();
	}
}

