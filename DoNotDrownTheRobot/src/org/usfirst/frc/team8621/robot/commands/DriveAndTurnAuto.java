package org.usfirst.frc.team8621.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveAndTurnAuto extends CommandGroup {

    public DriveAndTurnAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	
    	//TODO:Fixed Maybe Change these values to be inputted from the smart DASH
    	//this may not work how you expect it to
    	addSequential(new DriveAuto(1, 0, 3));
    	addSequential(new AutoTurningWithAntonsPID(0,1));

        // To run multiple commands at the same time,
    	// use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
