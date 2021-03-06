
package org.usfirst.frc.team8621.robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team8621.robot.commands.AutoTurnLeftSixty;
import org.usfirst.frc.team8621.robot.commands.AutoTurnRightSixty;
import org.usfirst.frc.team8621.robot.commands.CenterGearAutoV1;
import org.usfirst.frc.team8621.robot.commands.CenterGearAutoV2;
import org.usfirst.frc.team8621.robot.commands.CrazyTurning;
import org.usfirst.frc.team8621.robot.commands.DriveAndTurnAuto;
import org.usfirst.frc.team8621.robot.commands.DriveAndTurnV2;
import org.usfirst.frc.team8621.robot.commands.DriveAuto;
import org.usfirst.frc.team8621.robot.commands.DriveTurnleftsixtyDrive;
import org.usfirst.frc.team8621.robot.commands.DriveTurnrightsixtyDrive;
import org.usfirst.frc.team8621.robot.commands.MathPIDTurn;
import org.usfirst.frc.team8621.robot.commands.TankDrive;
import org.usfirst.frc.team8621.robot.subsystems.DriveTrain;
import org.usfirst.frc.team8621.robot.subsystems.Roller;
import org.usfirst.frc.team8621.robot.subsystems.GearArm;
import org.usfirst.frc.team8621.robot.subsystems.DriveTrainPID;
import java.lang.System.*;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

    public static OI oi;

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static final DriveTrain driveTrain = new DriveTrain();
    //public static final AnalogGyro gyro = new AnalogGyro(0);
    public static final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
    public static final boolean FLM = false;
    public static final Roller Roller = new Roller();
    public static final GearArm GearArm = new GearArm();
    //public static final DriveTrainPID DriveTrainPID = new DriveTrainPID();
    //public static final GearIntake GearIntake = new GearIntake();

	public static final String DriveTrain = null;
	private static long startTime;
	File f;
	BufferedWriter bw;
	FileWriter fw;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
	oi = new OI();
	gyro.calibrate();
	startTime = System.currentTimeMillis();
	SmartDashboard.putString("Time Elapsed", getFormattedTime(0) );

	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	camera.setResolution(1280, 720);	

	//chooser.addDefault("Default Auto", new ExampleCommand());
	SmartDashboard.putData("Auto mode", chooser);
	//XXX:this may not work how you expect it to
	//TODO:Hard Code these values and then just reupload 

	chooser.addObject("BaselineAuto", new DriveAuto(.5, 0, 5));
	chooser.addObject("Drive and Turn", new DriveAndTurnAuto());
	chooser.addObject("Drive and Turn Auto V2", new DriveAndTurnV2());
	chooser.addObject("Centergear", new CenterGearAutoV1());
	chooser.addObject("Correcting Tank", new TankDrive(.5, .5 , 5));
	chooser.addObject("Turn left 60 degrees", new DriveTurnleftsixtyDrive());
	chooser.addObject("Turn Right by 60 degrees", new DriveTurnrightsixtyDrive());
	chooser.addObject("CentergearV2", new CenterGearAutoV2());
	chooser.addObject("Anton Math Auto", new MathPIDTurn());
	// chooser.addObject("My Auto", new MyAutoCommand());
	
	
	SmartDashboard.putNumber("KI", 1);
	SmartDashboard.putNumber("KP", 1);
	SmartDashboard.putNumber("KD", 1);
	SmartDashboard.putNumber("Time", .5);
	SmartDashboard.putNumber("Turn Damp", 0.5);
	SmartDashboard.putNumber("Speed Damp", 0.5);
	//SmartDashboard.putNumber("Roller Speed", .3);
	SmartDashboard.putNumber("test time", 3);
	try {
		Map<String, String> env = System.getenv();
		f = new File(env.get("HOME") + "/Output.txt");
		if(!f.exists()){
			f.createNewFile();
		}
		fw = new FileWriter(f);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	bw = new BufferedWriter(fw);
	
	
    }

    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {
    	
    }

    @Override
    public void disabledPeriodic() {
	Scheduler.getInstance().run();
    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {
	autonomousCommand = chooser.getSelected();

	/*
	 * String autoSelected = SmartDashboard.getString("Auto Selector",
	 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
	 * = new MyAutoCommand(); break; case "Default Auto": default:
	 * autonomousCommand = new ExampleCommand(); break; }
	 */

	// schedule the autonomous command (example)
	if (autonomousCommand != null)
	    autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
    	double x = Robot.gyro.getAngle();
    	System.out.print(x);
	Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
	// This makes sure that the autonomous stops running when
	// teleop starts running. If you want the autonomous to
	// continue until interrupted by another command, remove
	// this line or comment it out.
	if (autonomousCommand != null)
	    autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {

	double gyroAngle = gyro.getAngle();
	double gyroRate = gyro.getRate();
	double encodervalue = GearArm.getPosition();
	SmartDashboard.putNumber("Gyro Angle", gyroAngle);
	SmartDashboard.putNumber("Gyro Rate", gyroRate);
	SmartDashboard.putNumber("encoderValeu", encodervalue);
	long passedTime = startTime - System.currentTimeMillis();
	SmartDashboard.putString("Time Elapsed", getFormattedTime(passedTime));
	// boolean FLMM = FLM.getBooleanProperty("FLM", fa
	// SmartDashboard.putBoolean("Front Left Motor", value);
	Scheduler.getInstance().run();
	
	

    }

    /**
     * This function is called periodically during test mode
     */
    @Override
    public void testPeriodic() {
	LiveWindow.run();
    }
    
    public static String getFormattedTime(long millis){
    	long minutes = TimeUnit.MILLISECONDS.toMinutes(millis);
    	long seconds = TimeUnit.MILLISECONDS.toSeconds(millis) -
    				   TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis));
    	long millisecs = millis - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(millis));
    	return minutes + ":" + seconds + ":" + millisecs; 
    }
    
}

