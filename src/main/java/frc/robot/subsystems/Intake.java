package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import static frc.robot.Constants.Intake.*;

import frc.robot.Robot;
import frc.statebasedcontroller.subsystem.fundamental.subsystem.BaseSubsystem;

import static frc.robot.Constants.*;

public class Intake extends BaseSubsystem<IntakeState> {
	static WPI_TalonSRX intakeRoller;
	static DoubleSolenoid intakeKickers;

	public Intake() {
		super(IntakeState.NEUTRAL);
		intakeRoller = new WPI_TalonSRX(INTAKE_ROLLER);
		intakeRoller.setInverted(ROBOTTYPE == RobotType.PBOT);
		intakeKickers = Robot.mainPCM.makeDoubleSolenoid(INTAKE_EXTEND, INTAKE_RETRACT);
	}

	@Override
	public void neutral() {
		intakeKickers.set(Value.kOff);
		intakeRoller.set(ControlMode.PercentOutput, 0.0);
	}

	public void kickOut() {
		if (getStateFirstRunThrough()) {
			intakeKickers.set(Value.kForward);
			intakeRoller.set(ControlMode.PercentOutput, 0.80);
		}
	}

	public void retract() {
		if (getStateFirstRunThrough()) {
			intakeKickers.set(Value.kReverse);
		}
	}

	public void extake() {
		intakeRoller.set(ControlMode.PercentOutput, -0.80);
	}

	public void rollIn() {
		intakeRoller.set(ControlMode.PercentOutput, 0.80);
	}

	@Override
	public boolean abort() {
		intakeRoller.set(ControlMode.PercentOutput, 0.0);
		intakeKickers.set(Value.kOff);
		return true;
	}
}
