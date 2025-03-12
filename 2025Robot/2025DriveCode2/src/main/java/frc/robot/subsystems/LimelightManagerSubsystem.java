package frc.robot.subsystems;

import frc.robot.LimelightHelpers;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LimelightManagerSubsystem extends SubsystemBase {
  private final String limelightAName = "limelightA";
  private final String limelightBName = "limelightB";

  private String activeLimelight = limelightAName; // Default active

  public void setActiveLimelight(boolean useLeft) {
      activeLimelight = useLeft ? limelightAName : limelightBName;
  }

  public boolean hasValidTarget() {
      return LimelightHelpers.getTV(activeLimelight);
  }

  public double getTx() {
      return LimelightHelpers.getTX(activeLimelight);
  }

  public double getTy() {
      return LimelightHelpers.getTY(activeLimelight);
  }

  public void setPipeline(int index) {
      LimelightHelpers.setPipelineIndex(activeLimelight, index);
  }

  public String getActiveLimelight() {
      return activeLimelight;
  }
}
