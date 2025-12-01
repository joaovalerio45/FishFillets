package objects.fixedObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Wall extends FixedObject {
    
    public Wall(Point2D p){
        super(p);
    }

    @Override
    public String getName() {
        return "wall";
    }

    @Override
    public boolean interact(GameCharacter fish, Direction direction, Room room) {
        return false;
    }

}
