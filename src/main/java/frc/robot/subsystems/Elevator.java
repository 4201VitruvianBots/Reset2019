/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.RobotMap;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Elevator extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX master = new WPI_TalonSRX(RobotMap.elevatorMaster);
  public WPI_TalonSRX slaveA = new WPI_TalonSRX(RobotMap.elevatorSlaveA);
  public WPI_TalonSRX slaveB = new WPI_TalonSRX(RobotMap.elevatorSlaveB);

  public Elevator() {
    super("ElevatorControl");

    slaveA.set(ControlMode.Follower, RobotMap.elevatorMaster);
    slaveB.set(ControlMode.Follower, RobotMap.elevatorMaster);
  }

  public void setElevatorOutput(double output){
    master.set(ControlMode.PercentOutput, output);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    // setDefaultCommand(new ElevatorControl());
  }
}
