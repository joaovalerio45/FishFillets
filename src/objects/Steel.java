package objects;

import pt.iscte.poo.utils.Point2D;

public class Steel extends GameObject{
public Steel(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "steelHorizontal";
	}

	@Override
	public int getLayer() {
		return 2;
	}
}
