package objects;

import java.util.List;

import objects.interfaces.Movable;
import objects.interfaces.Tickable;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;


public abstract class MobileObject extends GameObject implements Movable,Tickable{
    
	public MobileObject(Point2D position) {
		super(position);
	}


    //comportamento default
    //se o objeto que interage for um peixe, se poder movê-lo, retorna move(), senão retorna falso
    public boolean interact(GameObject object, Direction direction, Room room){
        if (object instanceof GameCharacter) {
            GameCharacter fish = (GameCharacter) object;
        
            if (!fish.canPush(room, direction, this)) {
                return false;
            }
        
            return this.move(fish, direction, room);
        }
    return false;
    }


    
	public boolean move(GameCharacter fish, Direction direction, Room room) {
        //posicao para onde se desloca
        Point2D nextPos = getPosition().plus(direction.asVector());

        //lista de objetos que estão na posição de destino
        List<GameObject> objects = room.getObjectsAt(nextPos);


        for(GameObject obj : objects){


            //se a interação com esse objeto retorna true (pode empurrar), continua a iteração
            if (obj.interact(this, direction, room)) {
                continue;
            }

            //se o obj pode atravessar retorna true, continua a iteração
            if (obj.isTraversable(this)) {
                continue;
            }

            //se o objeto nao for movel, cancela o movimento e retorna false
            if(!(obj instanceof MobileObject)){
                return false;
            }
            //se o movimento for efetuado por um peixe (não é a gravidade) e esse peixe pode empurrar
            if(fish != null && fish.canPush(room,direction, obj)){ 
                
                MobileObject nextMobile = (MobileObject) obj;
                if(nextMobile.move(fish, direction, room)){
                    this.setPosition(nextPos);
                    return true;
                }
            }
            return false;
        }
        this.setPosition(nextPos);
        return true;
    }

	@Override
	public void tickAction(Room room) {


		//Comportamento default para objetos móveis : ação da gravidade

		move(null, Direction.DOWN, room);


	}

    
    public void fishMoveAction(Room room) {

    }
	
	
	
	
}
