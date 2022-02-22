package challange.coding.cic.ibm.models;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Random;

import challange.coding.cic.ibm.Controller;
import challange.coding.cic.ibm.ElevatorService;

public class Elevator implements Runnable {
	private int id;
	private int currFloor;
	private int destFloor;
	private int waitingTime;
	private int standingTime;
	private MovingDirection direction;
	private boolean isAlive;

	private final ElevatorService elevatorService;
	private ArrayList<Person> people = new ArrayList<Person>();
	private Queue<Request> assignments = new LinkedList<Request>();
	private ArrayList<Integer> stops = new ArrayList<Integer>();

	public Elevator(int id, int minFloors, int maxFloors, ElevatorService elevatorService) {
		Random rand = new Random();

		currFloor = rand.nextInt(maxFloors - minFloors) + minFloors;
		destFloor = minFloors - 1;
		direction = MovingDirection.IDLE;
		waitingTime = elevatorService.getWaitingTime();
		standingTime = waitingTime * 4;

		this.id = id;
		this.elevatorService = elevatorService;

		elevatorService.updateControllerData(id, currFloor, direction, people);
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

	@Override
	public void run() {

		isAlive = true;

		while (isAlive) {

			while (assignments.size() > 0 || stops.size() > 0) {

				if (assignments.size() > 0) {
					handleAssignment();
				}

				handleStop();

				try {
					Thread.sleep(waitingTime);
				} catch (InterruptedException ie) {
					ie.printStackTrace();
				}

				// update current Floor (= move Elevator)
				if (direction != MovingDirection.IDLE) {
					if (direction == MovingDirection.DOWN) {
						currFloor--;
					} else {
						currFloor++;
					}
				}

				elevatorService.updateControllerData(id, currFloor, direction, people);
			}

			// set moving status of elevator to idle when done
			direction = MovingDirection.IDLE;
		}

	}

	// pull next Assignment from Queue & add new stop to List
	private void handleAssignment() {
		Request req = assignments.poll();

		if (direction == MovingDirection.IDLE) {
			direction = currFloor > req.getCurrentFloor() ? MovingDirection.DOWN : MovingDirection.UP;
		}

		Person p = new Person(req.getCurrentFloor(), req.getDestinationFloor());
		people.add(p);

		addNewStop(p);
	}

	// add necessary stop for elevator
	private void addNewStop(Person p) {

		if (stops.size() <= 0) {

			stops.add(p.getCurrentFloor());
			stops.add(p.getDestinationFloor());

		} else {

			int[] newStops = { p.getCurrentFloor(), p.getDestinationFloor() };
			int currentStopSize = stops.size();

			// insert new stops at correct position to keep the list ordered
			for (int i = 0; i < newStops.length; i++) {

				if (!stops.contains(newStops[i])) {
					
					for (int j = 0; j < currentStopSize; j++) {

						if (stops.get(j) >= newStops[i]) {
							stops.add(j, newStops[i]);
						} else if (j == stops.size() - 1) {
							stops.add(newStops[i]);
						}

						System.out.println(stops.toString());

					}
					
				}

			}
		}

		destFloor = stops.get(0);
	}

	// handle stops for people getting in and out of elevator
	private void handleStop() {

		if (stops.get(0) == currFloor) {
			
			int i = 0;
			while(i < people.size()) {
				
				Person p = people.get(i);

				if (p.getCurrentFloor() == currFloor) {
					direction = p.getDestinationFloor() > currFloor ? MovingDirection.UP : MovingDirection.DOWN;
					p.toggleIsWaiting();
				} else if (p.getDestinationFloor() == currFloor) {
					System.out.println("REMOVING PERSON: P" + p.getID());
					people.remove(i);
					System.out.println("PEOPLE-SIZE AFTER DELETE: " + people.size());
					i--;
				}

				i++;
				
			}
			
			stops.remove(0);

			if (stops.size() > 0) {
				destFloor = stops.get(0);
			} else {
				direction = MovingDirection.IDLE;
			}

			try {
				Thread.sleep(standingTime);
			} catch (InterruptedException ie) {

			}
		}

	}

	public int calcWaitingTime(int reqCurrentFloor) {
		int fullWaitingTime = Math.abs(currFloor - reqCurrentFloor - stops.size()) * waitingTime
				+ (stops.size() * standingTime);

		return fullWaitingTime;
	}

	public void addAssignment(Request req) {
		assignments.add(req);
	}

	public int getID() {
		return id;
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

	public boolean getIsAlive() {
		return isAlive;
	}

	public void toggleIsAlive() {
		isAlive = !isAlive;
	}
}
