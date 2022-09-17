package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.parent.BaseSubsystem;
import frc.robot.subsystems.states.IntakeState;

import static frc.robot.Constants.*;
import static frc.robot.Constants.ROBOTTYPE;

public class Intake extends BaseSubsystem<IntakeState> {

    static WPI_TalonSRX intakeRoller;

    static DoubleSolenoid intakeKickers;

    public Intake(){
        super(IntakeState.NEUTRAL);

        intakeRoller = new WPI_TalonSRX(INTAKE_ROLLER);
        intakeRoller.setInverted(ROBOTTYPE == RobotType.PBOT);

        intakeKickers = Robot.mainPCM.makeDoubleSolenoid(INTAKE_EXTEND, INTAKE_RETRACT);
    }

    @Override
    public void process() {

        super.process();

        switch (getCurrentSubsystemState()) {
            case NEUTRAL:
                neutral();
                break;
            case KICKOUT:
                kickOut();
                break;
            case RETRACT:
                retract();
                break;
            case EXTAKE:
                extake();
                break;
            case ROLLIN:
                rollIn();
                break;
            case HOLDPOSITION:
                break;
        }
    }

    @Override
    public void neutral() {
        intakeKickers.set(Value.kOff);
        intakeRoller.set(ControlMode.PercentOutput, 0.0);
    }

    public void kickOut() {
        if(getStateFirstRunThrough()) {
            setWithADelayToOff(intakeKickers, Value.kForward, Constants.DelayToOff.INTAKE_KICKERS.millis);
            intakeRoller.set(ControlMode.PercentOutput, 0.80);
        }
    }

    public void retract() {
        if(getStateFirstRunThrough()) {
            setWithADelayToOff(intakeKickers, Value.kReverse, Constants.DelayToOff.INTAKE_KICKERS.millis);
        }
    }

    public void extake(){ intakeRoller.set(ControlMode.PercentOutput, -0.80); }

    public void rollIn(){ intakeRoller.set(ControlMode.PercentOutput, 0.80); }

    @Override
    public boolean abort() {
        intakeRoller.set(ControlMode.PercentOutput, 0.0);
        intakeKickers.set(Value.kOff);
        return true;
    }
}

