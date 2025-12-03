package objects.fixedObjects;

import objects.*;
import pt.iscte.poo.utils.Point2D;

public class VerticalSteel extends FixedObject{
public VerticalSteel(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "steelVertical";
	}
}
