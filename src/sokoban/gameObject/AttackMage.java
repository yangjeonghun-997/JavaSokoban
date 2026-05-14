package sokoban.gameObject;

public class AttackMage extends Mage {
	
	public AttackMage(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void magic() {
		currentMagic.setType(MagicType.ATTACK);
	}
}
