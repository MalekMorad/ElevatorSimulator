package challange.coding.cic.ibm.models;

public enum MovingDirection {
	UP(1),
	IDLE(0),
	DOWN(-1);
	
	private int numVal;
	
	MovingDirection(int numVal) {
		this.numVal = numVal;
	}
	
	public int getNumVal() {
		return numVal;
	}
}
