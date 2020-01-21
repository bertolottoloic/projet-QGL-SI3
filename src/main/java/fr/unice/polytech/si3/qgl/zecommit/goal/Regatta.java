package fr.unice.polytech.si3.qgl.zecommit.goal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.util.List;

public class Regatta extends Goal {

    @JsonProperty
    private List<Checkpoint> checkpoints;

    @JsonCreator
    public Regatta(@JsonProperty("checkpoints")List<Checkpoint> checkpoints) {
        super("REGATTA");
        this.checkpoints = checkpoints;
    }


    //------------------------------GETTER-------------------------//

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }


    //------------------------------SETTER-------------------------//


    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
}