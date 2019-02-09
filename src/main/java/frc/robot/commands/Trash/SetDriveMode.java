/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ArcadeDriveYZ;

/**
 * A command that allows you to switch between different drive modes (arcade drive, tank drive and arcade yz)
 */
public class SetDriveMode extends Command {
  public SetDriveMode() {
    // Use requires() here to declare subsystem dependencies
    requires(Robot.driveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.m_oi.leftButtons[4].whenReleased(new ArcadeDriveYZ());
    Robot.m_oi.leftButtons[2].whenReleased(new ArcadeDrive());
    Robot.m_oi.leftButtons[5].whenReleased(new TankDrive());
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveTrain.setDriveOutput(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
