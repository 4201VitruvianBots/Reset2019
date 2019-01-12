/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class AutoTopRocket extends CommandGroup {
  public AutoTopRocket() {
    addSequential(new DriveStraight(0.5, 0.5));
    addSequential(new TurnInPlace(10));
    addSequential(new AutoFollowTarget(0.5, 1.5, 3.14));
    //addSequential(new AutoElevator(0.5, 0.5));
  }
}
