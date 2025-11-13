

// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.Autos;
import frc.robot.commands.DriveCommand;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.SpinCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;


public class RobotContainer {

  private final CommandXboxController driver = new CommandXboxController(0);

  private final DriveSubsystem drive = new DriveSubsystem();

  public RobotContainer() {
      drive.setDefaultCommand(
        new DriveCommand(
          drive,
          
          () -> -driver.getLeftY(),
          () -> driver.getLeftX()
        )
      );
      // drive.setDefaultCommand(new DriveCommand(drive, js));
      configureBindings();
  }

  private void configureBindings() {
      driver.a().whileTrue(new SpinCommand(drive));
      driver.b().onTrue(new InstantCommand(() -> drive.stop(), drive));

  }

}
