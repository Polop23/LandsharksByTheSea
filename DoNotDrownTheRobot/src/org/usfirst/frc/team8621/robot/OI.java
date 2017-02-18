package org.usfirst.frc.team8621.robot;

import org.usfirst.frc.team8621.robot.commands.AutoTurningWithAntonsPID;
import org.usfirst.frc.team8621.robot.commands.RollerMove;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {

    public static XboxController xboxController0 = new XboxController(0);
    public static XboxController xboxController1 = new XboxController(1);
    Button button13 = new JoystickButton(xboxController1, 3);
    Button button12 = new JoystickButton(xboxController1, 2);
    Button button04 = new JoystickButton(xboxController0, 4);

    public OI() {

	button13.whileHeld(new RollerMove(SmartDashboard.getNumber("Roller speed down", -0.5)));
	button12.whileHeld(new RollerMove(SmartDashboard.getNumber("Roller speed up", 0.5)));
	button04.whenPressed(new AutoTurningWithAntonsPID(0, 1));
	
	
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
