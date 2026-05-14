package sokoban.gameObject;

import java.util.List;

import sokoban.manager.Position;

public class Magic {

	public MagicType type;
	public Mage caster;
	public List<Position> path;
	public GameObject target;
	
	public Magic(Mage caster) {
		this.caster = caster;
	}
	
	public void setType(MagicType type) {
		this.type = type;
	}
	
	public void setPath(List<Position> path) {
		this.path = path;
	}
	
	public void setTarget(GameObject target) {
		this.target = target;
	}
}
