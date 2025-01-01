package frc.robot;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.SwerveDriveModule;

public class SwerveDriveSubsystem extends SubsystemBase {
    private final SwerveDriveModule frontLeftModule;
    private final SwerveDriveModule frontRightModule;
    private final SwerveDriveModule backLeftModule;
    private final SwerveDriveModule backRightModule;

    public SwerveDriveSubsystem(SwerveDriveModule frontLeft, SwerveDriveModule frontRight, SwerveDriveModule backLeft, SwerveDriveModule backRight) {
        this.frontLeftModule = frontLeft;
        this.frontRightModule = frontRight;
        this.backLeftModule = backLeft;
        this.backRightModule = backRight;
    }

    // Swerve drive logic (field-oriented control)
    public void drive(double forward, double strafe, double rotation) {
        // Implement Field-Oriented Control (FOC)
        // Calculate wheel speeds and angles for each swerve module
        frontLeftModule.drive(forward, strafe, rotation);
        frontRightModule.drive(forward, strafe, rotation);
        backLeftModule.drive(forward, strafe, rotation);
        backRightModule.drive(forward, strafe, rotation);
    }

    public void stop() {
        frontLeftModule.stop();
        frontRightModule.stop();
        backLeftModule.stop();
        backRightModule.stop();
    }
}
