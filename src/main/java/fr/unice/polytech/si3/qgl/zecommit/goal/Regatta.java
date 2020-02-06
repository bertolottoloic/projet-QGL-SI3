package fr.unice.polytech.si3.qgl.zecommit.goal;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import fr.unice.polytech.si3.qgl.zecommit.Logs;
import fr.unice.polytech.si3.qgl.zecommit.other.Checkpoint;

import java.util.List;

public class Regatta extends Goal {

    @JsonProperty
    private List<Checkpoint> checkpoints;

    @JsonCreator
    public Regatta(@JsonProperty("checkpoints")List<Checkpoint> checkpoints) {
        super("REGATTA");
        this.checkpoints = checkpoints;
        setRegatta(true);
    }

    public void validateCommonCheckpoint(){
        checkpoints.remove(0);
    }

    //------------------------------GETTER-------------------------//

    public List<Checkpoint> getCheckpoints() {
        return checkpoints;
    }

    /**
     * Donne le premier checkpoint de la list de cp, renvoie null si plus de cp
     * @return
     */
    public Checkpoint getFirstCheckpoint() {
        if (!this.checkpoints.isEmpty()) {
            return this.checkpoints.get(0);
        }
        else {
            Logs.add("PBCH");
            return null;
        }
    }


    //------------------------------SETTER-------------------------//


    public void setCheckpoints(List<Checkpoint> checkpoints) {
        this.checkpoints = checkpoints;
    }
}
