package objects;

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
    public void sink(){
        return;
    }

    @Override
    public boolean interact(GameCharacter fish, Direction direction, Room room) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

}