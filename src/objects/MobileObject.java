package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class MobileObject extends GameObject{
    public MobileObject(Point2D position) {
		super(position);
	}

	@Override
	public boolean isObstacle(GameCharacter gc){
		return true;
	}
	@Override
	public boolean isSupport() {
		return true;
	}
}
