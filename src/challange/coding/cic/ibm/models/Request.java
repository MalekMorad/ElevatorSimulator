package challange.coding.cic.ibm.models;

public class Request {
	private int currentFloor;
	private int destinationFloor;
	private MovingDirection direction;

	public Request(int currentFloor, int destinationFloor, MovingDirection direction) {
		this.currentFloor = currentFloor;
		this.destinationFloor = destinationFloor;
		this.direction = direction;
	}
	
	public int getCurrentFloor() {
		return currentFloor;
	}
	
	public int getDestinationFloor() {
		return destinationFloor;
	}
	
	public MovingDirection getMovingDirection() {
		return direction;
	}
}
