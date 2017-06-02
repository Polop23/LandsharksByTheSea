package org.usfirst.frc.team8621.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class DriveTurnrightsixtyDrive extends CommandGroup {

    public DriveTurnrightsixtyDrive() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.
    	addParallel(new GearEncoderMove());
    	addSequential(new TankDrive(0.5, 0.5, 3));
    	addSequential(new AutoTurnRightSixty(9));
    	addSequential(new Pause(0.5));
    	addSequential(new TankDrive(0.5, 0.5, 2));
    	addSequential(new PlaceGear());
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
