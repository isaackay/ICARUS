// Example file for running a servo and printing out the IR charcater your receiver should read.
// IR Receiver has to be connecte to pin 4 on the arduino for this to work.

import rxtxrobot.*;

//This code is to be used to detect the IR signal.



public class NavigationMethod
{

	private int angle_of_signal; //90deg (i.e. Directly in front)
	
	//Getter Method
	public int getAngleOfSignal() {
		return this.angle_of_signal;
	}
	
	//Setter Method
	public void setAngleOfSignal(int angle_received) {
		this.angle_of_signal = angle_received;
	}

	int Navigate(RXTXRobot robot) {

		System.out.printf("%c", robot.getIRChar());

		/* Angle degree notes:
		 * When the input angle is 90, the sensor is pointed directly forward.
		 * When the input angle is 120, the sensor is about a 30 degree angle starboard. 
		 * When the input angle is 60, the sensor is about a 30 degree angle port.
		 * Move from 20 degrees to 160 degrees.
		 */

		char signaldetected = ' ';
		int[] detections; // Declare integer array for storing ping sensor data.
		detections = new int[34];
		// Loop over relevant angles and gather IR sensor data.
		for (int x = 20; x <= 160; x += 5) {
			robot.runPCAServo(8, x);
			signaldetected = robot.getIRChar();
			System.out.printf("Angle: %d, Signal: %c\n", x, signaldetected);
	
			// Store angle if signal detected.
			if (signaldetected != '0') { 
				detections[(x/5) - 4] = x;
			} 
	
		}
	
	
		/* This code takes the first nonzero element of the angle array. */
	
		int angle_estimate = 0;
		for (int i = 0; i < 34; i++) {
			if (detections[i] != 0) {
				angle_estimate = detections[i];
				break;
			}
		}
		
		angle_of_signal = angle_estimate;
		
		System.out.printf("Estimated signal angle: %d degress\n\nSETTING ORIENTATION NOW...", angle_of_signal);
				
		setAngleOfSignal(angle_of_signal);
		
		return getAngleOfSignal();

		
		
		
		
		/* This code averages the values in the detection array. 
		 * 
		 * DO NOT USE FOR SPRINT 2!!!!!!!
		 
		 int angleestimate = 0;
		 int nonzero = 0;
		 for (int i = 0; i < 34; i++) {
			 if (detections[i] != 0) {
				 angleestimate += detections[i];
				 nonzeros += 1;
			 }
		 }
		 angleestimate = angleestimate/nonzeros;
		 System.out.printf("%d", angleestimate);
		 
		System.out.printf("Signal detected at angle %d.\n", signalangle);	
		
		*/
	}
	
	int Navigate(RXTXRobot robot, char Throw_Beacon_Character) {

		System.out.printf("%c", robot.getIRChar());

		/* Angle degree notes:
		 * When the input angle is 90, the sensor is pointed directly forward.
		 * When the input angle is 120, the sensor is about a 30 degree angle starboard. 
		 * When the input angle is 60, the sensor is about a 30 degree angle port.
		 * Move from 20 degrees to 160 degrees.
		 */

		char signaldetected = ' ';
		char ignoredetected = Throw_Beacon_Character;
		int[] detections; // Declare integer array for storing ping sensor data.
		detections = new int[28];
		// Loop over relevant angles and gather IR sensor data.
		for (int x = 20; x <= 160; x += 5) {
			robot.runPCAServo(8, x);
			signaldetected = robot.getIRChar();
			System.out.printf("Angle: %d, Signal: %c\n", x, signaldetected);
	
			// Store angle if signal detected.
			if (signaldetected != '0' && signaldetected != ignoredetected) { 
				detections[(x/5) - 4] = x;
			} 
	
		}
	
	
		/* This code takes the first nonzero element of the angle array. */
	
		int angle_estimate = 0;
		for (int i = 0; i < 28; i++) {
			if (detections[i] != 0) {
				angle_estimate = detections[i];
				break;
			}
		}
		
		angle_of_signal = angle_estimate;
		
		System.out.printf("Estimated signal angle: %d degress\n\nSETTING ORIENTATION NOW...", angle_of_signal);
				
		setAngleOfSignal(angle_of_signal);
		
		return getAngleOfSignal();

		
		
		
		
		/* This code averages the values in the detection array. 
		 * 
		 * DO NOT USE FOR SPRINT 2!!!!!!!
		 
		 int angleestimate = 0;
		 int nonzero = 0;
		 for (int i = 0; i < 34; i++) {
			 if (detections[i] != 0) {
				 angleestimate += detections[i];
				 nonzeros += 1;
			 }
		 }
		 angleestimate = angleestimate/nonzeros;
		 System.out.printf("%d", angleestimate);
		 
		System.out.printf("Signal detected at angle %d.\n", signalangle);	
		
		*/
	}
}
/////////////////IGNORE BELOW/////////////////
















////EXAMPLE provided by Traian	

//	public static void main(String[] args)
//	{
//		RXTXRobot r = new ArduinoNano(); //Create RXTXRobot object
//		r.setPort("/dev/tty.usbmodem14401"); // Set the port to COM3
//		r.setVerbose(true); //Turn on debugging messages
//		r.connect();
//
//		// goes through a for loop to turn the servo by 10 degrees, 0 to 180
//		for (int count = 0; count <= 180; count += 10)
//		{
//			//turns servo on channel 0 to the current angle
//			r.runPCAServo(0, count);
//			//Prints out the IR Charcater it reads from the Receiver
//			System.out.println(r.getIRChar());
//		}
//		r.close();
//	}
//}