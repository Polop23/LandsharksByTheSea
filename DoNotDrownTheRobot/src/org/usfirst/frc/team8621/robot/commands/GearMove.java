package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearMove extends Command {

	double T;
    public GearMove(double T) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	this.T = T;
    	requires(Robot.GearArm);
    	setTimeout(T);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.GearArm.gearMove(.3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.GearArm.gearStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
