package challange.coding.cic.ibm.interfaces;

import challange.coding.cic.ibm.Controller;
import challange.coding.cic.ibm.models.Request;

public interface IElevatorService {
	// DOCUMENTATION HERE
	
	// Check if there is a free Elevator & add Assignment to this Elevator
	public void checkFreeElevator();
	
	// Bind Controller to ElevatorService for communication purposes & data update
	public void addController(Controller controller);
	
	// Kill all Elevator Threads on System Shutdown
	public void killThreads();
	
	// Receive Requests from Controller
	public void addRequest(Request req);
	
	public void initElevators();
	
//	// Array of Elevators must be initialized
//	private void initElevators() {
//		
//	};
}
