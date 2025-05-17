package frc.robot;

import org.photonvision.PhotonCamera;
import org.photonvision.targeting.PhotonPipelineResult;

import edu.wpi.first.math.geometry.Transform3d;
import edu.wpi.first.networktables.NetworkTableInstance;

public class Vision {
    public static void main(String[] args) {
        //NetworkTableInstance inst = NetworkTableInstance.getDefault();
        @SuppressWarnings("resource")
        PhotonCamera camera = new PhotonCamera("Gurt");

        System.out.println('\n' + camera.toString() + '\n');

        while (true) {
            PhotonPipelineResult result = camera.getLatestResult();

            if (result.hasTargets()) {
                Transform3d cameraTargetTransform = result.getBestTarget().getAlternateCameraToTarget();
                System.out.println(
                    "X: " + cameraTargetTransform.getX() + "; Y: " + cameraTargetTransform.getY() +
                    "; Rotation: " + cameraTargetTransform.getRotation().getZ()
                );
            }
        }
    }
}
