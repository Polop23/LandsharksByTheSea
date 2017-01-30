package org.usfirst.frc.team8621.robot.subsystems;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Roller extends Subsystem {

    VictorSP rollerMotor;

    double rollerSpeedUp;
    double rollerSpeedDown;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());

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
