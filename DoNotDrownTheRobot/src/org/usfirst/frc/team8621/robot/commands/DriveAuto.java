package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveAuto extends Command {
	
	double speedF;
	double speedT;

    public DriveAuto(double speedF, double speedT, double T) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setTimeout(T);
    	this.speedF = speedF;
    	this.speedT = speedT;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.AutoDrive(speedF, speedT);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	//TODO:you have to call drive train in this loop
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
