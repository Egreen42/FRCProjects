package frc.robot;

import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.math.geometry.Translation2d;

public class Constants {

    //function to convert inches to meters
    public static double inchesToMeters(double inches){
        return inches * 0.0254;
    }
    public final class ModuleConstants {
        public static final double kWheelDiameterMeters = inchesToMeters(4);
        public static final double kDriveMotorGearRatio = 6.25; //Example, needs to be entereed
        public static final double kTurnMotorGearRatio = 1/18.0; //Example needs to be entered
        public static final double kDriveEncoderRot2Meter = kDriveMotorGearRatio * Math.PI * kWheelDiameterMeters;
        public static final double kTurnEncoderRot2Rad = kTurnMotorGearRatio * 2 * Math.PI;
        public static final double kDriveEncoderRPM2MeterPerSec = kDriveEncoderRot2Meter / 60;
        public static final double kTurnEncoderRPM2RadPerSec = kTurnEncoderRot2Rad / 60;
        public static final double kPTurning = .5;
        public static final double kITurning = .1;
        public static final double kDTurning = 0;
    }

    public static final class DriveConstants {
        public static final double kTrackWidth = inchesToMeters(23); //Example needs to be entered
        public static final double kWheelBase = inchesToMeters(27); //Example needs to be entered

        public static final SwerveDriveKinematics kDriveKinematics = new SwerveDriveKinematics(
            new Translation2d(kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(kWheelBase / 2, kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, -kTrackWidth / 2),
            new Translation2d(-kWheelBase / 2, kTrackWidth / 2));


        /*
         * The Folloing section is all the motor ports
         */

         public static final int kFrontLeftDriveMotorPort = 9;
         public static final int kRearLeftDriveMotorPort = 3;
         public static final int kFrontRightDriveMotorPort = 7;
         public static final int kRearRightDriveMotorPort = 5;

         public static final int kFrontLeftTurnMotorPort = 8;
         public static final int kRearLeftTurnMotorPort = 2;
         public static final int kFrontRightTurnMotorPort = 6;
         public static final int kRearRightTurnMotorPort = 4;

         /*
          * The following block determines whether the encoders are reversed
          */
          
         public static final boolean kFrontLeftTurnEncoderReversed = false;
         public static final boolean kRearLeftTurnEncoderReversed = false;
         public static final boolean kFrontRightTurnEncoderReversed = false;
         public static final boolean kRearRightTurnEncoderReversed = false;

         public static final boolean kFrontLeftDriveEncoderReversed = false;
         public static final boolean kRearLeftDriveEncoderReversed = false;
         public static final boolean kFrontRightDriveEncoderReversed = false;
         public static final boolean kRearRightDriveEncoderReversed = false;

         /*
          * Absolute Encoder Ports, reversal, and offset
          */
         
          public static final int kFrontLeftAbsoluteEncoderPort = 0;
          public static final int kRearLeftAbsoluteEncoderPort = 2;
          public static final int kFrontRightAbsoluteEncoderPort = 1;
          public static final int kRearRightAbsoluteEncoderPort = 3;

          public static final boolean kFrontLeftAbsoluteEncoderReversed = true;
          public static final boolean kRearLeftAbsoluteEncoderReversed = true;
          public static final boolean kFrontRightAbsoluteEncoderReversed = true;
          public static final boolean kRearRightAbosluteEncoderReversed = true;

          public static final double kFrontLeftAbsoluteEncoderOffsetRad = 0; //needs tuned
          public static final double kRearLeftAbsoluteEncoderOffsetRad = 0; //needs tuned
          public static final double kFrontRightAbsoluteEncoderOffsetRad = 0; //needs tuned
          public static final double kRearRightAbosluteEncoderOffsetRad = 0;

          /*
           * Max Speed Numbers
           */

         public static final double kPhysicalMaxSpeedMetersPerSecond = 1;
         public static final double kPhysicalMaxAngularSpeedRadiansPersecond = 2 * 2 * Math.PI;

         public static final double kTeleDriveMaxSpeedMetersPerSecond = kPhysicalMaxSpeedMetersPerSecond / 4;
         public static final double kTeleDriveMaxAnguularSpeedRadiansPerSecond = kPhysicalMaxAngularSpeedRadiansPersecond / 4;
         public static final double kTeleDriveMaxAccelerationUnitsPerSecond  = 1;
         public static final double kTeleDriveMaxAngularAccelerationUnitsPerSecond = 1;
    }

    public static final class AutoConstants{
        /*
         * Autonomous Constants
         */
        public static final double kMaxSpeedMetersPerSecond = DriveConstants.kPhysicalMaxSpeedMetersPerSecond / 4;
        public static final double kMaxAngualrSpeedRadiansPerSecond = DriveConstants.kPhysicalMaxAngularSpeedRadiansPersecond / 10;
        public static final double kMaxAccelerationMetersPerSecondSquared = 3;
        public static final double kMaxAngularAccelerationRadiansPErSecondSquared = Math.PI / 4;
        public static final double kPXController = 1.5;
        public static final double kPYController = 1.5;
        public static final double kPThetaController = 3;

        public static final TrapezoidProfile.Constraints kThetaControllerContraints = 
            new TrapezoidProfile.Constraints(kMaxAngualrSpeedRadiansPerSecond, kMaxAngularAccelerationRadiansPErSecondSquared);

    }

    public static final class OIConstants {
        /*
         * Driver Constants
         */
        
         public static final int kDriverControllerPort = 0;

         public static final int kDriverYAxis = 1;
         public static final int kDriverXAxis = 0;
         public static final int kDriverRotAxis = 4;
         public static final int kDriverFieldOrientedButtonIdx = 1;
 
         public static final double kDeadband = 0.05;
    }
}
