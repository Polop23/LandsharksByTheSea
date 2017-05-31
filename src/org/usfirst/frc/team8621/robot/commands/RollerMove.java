package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RollerMove extends Command {
    
    double speed;

    public RollerMove(double speed) {
	// Use requires() here to declare subsystem dependencies
	// eg. requires(chassis);
	requires(Robot.Roller);
	this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	speed = SmartDashboard.getNumber("Roller Speeed",0.3);
	Robot.Roller.rollerMove(speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
	return false;
    }

    // Called once after isFinished returns true
    protected void end() {
	Robot.Roller.rollerStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
	end();
    }
}
