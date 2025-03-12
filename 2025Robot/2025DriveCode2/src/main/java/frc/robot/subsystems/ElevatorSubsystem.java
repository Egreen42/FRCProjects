// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import java.util.function.Supplier;

public class ElevatorSubsystem extends SubsystemBase {
  /** Creates a new ElevatorSubsystem. */
  SparkMax elevatorMotor = new SparkMax(0, MotorType.kBrushless);
  public ElevatorSubsystem() {
  }

  public Command runElevator(Supplier<Double> speed){
    if(Constants.elevatorInverted){
    return run(() -> {
      elevatorMotor.set(-speed.get());
    });
    }else{
      return run(() -> {
        elevatorMotor.set(speed.get());
      });
    }
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
