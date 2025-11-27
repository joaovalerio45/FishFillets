package objects;

import pt.iscte.poo.utils.Direction;

public interface Movable {
    boolean move(GameCharacter fish, Direction direction);
}
