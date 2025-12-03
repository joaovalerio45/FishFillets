package objects.mobileObjects;


import java.util.List;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Stone extends MobileObject {

	int hasKrab = 0;
    
	public Stone(Point2D p) {
		super(p);
	}
	

	
	@Override
	public String getName() {
		return "stone";
	}

	public boolean isHeavy() {
		return true;
	}

	@Override
	public boolean move(GameCharacter fish, Direction direction, Room room) {
        Point2D nextPos = getPosition().plus(direction.asVector());

        List<GameObject> objects = room.getObjectsAt(nextPos);


        for(GameObject obj : objects){

            if (obj.interact(this, direction, room)) {
                continue;
            }

            if (obj.isTraversable(this)) {
                continue;
            }

            if(!(obj instanceof MobileObject)){
                return false;
            }
            if(fish != null && fish.canPush(room,direction, obj)){
                MobileObject nextMobile = (MobileObject) obj;
                if(nextMobile.move(fish, direction, room)){
                    this.setPosition(nextPos);
					if(direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)){
						hasKrab++;
					}
                    return true;
                }
            }
            return false;
        }
        this.setPosition(nextPos);
		if(direction.equals(Direction.LEFT) || direction.equals(Direction.RIGHT)){
			hasKrab++;
		}
        return true;
    }

	@Override
	public void tickAction(Room room) {

		move(null, Direction.DOWN, room);

		if(hasKrab == 1 && room.getObjectsAt(getPosition().plus(Direction.UP.asVector())).isEmpty()){
			hasKrab = 2;
			room.addObject(new Krab(getPosition().plus(Direction.UP.asVector())));
		}


	}
}
