package objects;

import pt.iscte.poo.utils.Point2D;

public class Stone extends MobileObject {
    public Stone(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "stone";
	}

	@Override
	public int getLayer() {
		return 2;
	}
	public boolean isObstacle(GameCharacter gc){
		return false;
	}
}
