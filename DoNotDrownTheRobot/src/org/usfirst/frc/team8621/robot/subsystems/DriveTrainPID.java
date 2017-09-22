package org.usfirst.frc.team8621.robot.subsystems;

import org.usfirst.frc.team8621.robot.RobotMap;
import org.usfirst.frc.team8621.robot.commands.DriveTrainControllerDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrainPID extends PIDSubsystem {
	
	 VictorSP frontLeftMotor;
	    VictorSP frontRightMotor;
	    VictorSP backLeftMotor;
	    VictorSP backRightMotor;
	    RobotDrive robotDrive;
	    Gyro gyro;
	    
	    double speedF;
	    double speedT;
	    
	    double turnDamp;
	     double speedDamp;
	     double output;

    // Initialize your subsystem here
    public DriveTrainPID() {
    	
    	super("DriveTrainPID", 4.0, 0.0, 0.0);
    	frontLeftMotor = new VictorSP(RobotMap.frontLeftMotor); //whats plugged in victor 0
    	frontRightMotor = new VictorSP(RobotMap.frontRightMotor); //ditto 1
    	backLeftMotor = new VictorSP(RobotMap.backLeftMotor); //ditto 2
    	backRightMotor = new VictorSP(RobotMap.backRightMotor); //ditto 3
    	robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
        //gyro = new ADXRS450_Gyro();
    	// Use these to get going:
        // setSetpoint() -  Sets where the PID controller should move the system
        //                  to
        // enable() - Enables the PID controller.
    	
    	getPIDController().setContinuous(false);
    	setSetpoint(90);
    	enable();
    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new DriveTrainControllerDrive());
    }
    
    public void DriveWithJoystick (Joystick joystick) {
        
        turnDamp = SmartDashboard.getNumber("Turn Damp", 0.5);
        speedDamp = SmartDashboard.getNumber("Speed Damp", 0.5);
        
        speedF = -1*speedDamp*joystick.getY();
        speedT = -1*turnDamp*joystick.getZ();
        
        
        robotDrive.arcadeDrive(speedF, speedT, true);
        }
    
    public void Stop() {
    	
    	robotDrive.arcadeDrive(0, 0);
    }
    
    public void AutoPID(double speedF, double speedT) {
    	robotDrive.arcadeDrive(speedF, speedT);
    }

    protected double returnPIDInput() {
        // Return your input value for the PID loop
        // e.g. a sensor, like a potentiometer:
        // yourPot.getAverageVoltage() / kYourMaxVoltage;
        return this.gyro.getAngle();
    }

    public void usePIDOutput(double output) {
        // Use output to drive your system, like a motor
        // e.g. yourMotor.set(output);
    	this.robotDrive.arcadeDrive(0, output);
    	this.output = output;
    	
    }
    public void drive(){
    	robotDrive.arcadeDrive(0, output);
    }
}
