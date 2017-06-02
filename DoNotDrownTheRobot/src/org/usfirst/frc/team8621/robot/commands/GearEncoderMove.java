package org.usfirst.frc.team8621.robot.commands;

import org.usfirst.frc.team8621.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearEncoderMove extends Command {

	boolean wrongup;
	boolean wrongdown;
	boolean correct;
	double error;
	
    public GearEncoderMove() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.GearArm);
    	
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.GearArm.gearMove(.3);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	error = Robot.GearArm.getPosition() - Robot.GearArm.getSetpoint();
    	if (error <= 1 && error >= -1) {
    		correct = true;
    	} else {
    		correct = false;
    	}
    	if (correct == true) {
    		Robot.GearArm.gearMove(-.05);
    	} 
    	if (error < -1 && error > -20) {
    		Robot.GearArm.gearMove(-.2);
    	}
    	if (error > 1 && error < 20) {
    		Robot.GearArm.gearMove(.2);
    	}
    	if (error < -20 ) {
    		Robot.GearArm.gearMove(-.3);
    	}
    	if (error > 20 ) {
    		Robot.GearArm.gearMove(.3);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
