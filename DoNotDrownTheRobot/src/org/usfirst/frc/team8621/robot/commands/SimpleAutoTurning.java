package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class SimpleAutoTurning extends Command {
	
	double speedF;
	double speedT;
	//AnalogGyro gyro = new AnalogGyro(0);
	double gyroAngle = Robot.gyro.getAngle();
	double setGyroAngle = SmartDashboard.getNumber("Turning Angle", 90);
	

    public SimpleAutoTurning(double speedF, double speedT) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	//requires(Robot.gyro);
    	//setTimeout(T);
    	this.speedF = speedF;
    	this.speedT = speedT;
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.AutoTurning(0, speedT);
    	//gyro.calibrate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	//TODO:Fixed maybe Set error bounds
    	boolean error = false;
    	if (setGyroAngle == Robot.gyro.getAngle()){
    		error = true;
    		
    	} else if (setGyroAngle+2 <= Robot.gyro.getAngle()||setGyroAngle-2 >= Robot.gyro.getAngle()){
    		error = true;
    	} else{
    		error = false;
    	}
    	
        return error;
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
