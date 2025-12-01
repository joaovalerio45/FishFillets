package objects.interfaces;

import pt.iscte.poo.utils.Direction;
import objects.*;
import pt.iscte.poo.game.Room;

public interface Interactable {
    boolean interact(GameCharacter fish, Direction direction, Room room);
}
