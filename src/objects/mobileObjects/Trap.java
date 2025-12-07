package objects.mobileObjects;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Trap extends MobileObject {
public Trap(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "trap";
	}

	@Override
	public boolean isHeavy(){
		return true;
	}

	//se o objeto que tentar interagir com a trap for big fish ou krab morre
	public boolean interact(GameObject object, Direction direction, Room room){
		if(object instanceof BigFish){
			((GameCharacter) object).kill(room);
		}else if(object instanceof SmallFish){
			return true;
		}else if (object instanceof Krab){
			room.removeObject(object);
		}
		return false;
	}
}
