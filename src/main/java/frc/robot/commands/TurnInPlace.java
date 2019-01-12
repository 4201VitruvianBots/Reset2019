/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * Turns LEFT
 */
public class TurnInPlace extends Command {
  double initialAngle;
  double targetAngle;
  double turnDegrees;
  double kP = 0.0087;
  public TurnInPlace(double degrees) {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.m_subsystem);
    requires(Robot.driveTrain);
    turnDegrees = degrees;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initialAngle = Robot.driveTrain.getAngle();
    targetAngle = initialAngle + turnDegrees;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double correction = (targetAngle - Robot.driveTrain.getAngle()) * kP;
    Robot.driveTrain.setDriveOutput(-correction, correction);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Math.abs(targetAngle - Robot.driveTrain.getAngle()) < 0.2){
      return true;
    }
    else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.setDriveOutput(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
