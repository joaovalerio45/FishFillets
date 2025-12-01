package objects.fixedObjects;

import objects.*;
import pt.iscte.poo.utils.Point2D;

public class HorizontalSteel extends FixedObject{
public HorizontalSteel(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "steelHorizontal";
	}
}
