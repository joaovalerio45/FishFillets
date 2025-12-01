package objects;

import pt.iscte.poo.utils.Point2D;

//decidir se adicionamos room aos parametros do movable e do interactable para saber se a posicao para a qual o objeto vai esta vazia
//se adicionarmos o room às duas interfaces tambem vai ser possivel fazer o kill dentro dos peixes(criamos no gameCharacter)
//e sempre que for preciso matar um dos peixes em alguma das interacoes é so invocar o kill


public abstract class MobileObject extends GameObject{
    
	public MobileObject(Point2D position) {
		super(position);
	}

	
	public void sink(){
		
	}
	
	
	
}
