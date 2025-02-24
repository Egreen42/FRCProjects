package frc.robot.subsystems;

//WPILIB Imports
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;

//Rev Imports
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;

public class IntakeSubsystem extends SubsystemBase{

    private SparkMax intakeMotor;
    private SparkMax intakeDeployMotor;
    private double intakeInSpeed;
    private double intakeOutSpeed;
    private double intakeDeployInSpeed;
    private double intakeDeployOutSpeed;

    /**
     * 
     * @param intakeMotor The motor that will intake the balls
     * @param intakeDeployMotor The motor that will deploy the intake
     * @param intakeInSpeed The speed at which the intake will intake the balls
     * @param intakeOutSpeed The speed at which the intake will outtake the balls
     * @param intakeDeployInSpeed The speed at which the intake will deploy
     * @param intakeDeployOutSpeed The speed at which the intake will retract
     */

    public IntakeSubsystem(){
        this.intakeMotor = new SparkMax(Constants.MotorConstants.intakeMotorID, MotorType.kBrushless);
        this.intakeDeployMotor = new SparkMax(Constants.MotorConstants.intakeDeployMotorID, MotorType.kBrushless);

        if(Constants.MotorConstants.intakeMotorReversed){ //Will reverse the intake motor if needed
            intakeInSpeed = - Constants.MotorConstants.intakeInSpeed;
            intakeOutSpeed = - Constants.MotorConstants.intakeOutSpeed;
        } else {
            intakeInSpeed = Constants.MotorConstants.intakeInSpeed;
            intakeOutSpeed = Constants.MotorConstants.intakeOutSpeed;
        }
        if(Constants.MotorConstants.intakeDeployMotorReversed){ //Will reverse the outtake motor if needed
            intakeDeployInSpeed = - Constants.MotorConstants.intakeDeployInSpeed;
            intakeDeployOutSpeed = - Constants.MotorConstants.intakeDeployOutSpeed;
        } else {
            intakeDeployInSpeed = Constants.MotorConstants.intakeDeployInSpeed;
            intakeDeployOutSpeed = Constants.MotorConstants.intakeDeployOutSpeed;
        }
    }

    public Command runIntake(){
        return runOnce(() -> intakeMotor.set(intakeInSpeed));
    }

    public Command runOuttake(){
        return runOnce(() -> intakeMotor.set(intakeOutSpeed));
    }

    public Command deployIntake(){
        return runOnce(() -> intakeDeployMotor.set(intakeDeployInSpeed));
    }

    public Command retractIntake(){
        return runOnce(() -> intakeDeployMotor.set(intakeDeployOutSpeed));
    }
    
}
