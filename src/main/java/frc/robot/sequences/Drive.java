package frc.robot.sequences;

import frc.robot.sequences.parent.BaseSequence;
import frc.robot.sequences.parent.ISequencePhase;
import frc.robot.sequences.parent.SequencePhase;
import frc.robot.subsystems.SwerveDriveState;
import frc.robot.subsystems.parent.SubsystemRequirement;
import frc.robot.subsystems.requirements.SwerveDriveReq;

enum DrivePhase implements ISequencePhase {
    NEUTRAL,
    DRIVE(new SwerveDriveReq(SwerveDriveState.DRIVE));

    SequencePhase state;

    DrivePhase(SubsystemRequirement... requirements) {
        state = new SequencePhase(requirements);
    }

    @Override
    public SequencePhase getPhase() {
        return state;
    }

}

public class Drive extends BaseSequence<DrivePhase> {

    public Drive(DrivePhase neutralState, DrivePhase startState) {
        super(neutralState, startState);
    }

    @Override
    public void process() {

        switch (getPhase()) {
            case DRIVE:
                break;
            case NEUTRAL:
                break;
            default:
                break;

        }
        updatePhase();
    }

    @Override
    public boolean abort() {
        // TODO Auto-generated method stub
        return false;
    }

}
