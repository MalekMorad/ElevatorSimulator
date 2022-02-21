package challange.coding.cic.ibm.models;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

import challange.coding.cic.ibm.Controller;
import challange.coding.cic.ibm.ElevatorService;

public class Elevator implements Runnable{
	private int id;
	private int currFloor;
	private int destFloor;
	private int waitingTime;
	private int standingTime;
	private MovingDirection direction;
	private boolean isCalled;
	private boolean isAlive;
	
	private final Controller controller;
	private final ElevatorService elevatorService;
	private ArrayList<Person> people = new ArrayList<Person>();
	private Queue<Request> assignments = new LinkedList<Request>();
	private ArrayList<Integer> stops = new ArrayList<Integer>();

	
	public Elevator(int id, int minFloors, int maxFloors, Controller controller, ElevatorService elevatorService) {
		Random rand = new Random();
		
		// generate a random floor between min- and maxfloor, where this elevator will be initialized
		currFloor = rand.nextInt(maxFloors - minFloors + 1) + minFloors;
		
		// on init, there is no destination until elevator is called
		// if destination is out of bounds = no destination
		destFloor = minFloors - 1;
		
		// when elevator is not moving, the direction is set to NEUTRAL
		direction = MovingDirection.IDLE;
		
		// on init, elevator is not called
		isCalled = false;
		
		this.controller = controller;
		this.elevatorService = elevatorService;
		
		waitingTime = elevatorService.getWaitingTime();
		standingTime = waitingTime*4;
		
		elevatorService.updateControllerData(id, currFloor, people);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Elevator other = (Elevator) obj;
		return id == other.id;
	}


	// runs the elevator-thread
	@Override
	public void run() {
		
		System.out.println("Elevator Thread started");
		isAlive = true;
		
		// while Thread is active
		while(isAlive) {
			
			// while there are assignments or stops -> run thread
			while(assignments.size() > 0 || stops.size() > 0){
				System.out.println("Assignment found!");

				if(assignments.size() > 0) {
					handleAssignment();
				}
				
				// check Floor for stops & handle people getting off the lift
				handleStop();

				try {
					Thread.sleep(waitingTime);
				} catch(InterruptedException ie) {
					
				}
				
				// update current Floor (= move Elevator)
				if(direction == MovingDirection.DOWN) {
					currFloor--;
				} else {
					currFloor++;
				}
				
				elevatorService.updateControllerData(id, currFloor, people);

			}
			
			// set moving status of elevator to idle when done
			direction = MovingDirection.IDLE;
		}

	}
	
	// pull next Assignment from Queue & add new stop to List
	private void handleAssignment() {
		Request req = assignments.poll();
		
		// check if first assignment after elevator has stopped
		// -> set moving direction
		if(direction == MovingDirection.IDLE) {
			direction = req.getMovingDirection();
		}
		
		// create & add Person for this request
		Person p = new Person(req.getCurrentFloor(), req.getDestinationFloor());
		System.out.println(p);
		people.add(p);
	
		// add new Stop
		addNewStop(p);
	}

	// add necessary stop for elevator
	private void addNewStop(Person p) {

		if(stops.size() <= 0) {
			
			stops.add(p.getCurrentFloor());
			stops.add(p.getDestinationFloor());
			
		} else {
			
			final int[] newStops = {p.getCurrentFloor(), p.getDestinationFloor()};
			
			// insert new stops at correct position to keep the list ordered
			for(int i = 0; i < newStops.length; i++) {
				for(int j = 0; j < stops.size(); j++) {
					if(stops.get(j) > newStops[i]) {
						stops.add(j, newStops[i]);
					} else if(j == stops.size()-1) {
						stops.add(newStops[i]);
					}
				}
			}
		}
		
		destFloor = stops.get(0);
	}
	
	private void handleStop() {
		
		if(currFloor == stops.get(0)) {

			for(int i = 0; i < people.size(); i++) {
				if(people.get(i).getDestinationFloor() == currFloor) {
					people.remove(i);
				}
			}

			stops.remove(0);
			destFloor = stops.get(0);
			
			
			try {
				Thread.sleep(standingTime);
			} catch(InterruptedException ie) {
				
			}
		}
		
	}
	
	public int calcWaitingTime() {
		return stops.size()*waitingTime;
	}
	
	public void addAssignment(Request req) {
		assignments.add(req);
	}
	
	public int getCurrFloor() {
		return currFloor;
	}
	
	public int getDestFloor() {
		return destFloor;
	}
	
	public MovingDirection getDirection() {
		return direction;
	}
	
	public boolean getIsCalled() {
		return isCalled;
	}
	
	public boolean getIsAlive() {
		return isAlive;
	}
	
	public void toggleIsAlive() {
		isAlive = !isAlive;
	}
}
