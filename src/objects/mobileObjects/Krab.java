package objects.mobileObjects;

import java.util.List;
import java.util.Random;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;


public class Krab extends MobileObject {

    public Krab(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "krab";
    }

    @Override
	public boolean isSmall(){
		return true;
	}

    @Override
    public boolean isEnemy(){
        return true;
    }

    @Override
    public boolean interact(GameObject object, Direction direction, Room room) {
        if (object instanceof BigFish) {
            room.removeObject(this);
            return true;
        }
    
        if (object instanceof SmallFish) {
            ((GameCharacter) object).kill(room);
        }
        return false;
    }

    @Override
    public void fishMoveAction(Room room) {
        Random rand = new Random();
		int d = rand.nextInt(2); 
        Direction direction;
        if(d == 0){
            direction = Direction.LEFT;
        }else{
            direction = Direction.RIGHT;
        }

        this.move(null, direction, room);
    }

  
}
