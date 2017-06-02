package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class TankDrive extends Command {

	double left;
	double right;
	boolean correct;
	boolean incorrectLeft;
	boolean incorrectRight;
	double t;
	
    public TankDrive(double left, double right, double t) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	this.left = left;
    	this.right = right;
    	this.t=t;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	// Robot.gyro.calibrate();
    	left = .5;
    	right = .5;
    	//t = SmartDashboard.getNumber("test time", 3);
    	setTimeout(t);
    	Robot.driveTrain.TankStraight2(left, right);
    	correct = false;
    	incorrectLeft = false;
    	incorrectRight = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: replace with calculated rate
    	//TODO: Have a low margin very low 
    	if (Robot.gyro.getAngle() <= 0.25/*calculated rate-.5*margin*/ && Robot.gyro.getAngle() >= -0.25/*Calculated rate + .5*margin*/) {
    		correct = true;
    	} else { 
    		correct = false;
    	}
    	if (correct == true) { 
    		right = right;
    		//Robot.driveTrain.TankStraight2(left, right);
    	}
    	if (Robot.gyro.getAngle() < -0.25/*calculated rate*/) {
    		incorrectLeft = true;
    	} else {
    		incorrectLeft = false;
    	}
    	if (Robot.gyro.getAngle() > 0.25/*calculated rate*/) {
    		incorrectRight = true;
    	} else {
    		incorrectRight = false;
    	}
    	if (incorrectLeft == true) {
    		right = (right*.999);
    	}
    	if (incorrectRight == true) {
    		right = (right*1.001);
    	}
    	SmartDashboard.putNumber("right power", right);
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
    	Robot.driveTrain.TankStraight2(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
