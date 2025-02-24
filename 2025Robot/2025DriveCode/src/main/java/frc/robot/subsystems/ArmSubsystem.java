package frc.robot.subsystems;

import frc.robot.Constants;

//WPILIB Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;

//Rev Imports
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase{
    private SparkMax armMotor;
    private double armUpSpeed;
    private double armDownSpeed;

    /**
     * 
     * @param armMotor The motor that will move the arm up and down
     * @param armUpSpeed The speed at which the arm will move up
     * @param armDownSpeed The speed at which the arm will move down
     * 
     */

    public ArmSubsystem(){
        this.armMotor = new SparkMax(Constants.MotorConstants.armMotorID, MotorType.kBrushless);
        if(Constants.MotorConstants.armMotorReversed){ //Reverse the arm motor if needed
            armUpSpeed = - Constants.MotorConstants.armUpSpeed;
            armDownSpeed = - Constants.MotorConstants.armDownSpeed;
        } else {
            armUpSpeed = Constants.MotorConstants.armUpSpeed;
            armDownSpeed = Constants.MotorConstants.armDownSpeed;
        }
        
    }

    public Command moveArmUp(){
        return runOnce(() -> armMotor.set(armUpSpeed));
    }
    public Command moveArmDown(){
        return runOnce(() -> armMotor.set(armDownSpeed));
    }
}
