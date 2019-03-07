import rxtxrobot.*;
import java.util.Scanner; //for user input in driver2() method

public class RunOneMotor extends NavigationMethod
{
	public static final RXTXRobot r = new ArduinoUno();

	public static final NavigationMethod navObj = new NavigationMethod();

	public static final GetPing pingObj = new GetPing();

	public static final MoveServo servoObj = new MoveServo();
	
	public static final GetSensorData tempObj = new GetSensorData();


	private static int runtime = 5000;

	private static final int Right_channel = 4; 
	private static final int Left_channel = 5;

	private static int right_forward_speed = -500; // Speed of port motor.
	private static int left_forward_speed = 800; // Speed of starboard of motor.

	private static final int R_0_runtime = 950;
	private static final int R_30_runtime = 450; //checked
	private static final int R_45_runtime = 200; //checked
	private static final int R_60_runtime = 10; //checked
	private static final int L_120_runtime = 15; //checked
	private static final int L_135_runtime = 125; //checked
	private static final int L_150_runtime = 325; //checked
	private static final int L_180_runtime = 648; //

	private static final int forward_runtime = 10000; 
	//////////// To go 3 meters, input 5000 for the forward_runtime

	private static final boolean TRUE = true;
	private static final boolean FALSE = false;


	public static void main(String[] args)

	{

		//RXTXRobot r = new ArduinoUno(); // Create RXTXRobot object

		//boolean v = true;
		//r.setVerbose(v); 
		r.setPort("/dev/tty.usbmodem14401"); // Set port to /dev/tty.usbmodem14401 on Isaac's computer.
		r.connect();
		r.refreshAnalogPins();

		boolean Drive_Toward_IR_Beacon = FALSE;
		boolean Orient_To_Specified_Beacons_Test = FALSE; 
		boolean Ping_Test = TRUE; 
		boolean Servo_Motor_Test = FALSE; 
		boolean Temperature_Test = FALSE; 

		
//		runtime = forward_runtime;
//		RunOneMotor.runForward(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime);
		if (Drive_Toward_IR_Beacon == TRUE) {
			driver1();
		}

		if (Orient_To_Specified_Beacons_Test == TRUE) {
			driver2();
		}

		if (Ping_Test == TRUE) {
			driver3();
		}

		if (Servo_Motor_Test == TRUE) {
			driver4();
		}
		
		if (Temperature_Test == TRUE) {
			driver5();
		}





		/* Close connection to ArduinoUno. */
		r.close();
	}


	public static void driver1() {
		int relative_orientation = navObj.Navigate(r); // DEFAULT SETTING is 90deg (which is facing the target head-on)


		//int testANGLE = 90;


		//navObj.setAngleOfSignal(testANGLE);

		//relative_orientation = navObj.getAngleOfSignal();/*DO I NEED THIS?????*/
		//		navObj.setAngleOfSignal(125);
		//		relative_orientation = navObj.getAngleOfSignal();

		for (int x = 0; x < 6; x++) {
			AnalogPin temp = r.getAnalogPin(x);
			//AnalogPin[] temp = new AnalogPin[r.getAnalogPin(x)];
			System.out.println("Sensor " + x + " has value: " + temp.getValue());
		}

		/* Set parameters. */


		/* Run both motors for specified time. */
		//		r.runTwoPCAMotor(<RIGHT Motor Channel>, <RIGHT Motor Speed>, <LEFT Motor Channel>, <LEFT Motor Speed>, <runtime>);

		//		int Right_channel = 4; 
		//		int Left_channel = 5;
		//		
		//		int runtime = 500;


		if (relative_orientation >= 0 && relative_orientation < 15 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 0deg  */) {
			runtime = R_0_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 90deg
		}

		if (relative_orientation >= 15 && relative_orientation < 30 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = R_60_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 30deg
			runtime = R_45_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg more -- ending up at a sum of 75deg from initial position (i.p.) if i.p. = 90deg  
		}

		if (relative_orientation >= 30 && relative_orientation < 45 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = R_30_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 60deg
		}

		if (relative_orientation >= 45 && relative_orientation < 60 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 45deg  */) {
			runtime = R_45_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg
		}

		if (relative_orientation >= 60 && relative_orientation < 75 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 60deg  */) {
			runtime = R_60_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, 6); //Turns RIGHT 30deg
		}

		if (relative_orientation >= 75 && relative_orientation < 90  /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = R_45_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 30deg
			runtime = L_120_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg more -- ending up at a sum of 75deg from initial position (i.p.) if i.p. = 90deg  
		}

		if (relative_orientation == 90) {
			runtime = forward_runtime;
			RunOneMotor.runForward(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime);
		}

		if (relative_orientation > 90 && relative_orientation == 105 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = L_135_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 30deg
			runtime = R_60_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg more -- ending up at a sum of 75deg from initial position (i.p.) if i.p. = 90deg  
		}

		if (relative_orientation > 105 && relative_orientation <= 120 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 120deg  */) {
			runtime = L_120_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 30deg
		}

		if (relative_orientation > 120 && relative_orientation <= 135 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 135deg  */) {
			runtime = L_135_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg
		}

		if (relative_orientation > 135 && relative_orientation <= 150 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 150deg  */) {
			runtime = L_150_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 60deg
		}

		if (relative_orientation > 150 && relative_orientation <= 165 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = L_120_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns LEFT 30deg
			runtime = L_135_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns LEFT 45deg more -- ending up at a sum of 75deg from initial position (i.p.) if i.p. = 90deg  
		}

		if (relative_orientation > 165 && relative_orientation <= 180 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 180deg  */) {
			runtime = L_180_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 90deg
		}


		//		turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 90deg
		//		
		//		turnLeft(Right_channel, -right_forward_speed, Left_channel, -left_forward_speed, runtime); //Turns LEFT 90deg

		RunOneMotor.runForward(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime);






		//TEST HERE
		//int runtime = 50; // Runtime in milliseconds. (1 second = 1000 milliseconds)
		//r.runTwoPCAMotor(Right_channel, right_forward_speed, Left_channel, -left_forward_speed, runtime);


		//r.allPCAStop();
		//r.runTwoPCAMotor(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime);

	}

	public static void driver2() {
		char Character_to_ignore = 'K';
//		do {
//			System.out.println("Please choose a character val to ignore: (G/K)\n");
//			Scanner input = new Scanner(System.in);
//			Character_to_ignore = input.next().charAt(0);
//			input.close();
//		}
//		while (Character_to_ignore != 'k' || Character_to_ignore != 'K' || Character_to_ignore != 'g' || Character_to_ignore != 'G');

		int relative_orientation = navObj.Navigate(r, Character_to_ignore); // DEFAULT SETTING is 90deg (which is facing the target head-on)


		//int testANGLE = 90;


		//navObj.setAngleOfSignal(testANGLE);

		//relative_orientation = navObj.getAngleOfSignal();/*DO I NEED THIS?????*/		
		//		/////////////////////////////////////////************************************PLEASE CHANGE THIS BEFORE TESTING
		//int testANGLE = 90;
		/////////////////////////////////////////************************************PLEASE CHANGE THIS BEFORE TESTING
		//relative_orientation = testANGLE;


		//navObj.setAngleOfSignal(testANGLE);

		System.out.println("\n\n\n");
		System.out.println(relative_orientation);
		System.out.println("\n\n");

		for (int x = 0; x < 6; x++) {
			AnalogPin temp = r.getAnalogPin(x);
			System.out.println("Sensor " + x + " has value: " + temp.getValue());
		}

		/* Set parameters. */


		/* Run both motors for specified time. */
		//		r.runTwoPCAMotor(<RIGHT Motor Channel>, <RIGHT Motor Speed>, <LEFT Motor Channel>, <LEFT Motor Speed>, <runtime>);

		//		int Right_channel = 4; 
		//		int Left_channel = 5;
		//		
		//		int runtime = 500;


		if (relative_orientation >= 0 && relative_orientation < 15 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 0deg  */) {
			runtime = R_0_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 90deg
		}

		if (relative_orientation >= 15 && relative_orientation < 30 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = R_60_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 30deg
			runtime = R_45_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg more -- ending up at a sum of 75deg from initial position (i.p.) if i.p. = 90deg  
		}

		if (relative_orientation >= 30 && relative_orientation < 45 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = R_30_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 60deg
		}

		if (relative_orientation >= 45 && relative_orientation < 60 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 45deg  */) {
			runtime = R_45_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg
		}

		if (relative_orientation >= 60 && relative_orientation < 75 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 60deg  */) {
			runtime = R_60_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, 6); //Turns RIGHT 30deg
		}

		if (relative_orientation >= 75 && relative_orientation < 90  /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = R_45_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 30deg
			runtime = L_120_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg more -- ending up at a sum of 75deg from initial position (i.p.) if i.p. = 90deg  
		}

		if (relative_orientation == 90) {
			runtime = forward_runtime;
			RunOneMotor.runForward(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime);
		}

		if (relative_orientation > 90 && relative_orientation == 105 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = L_135_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 30deg
			runtime = R_60_runtime;
			turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg more -- ending up at a sum of 75deg from initial position (i.p.) if i.p. = 90deg  
		}

		if (relative_orientation > 105 && relative_orientation <= 120 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 120deg  */) {
			runtime = L_120_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 30deg
		}

		if (relative_orientation > 120 && relative_orientation <= 135 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 135deg  */) {
			runtime = L_135_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 45deg
		}

		if (relative_orientation > 135 && relative_orientation <= 150 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 150deg  */) {
			runtime = L_150_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 60deg
		}

		if (relative_orientation > 150 && relative_orientation <= 165 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 30deg  */) {
			runtime = L_120_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns LEFT 30deg
			runtime = L_135_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns LEFT 45deg more -- ending up at a sum of 75deg from initial position (i.p.) if i.p. = 90deg  
		}

		if (relative_orientation > 165 && relative_orientation <= 180 /*  (orientation == servo_motor_angle) //in degrees received from IR servo motor is 180deg  */) {
			runtime = L_180_runtime;
			turnLeft(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 90deg
		}


		//		turnRight(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime); //Turns RIGHT 90deg
		//		
		//		turnLeft(Right_channel, -right_forward_speed, Left_channel, -left_forward_speed, runtime); //Turns LEFT 90deg

		RunOneMotor.runForward(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime);






		//TEST HERE
		//int runtime = 50; // Runtime in milliseconds. (1 second = 1000 milliseconds)
		//r.runTwoPCAMotor(Right_channel, right_forward_speed, Left_channel, -left_forward_speed, runtime);


		//r.allPCAStop();
		//r.runTwoPCAMotor(Right_channel, right_forward_speed, Left_channel, left_forward_speed, runtime);

	}

	public static void driver3() {
		r.refreshDigitalPins();
		pingObj.runPing(r);
	}

	public static void driver4() {
		String servo_name = "IR";
		//		do {
		//			System.out.println("Please choose a servo to ignore,\nType 'IR' for the IR Servo,\nOr type Temp for the Temperature Servo\n\n");
		//			Scanner input = new Scanner(System.in);
		//			servo_name = input.next();
		////			servo_name.equalsIgnoreCase();
		//			input.close();
		//		}
		//		while (servo_name != "IR" || servo_name != "Temp");

		servoObj.runServo(r, servo_name);
	}
	
	public static void driver5() {
		tempObj.takeTemperature(r);
	}

	public static RXTXRobot turnRight(int r_chan, int r_spd, int l_chan, int l_spd, int r_time) {


		r.runTwoPCAMotor(r_chan, -r_spd, l_chan, l_spd, r_time);

		return r;
	}




	public static RXTXRobot turnLeft(int r_chan, int r_spd, int l_chan, int l_spd, int l_time) {

		r.runTwoPCAMotor(r_chan, r_spd, l_chan, -l_spd, l_time);

		return r;
	}




	public static RXTXRobot runForward(int r_chan, int r_spd, int l_chan, int l_spd, int forward_time) {

		r.runTwoPCAMotor(r_chan, r_spd, l_chan, l_spd, forward_runtime);

		return r;
	}
}





//r.refreshAnalogPins(); // Cache the Analog pin information
// Should be ran before calling the values from any analog pins.


//s.refreshAnalogPins(); // Cache the Analog pin information - this doesn't work, b/c the USBSensor class doesn't have a .refreshAnalogPins() method

//r.getAnalogPin(analogPinsAvailable); // Returns the value from the specific pin called.


/////////////Run motor on channel 4 at speed 400 for 5000 milliseconds
//r.runPCAMotor(4, -400, 5000); //THIS RUNS the RIGHT MOTOR

//Run motor on channel 5 at speed 400 for 5000 milliseconds
//r.runPCAMotor(5, 400, 2000); //THIS RUNS the LEFT MOTOR

////Run LEFT motor on channel 5 at the same speed relative to the RIGHT motor for 5000 milliseconds
//r.runPCAMotor(5, 800, 5000); //THIS WORKS!!!!



//COUNTER-CLOCKWISE (FAST) ≤720 degrees
////Runs a R. motor on channel 4 at speed -400 and a L. motor on channel 5 at speed -400 for 5000 milliseconds
//r.runTwoPCAMotor(4, -400, 5, -400, 5000);

//CLOCKWISE (SLOW) ≤180 degrees
//Runs a motor on channel 0 at speed 400 and a motor on channel 1 at speed -400 for 2000 milliseconds
//r.runTwoPCAMotor(4, 400, 5, 400, 5000); // This function with it's parameters will rotate
