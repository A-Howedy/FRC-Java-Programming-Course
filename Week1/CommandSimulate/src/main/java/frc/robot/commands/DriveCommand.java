


package frc.robot.commands;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;
import java.util.function.DoubleSupplier;

public class DriveCommand extends Command {
    private final DriveSubsystem drive;
    private final DoubleSupplier forward, rotation;

    public DriveCommand(DriveSubsystem drive,
     DoubleSupplier forward,
      DoubleSupplier rotation
      ) {
        this.drive = drive;
        this.forward = forward;
        this.rotation = rotation;
        addRequirements(drive);
    }

    @Override
    public void initialize(){
        System.out.println("Starting Drive Command");
    }

    @Override
    public void execute() {
        drive.arcadeDrive(forward.getAsDouble(), rotation.getAsDouble());
        SmartDashboard.putNumber("DriveCommand Forward", forward.getAsDouble());
        SmartDashboard.putNumber("DriveCommand Rotation", rotation.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        System.out.println("Stopping Drive Command");
        drive.stop();
    }
}


