package org.usfirst.frc.team8621.robot.subsystems;

import org.usfirst.frc.team8621.robot.RobotMap;
import org.usfirst.frc.team8621.robot.commands.DriveTrainControllerDrive;

import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveTrain extends Subsystem {

    VictorSP frontLeftMotor;
    VictorSP frontRightMotor;
    VictorSP backLeftMotor;
    VictorSP backRightMotor;
    RobotDrive robotDrive;
    
    double speedF;
    double speedT;

    // double turnDamp = 0.5;
    // double speedDamp = 0.5;

    public DriveTrain() {
	frontLeftMotor = new VictorSP(RobotMap.frontLeftMotor);
	frontRightMotor = new VictorSP(RobotMap.frontRightMotor);
	backLeftMotor = new VictorSP(RobotMap.backLeftMotor);
	backRightMotor = new VictorSP(RobotMap.backRightMotor);
	robotDrive = new RobotDrive(frontLeftMotor, backLeftMotor, frontRightMotor, backRightMotor);

	// roborDrive.setSafetyEnabled(false); if needed to stop jumpyness

    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
	// Set the default command for a subsystem here.
	// setDefaultCommand(new MySpecialCommand());

	setDefaultCommand(new DriveTrainControllerDrive());

    }

    /*
     * public static final Boolean FLM = new Boolean(false) {
     * if(xboxController1.getX(Hand.kLeft)>0||xboxController1.getX(Hand.kLeft)<0
     * ||xboxController1.getY(Hand.kRight)>0||xboxController1.getY(Hand.kRight)<
     * 0){ return true; }else{ return false; } }
     */
    //TODO: funtions dont start with capitals
    public void DriveWithController(XboxController xboxController) {

	// turnDamp = SmartDashboard.getNumber("Turn Damp", 0.5);
	// speedDamp = SmartDashboard.getNumber("Speed Damp", 0.5);
    	
    speedF = xboxController.getY(Hand.kRight);
    speedT = xboxController.getX(Hand.kLeft);
    
	robotDrive.arcadeDrive(speedF, speedT, true);

    }
    
    public void AutoDrive(double speedF, double speedT) {
    	robotDrive.arcadeDrive(speedF, speedT);
    }
    
    public void Stop() {
    	
    	robotDrive.arcadeDrive(0, 0);
    }

}