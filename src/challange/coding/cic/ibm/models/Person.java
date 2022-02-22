package challange.coding.cic.ibm.models;

public class Person {
	private int id;
	private final int currFloor;
	private final int destFloor;
	private static int numberOfPerson = 0;
	private boolean isWaiting;
	
	public Person(int currFloor, int destFloor) {
		this.currFloor = currFloor;
		this.destFloor = destFloor;
		isWaiting = true;
		
		generateID();
	}
	
	private void generateID() {
		id = numberOfPerson;
		numberOfPerson++;
	}
	
	public int getID() {
		return id;
	}
	
	public int getCurrentFloor() {
		return currFloor;
	}
	
	public int getDestinationFloor() {
		return destFloor;
	}
	
	public void toggleIsWaiting() {
		isWaiting = !isWaiting;
	}
	
	public boolean getIsWaiting() {
		return isWaiting;
	}
}
