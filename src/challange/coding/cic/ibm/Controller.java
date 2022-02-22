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

/*
 * Controller of MVC - Pattern
 * Handles View interactions & contacts the Elevator Service
 */
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
	
	// add listener to button that calls next available elevator
	private void addViewListeners() {

		view.getBtnCallElevator().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int currentFloor;
				int destinationFloor;
				MovingDirection direction;

				try {

					if (view.getLblRequestError() != "") {
						view.setMessage("");
					}

					currentFloor = Integer.parseInt(view.getTxtFieldCurrFloor());
					destinationFloor = Integer.parseInt(view.getTxtFieldDestFloor());
					direction = currentFloor > destinationFloor ? MovingDirection.DOWN : MovingDirection.UP;

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

	/*
	 * Receives Changes of Model (Elevator > ElevatorService > Controller)
	 * Updates the View according to new changes
	 */
	public void updateElevatorLbl(int elevatorID, int currentFloor, MovingDirection direction, ArrayList<Person> currentPeople) {
		ArrayList<String> labeledPeople = new ArrayList<String>();

		if (currentPeople.size() <= 0) {
			labeledPeople.add("No People inside");
		} else {
			for (int i = 0; i < currentPeople.size(); i++) {
				Person person = currentPeople.get(i);
				labeledPeople.add((person.getIsWaiting() ? "WAITING - " : "") + "P." + person.getID() + " - dest." + person.getDestinationFloor() + " ");
			}
		}
		
		String floor = String.valueOf(currentFloor);
		String people = labeledPeople.toString();
		
		if(direction == MovingDirection.DOWN) {
			floor += " - DOWN";
		} else if(direction == MovingDirection.UP) {
			floor += " - UP";
		}

		switch (elevatorID) {
		case 0:
			view.setLblCurrFloorOfElevatorOne(floor);
			view.setTxtAreaPeopleInElevatorOne(people);
			break;
		case 1:
			view.setLblCurrFloorOfElevatorTwo(floor);
			view.setTxtAreaPeopleInElevatorTwo(people);
			break;
		case 2:
			view.setLblCurrFloorOfElevatorThree(floor);
			view.setTxtAreaPeopleInElevatorThree(people);
			break;
		case 3:
			view.setLblCurrFloorOfElevatorFour(floor);
			view.setTxtAreaPeopleInElevatorFour(people);
			break;
		case 4:
			view.setLblCurrFloorOfElevatorFive(floor);
			view.setTxtAreaPeopleInElevatorFive(people);
			break;
		case 5:
			view.setLblCurrFloorOfElevatorSix(floor);
			view.setTxtAreaPeopleInElevatorSix(people);
			break;
		case 6:
			view.setLblCurrFloorOfElevatorSeven(floor);
			view.setTxtAreaPeopleInElevatorSeven(people);
			break;
		}
	}
}
