package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
// import edu.wpi.first.wpilibj.CAN;
// import edu.wpi.first.wpilibj2.command.Command;

import edu.wpi.first.math.controller.PIDController;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkLowLevel.MotorType;
import com.revrobotics.CANAnalog.AnalogMode;

public class SwerveModule extends SubsystemBase {
    
    private final CANSparkMax driveMotor;
    private final CANSparkMax turnMotor;
    
    private final PIDController turnPIDController;

    private final double encoderOffset;

    public SwerveModule(int driveMotorID, int turnMotorID, double encoderOffset){
        this.driveMotor = new CANSparkMax(driveMotorID, MotorType.kBrushless);
        this.turnMotor = new CANSparkMax(turnMotorID, MotorType.kBrushless);

        this.encoderOffset = encoderOffset;

        turnPIDController = new PIDController(1.0,0.0,0.0);
        turnPIDController.enableContinuousInput(0, 360);
    }

    public double getAbsoluteEncoderAngle(){
        double voltage = turnMotor.getAnalog(AnalogMode.kAbsolute).getPosition();
        double angle = ((voltage / 5.0) * 360.0 - encoderOffset + 360.0) % 360.0;
        return angle;
    }

    public void setDesiredState(double speed, double angle){
        double currentAngle = getAbsoluteEncoderAngle();
        double targetAngle = turnPIDController.calculate(currentAngle,angle);

        driveMotor.set(speed);
        turnMotor.set(targetAngle / 360.0);
    }


}
