package fr.unice.polytech.si3.qgl.zecommit.goal;



public abstract class Goal {

    private String mode;
    private boolean isRegatta;

    protected Goal(String mode){
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
