package frc.robot.subsystems;

import com.studica.frc.AHRS;
import com.studica.frc.AHRS.NavXComType;

import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.DriveConstants;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;

public class SwerveSubsystem extends SubsystemBase{
    /*
     * Creating the Swerve modules
     */
    private final SwerveModule frontLeft = new SwerveModule(
        DriveConstants.kFrontLeftDriveMotorPort,
        DriveConstants.kFrontLeftTurnMotorPort,
        DriveConstants.kFrontLeftDriveEncoderReversed,
        DriveConstants.kFrontLeftTurnEncoderReversed,
        DriveConstants.kFrontLeftAbsoluteEncoderPort,
        DriveConstants.kFrontLeftAbsoluteEncoderOffsetRad,
        DriveConstants.kFrontLeftAbsoluteEncoderReversed);

    private final SwerveModule frontRight = new SwerveModule(
        DriveConstants.kFrontRightDriveMotorPort,
        DriveConstants.kFrontRightTurnMotorPort,
        DriveConstants.kFrontRightDriveEncoderReversed,
        DriveConstants.kFrontRightTurnEncoderReversed,
        DriveConstants.kFrontRightAbsoluteEncoderPort,
        DriveConstants.kFrontRightAbsoluteEncoderOffsetRad,
        DriveConstants.kFrontRightAbsoluteEncoderReversed);

    private final SwerveModule rearLeft = new SwerveModule(
        DriveConstants.kRearLeftDriveMotorPort,
        DriveConstants.kRearLeftTurnMotorPort,
        DriveConstants.kRearLeftDriveEncoderReversed,
        DriveConstants.kRearLeftTurnEncoderReversed,
        DriveConstants.kRearLeftAbsoluteEncoderPort,
        DriveConstants.kRearLeftAbsoluteEncoderOffsetRad,
        DriveConstants.kRearLeftAbsoluteEncoderReversed);

    private final SwerveModule rearRight = new SwerveModule(
        DriveConstants.kRearRightDriveMotorPort,
        DriveConstants.kRearRightTurnMotorPort,
        DriveConstants.kRearRightDriveEncoderReversed,
        DriveConstants.kRearRightTurnEncoderReversed,
        DriveConstants.kRearRightAbsoluteEncoderPort,
        DriveConstants.kRearRightAbosluteEncoderOffsetRad,
        DriveConstants.kRearRightAbosluteEncoderReversed);

    private AHRS gyro = new AHRS(NavXComType.kMXP_SPI);

    private SwerveModule[] swerveModules = {frontLeft, frontRight, rearLeft, rearRight}; //array to hold the modules to eliminate redundant code when outputting numbers

    public SwerveSubsystem(){
        new Thread(() -> {
            try {
                Thread.sleep(1000);
                zeroHeading();
            } catch (Exception e){
            }
        }).start();
    }

    public void zeroHeading(){
        gyro.reset();
    }

    public double getHeading(){
        return Math.IEEEremainder(gyro.getAngle(), 360);
    }

    public Rotation2d getRotation2d(){
        return Rotation2d.fromDegrees(getHeading());
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("Robot Heading", getHeading());

        /*
         * This block of code will display the moduleInformation on the SmartDashboard, it is used for debugging and will be 
         * commented out before competition use of the code
         */

         
         for(int i = 0; i < swerveModules.length; i++){ //loop through the array
            //Put the drive velocity on the Dashboard
            SmartDashboard.putNumber("Module " + i + " drive velocity", swerveModules[i].getDriveVelocity());

            //Put the turn velocity on the dashboard
            SmartDashboard.putNumber("Module " + i + " turn velocity", swerveModules[i].getTurnVelocity());

            //put the drive position on the dashboard
            SmartDashboard.putNumber("Module " + i + " drive position", swerveModules[i].getDrivePosition());

            //put the turn position on the dashboard
            SmartDashboard.putNumber("Module " + i + " turn position", swerveModules[i].getTurnPosition());

            //put the absolute encoder position on the dashboard in radians
            SmartDashboard.putNumber("Module " + i + " absolute encoder position", swerveModules[i].getAbsoluteEncoderRad());
        
         }
    }

    public void stopModules(){
        frontLeft.stop();
        frontRight.stop();
        rearLeft.stop();
        rearRight.stop();
    }

    public void setModuleStates(SwerveModuleState[] desiredStates){
        SwerveDriveKinematics.desaturateWheelSpeeds(desiredStates, DriveConstants.kPhysicalMaxSpeedMetersPerSecond);
        frontLeft.setDesiredState(desiredStates[0]);
        frontRight.setDesiredState(desiredStates[1]);
        rearLeft.setDesiredState(desiredStates[2]);
        rearRight.setDesiredState(desiredStates[3]);
    }
}
