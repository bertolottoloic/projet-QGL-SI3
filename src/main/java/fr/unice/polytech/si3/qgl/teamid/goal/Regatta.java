package fr.unice.polytech.si3.qgl.teamid;

import java.util.List;
import java.util.zip.CheckedInputStream;

public class Regatta extends Goal{

    private List<Checkpoint> checkpoint;

    Regatta(String mode, List<Checkpoint> checkpoint) {
        super(mode);
        this.checkpoint = checkpoint;
    }
}
