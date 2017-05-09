package org.usfirst.frc.team8621.robot.subsystems;

import org.usfirst.frc.team8621.robot.RobotMap;
import org.usfirst.frc.team8621.robot.commands.GearArmWithJoystick;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearArm extends Subsystem {
	
	VictorSP gearMotor;
	double speed;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public GearArm() {
		gearMotor = new VictorSP(RobotMap.gearMotor);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GearArmWithJoystick());
    	
    }
    
    public void gearMoveWithJoystick(XboxController xboxController1) {
    	speed = -.3*xboxController1.getY(Hand.kRight);
    	gearMotor.setSpeed(speed);
    	
    }
    
    public void gearMove(double speed) {
    	gearMotor.setSpeed(speed);
    }
    
    public void gearStop() {
    	gearMotor.setSpeed(0);
    }
}

