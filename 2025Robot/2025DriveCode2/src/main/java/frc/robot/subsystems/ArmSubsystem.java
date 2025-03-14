// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import java.util.function.Supplier;


public class ArmSubsystem extends SubsystemBase {
  /** Creates a new ArmSubsystem. */
  SparkMax armMotor = new SparkMax(Constants.armMotorPort, MotorType.kBrushless);

  double reducedSpeed;
  double deadbandSpeed;

  public ArmSubsystem() {}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public Command runArm(Supplier<Double> speed){
    reducedSpeed = speed.get() * 0.75;
    deadbandSpeed = applyDeadband(reducedSpeed, 0.1);
    if(Constants.armInverted){
    return run(() -> {
      armMotor.set(-deadbandSpeed);
    });
    }else{
      return run(() -> {
        armMotor.set(deadbandSpeed);
      });
    }
  }

  private double applyDeadband(double value, double deadband) {
    if (Math.abs(value) < deadband) {
        return 0.0;
    } else {
        // Optionally rescale the output to use full range after deadband
        return Math.copySign((Math.abs(value) - deadband) / (1.0 - deadband), value);
    }
}
}
