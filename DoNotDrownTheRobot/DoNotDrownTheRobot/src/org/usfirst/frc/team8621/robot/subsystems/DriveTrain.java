package org.usfirst.frc.team8621.robot.subsystems;

import org.usfirst.frc.team8621.robot.RobotMap;
import org.usfirst.frc.team8621.robot.commands.DriveTrainControllerDrive;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

	
	VictorSP frontLeftMotor;
	VictorSP frontRightMotor;
	VictorSP backLeftMotor;
	VictorSP backRightMotor;
	RobotDrive robotDrive;
	
	
	public DriveTrain(){
		frontLeftMotor = new VictorSP(RobotMap.frontLeftMotor);
		frontRightMotor = new VictorSP(RobotMap.frontRightMotor);
		backLeftMotor = new VictorSP(RobotMap.backLeftMotor);
		backRightMotor = new VictorSP(RobotMap.backRightMotor);
		robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);
	//	roborDrive.setSafetyEnabled(false); if needed to stop jumpyness
		
	}
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    	setDefaultCommand(new DriveTrainControllerDrive());
    	
    }
    
    public void DriveWithController(XboxController xboxcontroller1 ){
    
    }
}

