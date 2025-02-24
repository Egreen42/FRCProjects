package frc.robot.subsystems;

import frc.robot.Constants;

//WPILIB Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;

//REVLIB Imports
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ClimbSubsystem extends SubsystemBase{
    private SparkMax climbMotor;
    private double climbUpSpeed;
    private double climbDownSpeed;

    /**
     * 
     * @param climbMotor The motor that will move the climb up and down
     * @param climbUpSpeed The speed at which the climb will move up
     * @param climbDownSpeed The speed at which the climb will move down
     * 
     */

    public ClimbSubsystem(){
        this.climbMotor = new SparkMax(Constants.MotorConstants.climbMotorID, MotorType.kBrushless);
        if(Constants.MotorConstants.climbMotorReversed){ //Reverse the climb motor if needed
            climbUpSpeed = - Constants.MotorConstants.climbUpSpeed;
            climbDownSpeed = - Constants.MotorConstants.climbDownSpeed;
        } else {
            climbUpSpeed = Constants.MotorConstants.climbUpSpeed;
            climbDownSpeed = Constants.MotorConstants.climbDownSpeed;
        }
        
    }

    public Command moveClimbUp(){
        return runOnce(() -> climbMotor.set(climbUpSpeed));
    }
    public Command moveClimbDown(){
        return runOnce(() -> climbMotor.set(climbDownSpeed));
    }
}
