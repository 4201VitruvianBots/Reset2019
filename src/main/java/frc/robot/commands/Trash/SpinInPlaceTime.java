/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class SpinInPlaceTime extends Command {
  double time, output;
  Timer stopwatch = new Timer();
  public SpinInPlaceTime(double time, double output) {
    // Use requires() here to declare subsystem dependencies
    // requires(Robot.m_subsystem);
    requires(Robot.driveTrain);
    this.time = time;
    this.output = output;
    setTimeout(time);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    stopwatch.reset();
    stopwatch.start();
    Robot.driveTrain.resetEncoders();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.driveTrain.setDriveOutput(-output, output);
    SmartDashboard.putNumber("Stopwatch", stopwatch.get());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return isTimedOut();
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
