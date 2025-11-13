package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMSparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {

    private final PWMSparkMax left = new PWMSparkMax(0);
    private final PWMSparkMax right = new PWMSparkMax(1);

    public DriveSubsystem() { 
        right.setInverted(true); 
    }

    public void arcadeDrive(double fwd, double rot) {
        left.set(fwd + rot);
        right.set(fwd - rot);
    }

    public void stop() {
        left.stopMotor();
        right.stopMotor();
        System.out.println("STOPPING DRIVE TRAIN");
    }
}
