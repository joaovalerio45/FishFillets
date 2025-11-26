package objects;

import pt.iscte.poo.utils.Point2D;
import pt.iscte.poo.utils.Vector2D;

public class Anchor extends MobileObject{

	private boolean hasMovedOnce = false;


    public Anchor(Point2D p) {
		super(p);
	}

	
	@Override
	public String getName() {
		return "anchor";
	}

	@Override
	public void move(Vector2D dir) {
		setPosition(getPosition().plus(dir));
		if(dir.getY() == 0){
			hasMovedOnce = true;
		}
	}

	@Override
	public boolean isObstacle(GameCharacter gc){
		return hasMovedOnce;
	}
	
}
