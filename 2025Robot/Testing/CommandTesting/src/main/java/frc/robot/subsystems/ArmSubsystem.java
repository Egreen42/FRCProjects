package frc.robot.subsystems;


//WPILIB Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.Command;

//Rev Imports
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class ArmSubsystem extends SubsystemBase{
    private double armUpSpeed;
    private double armDownSpeed;
    private SparkMax armMotor = new SparkMax(1, MotorType.kBrushless);
    

    /**
     * 
     * @param armMotor The motor that will move the arm up and down
     * @param armUpSpeed The speed at which the arm will move up
     * @param armDownSpeed The speed at which the arm will move down
     * 
     */

    /**  
    if(false){ //Reverse the arm motor if needed
        armUpSpeed = - .5;
        armDownSpeed = .5;
    }else {
        armUpSpeed = .5;
        armDownSpeed = -0.5;
    }
        */

    public ArmSubsystem(){
        
        
    }

    public void moveArmUp(){
        System.out.println("Moving Arm Up");
        armMotor.set(armUpSpeed);
    }
    public Command moveArmDown(){
        return this.run(() -> armMotor.set(armDownSpeed));
    }
    public void stopArm(){
        armMotor.set(0);
    }


}
