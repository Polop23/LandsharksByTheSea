
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

import org.usfirst.frc.team8621.robot.commands.DriveAndTurnAuto;
import org.usfirst.frc.team8621.robot.commands.DriveAuto;
import org.usfirst.frc.team8621.robot.subsystems.DriveTrain;
import org.usfirst.frc.team8621.robot.subsystems.Roller;

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

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {
	oi = new OI();
	gyro.calibrate();

	UsbCamera camera = CameraServer.getInstance().startAutomaticCapture();
	camera.setResolution(1280, 720);	

	//chooser.addDefault("Default Auto", new ExampleCommand());
	SmartDashboard.putData("Auto mode", chooser);
	chooser.addObject("BaselineAuto", new DriveAuto(SmartDashboard.getNumber("Speed forward", .5), 0, SmartDashboard.getNumber("Time", 0.5)));
	chooser.addObject("Drive and Turn", new DriveAndTurnAuto());
	// chooser.addObject("My Auto", new MyAutoCommand());
	
	
	SmartDashboard.putNumber("Turning Angle", 90);
	SmartDashboard.putNumber("Speed forward", 0.5);
	SmartDashboard.putNumber("Speed Backward", 0.5);
	SmartDashboard.putNumber("Time", .5);
	SmartDashboard.putNumber("Turn Damp", 0.5);
	SmartDashboard.putNumber("Speed Damp", 0.5);
	
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
	SmartDashboard.putNumber("Gyro Angle", gyroAngle);
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
}
