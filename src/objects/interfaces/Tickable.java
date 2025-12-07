package objects.interfaces;

import pt.iscte.poo.game.*;

// interface que implementa a ação de um objeto a cada tick ( ver implementação de processTick() no GameEngine)

public interface Tickable {
    void tickAction(Room room);
}
