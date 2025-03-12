// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.math.util.Units;

/** Add your docs here. */
public class Constants {
    //SwerveConstants
    public static final double maximumSpeed = Units.feetToMeters(4.5);
    public static final double deadband = 0.1;

    //Elevator Constants
    public static final int elevatorMotorPort = 0;
    public static final boolean elevatorInverted = false;

    //Arm Constants
    public static final int armMotorPort = 1;
    public static final boolean armInverted = false;

    //Intake Constants
    public static final int intakeMotorPort = 2;
    public static final int intakeDeployPort = 3;
    public static final boolean intakeInverted = false;
    public static final boolean intakeDeployInverted = false;
    public static final int intakeInSpeed = 1;
    public static final int intakeOutSpeed = -1;
    public static final double intakeDeployInSpeed = 0.5;
    public static final double intakeDeployOutSpeed = -0.5;

    //Climb Constants
    public static final int climbMotorPort = 4;
    public static final boolean climbInverted = false;
    public static final double climbDownSpeed = 0.5;
    public static final double climbUpSpeed = -0.5;


}
