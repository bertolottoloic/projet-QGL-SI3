package fr.unice.polytech.si3.qgl.zecommit.goal;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("mode")
    private String mode;

    @JsonCreator
    protected Goal(@JsonProperty("mode")String mode){
        this.mode = mode;
    }

    @Override
    public String toString() {
        String chaine =this.mode;
        return chaine;
    }
    //------------------------------GETTER-------------------------//

    public String getMode() {
        return mode;
    }


    //------------------------------SETTER-------------------------//


    public void setMode(String mode) {
        this.mode = mode;
    }
}