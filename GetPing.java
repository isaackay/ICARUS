import rxtxrobot.*;


//This code is to be used to turn the IR sensor to the specified angle (given by desiredangle).




public class GetPing
{

	
	private static int sampletime = 4250; //Sample time in milliseconds.

	private static final int samplingrate = 350; //  Gap between ping sensor readings (in milliseconds)
	private static final int triggerdistance = 40; // Distance (in cm) at which robot deems an obstacle is present. 
	


	//	private static final int runtime = 0; // Runtime in milliseconds. (1 second = 1000 milliseconds)
	//This time value will RUN both motors INFINITELY until someone TURNS THE BATTERY OFF

	private static final int rightspeed = -500; // Speed of port motor.
	private static final int leftspeed = 800; // Speed of starboard of motor.

	//	public static void main(String[] args)
	//	{
	//		RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object
	//		r.setPort("/dev/tty.usbmodem14401"); // Set the port to /dev/tty.usbmodem14401
	//		r.connect();
	//		r.refreshDigitalPins();
	//		r.refreshAnalogPins();

	////This code is to be used to test cinder block detection.

	public void runPing(RXTXRobot robot) {
//		robot.runTwoPCAMotor(4, rightspeed, 5, leftspeed, sampletime);
		int its = 0; // Integer to store number of iterations needed for sampling.

		for (int x = 0; x < sampletime; x++) {
			x += samplingrate;
			its += 1;
		}


		/* Debugging output. */

		System.out.printf("The total sampling time is %d milliseconds.\n", sampletime);
		System.out.printf("The gap between samples is %d milliseconds.\n", samplingrate);
		System.out.printf("%d iterations are necessary.\n", its);


		/* Sample ping sensor for given time at given rate, and store data in array for output. */

//		robot.runTwoPCAMotor(4, rightspeed, 5, leftspeed, sampletime);

		int[] pingdata; // Declare integer array for storing ping sensor data.
		pingdata = new int[its+1];
		robot.runTwoPCAMotor(4, rightspeed, 5, leftspeed, sampletime);

		for (int x = 0; x < its; x++) {
			System.out.printf("Sample %d\n", x+1);
			int close_enough = 0; // Binary variable for storing detection of cinder block.

			if (robot.getPing(8) < triggerdistance) {
				close_enough = 1;
			}

			pingdata[x] = close_enough;
			robot.sleep(samplingrate); // Pause sampling based on input sampling rate.
		}

		//r.runTwoPCAMotor(4, 0, 5, 0, 100); // End motor movements.

		/* Close connection to ArduinoUno. */

		robot.close();


		/* Print out the X-0 map of the ping data. X means obstacle, 0 means no obstacle. */
		for (int x = 0; x < its; x++) {
			if (pingdata[x] == 0) {
				System.out.printf("0 ");
			}
			else {
				System.out.printf("X ");
			}
		}
	}
}
