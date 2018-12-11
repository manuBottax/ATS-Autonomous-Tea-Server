public class BehaviourHandler
{

    private LocationController locationController = new LocationController();
    private MotorController motorController = new MotorController();
    private FollowPathController followPathController = new FollowPathController(this.motorController);
    private Button button = new Button();

    private String location;
   
    public BehaviourHandler () {}

    //TODO prima di richiamare questo metodo controllare che ci sia abbastanza carica per fare tutto
    private void serveTea() {
        try {
            //mettere lo stop in FollowPathController
            this.motorController.stop();

            System.out.println("Waiting for a client !");
            while(!button.isPressed()) { Thread.sleep(100); }
            button.setPressed(false);
            System.out.println("A client is arrived !");

            System.out.println("Going to the client !");
            goingToTheClient();
        
            System.out.println("At table, waiting for orders !");
            while(! button.isPressed()) { Thread.sleep(100); }
            button.setPressed(false);
            System.out.println("The client has choose his tea");

            System.out.println("Going to bar !");
            goingToTheBar();

            System.out.println("At bar, waiting for preparation !");
            while(!button.isPressed()) { Thread.sleep(100); }
            button.setPressed(false);
            System.out.println("The tea is ready ! ");

            System.out.println("Going back to client to delivery the tea !");
            goingToTheClient();

            System.out.println("At client, waiting for him to retrieve his tea !");
            while(!button.isPressed()) { Thread.sleep(100); }
            button.setPressed(false);
            System.out.println("The client has retrieve the tea !");

            System.out.println("Going to wait for the client to finish his tea !");
            goingToTheChargeStation();

            System.out.println("At charge station, waiting for the client to finish his tea !");
            while(!button.isPressed()) { Thread.sleep(100); }
            button.setPressed(false);
            System.out.println("The client has finish his tea !");

            System.out.println("Going back to client to receive the empty cup !");
            goingToTheClient();

            System.out.println("At client, waiting for him to receive his empty cup !");
            while(!button.isPressed()) { Thread.sleep(100); }
            button.setPressed(false);
            System.out.println("Received the empty cup !");
            System.out.println("Thank you for choosing us and see you soon !");

            System.out.println("Going to bar !");
            goingToTheBar();

            System.out.println("At bar, waiting for give back the empty cup !");
            while(!button.isPressed()) { Thread.sleep(100); }
            button.setPressed(false);
            System.out.println("The empty cup has been taken ! ");

            System.out.println("Going to wait for a new client !");
            goingToTheChargeStation();

        } catch(InterruptedException e) {
            System.err.println("InterruptException detected : " + e);
        }
    }

    private void goingToTheClient() {
        //TODO gestire lo start con la linea nera davanti
        this.followPathController.start();
        while(!this.motorController.isRobotArrested()) { Thread.sleep(100); }
        this.location = this.locationController.getLocation();
        if(this.location == "UNKNOWN") {
            checkLocation();
        } else if(this.location != "CLIENT'S TABLE") {
            goingToTheClient();
        }
    }

    private void checkLocation() {
        Thread.sleep(500);
        this.location = this.locationController.getLocation();
        if(this.location == "UNKNOWN") {
            checkLocation();
        }
    }

    private void goingToTheBar() {
        this.followPathController.start();
        while(!this.motorController.isRobotArrested()) { Thread.sleep(100); }
        this.location = this.locationController.getLocation();
        if(this.location == "UNKNOWN") {
            checkLocation();
        } else if(this.location != "BAR") {
            goingToTheBar();
        }
    }

    private void goingToTheChargeStation() {
        this.followPathController.start();
        while(!this.motorController.isRobotArrested()) { Thread.sleep(100); }
        this.location = this.locationController.getLocation();
        if(this.location == "UNKNOWN") {
            checkLocation();
        } else if(this.location != "CHARGE STATION") {
            goingToTheClient();
        }
    }

}