package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
//import edu.wpi.first.wpilibj.motorcontrol.SparkMax;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import edu.wpi.first.wpilibj.AnalogEncoder;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.*;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.SwerveDriveSubsystem;
import frc.robot.utils.SwerveDriveModule;
import frc.robot.utils.ThriftyAbsoluteEncoder;

public class Robot extends TimedRobot {
  
    // Define motors and encoders
    private SparkMax frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor;
    private SparkMax frontLeftSteer, frontRightSteer, backLeftSteer, backRightSteer;
    private ThriftyAbsoluteEncoder frontLeftEncoder, frontRightEncoder, backLeftEncoder, backRightEncoder;
    private SwerveDriveSubsystem swerveDrive;

    // Constants
    private final double maxSpeed = 3.0; // m/s, max speed of robot
    private final double maxRotationSpeed = 3.0; // rad/s

    @Override
    public void robotInit() {
        // Initialize motors and encoders for each swerve module (drive and steer motors)
        frontLeftMotor = new SparkMax(1, MotorType.kBrushless);
        frontRightMotor = new SparkMax(2, MotorType.kBrushless);
        backLeftMotor = new SparkMax(3, MotorType.kBrushless);
        backRightMotor = new SparkMax(4, MotorType.kBrushless);

        frontLeftSteer = new SparkMax(5, MotorType.kBrushless);
        frontRightSteer = new SparkMax(6, MotorType.kBrushless);
        backLeftSteer = new SparkMax(7, MotorType.kBrushless);
        backRightSteer = new SparkMax(8, MotorType.kBrushless);

        // Initialize Thrifty Absolute Encoders (Use your specific encoder interface)
        frontLeftEncoder = new AnalogEncoder(0);
        

        // Initialize SwerveDriveSubsystem
        swerveDrive = new SwerveDriveSubsystem(
            new SwerveDriveModule(frontLeftMotor, frontLeftSteer, frontLeftEncoder),
            new SwerveDriveModule(frontRightMotor, frontRightSteer, frontRightEncoder),
            new SwerveDriveModule(backLeftMotor, backLeftSteer, backLeftEncoder),
            new SwerveDriveModule(backRightMotor, backRightSteer, backRightEncoder)
        );
    }

    @Override
    public void teleopPeriodic() {
        // Get joystick inputs (e.g., from Xbox controller or Joystick)
        double forward = -m_oi.getJoystick().getY(); // Forward/backward (invert for proper direction)
        double strafe = m_oi.getJoystick().getX();  // Left/right
        double rotation = m_oi.getJoystick().getZ(); // Rotation (turn)

        // Apply speed scaling
        forward *= maxSpeed;
        strafe *= maxSpeed;
        rotation *= maxRotationSpeed;

        // Field-Oriented Control (FOC) to maintain robot orientation regardless of robot heading
        swerveDrive.drive(forward, strafe, rotation);
    }

    @Override
    public void autonomousPeriodic() {
        // Implement autonomous driving logic if needed (example: drive straight)
        swerveDrive.drive(3.0, 0.0, 0.0); // Drive forward at 3 m/s
    }

    @Override
    public void disabledInit() {
        // Code to run when the robot is disabled (e.g., stop motors, reset encoders)
        swerveDrive.stop();
    }
}
