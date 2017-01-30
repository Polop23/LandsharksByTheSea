package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.OI;
import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrainControllerDrive extends Command {

    public DriveTrainControllerDrive() {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
	Robot.driveTrain.DriveWithController(OI.xboxController1);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
