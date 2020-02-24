package fr.unice.polytech.si3.qgl.zecommit.other;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import fr.unice.polytech.si3.qgl.zecommit.deserializer.CheckpointsDeserializer;

import java.util.List;

@JsonDeserialize(using = CheckpointsDeserializer.class)
public class Checkpoints {

    List<Checkpoint> checkpoints;

    public Checkpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
}
