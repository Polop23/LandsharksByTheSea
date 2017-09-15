package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MathPIDTurn extends Command {

	double output;
	double x,y,z;
	
    public MathPIDTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.DriveTrainPID);
    	setTimeout(10);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.DriveTrainPID.usePIDOutput(output);
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
    	SmartDashboard.putNumber("Gyro Angles", Robot.gyro.pidGet());
    	Robot.gyro.startLiveWindowMode();
    	x=Robot.gyro.getAngle();
    	System.out.print(x);
    	System.out.print(y);
    	System.out.print(z);
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.gyro.stopLiveWindowMode();
    	Robot.DriveTrainPID.Stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
