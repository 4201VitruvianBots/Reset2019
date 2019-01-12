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
import frc.robot.commands.DriveStraight;


/**
 * An example command.  You can replace me with your own command.
 */
public class DrivePolygon extends CommandGroup {
  public DrivePolygon(int sides, double time) {
    for(int i = 0; i < sides; i++) {
      addSequential(new DriveStraight(0.5, time));
      addSequential(new TurnInPlace(360 / sides));
    }
  }
}
