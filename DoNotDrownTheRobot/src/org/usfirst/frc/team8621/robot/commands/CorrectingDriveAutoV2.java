package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CorrectingDriveAutoV2 extends Command {

	
    boolean fl;
    boolean flg;
    double speedF;
    double speedT;
    
    public CorrectingDriveAutoV2(double speedF, double speedT, double T) {
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
    	flg= false; //false: slower turn true: faster turn
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	SmartDashboard.putNumber("Gyro Angle", Robot.gyro.getAngle());
    	if (!fl){
    		Robot.driveTrain.AutoDrive(speedF, 0);
    	}
    	else {
    		if (Robot.gyro.getAngle()<= -1){
    			flg = true;
    		}
    		if (Robot.gyro.getAngle()>= 0.5){
    			flg = false;
    		}
    		if (flg){
    			Robot.driveTrain.AutoTurning(0, -0.202);
    		}
    		else{		
    			Robot.driveTrain.AutoTurning(0, speedT);
    		}
    	}
    	fl = !fl;
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
