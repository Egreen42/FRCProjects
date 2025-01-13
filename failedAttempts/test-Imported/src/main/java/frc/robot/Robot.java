// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import java.time.format.SignStyle;

import com.revrobotics.RelativeEncoder;

/**
 * The methods in this class are called automatically corresponding to each mode, as described in
 * the TimedRobot documentation. If you change the name of this class or the package after creating
 * this project, you must also update the Main.java file in the project.
 */
public class Robot extends TimedRobot {
  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */

   AnalogEncoder frontLeft = new AnalogEncoder(0);
   AnalogEncoder frontRight = new AnalogEncoder(1);
   AnalogEncoder backLeft = new AnalogEncoder(2);
   AnalogEncoder backRight = new AnalogEncoder(3);

  SparkMax frontLeftDrive = new SparkMax(9, MotorType.kBrushless);
  SparkMax frontRightDrive = new SparkMax(7, MotorType.kBrushless);
  SparkMax backLeftDrive = new SparkMax(3, MotorType.kBrushless);
  SparkMax backRightDrive = new SparkMax(5, MotorType.kBrushless);

  SparkMax frontRightTurn = new SparkMax(6, MotorType.kBrushless);
  SparkMax frontLeftTurn = new SparkMax(8, MotorType.kBrushless);
  SparkMax backRightTurn = new SparkMax(4, MotorType.kBrushless);
  SparkMax backLeftTurn = new SparkMax(2, MotorType.kBrushless);

  RelativeEncoder frontLeftDriveEncoder = frontLeftDrive.getEncoder();
  RelativeEncoder frontRightDriveEncoder = frontRightDrive.getEncoder();
  RelativeEncoder backLeftDriveEncoder = backLeftDrive.getEncoder();
  RelativeEncoder backRightDriveEncoder = backRightDrive.getEncoder();

  RelativeEncoder frontLeftTurnEncoder = frontLeftTurn.getEncoder();
  RelativeEncoder frontRightTurnEncoder = frontRightTurn.getEncoder();
  RelativeEncoder backLeftTurnEncoder = backLeftTurn.getEncoder();
  RelativeEncoder backRightTurnEncoder = backRightTurn.getEncoder();

  AHRS navX = new AHRS(NavXComType.kMXP_SPI);



  public Robot() {}

  @Override
  public void robotPeriodic() {
    SmartDashboard.putNumber("Front Left", frontLeft.get()*360);
    SmartDashboard.putNumber("Front Right", frontRight.get()*360);
    SmartDashboard.putNumber("Back Left", backLeft.get()*360);
    SmartDashboard.putNumber("Back Right", backRight.get()*360);

    SmartDashboard.putNumber("Front Left Drive", frontLeftDriveEncoder.getPosition());
    SmartDashboard.putNumber("Front Right Drive", frontRightDriveEncoder.getPosition());
    SmartDashboard.putNumber("Back Left Drive", backLeftDriveEncoder.getPosition());
    SmartDashboard.putNumber("Back Right Drive", backRightDriveEncoder.getPosition());

    SmartDashboard.putNumber("Front Left Turn", frontLeftTurnEncoder.getPosition());
    SmartDashboard.putNumber("Front Right Turn", frontRightTurnEncoder.getPosition());
    SmartDashboard.putNumber("Back Left Turn", backLeftTurnEncoder.getPosition());
    SmartDashboard.putNumber("Back Right Turn", backRightTurnEncoder.getPosition());

    SmartDashboard.putNumber("Yaw", navX.getYaw());
  }

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {}

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {}

  @Override
  public void simulationInit() {}

  @Override
  public void simulationPeriodic() {}
}
