package objects;



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
	
}
