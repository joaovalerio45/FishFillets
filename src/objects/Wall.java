package objects;

import pt.iscte.poo.utils.Point2D;

public class Wall extends FixedObject {
    
    public Wall(Point2D p){
        super(p);
    }

    @Override
    public String getName() {
        return "wall";
    }

}
