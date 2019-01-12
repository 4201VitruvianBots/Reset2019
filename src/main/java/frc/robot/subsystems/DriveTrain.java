/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.*;
/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public WPI_TalonSRX leftFront = new WPI_TalonSRX(RobotMap.driveTrainLeftFront);
  public WPI_TalonSRX leftRear = new WPI_TalonSRX(RobotMap.driveTrainLeftRear);
  public WPI_TalonSRX rightFront = new WPI_TalonSRX(RobotMap.driveTrainRightFront);
  public WPI_TalonSRX rightRear = new WPI_TalonSRX(RobotMap.driveTrainRightRear);

  DifferentialDrive robotDrive = new DifferentialDrive(leftFront, rightFront);

  DoubleSolenoid driveTrainShifters = new DoubleSolenoid(RobotMap.PCMOne, RobotMap.driveTrainShifterForward, RobotMap.driveTrainShifterReverse);
  public AHRS navX = new AHRS(SPI.Port.kMXP);
  public DriveTrain(){
    super("DriveTrain");

    leftFront.configPeakOutputForward(1);
    leftFront.configPeakOutputReverse(-1);
    leftRear.configPeakOutputForward(1);
    leftRear.configPeakOutputReverse(-1);
    rightFront.configPeakOutputForward(1);
    rightFront.configPeakOutputReverse(-1);
    rightRear.configPeakOutputForward(1);
    rightRear.configPeakOutputReverse(-1);
    // RESET
    leftFront.setInverted(true);
    leftRear.setInverted(true);
    rightFront.setInverted(false);
    rightRear.setInverted(false);
    /*// Susan
    leftFront.setInverted(false);
    leftRear.setInverted(false);
    rightFront.setInverted(true);
    rightRear.setInverted(true);*/
    leftRear.set(ControlMode.Follower, RobotMap.driveTrainLeftFront);
    rightRear.set(ControlMode.Follower, RobotMap.driveTrainRightFront);

    leftRear.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);
    rightRear.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative);

    driveTrainShifters.setName("Shifters");
    driveTrainShifters.setSubsystem("Drive Train");
  }
  public void setDriveOutput(double leftOutput, double rightOutput) {
    leftFront.set(ControlMode.PercentOutput, leftOutput);
    rightFront.set(ControlMode.PercentOutput, rightOutput);
  }

  public void setVelocityOutput(double leftOutput, double rightOutput){
    double leftVelocity = leftOutput * 87.17134567;
    double rightVelocity = rightOutput * 87.17134567;

    leftFront.set(ControlMode.Follower, leftRear.getDeviceID());
    rightFront.set(ControlMode.Follower, rightRear.getDeviceID());
    leftRear.config_kP(0, 0.8);
    rightRear.config_kP(0, 0.8);
    leftRear.set(ControlMode.Velocity, leftVelocity);
    rightRear.set(ControlMode.Velocity, rightVelocity);
  }

  public void setBrake(){
    leftFront.setNeutralMode(NeutralMode.Brake);
    leftRear.setNeutralMode(NeutralMode.Brake);
    rightFront.setNeutralMode(NeutralMode.Brake);
    rightRear.setNeutralMode(NeutralMode.Brake);
  }

  public void setCoast(){
    leftFront.setNeutralMode(NeutralMode.Coast);
    leftRear.setNeutralMode(NeutralMode.Coast);
    rightFront.setNeutralMode(NeutralMode.Coast);
    rightRear.setNeutralMode(NeutralMode.Coast);
  }

  public void setDriveShiftHigh(){
    driveTrainShifters.set(DoubleSolenoid.Value.kForward);
  }

  public void setDriveShiftLow(){
    driveTrainShifters.set(DoubleSolenoid.Value.kReverse);
  }

  public boolean getDriveShiftStatus(){
    return driveTrainShifters.get() == DoubleSolenoid.Value.kForward ? true : false;
  }
  public double getAngle(){
    // Angle is negated due to that navX being upside-down on Susan
    return -navX.getAngle();
  }

  public void resetAngle(){
    navX.reset();
  }

  public void updateSmartDashboard() {
    SmartDashboard.putNumber("Angle", getAngle());
    SmartDashboard.putNumber("Left Encoder Count", getLeftEncoderCount());
    SmartDashboard.putNumber("Right Encoder Count", getRightEncoderCount());
    SmartDashboard.putNumber("Left Distance (in)", getLeftEncoderIN());
    SmartDashboard.putNumber("Right Distance (in)", getRightEncoderIN());
  }

  public int getLeftEncoderCount(){
    return leftRear.getSelectedSensorPosition();
  }


  public int getRightEncoderCount(){
    return rightRear.getSelectedSensorPosition();
  }

  public double getLeftEncoderIN(){
    return (getLeftEncoderCount()/4096)*19.24226;
  }

  public double getRightEncoderIN(){
    return (getRightEncoderCount()/4096)*19.24226;
  }

  public void resetEncoders(){
    rightRear.setSelectedSensorPosition(0);
    leftRear.setSelectedSensorPosition(0);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TankDriveVelocity() );
  }
}
