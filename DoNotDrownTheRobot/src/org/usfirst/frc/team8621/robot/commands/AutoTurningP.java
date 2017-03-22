package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurningP extends Command {
	double Kp =-0.6;
	double setSpeed;
	double error;
	boolean finished;
	double turnAngle;

    public AutoTurningP() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	turnAngle = 90;
    	SmartDashboard.putNumber("Kp", Kp);
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        setTimeout(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    Robot.gyro.reset();
    SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
    finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
    	Kp = SmartDashboard.getNumber("Kp", Kp);
    	error = Math.sqrt((turnAngle - Robot.gyro.getAngle())/turnAngle);
    	setSpeed = Kp * (error);
    	
    	if (Math.abs(Robot.gyro.getAngle() - turnAngle) >= 2.5){
    		if (Math.abs(setSpeed) > 0.4){
    			Robot.driveTrain.AutoTurning(0, setSpeed);
    		}
    		else{
    			Robot.driveTrain.AutoTurning(0,-0.4);
    		}
    	}
    	else {
    	    finished = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return finished || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	if(isFinished() == true)
    	{
    		Robot.driveTrain.Stop();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
