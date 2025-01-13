package frc.robot.subsystems;

//File Imports
import frc.robot.Constants;

//WPILib imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;

//Rev Imports
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;


public class ManipulatorSubsystem extends SubsystemBase {
    
    private SparkMax manipulatorMotor;

    /**
     * The manipulator subsystem controlls the end manipulator of the robot. This subsystem does not control any elevators or climbers. Only the end effector
     * 
     * 
     * @param manipulatorMotor The motor that will intake/eject game pieces
     */


    public ManipulatorSubsystem(){ //Class Contstructor
        this.manipulatorMotor = new SparkMax(Constants.MotorConstants.manuipulatorMotorID, MotorType.kBrushless); 
    }

    public void setSpeed(double speed){
        manipulatorMotor.set(speed);
    }

    public void stop(){
        manipulatorMotor.stopMotor();
    }


}
