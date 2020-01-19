package fr.unice.polytech.si3.qgl.ZeCommiT.goal;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "mode")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Battle.class, name = "BATTLE"),
        @JsonSubTypes.Type(value = Regatta.class, name = "REGATTA")
})
public abstract class Goal {

    private String mode;

    protected Goal(String mode){
        this.mode = mode;
    }

    public String getMode() {
        return mode;
    }
}
