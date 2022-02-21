package challange.coding.cic.ibm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import challange.coding.cic.ibm.interfaces.IElevatorService;
import challange.coding.cic.ibm.models.Elevator;
import challange.coding.cic.ibm.models.MovingDirection;
import challange.coding.cic.ibm.models.Person;
import challange.coding.cic.ibm.models.Request;

// This controller acts as the main system of this Elevator Simulation
// Also, this controller manipulates & updates the view (GUI) of this Program
public class Controller {
	private View view;
	private IElevatorService elevatorService;
	private ExecutorService elevatorExecutor = Executors.newFixedThreadPool(7);
	
	public Controller(View view, ElevatorService elevatorService) {
		
		this.view = view;
		
		this.elevatorService = elevatorService;
		elevatorService.addController(this);
		elevatorService.initElevators();
		
		addViewListeners();
		
	}
	
	private void addViewListeners() {
		
		// add listener to button that calls next available elevator
		view.getBtnCallElevator().addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int currentFloor;
				int destinationFloor;
				MovingDirection direction;
				
				try {
					
					// reset Request-Error Message
					if(view.getLblRequestError() != "") {
						view.setMessage("");
					}
					
					currentFloor = Integer.parseInt(view.getTxtFieldCurrFloor());
					destinationFloor = Integer.parseInt(view.getTxtFieldDestFloor());
					direction = currentFloor > destinationFloor ? MovingDirection.DOWN : MovingDirection.UP;
					
					// only if current- and destination Floor are different => send Request to ElevatorService
					if(currentFloor != destinationFloor) {
						Request req = new Request(currentFloor, destinationFloor, direction);
						elevatorService.addRequest(req);
					}
					
				} catch(Exception exception) {
					view.setMessage("Invalid Input.");
				}
				
			}
			
		});
	}
	
	// updates the correct Elevator-Label (Current Floor) in view
	public void updateElevatorLbl(int elevatorID, int currentFloor, ArrayList<Person> currentPeople) {
		String[] labeledPeople = new String[currentPeople.size()];
		
		for(int i = 0; i < labeledPeople.length; i++) {
			labeledPeople[i] = "P." + currentPeople.get(i).getID() + ": " + currentPeople.get(i).getDestinationFloor();
		}
		
		System.out.println(String.valueOf(currentFloor));
		
		switch(elevatorID) {
			case 0:
				view.setLblCurrFloorOfElevatorOne(String.valueOf(currentFloor));
				view.setLblPeopleInElevatorOne(labeledPeople.toString());
				// TO DO: update correct label of elevator
				break;
			case 1:
				view.setLblCurrFloorOfElevatorTwo(String.valueOf(currentFloor));
				view.setLblPeopleInElevatorTwo(labeledPeople.toString());
				// TO DO: update correct label of elevator
				break;
			case 2:
				view.setLblCurrFloorOfElevatorThree(String.valueOf(currentFloor));
				view.setLblPeopleInElevatorThree(labeledPeople.toString());
				// TO DO: update correct label of elevator
				break;
			case 3:
				view.setLblCurrFloorOfElevatorFour(String.valueOf(currentFloor));
				view.setLblPeopleInElevatorFour(labeledPeople.toString());
				// TO DO: update correct label of elevator
				break;
			case 4:
				view.setLblCurrFloorOfElevatorFive(String.valueOf(currentFloor));
				view.setLblPeopleInElevatorFive(labeledPeople.toString());
				// TO DO: update correct label of elevator
				break;
			case 5:
				view.setLblCurrFloorOfElevatorSix(String.valueOf(currentFloor));
				view.setLblPeopleInElevatorSix(labeledPeople.toString());
				// TO DO: update correct label of elevator
				break;
			case 6:
				view.setLblCurrFloorOfElevatorSeven(String.valueOf(currentFloor));
				view.setLblPeopleInElevatorSeven(labeledPeople.toString());
				// TO DO: update correct label of elevator
				break;
		}
	}
}
