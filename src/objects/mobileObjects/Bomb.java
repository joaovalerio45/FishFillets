package objects.mobileObjects;

import java.util.List;

import objects.*;
import pt.iscte.poo.game.Room;
import pt.iscte.poo.utils.Direction;
import pt.iscte.poo.utils.Point2D;

public class Bomb extends MobileObject {

	//indica se a bomba esteve em movimento de queda no ultimo tick
	//usada para diferenciar entre estar a ser empurrada (nao explode) e estar a cair (explode ao parar)
	private boolean isFalling = false;

    public Bomb(Point2D p) {
		super(p);
	}
	
	@Override
	public String getName() {
		return "bomb";
	}

	//define o comportamento da bomba a cada tick
	@Override
	public void tickAction(Room room){
		//vai retornar true se a bomba fez um movimento para baixo)
		boolean moved = move(null,Direction.DOWN,room);
		//se for true a bomba esta a cair e atualiza se a variavel
		if(moved){
			isFalling = true;

		} else {
			//se moved for false mas isFalling ainda ta true é porque a bomba ja caiu tudo por isso explode e isFalling atualiza se para false novamente
			if(isFalling){
			explode(room);
			}
			isFalling = false;
		}
	}
			
	private void explode(Room room) {
		//obtem as 4 posicoes adjacentes à bomba(funcao getNeighbourhoodPoints ja implementada na classe Point2D)
		List<Point2D> area = getPosition().getNeighbourhoodPoints();

		for(Point2D p : area){
			//lista de objetos em cada 1 dessas posicoes
			List<GameObject> objects = room.getObjectsAt(p);
			//remove objetos em ordem inversa para evitar problemas de indice
				for(int i = objects.size() - 1; i >= 0; i--){
					GameObject obj = objects.get(i);
					if(obj instanceof GameCharacter){
						//mata o peixe se estiver numa das posicoes
						((GameCharacter) obj).kill(room);
					}
					else {
						//remove os outros objetos que estao nessas posicoes
						room.removeObject(obj);
					}
				}
			}
			//por fim remove a propria bomba
		room.removeObject(this);
		}

}
