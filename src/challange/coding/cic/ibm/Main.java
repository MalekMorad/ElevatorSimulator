package challange.coding.cic.ibm;

public class Main {

	public static void main(String[] args) {
		final ElevatorService elevatorService;
		final Controller controller;
		
		// init Elevator Service (= Model-Layer) | Ground Floor, Max Floor, WaitingTime each Floor
		elevatorService = new ElevatorService(0, 55, 500);
		
		// init View (-window)
		View window = new View();
		
		// init Controller (= Control-Layer) & connect view to controller
		controller = new Controller(window, elevatorService);

		// add Controller to ElevatorService
		elevatorService.addController(controller);
		elevatorService.initElevators();

		try {
			
			// set View-window (= GUI) to visible
			window.frmElevatorSimulation.setVisible(true);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Runtime.getRuntime().addShutdownHook(new Thread()
	    {
	        @Override
	        public void run()
	        {
		        elevatorService.killThreads();
	        }
	    });
		
	}

}