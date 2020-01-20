package fr.unice.polytech.si3.qgl.ZeCommiT;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Loic Bertolotto
 */
public class Deck{
    @JsonProperty("width")private int width;
    @JsonProperty("length")private int length;

    @JsonCreator
    Deck(@JsonProperty("width")int width,@JsonProperty("length") int length){
        this.width = width;
        this.length = length;
    }


    @Override
    public String toString() {
        String chaine = "width : "+this.width+
                " | height : "+this.length;
        return chaine;
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
