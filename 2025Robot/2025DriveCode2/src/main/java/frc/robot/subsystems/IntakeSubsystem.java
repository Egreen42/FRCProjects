// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase {
  /** Creates a new IntakeSubsystem. */
  SparkMax intakeMotor = new SparkMax(Constants.intakeMotorPort, MotorType.kBrushless);
  SparkMax intakeDeploy = new SparkMax(Constants.intakeDeployPort, MotorType.kBrushless);
  public IntakeSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Command intakeIn(){
    if(Constants.intakeInverted){
    return run(() -> {
      intakeMotor.set(-Constants.intakeInSpeed);
    });
    }else{
      return run(() -> {
        intakeMotor.set(Constants.intakeInSpeed);
      });
    }
  }

  public Command intakeOut(){
    if(Constants.intakeInverted){
    return run(() -> {
      intakeMotor.set(-Constants.intakeOutSpeed);
    });
    }else{
      return run(() -> {
        intakeMotor.set(Constants.intakeOutSpeed);
      });
    }
  }

  public Command intakeDeployOut(){
    if(Constants.intakeDeployInverted){
    return run(() -> {
      intakeDeploy.set(-Constants.intakeDeployOutSpeed);
    });
    }else{
      return run(() -> {
        intakeDeploy.set(Constants.intakeDeployOutSpeed);
      });
    }
  }

  public Command intakeDeployIn(){
    if(Constants.intakeDeployInverted){
    return run(() -> {
      intakeDeploy.set(-Constants.intakeDeployInSpeed);
    });
    }else{
      return run(() -> {
        intakeDeploy.set(Constants.intakeDeployInSpeed);
      });
    }
  }
}
