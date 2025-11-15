package objects;

import pt.iscte.poo.utils.Point2D;

public class Anchor extends MobileObject{
    public Anchor(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "anchor";
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
