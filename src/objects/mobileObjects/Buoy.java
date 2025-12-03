package objects.mobileObjects;

import java.util.List;

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
        return "buoy";
    }

    @Override
    public boolean interact(GameObject object, Direction direction, Room room){
        if (object instanceof GameCharacter) {
            GameCharacter fish = (GameCharacter) object;
        
            if (fish instanceof SmallFish || !fish.canPush(room, direction, this)) {
                return false;
            }
        
            return this.move(fish, direction, room);
        }
    return false;
    }

    
    @Override
	public void tickAction(Room room) {

        List<GameObject> objsAbove = room.getObjectsAt(getPosition().plus(Direction.UP.asVector()));

        if(!objsAbove.isEmpty()){
            for(GameObject obj : objsAbove){
                if(obj instanceof MobileObject){
                    move(null, Direction.DOWN, room);
                }
            }
        }else{
            move(null, Direction.UP, room);
        }


	}

}