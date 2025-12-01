package objects;

import pt.iscte.poo.utils.Point2D;


public class Krab extends MobileObject {

    public Krab(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "krab";
    }
    
}
