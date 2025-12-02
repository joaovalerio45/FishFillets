package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class BigFish extends GameCharacter {
	
	public BigFish(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		if(getIsFacingRight()){
			return "bigFishRight";
		}else{
			return "bigFishLeft";
		}
		
	}

	@Override
	public boolean canPush(Room room, Direction direction, GameObject obj){
		if(room.getObjectsAt(obj.getPosition().plus(direction.asVector())).isEmpty()){

		}else{
			for(GameObject objs : room.getObjectsAt(obj.getPosition().plus(direction.asVector()))){
				if(!canPush(room, direction, objs)){
					return false;
				}else{
					return true;
				}
			}
		}
	}


}
