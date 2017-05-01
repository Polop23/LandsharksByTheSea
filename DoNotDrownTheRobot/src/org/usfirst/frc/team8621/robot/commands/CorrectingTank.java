package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CorrectingTank extends Command {
	
	 boolean fl;
	 boolean flg;
	double left;
	double right;
	

    public CorrectingTank(/*double left, double right*/) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis
    	//requires(Robot.driveTrain);
    	setTimeout(15);
    	//this.left = left;
    	//this.right = right;
    	fl = false; // the code constantly switches between turn and move with fl = !fl
    	flg= false;
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.gyro.reset();
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
		/*if (Robot.gyro.getAngle() >1){
			flg = true;
		}
		if (Robot.gyro.getAngle()>= 1){
			flg = false;
		}
		
    	if (!fl){
    		Robot.driveTrain.AutoDrive(speedF, 0);
    	}
    	else {

   
    		if (flg){
    			Robot.driveTrain.AutoTurning(0, speedT - 0.002);
    		}
    		else{		
    			Robot.driveTrain.AutoTurning(0, speedT);
    		}
    	}
    	fl = !fl;*/
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.gyro.getAngle();
    	
    	
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
    }
}
