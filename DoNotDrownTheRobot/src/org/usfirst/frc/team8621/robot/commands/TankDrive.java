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
	
    public TankDrive(double left, double right) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setTimeout(5);
    	this.left = left;
    	this.right = right;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	//left = .5;
    	//right = .5;
    	Robot.driveTrain.TankStraight2(left, right);
    	correct = false;
    	incorrectLeft = false;
    	incorrectRight = false;
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//TODO: replace with calculated rate
    	//TODO: Have a low margin very low 
    	if (Robot.gyro.getRate() <= 5/*calculated rate-.5*margin*/ && Robot.gyro.getAngle() >= 4/*Calculated rate + .5*margin*/) {
    		correct = true;
    	} else { 
    		correct = false;
    	}
    	if (correct = true) { 
    		right = right;
    		//Robot.driveTrain.TankStraight2(left, right);
    	}
    	if (Robot.gyro.getRate() < 5/*calculated rate*/) {
    		incorrectLeft = true;
    	} else {
    		incorrectLeft = false;
    	}
    	if (Robot.gyro.getRate() > 5/*calculated rate*/) {
    		incorrectRight = true;
    	} else {
    		incorrectRight = false;
    	}
    	if (incorrectLeft = true) {
    		right = (right*.9);
    	}
    	if (incorrectRight = true) {
    		right = (right*1.1);
    	}
    	SmartDashboard.putNumber("right power", right);
    	Robot.driveTrain.TankStraight2(left, right);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
