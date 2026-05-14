package sokoban.gameObject;

public class TeleportMage extends Mage {

	public TeleportMage(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void magic() {
		currentMagic.setType(MagicType.TELEPORT);
	}
}
