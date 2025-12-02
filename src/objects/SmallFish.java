package objects;



import java.util.List;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class SmallFish extends GameCharacter {

	public SmallFish(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		if(getIsFacingRight()){
			return "smallFishRight";
		}else{
			return "smallFishLeft";
		}
		
	}

	public boolean canPush(Room room, Direction direction, GameObject obj){
		
		if(!room.getObjectsAt(obj.getPosition().plus(direction.asVector())).isEmpty() || obj.isHeavy()){
			return false;
		}
		return true;
	}


	public void kill(Room room){
		if(this.equals(room.getActiveFish())){
			room.switchActiveFish();
		}
		room.removeObject(this);
		room.setSmallFish(null);
	}

	@Override
    public boolean isKilled(List<MobileObject> stack){
        if(stack.size() > 1){
            return true;
        }
        for(MobileObject obj : stack){
            if(obj.isHeavy()){
                return true;
            }
        }
        return false;
    }
	
}
