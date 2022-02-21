package challange.coding.cic.ibm;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import challange.coding.cic.ibm.interfaces.IElevatorService;
import challange.coding.cic.ibm.models.Elevator;
import challange.coding.cic.ibm.models.MovingDirection;
import challange.coding.cic.ibm.models.Person;
import challange.coding.cic.ibm.models.Request;

public class ElevatorService implements IElevatorService{

	private Controller controller;
	private final int MAX_ELEVATORS;
	private ArrayList<Elevator> elevators;
	private Queue<Request> requests;
	private ArrayList<Person> peopleWaiting;
	
	private final int minFloor;
	private final int maxFloor;
	private final int waitingTime;
	
	public ElevatorService(int minFloor, int maxFloor, int waitingTime, int maxElevators) {
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
		this.waitingTime = waitingTime;
		MAX_ELEVATORS = maxElevators;
		
		elevators = new ArrayList<Elevator>();
		requests = new LinkedList<Request>();
		peopleWaiting = new ArrayList<>();
	}
	
	// initialize 7 Elevators
	public void initElevators() {
		for(int i = 0; i < MAX_ELEVATORS; i++) {
			Elevator el = new Elevator(i, minFloor, maxFloor, this);
			elevators.add(el);
			Thread t = new Thread(el);
			t.start();
		}
	}

	/* 
	 * check which elevator is the best choice for this request
	 * & send this request as an assignment to the elevator
	 * */
	@Override
	public void checkFreeElevator() {
		Elevator bestElevator = null;
		Request currentRequest = requests.peek();
		int bestWaitingTime = Integer.MAX_VALUE;
		
		for(Elevator elevator : elevators) {
			MovingDirection eDirection = elevator.getDirection();
			MovingDirection rDirection = currentRequest.getMovingDirection();
			
			// check first if Elevator moving in the same direction or standing still (= idle)
			if(eDirection == rDirection || eDirection == MovingDirection.IDLE) {
				
				// check if person stands between elevator & elevator-destination
				if(eDirection == MovingDirection.DOWN && elevator.getCurrFloor() > currentRequest.getCurrentFloor() ||
						eDirection == MovingDirection.UP && elevator.getCurrFloor() < currentRequest.getCurrentFloor() ||
						eDirection == MovingDirection.IDLE) {
					
					// calculate waiting time for this elevator until it reaches person
					int currentWaitingTime = Math.abs(elevator.getCurrFloor() - currentRequest.getCurrentFloor())*waitingTime;
					
					if(bestElevator == null || currentWaitingTime < bestWaitingTime) {
						bestWaitingTime = currentWaitingTime;
						bestElevator = elevator;
					}
					
				}
				
			}
			
		}
		
		// check if matching elevator found
		if(bestElevator == null) {
			System.out.println("No Best Elevator Found");
			// TO DO: Wait 1 second or less and check again(sleep 1000ms)
		} else {
			System.out.println("Best Elevator Found!");
			bestElevator.addAssignment(currentRequest);
			requests.remove();
		}
	}
	
	@Override
	public void addController(Controller controller) {
		this.controller = controller;
	}

	@Override
	public void killThreads() {
		
		for(Elevator elevator : elevators) {
			elevator.toggleIsAlive();
		}
		
	}

	// add received Request to Queue
	@Override
	public void addRequest(Request req) {
		System.out.println("Adding Request in ElevatorService");
		requests.add(req);
		checkFreeElevator();
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}
	
	public void updateControllerData(int id, int currentFloor, ArrayList<Person> currentPeople) {
		controller.updateElevatorLbl(id, currentFloor, currentPeople);
	}
}
