package fr.unice.polytech.si3.qgl.zecommit.goal;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers.BooleanDeserializer;

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
    protected boolean whichMode; //TODO changer le nom

    @JsonCreator
    protected Goal(@JsonProperty("mode")String mode){
        this.mode = mode;
    }

    @Override
    public String toString() {
        return this.mode;
    }

    public boolean isRegatta(){
        return this.whichMode;
    }

    public boolean isBattle(){ //TODO à supprimer
        return !this.whichMode;
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
