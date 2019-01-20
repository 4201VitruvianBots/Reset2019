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
  double kPB = 0.2;
  double ds;
  double tta;
  public AutoFollowTarget(double output, double targetTA) {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.m_subsystem);
    requires(Robot.limelight);
    this.ds = output;
    this.tta = targetTA;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.limelight.isValidTarget()) {
      double correction = Robot.limelight.getTargetX() * kP;
      double paddingCorrection = (tta - Robot.limelight.getTA()) * kPB;
      Robot.driveTrain.setDriveOutput(ds*paddingCorrection + correction, ds*paddingCorrection + (-correction));
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.limelight.getTA() + tta/3 >= tta || Robot.limelight.getTA() - tta/3 <= tta) {
      return true;
    }
    else{
      return false;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
