package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class SpinCommand extends Command {
    private final DriveSubsystem drive;

    public SpinCommand(DriveSubsystem drive) {
        this.drive = drive;
        addRequirements(drive);
    }

    @Override
    public void initialize() {
        System.out.println("Spin command started!");
    }

    @Override
    public void execute() {
        drive.arcadeDrive(0.0, 0.5); // spin in place at half speed
    }

    @Override
    public void end(boolean interrupted) {
        drive.stop();
        System.out.println("Spin command ended!");
    }

    @Override
    public boolean isFinished() {
        return false; // run until button released
    }
}
