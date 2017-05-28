package org.usfirst.frc.team8621.robot.subsystems;

import org.usfirst.frc.team8621.robot.RobotMap;
import org.usfirst.frc.team8621.robot.commands.GearIntakeRun;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearIntake extends Subsystem {
	
	VictorSP gearIntake;
	double speed;
	
	
	public GearIntake() {
		gearIntake = new VictorSP(RobotMap.gearIntake);
	}

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GearIntakeRun(speed));
    }
    
   public void gearIntakeWithButton(double speed) { // manual
    	gearIntake.setSpeed(speed);
    	
    }
    
    public void stop(){
    	gearIntake.setSpeed(0);
    }
}

