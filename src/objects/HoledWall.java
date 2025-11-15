package objects;

import pt.iscte.poo.utils.Point2D;

public class HoledWall extends FixedObject {
public HoledWall(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "holedWall";
	}

	@Override
	public int getLayer() {
		return 2;
	} 

	@Override
	public boolean isObstacle(GameCharacter gc){
		return(!gc.canPassThrough());
	}
}
