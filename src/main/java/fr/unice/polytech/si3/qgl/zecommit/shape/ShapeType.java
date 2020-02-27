package fr.unice.polytech.si3.qgl.zecommit.shape;

public enum ShapeType {
        RECTANGLE,CIRCLE,POLYGON;

        @Override
        public String toString(){
            return this.name().toLowerCase();
        }

}
