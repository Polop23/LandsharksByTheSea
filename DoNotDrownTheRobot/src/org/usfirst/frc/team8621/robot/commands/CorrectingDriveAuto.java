package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CorrectingDriveAuto extends Command {

	
    boolean fl;
    double speedF;
    double speedT;
    
    public CorrectingDriveAuto(double speedF, double speedT, double T) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		// hopefully in the future we can input distance instead of time
		// and have the code work out the timeout value
    	requires(Robot.driveTrain);
    	setTimeout(T);
    	this.speedF = speedF;
    	this.speedT = speedT;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	fl = false; // true means it's in the process of turning, false means running
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	// IIRC the robot only diverges to the left, so I'm gonna be lazy here
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
    	if (Robot.gyro.getAngle() <= -2){
    		fl = true;
    	}
    	if (Robot.gyro.getAngle() >= 0.1){ //to compensate for gyro drift. Nope not good
    		fl = false;
    	}
    	if (fl){
    		Robot.driveTrain.AutoTurning(0, -0.3); //imo speedT should be a fixed value here
    	}
    	else if (!fl){
    		Robot.driveTrain.AutoDrive(speedF, speedT);
    	}
    	
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
