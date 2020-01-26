package fr.unice.polytech.si3.qgl.zecommit.boat;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Loic Bertolotto
 */
public class Deck{
    @JsonProperty("width")private int width;
    @JsonProperty("length")private int length;

    @JsonCreator
    public Deck(@JsonProperty("width")int width,@JsonProperty("length") int length){
        this.width = width;
        this.length = length;
    }


    @Override
    public String toString() {
        return  "width : "+this.width+
                " | height : "+this.length;
    }
    //------------------------------GETTER-------------------------//

    public int getWidth() {
        return width;
    }

    public int getLength() {
        return length;
    }


    //------------------------------SETTER-------------------------//


    public void setWidth(int width) {
        this.width = width;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
