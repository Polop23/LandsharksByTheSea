package org.usfirst.frc.team8621.robot;

import org.usfirst.frc.team8621.robot.commands.AutoTurningWithAntonsPID;
import org.usfirst.frc.team8621.robot.commands.GearIntakeRun;
import org.usfirst.frc.team8621.robot.commands.GryoCalibration;
import org.usfirst.frc.team8621.robot.commands.PlaceGear;
import org.usfirst.frc.team8621.robot.commands.RollerMove;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    //This is primary driving controller
	//public static XboxController xboxController0 = new XboxController(0);
    //This is secondary control controller for manipulation functions
	public static XboxController xboxController1 = new XboxController(1);
    //TODO:Please comment which button and which controller your button function uses
    //Left Bumper controller 1
    Button button16 = new JoystickButton(xboxController1, 6);
    //Right Bumper controller 1
    Button button15 = new JoystickButton(xboxController1, 5);
    Button button17 = new JoystickButton(joystick, 11);
    
    //Y or triangle button controller 0
    Button button04 = new JoystickButton(xboxController1, 4);
    //x or X button controller 0
    Button button03 = new JoystickButton(xboxController1, 3);
    
    Button button05 = new JoystickButton(xboxController1, 2);
    
    public static Joystick joystick = new Joystick(0);
    //Button button04 = new JoystickButton(joystick, 3);
    //Button button03 = new JoystickButton(joystick, 1);
    

    public OI() {
	button16.whileHeld(new RollerMove(-.5));
	button15.whileHeld(new RollerMove(-1));
	button04.whenPressed(new GearIntakeRun(1));
	button03.whenPressed(new GearIntakeRun(-1));
	button05.whenPressed(new GearIntakeRun(0));
	button17.whenPressed(new PlaceGear());
	
	
    }
}

//// CREATING BUTTONS
// One type of button is a joystick button which is any button on a
//// joystick.
// You create one by telling it which joystick it's on and which button
// number it is.
// Joystick stick = new Joystick(port);
// Button button = new JoystickButton(stick, buttonNumber);

// There are a few additional built in buttons you can use. Additionally,
// by subclassing Button you can create custom triggers and bind those to
// commands the same as any other Button.

//// TRIGGERING COMMANDS WITH BUTTONS
// Once you have a button, it's trivial to bind it to a button in one of
// three ways:

// Start the command when the button is pressed and let it run the command
// until it is finished as determined by it's isFinished method.
// button.whenPressed(new ExampleCommand());

// Run the command while the button is being held down and interrupt it once
// the button is released.
// button.whileHeld(new ExampleCommand());

// Start the command when the button is released and let it run the command
// until it is finished as determined by it's isFinished method.
// button.whenReleased(new ExampleCommand());

