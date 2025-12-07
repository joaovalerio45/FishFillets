package objects.interfaces;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;

// implementa o movimento de um objeto

public interface Movable {
    boolean move(GameCharacter fish, Direction direction, Room room);
}