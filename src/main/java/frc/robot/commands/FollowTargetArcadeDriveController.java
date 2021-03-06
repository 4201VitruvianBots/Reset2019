/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.PIDCommand;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */

public class FollowTargetArcadeDriveController extends PIDCommand {
  static double kP = 0.04; //Proportion for turning
  static double kI = 0; //Proportion for turning
  static double kD = 0.15; //Proportion for turning
  double tta = 0.85; //Target TA val

  public FollowTargetArcadeDriveController() {
    super(kP, kI, kD);
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

    this.getPIDController().setAbsoluteTolerance(1);
    this.getPIDController().setOutputRange(-1, 1);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Robot.limelight.isValidTarget()) {
      this.getPIDController().enable();
      //double correction = Robot.limelight.getTargetX() * kP;
      //Robot.driveTrain.setDriveOutput((Robot.m_oi.getLeftY() - Robot.m_oi.getRightX()) + correction, (Robot.m_oi.getLeftY() + Robot.m_oi.getRightX()) - correction);
    } else
      this.getPIDController().disable();
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
    //Robot.driveTrain.setDriveOutput(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }

  @Override
  protected double returnPIDInput() {
    return Robot.limelight.getTargetX();
  }

  @Override
  protected void usePIDOutput(double output) {
      Robot.driveTrain.setDriveOutput((Robot.m_oi.getLeftY() - Robot.m_oi.getRightX()) - output, (Robot.m_oi.getLeftY() + Robot.m_oi.getRightX()) + output);
  }
}