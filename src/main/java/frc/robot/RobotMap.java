/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  // DriveTrain motors
  public static final int driveTrainLeftFront = 20;
  public static final int driveTrainLeftRear = 21;
  public static final int driveTrainRightFront = 22;
  public static final int driveTrainRightRear = 23;

  // Elevator Motors
  public static final int elevatorMaster = 24;
  public static final int elevatorSlaveA = 25;
  public static final int elevatorSlaveB = 26;

  // Joysticks/Controllers
  public static final int leftJoystick = 0;
  public static final int rightJoystick = 1;
  public static final int xBoxController = 2;

  // Pneumatic Modules & their devices
  public static final int PCMOne = 11;
  public static final int driveTrainShifterForward = 0;
  public static final int driveTrainShifterReverse = 1;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
