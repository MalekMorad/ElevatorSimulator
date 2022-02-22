package challange.coding.cic.ibm;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import challange.coding.cic.ibm.interfaces.IElevatorService;
import challange.coding.cic.ibm.models.Elevator;
import challange.coding.cic.ibm.models.MovingDirection;
import challange.coding.cic.ibm.models.Person;
import challange.coding.cic.ibm.models.Request;

public class ElevatorService implements IElevatorService, Runnable{

	private Controller controller;
	private final int MAX_ELEVATORS;
	private ArrayList<Elevator> elevators;
	private Queue<Request> requests;
	
	private final int minFloor;
	private final int maxFloor;
	private final int waitingTime;
	private boolean isActive;
	
	public ElevatorService(int minFloor, int maxFloor, int waitingTime, int maxElevators) {
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
		this.waitingTime = waitingTime;
		MAX_ELEVATORS = maxElevators;
		isActive = false;
		
		elevators = new ArrayList<Elevator>();
		requests = new LinkedList<Request>();
		
		Thread t = new Thread(this);
		t.start();
	}
	
	// initialize all Elevators
	public void initElevators() {
		for(int i = 0; i < MAX_ELEVATORS; i++) {
			Elevator el = new Elevator(i, minFloor, maxFloor, this);
			elevators.add(el);
			Thread t = new Thread(el);
			t.start();
		}
	}


	@Override
	public void run() {
		
		isActive = true;
		
		while(isActive) {
			
			checkRequests();
			
		}
	}

	/* 
	 * Check which elevator is the best choice for this request,
	 * by calculating the waiting-time for this person until elevator arrives
	 * Finally, send this request as an assignment to the elevator
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
					int currentWaitingTime = elevator.calcWaitingTime(currentRequest.getCurrentFloor());
					
					if(bestElevator == null || currentWaitingTime < bestWaitingTime) {
						bestWaitingTime = currentWaitingTime;
						bestElevator = elevator;
					}
					
				}
				
			}
			
		}
		
		if(bestElevator != null) {
			bestElevator.addAssignment(currentRequest);
			requests.remove();
		}
	}
	
	private void checkRequests() {
		if(requests.size() > 0) {
			checkFreeElevator();
		} else {
			try {
				Thread.sleep(waitingTime);	
			} catch(InterruptedException ie) {
				ie.printStackTrace();	
			}
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
		
		isActive = false;
		
	}

	@Override
	public void addRequest(Request req) {
		requests.add(req);
	}
	
	@Override
	public boolean isValidRequest(int currentFloor, int destinationFloor) {
		return currentFloor >= minFloor && currentFloor <= maxFloor && destinationFloor >= minFloor && destinationFloor <= maxFloor;
	}
	
	public void updateControllerData(int id, int currentFloor, MovingDirection direction, ArrayList<Person> currentPeople) {
		controller.updateElevatorLbl(id, currentFloor, direction, currentPeople);
	}
	
	public int getWaitingTime() {
		return waitingTime;
	}
}
