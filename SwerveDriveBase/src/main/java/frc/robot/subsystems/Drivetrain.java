package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.driveTrainConstants;
import com.kauailabs.navx.frc.AHRS;
import frc.robot.Constants.RobotDimensions;
import edu.wpi.first.wpilibj.SPI;

public class Drivetrain extends SubsystemBase{
    private final SwerveModule frontLeft;
    private final SwerveModule frontRight;
    private final SwerveModule rearLeft;
    private final SwerveModule rearRight;

    private double gyroAngle;

    private final AHRS gyro = new AHRS(SPI.Port.kMXP);
    
    public Drivetrain(){
        frontLeft = new SwerveModule(driveTrainConstants.leftFrontDriveID, driveTrainConstants.leftFrontSteerID, driveTrainConstants.frontLeftEncoderOffset);
        frontRight = new SwerveModule(driveTrainConstants.rightFrontDriveID, driveTrainConstants.rightFrontSteerID, driveTrainConstants.frontRightEncoderOffset);
        rearLeft = new SwerveModule(driveTrainConstants.leftRearDriveID,driveTrainConstants.leftRearSteerID, driveTrainConstants.rearLeftEncoderOffset);
        rearRight = new SwerveModule(driveTrainConstants.rightRearDriveID, driveTrainConstants.rightRearSteerID, driveTrainConstants.rearRightEnoderOffset);
    }

    public void setGyroAngle(double angle){
        this.gyroAngle = getGyroAngle();
    }

    public double getGyroAngle(){
        return gyro.getYaw();
    }

    public void resetGyro(){
        gyro.reset();
    }

    public void drive(double forward, double strafe, double rotation){
        //Field ordiented control
        double angleRad = Math.toRadians(gyroAngle);
        double tempForward = forward * Math.cos(angleRad) + strafe * Math.sin(angleRad);
        double tempStrafe = -forward * Math.sin(angleRad) + strafe * Math.cos(angleRad);

        forward = tempForward;
        strafe = tempStrafe;

        //Robot Dimensions
        double wheelBase = RobotDimensions.wheelBase;
        double trackWidth = RobotDimensions.trackWidth;
        double radius = Math.sqrt((wheelBase * wheelBase) + (trackWidth * trackWidth));

        //Calcultae wheel speeds and angles
        double a = strafe - rotation * (wheelBase / radius);
        double b = strafe + rotation * (wheelBase / radius);
        double c = forward - rotation * (trackWidth / radius);
        double d = forward + rotation * (trackWidth / radius);

        double frontLeftSpeed = Math.sqrt(b * b + d * d);
        double frontRightSpeed = Math.sqrt(b * b + c * c);
        double rearLeftSpeed = Math.sqrt(a * a + d * d);
        double rearRightSpeed = Math.sqrt(a * a + c + c);

        double frontLeftAngle = Math.toDegrees(Math.atan2(d,b));
        double frontRightAngle = Math.toDegrees(Math.atan2(c,b));
        double rearLeftAngle = Math.toDegrees(Math.atan2(d,a));
        double rearRightAngle = Math.toDegrees(Math.atan2(c,a));

        //Normalize speeds
        double maxSpeed = Math.max(Math.max(frontLeftSpeed, frontRightSpeed), Math.max(rearLeftSpeed, rearRightSpeed));
        if (maxSpeed > 1.0){
            frontLeftSpeed /= maxSpeed;
            frontRightSpeed /= maxSpeed;
            rearLeftSpeed /= maxSpeed;
            rearRightSpeed /= maxSpeed;
        }

        //set wheel speeds and angles
        frontLeft.setDesiredState(frontLeftSpeed, frontLeftAngle);
        frontRight.setDesiredState(frontRightSpeed, frontRightAngle);
        rearLeft.setDesiredState(rearLeftSpeed, rearLeftAngle);
        rearRight.setDesiredState(rearRightSpeed, rearRightAngle);

    }

}
