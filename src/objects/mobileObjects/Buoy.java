package objects.mobileObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Buoy extends MobileObject{

    public Buoy(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "bouy";
    }



    @Override
    public boolean interact(GameObject object, Direction direction, Room room) {
        return true;
    }

    

}