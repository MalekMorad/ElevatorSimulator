package challange.coding.cic.ibm.models;

/*
 * Request on User(=Person) interaction with interface
 * Request as noted: Current Floor, Destination Floor, Moving Direction
 */
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
