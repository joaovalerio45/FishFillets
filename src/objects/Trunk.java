package objects;

import pt.iscte.poo.utils.Point2D;

public class Trunk extends GameObject {
public Trunk(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "trunk";
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
