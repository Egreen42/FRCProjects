package frc.robot.commands;

import java.util.function.Supplier;

import edu.wpi.first.math.filter.SlewRateLimiter;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.OIConstants;
import frc.robot.subsystems.SwerveSubsystem;

public class SwerveJoystickCommand extends CommandBase {
    private final SwerveSubsystem swerveSubsystem;
    private final Supplier<Double> xSpdFunction, ySpdFunction, turnSpeedFunction;
    private final Supplier<Boolean> fieldOrientedFunction;
    private final SlewRateLimiter xLimiter, yLimiter, turnLimiter;

    public SwerveJoystickCommand(SwerveSubsystem swerveSubsystem,
        Supplier<Double> xSpdFunction, Supplier<Double> ySpdFunction, Supplier<Double> turningSpdFunction,
        Supplier<Boolean> fieldOrientedFunction){
            this.swerveSubsystem = swerveSubsystem;
            this.xSpdFunction = xSpdFunction;
            this.ySpdFunction = ySpdFunction;
            this.turnSpeedFunction = turningSpdFunction;
            this.fieldOrientedFunction = fieldOrientedFunction;
            this.xLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
            this.yLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAccelerationUnitsPerSecond);
            this.turnLimiter = new SlewRateLimiter(DriveConstants.kTeleDriveMaxAngularAccelerationUnitsPerSecond);
            addRequirements(swerveSubsystem);
        }

    @Override
    public void initialize(){

    }
    
    @Override
    public void execute(){
        double xSpeed = xSpdFunction.get();
        double ySpeed = ySpdFunction.get();
        double turnSpeed = turnSpeedFunction.get();

        //Apply Deadband
        xSpeed = Math.abs(xSpeed) > OIConstants.kDeadband ? xSpeed : 0.0;
        ySpeed = Math.abs(ySpeed) > OIConstants.kDeadband ? ySpeed : 0.0;
        turnSpeed = Math.abs(turnSpeed) > OIConstants.kDeadband ? turnSpeed : 0.0;

        //Make Driving Smoother
        xSpeed = xLimiter.calculate(xSpeed) * DriveConstants.kTeleDriveMaxSpeedMetersPerSecond;
        ySpeed = yLimiter.calculate(ySpeed) * DriveConstants.kTeleDriveMaxSpeedMetersPerSecond;
        turnSpeed = turnLimiter.calculate(turnSpeed) * DriveConstants.kTeleDriveMaxAnguularSpeedRadiansPerSecond;

        //Construct desired chassis speeds
        ChassisSpeeds chassisSpeeds;
        if (fieldOrientedFunction.get()){
            //relative to field
            chassisSpeeds = ChassisSpeeds.fromFieldRelativeSpeeds(
                xSpeed, ySpeed, turnSpeed, swerveSubsystem.getRotation2d());
        } else {
            //relative to robot
            chassisSpeeds = new ChassisSpeeds(xSpeed, ySpeed, turnSpeed);
        }

        //convert chassis speeds to swerve module states
        SwerveModuleState[] moduleStates = DriveConstants.kDriveKinematics.toSwerveModuleStates(chassisSpeeds);

        //output module states to modules
        swerveSubsystem.setModuleStates(moduleStates);


            
        
    }

    @Override
    public void end(boolean interrupted){
        swerveSubsystem.stopModules();
    }

    @Override
    public boolean isFinished(){
        return false;
    }
    

}
