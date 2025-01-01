package frc.robot.SubSystems;

import java.io.File;
import java.io.IOException;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.util.Units;

import swervelib.parser.SwerveParser;
import swervelib.SwerveDrive;
import swervelib.math.SwerveMath;
import swervelib.telemetry.SwerveDriveTelemetry;
import swervelib.telemetry.SwerveDriveTelemetry.TelemetryVerbosity;



public class SwerveSubsystem {
    //Robot Max Speed
    double maximumSpeed = Units.feetToMeters(2);
    SwerveDrive swerveDrive;

    // Constructor
    public SwerveSubsystem() {
        // Open the configuration files
        File swerveJsonDirectory = new File(Filesystem.getDeployDirectory(), "swerve");
        try {
            swerveDrive = new SwerveParser(swerveJsonDirectory).createSwerveDrive(maximumSpeed);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Telemetry Stuff
    //SwerveDriveTelemetry.verbosity = telemetryVerbosity.HIGH;

    //Command to drive the robot
    /**
     * @param translationX, Translation in the X direction
     * @param translationY, Translation in the Y direction
     * @param headingX, heading X to calculate angle of joystick
     * @param headingY, heading y to calcultate angle of joystick
     * @return Drive Command
     */
    public void driveOriented(DoubleSupplier translationX, DoubleSupplier translationY, DoubleSupplier headingX, DoubleSupplier headingY){
        Translation2d scaledInputs = SwerveMath.scaleTranslation(new Translation2d(translationX.getAsDouble(), translationY.getAsDouble()), 0.8);

        swerveDrive.driveFieldOriented(swerveDrive.swerveController.getTargetSpeeds(scaledInputs.getX(), scaledInputs.getY(),
            headingX.getAsDouble(), headingY.getAsDouble(), swerveDrive.getOdometryHeading().getRadians(), swerveDrive.getMaximumChassisVelocity()));
    }
}

