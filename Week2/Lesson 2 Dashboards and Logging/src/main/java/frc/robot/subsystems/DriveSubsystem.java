// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import java.util.function.DoubleSupplier;

import edu.wpi.first.epilogue.Logged;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.DoubleSubscriber;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

@Logged
public class DriveSubsystem extends SubsystemBase {
  /** Creates a new DriveSubsystem. */
  private Spark leftSpark=new Spark(1);

  //TODO 1: Declare NetworkTable entries
  private DoubleSubscriber speedNetworkTableEntry;
  private Double dashboardSpeed=0.0;
  private DoublePublisher encoder;

  private Encoder leftEncoder = new Encoder(0, 1);


  public DriveSubsystem() {
    //TODO 2: Initialize NetworkTable entries
    NetworkTable tableInstance= NetworkTableInstance.getDefault().getTable("Drive");
    speedNetworkTableEntry= tableInstance.getDoubleTopic("Power").subscribe(0.0);
    encoder=tableInstance.getDoubleTopic("Encoder").publish();
  }


  // Update dashboard values
  //TODO 3: Create method to update dashboard values
    private void dashboardUpdate(){
    encoder.set(getEncoderValue());
  }

  private void setPower(double power){
    leftSpark.set(power);
  }

  private void stop(){
    leftSpark.set(0);
  }

  private double getEncoderValue(){
    return leftEncoder.getDistance();
  }
  public Command drivePercent(DoubleSupplier power){
    return run (()-> setPower(power.getAsDouble()));
  }

  //TODO 4: Create command to drive from dashboard
  public Command driveFromDashboard(){
    return  new FunctionalCommand(
      // Initialize
      () -> {},
      // Execute
      () -> {
        this.dashboardSpeed=speedNetworkTableEntry.get();
        setPower(this.dashboardSpeed);
      },
      // End
      interrupted -> stop(),
      // IsFinished
      () -> false,
      this
    );
  }

  @Override
  public void periodic() {
    //TODO 5: Call method to update dashboard values
    dashboardUpdate();
    // This method will be called once per scheduler run
  }
}
