package objects;

import pt.iscte.poo.utils.Point2D;

public class Cup extends GameObject {
    public Cup(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "cup";
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
