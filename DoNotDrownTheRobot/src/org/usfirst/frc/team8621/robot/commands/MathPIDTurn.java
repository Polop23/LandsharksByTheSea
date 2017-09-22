package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MathPIDTurn extends Command {

	double output;
	double error;
	double error_prior;
	double integral;
	double KI;
	double KP;
	double KD;
	double derivative;
	double x,y,z;
	double speedT;
	
    public MathPIDTurn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setTimeout(10);
    	output = 1;
    	integral = 0;
    	error_prior = 0;
    	KP = SmartDashboard.getNumber("KP",1);
    	KI = SmartDashboard.getNumber("KI", 1);
    	KD = SmartDashboard.getNumber("KD", 1);
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	Robot.driveTrain.DriveWithPID(output);
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	error = 90 - Robot.gyro.getAngle();
    	integral = integral + (error*0.02);
    	derivative = (error - error_prior)/0.02;
    	output = KP*error + KI*integral + KD*derivative;
    	error_prior = error;
    	
    	Robot.driveTrain.DriveWithPID(output);
    	
    	
    	
    	
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
