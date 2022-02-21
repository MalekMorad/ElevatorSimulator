package challange.coding.cic.ibm.models;

public class Person {
	private int id;
	private final int currFloor;
	private final int destFloor;
	private static int numberOfPerson = 0;
	
	public Person(int currFloor, int destFloor) {
		this.currFloor = currFloor;
		this.destFloor = destFloor;
		
		generateID();
	}
	
	// set id from numberOfPerson & increment variable
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
}
