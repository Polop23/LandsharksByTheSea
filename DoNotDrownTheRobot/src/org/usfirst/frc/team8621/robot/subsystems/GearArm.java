package org.usfirst.frc.team8621.robot.subsystems;

import org.usfirst.frc.team8621.robot.RobotMap;
import org.usfirst.frc.team8621.robot.commands.GearArmWithJoystick;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.PIDSubsystem;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearArm extends PIDSubsystem {
	
	VictorSP gearMotor;
	Encoder gearEnc;
	double speed;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public GearArm() {
		super("GearArm", 1.0, 0.0, 0.0);
		gearMotor = new VictorSP(RobotMap.gearMotor);
		gearEnc = new Encoder(RobotMap.gearEncoderChannelA, RobotMap.gearEncoderChannelB);
		getPIDController().setContinuous(false);
		setSetpoint(127);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new GearArmWithJoystick());
    	
    }
    
    public void gearMoveWithJoystick(XboxController xboxController1) { // manual
    	speed = .3*xboxController1.getY(Hand.kRight);
    	//this.setSetpoint(speed);
    	gearMotor.setSpeed(speed);
    }
    
    public void gearMove(double speed) { // auto
    	//this.setSetpoint(speed);
    	gearMotor.setSpeed(speed);
    }
    
    public void gearStop() {
    	gearMotor.setSpeed(0);
    }

	@Override
	protected double returnPIDInput() {
		// TODO Auto-generated method stub
		return this.gearEnc.pidGet();
	}

	@Override
	protected void usePIDOutput(double output) {
		// TODO Auto-generated method stub
		this.gearMotor.set(output);
	}
}

