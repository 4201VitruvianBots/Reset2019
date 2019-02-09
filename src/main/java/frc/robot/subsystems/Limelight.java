/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Limelight extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  static NetworkTable limelightTable = NetworkTableInstance.getDefault().getTable("limelight");

  public double getTargetX(){
    return limelightTable.getEntry("tx").getDouble(0);
  }

  public double getTA(){
     return limelightTable.getEntry("ta").getDouble(0);
  }
  public void setPipeline(int pipelineIndex) {
    limelightTable.getEntry("pipeline").setDouble(pipelineIndex);
  }
  public double getSkew(){
    return limelightTable.getEntry("ts").getDouble(0);
  }

  public boolean isValidTarget() {
    return (limelightTable.getEntry("tv").getDouble(0) == 1) ? true : false;
  }

  public void updateSmartDashboard(){
    SmartDashboard.putNumber("ts", getSkew());
    SmartDashboard.putNumber("tx", getTargetX());
    SmartDashboard.putNumber("ta", getTA());
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
