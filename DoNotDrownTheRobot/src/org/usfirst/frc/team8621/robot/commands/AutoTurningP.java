package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurningP extends Command {
	double Kp = 0.6;
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
        finished = false;
        setTimeout(5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	SmartDashboard.putNumber("gyroAngle", Robot.gyro.getAngle());
    	Kp = SmartDashboard.getNumber("Kp", Kp);
    	error = (turnAngle - Robot.gyro.getAngle())/90.0;
    	setSpeed = Kp * (error);
    	
    	if (Math.abs(Robot.gyro.getAngle() - turnAngle) >= 2.5){
    		Robot.driveTrain.AutoTurning(0, setSpeed);
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
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
