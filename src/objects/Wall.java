package objects;

import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Wall extends FixedObject {
    
    public Wall(Point2D p){
        super(p);
    }

    @Override
    public String getName() {
        return "wall";
    }

    @Override
    public boolean interact(GameCharacter fish, Direction direction) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'interact'");
    }

}
