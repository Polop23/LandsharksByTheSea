package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class AutoTurningWithAntonsPID extends Command {
	
	double speedF;
	double speedT;
	AnalogGyro gyro = new AnalogGyro(0);
	double gyroAngle = gyro.getAngle();
	double setGyroAngle = SmartDashboard.getNumber("Anton's PID Turning Angle", 90);
	

    public AutoTurningWithAntonsPID(double speedF, double speedT, double T) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	setTimeout(T);
    	this.speedF = speedF;
    	this.speedT = speedT;
    	
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.AutoTurning1(speedF, speedT);
    	gyro.calibrate();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angleCompletedPercent = ((setGyroAngle - gyroAngle)/setGyroAngle);
    	Robot.driveTrain.AutoTurning1(0,(speedT-(speedT*angleCompletedPercent)));
    	
    	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (setGyroAngle == gyroAngle);
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
