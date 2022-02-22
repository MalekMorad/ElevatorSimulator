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
					if (view.getLblRequestError() != "") {
						view.setMessage("");
					}

					currentFloor = Integer.parseInt(view.getTxtFieldCurrFloor());
					destinationFloor = Integer.parseInt(view.getTxtFieldDestFloor());
					direction = currentFloor > destinationFloor ? MovingDirection.DOWN : MovingDirection.UP;

					// only if current- and destination Floor are different => send Request to
					// ElevatorService
					if (currentFloor != destinationFloor && elevatorService.isValidRequest(currentFloor, destinationFloor)) {
						Request req = new Request(currentFloor, destinationFloor, direction);
						elevatorService.addRequest(req);
					} else {
						view.setMessage("Invalid Input");
					}

				} catch (Exception exception) {
					view.setMessage("Invalid Input.");
				}

			}

		});
	}

	// updates the correct Elevator-Label (Current Floor) in view
	public void updateElevatorLbl(int elevatorID, int currentFloor, MovingDirection direction, ArrayList<Person> currentPeople) {
		ArrayList<String> labeledPeople = new ArrayList<String>();

		if (currentPeople.size() <= 0) {
			labeledPeople.add("No People inside");
		} else {
			for (int i = 0; i < currentPeople.size(); i++) {
				Person person = currentPeople.get(i);
				labeledPeople.add((person.getIsWaiting() ? "WAITING - " : "") + "P" + person.getID() + " | d." + person.getDestinationFloor());
			}
		}
		
		String floor = String.valueOf(currentFloor);
		String people = "<html>" + labeledPeople.toString() + "</html>";
		
		if(direction == MovingDirection.DOWN) {
			floor += " - DOWN";
		} else if(direction == MovingDirection.UP) {
			floor += " - UP";
		}

		switch (elevatorID) {
		case 0:
			view.setLblCurrFloorOfElevatorOne(floor);
			view.setLblPeopleInElevatorOne(people);
			// TO DO: update correct label of elevator
			break;
		case 1:
			view.setLblCurrFloorOfElevatorTwo(floor);
			view.setLblPeopleInElevatorTwo(people);
			// TO DO: update correct label of elevator
			break;
		case 2:
			view.setLblCurrFloorOfElevatorThree(floor);
			view.setLblPeopleInElevatorThree(people);
			// TO DO: update correct label of elevator
			break;
		case 3:
			view.setLblCurrFloorOfElevatorFour(floor);
			view.setLblPeopleInElevatorFour(people);
			// TO DO: update correct label of elevator
			break;
		case 4:
			view.setLblCurrFloorOfElevatorFive(floor);
			view.setLblPeopleInElevatorFive(people);
			// TO DO: update correct label of elevator
			break;
		case 5:
			view.setLblCurrFloorOfElevatorSix(floor);
			view.setLblPeopleInElevatorSix(people);
			// TO DO: update correct label of elevator
			break;
		case 6:
			view.setLblCurrFloorOfElevatorSeven(floor);
			view.setLblPeopleInElevatorSeven(people);
			// TO DO: update correct label of elevator
			break;
		}
	}
}
