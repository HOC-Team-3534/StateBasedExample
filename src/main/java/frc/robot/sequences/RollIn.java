package frc.robot.sequences;

import frc.robot.RobotContainer;
import frc.robot.sequences.parent.BaseSequence;
import frc.robot.sequences.parent.ISequenceState;
import frc.robot.sequences.parent.SequenceState;
import frc.robot.subsystems.parent.BaseSubsystem;
import frc.robot.subsystems.parent.SubsystemRequirement;
import frc.robot.subsystems.requirements.IntakeReq;
import frc.robot.subsystems.IntakeState;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class RollIn extends BaseSequence<RollInState> {
    public RollIn (RollInState neutralState, RollInState startState) {
        super(neutralState, startState);
        //TODO Auto-generated constructor stub
    }

    @Override
    public void process() {
        switch (getState()) {
            case NEUTRAL:
                break;
            case ROLLIN:
                if(!RobotContainer.Buttons.RollIn.getButton()) {
                    setNextState(RollInState.NEUTRAL);
                }
                break;
        }
        updateState();

    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }

}

enum RollInState implements ISequenceState {
    NEUTRAL,
    ROLLIN(new IntakeReq(IntakeState.ROLLIN));

    SequenceState state;

    RollInState(SubsystemRequirement... requirements) {
        state = new SequenceState(requirements);
    }

    @Override
    public SequenceState getState() {
        return state;
    }

}

