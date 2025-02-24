package frc.robot.subsystems;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
//WPILIB IMPORTS
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase {
    
    private SparkMax elevatorMotor;
    private double elevatorUpSpeed;
    private double elevatorDownSpeed;

    /**
     * The elevator subsystem controlls the elevator of the robot.
     * 
     * 
     * @param elevatorMotor The motor that will move the elevator up and down
     * @param elevatorUpSpeed The speed at which the elevator will move up
     * @param elevatorDownSpeed The speed at which the elevator will move down
     */


     public ElevatorSubsystem(){ //Class Contstructor
         this.elevatorMotor = new SparkMax(Constants.MotorConstants.elevatorMotorID, MotorType.kBrushless);
         if(Constants.MotorConstants.elevatorMotorReversed){ //Reverse the elevator motor if needed
             elevatorUpSpeed = - Constants.MotorConstants.elevatorUpSpeed;
             elevatorDownSpeed = - Constants.MotorConstants.elevatorDownSpeed;
         } else {
            elevatorUpSpeed = Constants.MotorConstants.elevatorUpSpeed;
            elevatorDownSpeed = Constants.MotorConstants.elevatorDownSpeed;
         }
     }

     public void zeroEncoder(){ //Zero the encoder
        elevatorMotor.getEncoder().setPosition(0);
     }

     //Function to stop the elevotor motor
     public void stop(){ 
        elevatorMotor.stopMotor();
     }

     public Command moveElevatorUp(){
        return runOnce(()  -> elevatorMotor.set(elevatorUpSpeed));
    }

      public Command moveElevatorDown(){
         return runOnce(()  -> elevatorMotor.set(elevatorDownSpeed));
      }
}


//TODO:  Add elevator height control
