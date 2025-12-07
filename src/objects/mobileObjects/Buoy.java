package objects.mobileObjects;

import java.util.List;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Buoy extends MobileObject{

    public Buoy(Point2D position) {
        super(position);
    }

    @Override
    public String getName() {
        return "buoy";
    }

    //
    @Override
    public boolean interact(GameObject object, Direction direction, Room room){
        if (object instanceof GameCharacter) {
            GameCharacter fish = (GameCharacter) object;
        // proibe o SmallFish de empurrar a boia verticalmente, e da false se o peixe nao a conseguir empurrar
            if (((direction == Direction.UP || direction == Direction.DOWN) && fish instanceof SmallFish) || !fish.canPush(room, direction, this)) {
                return false;
            }
        //tenta mover a boia(vai buscar o move do mobile object)
            return this.move(fish, direction, room);
        }
    return false;
    }

    
    @Override
	public void tickAction(Room room) {
        //obtem a lista de objetos na posicao acima da boia
        List<GameObject> objsAbove = room.getObjectsAt(getPosition().plus(Direction.UP.asVector()));

        //se a posicao nao estiver vazia, se nessa posicao estiver um objeto movel que nao seja a boia a boia move se para baixo para suportar
        //o objeto que esta por cima
        if(!objsAbove.isEmpty()){
            for(GameObject obj : objsAbove){
                if(obj instanceof MobileObject && !(obj instanceof Buoy)){
                    move(null, Direction.DOWN, room);
                    return;
                }
            }
        }
        //se nao houver objetos em cima, a boia flutua(vai para cima)
        move(null, Direction.UP, room);

	}

}