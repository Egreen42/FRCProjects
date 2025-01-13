package frc.robot.utils;

import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.ThriftyAbsoluteEncoder;
import com.revrobotics.spark.SparkClosedLoopController;
import edu.wpi.first.wpilibj.AnalogEncoder;

public class SwerveDriveModule {
    private final SparkMax driveMotor;
    private final SparkMax steerMotor;
    private final AnalogEncoder encoder;
    private final SparkClosedLoopController steerPIDController;

    public SwerveDriveModule(SparkMax driveMotor, SparkMax steerMotor, AnalogEncoder encoder) {
        this.driveMotor = driveMotor;
        this.steerMotor = steerMotor;
        this.encoder = encoder;
        this.steerPIDController = steerMotor.getPIDController();
        configureSteerMotor();
    }

    private void configureSteerMotor() {
        // Example PID settings
        steerPIDController.setP(0.1);
        steerPIDController.setI(0.0);
        steerPIDController.setD(0.0);
    }

    public void drive(double forward, double strafe, double rotation) {
        // Calculate speed and angle based on forward, strafe, and rotation input
        double speed = Math.sqrt(forward * forward + strafe * strafe);
        double angle = Math.atan2(strafe, forward);  // Use arctangent for steering angle calculation

        // Set motor speeds based on calculation
        driveMotor.set(speed);
        steerMotor.set(angle);
    }

    public void stop() {
        driveMotor.stopMotor();
        steerMotor.stopMotor();
    }
}

