package objects.interfaces;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;

public interface Movable {
    boolean move(GameCharacter fish, Direction direction, Room room);
}