package frc.robot.commands;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivetrain;

public class DriveCommand extends Command{

    private final Drivetrain driveTrain;
    private final XboxController controller;

    public DriveCommand(Drivetrain driveTrain, XboxController controller){
        this.driveTrain = driveTrain;
        this.controller = controller;

        addRequirements(driveTrain);
    }

    @Override
    public void execute(){
        double forward = -controller.getLeftY();
        double strafe = controller.getLeftX();
        double rotation = controller.getRightX();

        driveTrain.drive(forward, strafe, rotation);
    }
    
}
