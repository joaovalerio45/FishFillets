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

	public void kill(GameCharacter fish, Room room){
		room.removeObject(fish);
	}

	@Override
	public int getLayer() {
		return 2;
	}

	@Override
	public boolean interact(GameCharacter fish, Direction direction, Room room) {
		return false;
	}
	
}