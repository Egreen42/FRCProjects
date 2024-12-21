// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Constants.OIConstants;
import frc.robot.commands.SwerveJoystickCommand;
import frc.robot.subsystems.SwerveSubsystem;

public class RobotContainer {

  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem();

  private final XboxController driverController = new XboxController(OIConstants.kDriverControllerPort);

  public RobotContainer() {

    swerveSubsystem.setDefaultCommand(new SwerveJoystickCommand(
      swerveSubsystem,
      () -> -driverController.getRawAxis(OIConstants.kDriverYAxis),
      () -> driverController.getRawAxis(OIConstants.kDriverXAxis),
      () -> driverController.getRawAxis(OIConstants.kDriverXAxis),
      () -> !driverController.getRawButton(OIConstants.kDriverFieldOrientedButtonIdx)));

    configureBindings();
  }

  private void configureBindings() {
    if(driverController.getAButtonPressed()){
      swerveSubsystem.zeroHeading();
    }
  }

  public Command getAutonomousCommand() {
    return Commands.print("No autonomous command configured");
  }
}
