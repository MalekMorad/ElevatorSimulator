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
	private Elevator[] elevators = new Elevator[7];
	private Queue<Request> requests = new LinkedList<Request>();
	private ArrayList<Person> peopleWaiting = new ArrayList<>();
	
	private final int minFloor;
	private final int maxFloor;
	private final int waitingTime;
//	private boolean isActive;
	
	public ElevatorService(int minFloor, int maxFloor, int waitingTime) {
		this.minFloor = minFloor;
		this.maxFloor = maxFloor;
		this.waitingTime = waitingTime;
		
//		initElevators();
	}
	
	// initialize 7 Elevators
	public void initElevators() {
		for(int i = 0; i < 7; i++) {
			elevators[i] = new Elevator(i, minFloor, maxFloor, controller, this);
			Thread t = new Thread(elevators[i]);
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
		int bestWaitingTime = 0;
		
		for(Elevator elevator : elevators) {
			final MovingDirection eDirection = elevator.getDirection();
			final MovingDirection rDirection = currentRequest.getMovingDirection();
			
			// check first if Elevator moving in the same direction or standing still (= idle)
			if(eDirection == rDirection || eDirection == MovingDirection.IDLE) {
				
				// check if person stands between elevator & elevator-destination
				if(eDirection == MovingDirection.DOWN && elevator.getCurrFloor() > currentRequest.getCurrentFloor() ||
						eDirection == MovingDirection.UP && elevator.getCurrFloor() < currentRequest.getCurrentFloor() ||
						eDirection == MovingDirection.IDLE) {
					
					// calculate waiting time for this elevator until it reaches person
					final int currentWaitingTime = Math.abs(elevator.getCurrFloor() - currentRequest.getCurrentFloor())*waitingTime;
					
					if(bestElevator == null || bestWaitingTime > currentWaitingTime) {
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
