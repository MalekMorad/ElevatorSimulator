package challange.coding.cic.ibm;

public class Main {

	/*
	 * Init Components (MVC)
	 * ElevatorService (= Logic), Controller & View
	 */
	public static void main(String[] args) {
		final ElevatorService elevatorService;
		final Controller controller;
		
		elevatorService = new ElevatorService(0, 55, 500, 7);
		
		View window = new View();
		
		controller = new Controller(window, elevatorService);

		try {
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
