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
public class ApproachTargetPerpendicular extends Command {
  int notPerpendicular = 3; //Turn twice as much if not within 2 degrees of perpendicular
  double kP = 0.04; //Proportion for turning
  double kPB = 1.4; //Proportion for moving
  double ds = 0.65; //Default speed multiplier
  double tta = 0.65; //Target TA val
  public ApproachTargetPerpendicular() {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.m_subsystem);
    requires(Robot.driveTrain);
    requires(Robot.limelight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    while(Robot.limelight.getSkew() < -2){
      double difference = Math.abs(Robot.limelight.getSkew()) - Math.abs(Robot.limelight.getTargetX());
    }
    if(Robot.limelight.isValidTarget()) {
      double correction = (Robot.limelight.getTargetX() * kP) * notPerpendicular;
      double paddingCorrection = (tta - Robot.limelight.getTA()) * kPB;
      Robot.driveTrain.setDriveOutput(ds*paddingCorrection + correction, ds*paddingCorrection + (-correction));
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
    Robot.driveTrain.setDriveOutput(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
