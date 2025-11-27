package objects;

import pt.iscte.poo.utils.Direction;

public interface Interactable {
    boolean interact(GameCharacter fish, Direction direction);
}
