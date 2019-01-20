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
  double tta = 0.65; //Target TA val
  double originalPercent, originalAngle, initialAngle, targetAngle;
  double kG = 4; //Reciprocal of guess at how much the TA will have gone between the initial TA and TTA
  double kPT = 0.1; //Proportion for initial turn
  public ApproachTargetPerpendicular() {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.m_subsystem);
    requires(Robot.driveTrain);
    requires(Robot.limelight);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    this.originalPercent = Robot.limelight.getTA();
    this.originalAngle = Robot.limelight.getSkew();
    this.targetAngle = Robot.driveTrain.getAngle() + (90 - Robot.limelight.getSkew()) / 2;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double correction = (targetAngle - Robot.driveTrain.getAngle()) * kPT;
    Robot.driveTrain.setDriveOutput(0.5-correction, 0.5+correction);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if((tta - originalPercent) / kG >= Robot.limelight.getTA() - originalPercent){
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
