// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ClimbSubsystem extends SubsystemBase {
  /** Creates a new ClimbSubsystem. */
  SparkMax climbMotor = new SparkMax(Constants.climbMotorPort, MotorType.kBrushless);
  public ClimbSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Command climbUp(){
    if(Constants.climbInverted){
    return run(() -> {
      climbMotor.set(-Constants.climbUpSpeed);
    });
    }else{
      return run(() -> {
        climbMotor.set(Constants.climbUpSpeed);
      });
    }
  }

  public Command climbDown(){
    if(Constants.climbInverted){
    return run(() -> {
      climbMotor.set(-Constants.climbDownSpeed);
    });
    }else{
      return run(() -> {
        climbMotor.set(Constants.climbDownSpeed);
      });
    }
  }
}
