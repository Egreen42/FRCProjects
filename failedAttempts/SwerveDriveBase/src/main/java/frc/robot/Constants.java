// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static class OperatorConstants {
    public static final int kDriverControllerPort = 0;
  }

  public static class driveTrainConstants {

    //CAN IDs for the drive motors
    public static final int leftFrontDriveID = 0;
    public static final int rightFrontDriveID = 1;
    public static final int leftRearDriveID = 2;
    public static final int rightRearDriveID = 3;

    //CAN IDs for the steer motors
    public static final int leftFrontSteerID = 0;
    public static final int rightFrontSteerID = 1;
    public static final int leftRearSteerID = 2;
    public static final int rightRearSteerID = 3;

    //Encoder Values
    public static final double frontLeftEncoderOffset = 0.0;
    public static final double frontRightEncoderOffset = 0.0;
    public static final double rearLeftEncoderOffset = 0.0;
    public static final double rearRightEnoderOffset = 0.0;
    public static final double countsPerRev = 4096;

  }

  public static class RobotDimensions{
    public static final double wheelBase = 1;
    public static final double trackWidth = 1;
  }
}
