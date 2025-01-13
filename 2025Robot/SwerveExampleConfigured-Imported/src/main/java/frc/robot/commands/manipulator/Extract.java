package frc.robot.commands.manipulator;

import edu.wpi.first.wpilibj2.command.Command;

import frc.robot.subsystems.ManipulatorSubsystem;

public class Extract extends Command{
    
    private final ManipulatorSubsystem manipSubsystem;

    public Extract(ManipulatorSubsystem m_Subsystem){
        manipSubsystem = m_Subsystem;
        addRequirements(getRequirements());
    }

    @Override
    public void initialize(){
        manipSubsystem.setSpeed(-1);
    }

    @Override
    public boolean isFinished(){
        return true;
    }

}