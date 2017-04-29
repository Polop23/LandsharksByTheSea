package org.usfirst.frc.team8621.robot.subsystems;

import org.usfirst.frc.team8621.robot.RobotMap;
import org.usfirst.frc.team8621.robot.commands.RollerMove;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Roller extends Subsystem {

    VictorSP rollerMotor;
    double speed;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
    public Roller() {
    	rollerMotor = new VictorSP(RobotMap.rollerMotor);
    	
    	
    }

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new RollerMove(speed));

    }

//    public void rollerUP() {
//	if ((SmartDashboard.getNumber("Roller Speed Up", 0.5)) < .99
//		|| (SmartDashboard.getNumber("Roller Speed Up", 0.5)) > 0.05) {
//	    rollerSpeedUp = SmartDashboard.getNumber("Roller Speed Up", 0.5);
//	} else {
//	    rollerSpeedUp = 0.5;
//	}
//	rollerMotor.set(rollerSpeedUp);
//    }
//
//    public void rollerDown() {
//
//	if ((SmartDashboard.getNumber("Roller Speed Down", 0.5)) < .99
//		|| (SmartDashboard.getNumber("Roller Speed Down", 0.5)) > 0.05) {
//	    rollerSpeedDown = SmartDashboard.getNumber("Roller Speed Down", 0.5);
//	} else {
//	    rollerSpeedDown = 0.5;
//	}
//	rollerMotor.set(rollerSpeedDown);
//
//    }
    public void rollerMove(double speed) {
	rollerMotor.set(speed);
    }

    public void rollerStop() {

	rollerMotor.set(0);

    }
}
