package objects;

import pt.iscte.poo.utils.Point2D;

public class Bomb extends GameObject {
    public Bomb(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "bomb";
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
