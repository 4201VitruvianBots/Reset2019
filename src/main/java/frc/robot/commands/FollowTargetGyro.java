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
 * NOTE: This is an EXPERIMENTAL UNTESTED command that WILL NOT STOP THE ROBOT. It is only intended to use the gyro to align and move forward.
 */

public class FollowTargetGyro extends Command {
  double kP = 0.04; //Proportion for turning
  double kPB = 1.4; //Proportion for moving
  double ds = 0.5; //Default speed multiplier
  double tta = 0.85; //Target TA val
  double targetAngle;
  public FollowTargetGyro() {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.m_subsystem);
    requires(Robot.limelight);
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.limelight.setPipeline(1);
    Robot.driveTrain.setBrake();
    Robot.driveTrain.resetAngle();
    this.targetAngle = Robot.limelight.getTargetX();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.limelight.isValidTarget()) {
      double correction = Robot.driveTrain.getAngle() * kP;
      Robot.driveTrain.setDriveOutput(ds + correction, ds - correction);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.setCoast();
    Robot.driveTrain.setDriveOutput(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}