package objects;

import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class GameCharacter extends GameObject {

	private boolean isFacingRight = false;

	public boolean getIsFacingRight(){
		return isFacingRight;
	}
	
	public GameCharacter(Point2D p) {
		super(p);
	}
	
	public void moveFish(Vector2D dir) {
		if(dir.getX() < 0){
			isFacingRight = false;
		}else if(dir.getX() > 0){
			isFacingRight = true;
		}


		
		setPosition(getPosition().plus(dir));	
	}

	public void kill(Room room){
		room.removeObject(this);
		room.switchActiveFish();
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean interact(GameObject object, Direction direction, Room room) {
		return false;
	}

	public boolean canPush(Room room ,Direction direction, GameObject object){
		return true;
	}

	
	
}