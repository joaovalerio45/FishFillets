package objects;

import pt.iscte.poo.utils.Point2D;

public abstract class MobileObject extends GameObject{
    
	public MobileObject(Point2D position) {
		super(position);
	}

	@Override
	public int getLayer() {
		return 1;
	}
}
