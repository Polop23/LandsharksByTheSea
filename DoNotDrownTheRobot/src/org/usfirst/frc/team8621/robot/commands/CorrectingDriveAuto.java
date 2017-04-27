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
    
    public CorrectingDriveAuto(double speedF, double T) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		// hopefully in the future we can input distance instead of time
		// and have the code work out the timeout value
    	requires(Robot.driveTrain);
    	setTimeout(T);
    	this.speedF = speedF;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.TankStraight(speedF);
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
    	
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
