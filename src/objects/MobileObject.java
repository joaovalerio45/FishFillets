package objects;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public abstract class MobileObject extends GameObject{
    
	public MobileObject(Point2D position) {
		super(position);
	}

	@Override
	public boolean isObstacle(GameCharacter gc){
		return false;
	}
	@Override
	public boolean isSupport() {
		return false;
	}

	@Override
	public boolean isMobile(){
		return true;
	}
	
	

	public void move(Vector2D dir) {
		setPosition(getPosition().plus(dir));	
	}
}
