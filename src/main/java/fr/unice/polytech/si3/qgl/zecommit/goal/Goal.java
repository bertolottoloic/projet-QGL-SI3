package fr.unice.polytech.si3.qgl.zecommit.goal;


import com.fasterxml.jackson.annotation.*;

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
    @JsonIgnore
    private boolean isRegatta;

    @JsonCreator
    protected Goal(@JsonProperty("mode")String mode){
        this.mode = mode;
    }

    @Override
    public String toString() {
        return this.mode;
    }

    public boolean isRegatta(){
        return this.isRegatta;
    }



    //------------------------------GETTER-------------------------//

    public String getMode() {
        return mode;
    }


    //------------------------------SETTER-------------------------//


    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setRegatta(boolean regatta) {
        isRegatta = regatta;
    }
}
