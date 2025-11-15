package objects;

import pt.iscte.poo.utils.Point2D;

public class VerticalSteel extends FixedObject{
public VerticalSteel(Point2D p) {
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
