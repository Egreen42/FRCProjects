// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Commands.AlignToTagXAndThetaCommand;

import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.subsystems.LimelightManagerSubsystem;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ClimbSubsystem;

import java.util.function.Supplier;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;

import swervelib.SwerveInputStream;

public class RobotContainer {
  private final SwerveSubsystem swerveSubsystem = new SwerveSubsystem(); 
  private final ElevatorSubsystem elevatorSubsystem = new ElevatorSubsystem();
  private final ArmSubsystem armSubsystem = new ArmSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ClimbSubsystem climbSubsystem = new ClimbSubsystem();

  private final LimelightManagerSubsystem limelightManager = new LimelightManagerSubsystem();

  private CommandXboxController driverController = new CommandXboxController(0);
  private CommandXboxController manipController = new CommandXboxController(1);

  SwerveInputStream driveAngularVelocity = SwerveInputStream.of(swerveSubsystem.getSwerveDrive(), 
                                                                () -> driverController.getLeftY() * - 1, 
                                                                () -> driverController.getLeftX() * - 1)
                                                                .withControllerRotationAxis(driverController::getRightX)
                                                                .deadband(Constants.deadband)
                                                                .scaleTranslation(0.8)
                                                                .allianceRelativeControl(true);

  SwerveInputStream driveDirectAngle = driveAngularVelocity.copy().withControllerHeadingAxis(driverController::getRightX, 
                                                                                            driverController::getRightY)
                                                                                            .headingWhile(true);

  Command driveFeildOrientatedDirectAngle = swerveSubsystem.driveFeildOriented(driveDirectAngle);
  Command driveFeildOrientatedAngularVelocity = swerveSubsystem.driveFeildOriented(driveAngularVelocity);
  Command driveRobotOrientatedAngularVelocity = swerveSubsystem.driveRobotOriented(driveAngularVelocity);

  private Command autoAlignLeftCommand(Supplier<Double> driverYInput) {
    limelightManager.setActiveLimelight(true); // Use Limelight A (left side)
    return new AlignToTagXAndThetaCommand(limelightManager, swerveSubsystem, driverYInput);
}

private Command autoAlignRightCommand(Supplier<Double> driverYInput) {
    limelightManager.setActiveLimelight(false); // Use Limelight B (right side)
    return new AlignToTagXAndThetaCommand(limelightManager, swerveSubsystem, driverYInput);
}

//MANIP COMMANDS
Command runElevator = elevatorSubsystem.runElevator(manipController::getRightY);
Command runArm = armSubsystem.runArm(manipController::getLeftY);

SendableChooser<Command> autoChooser;

  public RobotContainer() {

    autoChooser = AutoBuilder.buildAutoChooser();
    SmartDashboard.putData("Auto Chooser", autoChooser);

    configureBindings();
    swerveSubsystem.setDefaultCommand(driveFeildOrientatedAngularVelocity); //set drivebase to drive feild oriented
    elevatorSubsystem.setDefaultCommand(runElevator); //set elevator to run elevator using stick
    armSubsystem.setDefaultCommand(runArm); //set arm to run arm using stick
  }

  private void configureBindings() {

    //DRIVER BINDINGS
    driverController.leftBumper().whileTrue(autoAlignLeftCommand(driverController::getRightY)); //Align to left reef
    driverController.rightBumper().whileTrue(autoAlignRightCommand(driverController::getRightY)); //Align to right reef

    driverController.leftTrigger().whileTrue(climbSubsystem.climbDown());
    driverController.rightTrigger().whileTrue(climbSubsystem.climbUp());

    //MANIP BINDINGS
    manipController.leftBumper().whileTrue(intakeSubsystem.intakeDeployIn());
    manipController.rightBumper().whileTrue(intakeSubsystem.intakeDeployOut());
    manipController.leftTrigger().whileTrue(intakeSubsystem.intakeIn());
    manipController.rightTrigger().whileTrue(intakeSubsystem.intakeOut());
    
  }

  public Command getAutonomousCommand() {
    //return swerveSubsystem.getAutonomousCommand("New Auto"); //Manually select auto by typing here TESTED WORKIKNG
    return autoChooser.getSelected(); //Get the selected from path planner chooser  NOT TESTED
  }
}



