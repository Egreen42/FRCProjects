package frc.robot.Commands;

import frc.robot.subsystems.LimelightManagerSubsystem;
import frc.robot.subsystems.SwerveSubsystem;
import frc.robot.LimelightHelpers;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.MathUtil;

import java.util.function.Supplier;


public class AlignToTagXAndThetaCommand extends Command {
    private final LimelightManagerSubsystem limelightManager;
    private final SwerveSubsystem swerve;
    private final Supplier<Double> driverYAxisInput; // Driver Y-axis input supplier

    private final PIDController xController = new PIDController(0.05, 0, 0); // Tune this
    private final PIDController thetaController = new PIDController(0.01, 0, 0); // Tune this

    public AlignToTagXAndThetaCommand(LimelightManagerSubsystem limelightManager, SwerveSubsystem swerve, Supplier<Double> driverYAxisInput) {
        this.limelightManager = limelightManager;
        this.swerve = swerve;
        this.driverYAxisInput = driverYAxisInput;
        addRequirements(swerve);
    }

    @Override
    public void initialize() {
        xController.setTolerance(1.0); // Tolerance for alignment in X
        thetaController.setTolerance(3.0); // Degrees tolerance for rotation
    }

    @Override
    public void execute() {
        if (limelightManager.hasValidTarget()) {
            double tx = limelightManager.getTx(); // Horizontal offset
            double yInput = driverYAxisInput.get(); // Driver control on Y-axis

            // Optional: Get rotation/yaw from target pose (you may need to validate pipeline data)
            Pose3d targetPose = LimelightHelpers.getTargetPose3d_RobotSpace(limelightManager.getActiveLimelight());
            double targetYaw = targetPose.getRotation().getY(); // Check which axis for yaw

            // PID control for X and Rotation
            double xSpeed = -xController.calculate(tx, 0); // We want TX to reach 0
            double thetaSpeed = -thetaController.calculate(targetYaw, 0); // Align rotation to 0 (facing tag)

            // You may want to cap speeds
            xSpeed = MathUtil.clamp(xSpeed, -0.5, 0.5);
            thetaSpeed = MathUtil.clamp(thetaSpeed, -0.5, 0.5);

            // Drive with auto X & Theta, manual Y
            swerve.drive(new Translation2d(xSpeed, yInput), thetaSpeed, true, false);

        } else {
            // No target — allow full manual control on Y, stop X & Theta
            swerve.drive(new Translation2d(0, driverYAxisInput.get()), 0, true, false);
        }
    }

    @Override
    public boolean isFinished() {
        // Optional: Could be always running unless interrupted OR finish when aligned
        return xController.atSetpoint() && thetaController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        swerve.drive(new Translation2d(0, 0), 0, true, false); // Stop if ended
    }
}
