package objects.interfaces;

import pt.iscte.poo.utils.Direction;
import objects.*;
import pt.iscte.poo.game.Room;

//serve para implementar o efeito de um object sobre outro, consoante a direcao e o room, e retorna um boolean que 
// indica se pode deslocar se para essa posicão

public interface Interactable {
    boolean interact(GameObject object, Direction direction, Room room);
}
