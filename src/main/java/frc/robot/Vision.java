package frc.robot;

import org.photonvision.PhotonCamera;
import org.photonvision.PhotonUtils;
import org.photonvision.common.hardware.VisionLEDMode;
import org.photonvision.targeting.PhotonPipelineResult;
import org.photonvision.targeting.PhotonTrackedTarget;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision {
  private final PhotonCamera camera;
  private final String cameraName = "Gurt"; // Replace with your camera name from the PhotonVision UI
  private final double cameraHeightMeters = 0.5; // Example camera height
  private final double targetHeightMeters = 1.0; // Example target height
  private final double cameraPitchDegrees = 15.0; // Example camera pitch

  public Vision() {
    camera = new PhotonCamera(cameraName);
    // Set LED mode to pipeline (if you want the LED to follow the pipeline setting)
    //camera.setLEDMode(VisionLEDMode.kPipeline);
  }

  public PhotonPipelineResult getLatestResult() {
    return camera.getLatestResult();
  }

  public boolean hasTarget() {
    return getLatestResult().hasTargets();
  }

  public PhotonTrackedTarget getBestTarget() {
      return getLatestResult().getBestTarget();
  }


  public double getYawToTarget() {
      if (hasTarget()) {
          return getBestTarget().getYaw();
      } else {
          return 0.0;
      }
  }

  public double getDistanceToTargetMeters() {
    if (hasTarget()) {
      double yaw = Math.toRadians(getBestTarget().getYaw());
      double pitch = Math.toRadians(getBestTarget().getPitch());

       return PhotonUtils.calculateDistanceToTargetMeters(
        cameraHeightMeters,
        targetHeightMeters,
        cameraPitchDegrees,
        pitch);
    } else {
      return 0.0;
    }
  }

//   public Transform3d getRobotPose(Pose2d estimatedRobotPose) {
//     if (hasTarget()) {
//       Transform3d cameraToTarget = getBestTarget().getAlternateCameraToTarget();
//       // Assuming you have camera to robot transform defined
//       return cameraToTarget;
//     } else {
//         return null; // idk what to put here
//     }
//   }

  //goes periodically
  public void loop() {
    if (hasTarget()) {
        
        System.out.println(new Pose2d().getX());
    }
    // SmartDashboard.putBoolean("Has Target", hasTarget());
    // SmartDashboard.putNumber("Yaw to Target", getYawToTarget());
    // SmartDashboard.putNumber("Distance to Target", getDistanceToTargetMeters());
    // if (hasTarget()) {
    //   SmartDashboard.putNumber("Calculated X", getRobotPose(new Pose2d()).getX());
    //    SmartDashboard.putNumber("Calculated Y", getRobotPose(new Pose2d()).getY());
    // }
  }
}