package objects;

import pt.iscte.poo.utils.Point2D;

public class SmallFish extends GameCharacter {

	public SmallFish(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "smallFishLeft";
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
