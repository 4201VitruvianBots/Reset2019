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
 * An example command.  You can replace me with your own command.
 */

public class AutoFollowTarget extends Command {
  double initialAngle;
  double targetAngle;
  double kP = 0.04;
  double kPB = 1.4;
  double ds;
  double tta;
  public AutoFollowTarget(double output, double targetTA) {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.m_subsystem);
    requires(Robot.limelight);
    requires(Robot.driveTrain);
    this.ds = output;
    this.tta = targetTA;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveTrain.setBrake();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.limelight.isValidTarget()) {
      double correction = Robot.limelight.getTargetX() * kP;
      double paddingCorrection = ds * (tta - Robot.limelight.getTA()) * kPB;
      Robot.driveTrain.setDriveOutput(paddingCorrection + correction, paddingCorrection + (-correction));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.driveTrain.getLeftRPM() <= 0 && Robot.driveTrain.getRightRPM() <= 0) {
      return true;
    }
    else{
      return false;
    }
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
