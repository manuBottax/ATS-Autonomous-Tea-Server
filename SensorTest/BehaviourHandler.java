public class BehaviourHandler
{

    private LocationController locationController = new LocationController();
    private MotorController motorController = new MotorController();
    private FollowPathController followPathController = new FollowPathController(this.motorController);
    private Button button = new Button();

    private String location;
   
    public BehaviourHandler () {}

    //TODO prima di richiamare questo metodo controllare che ci sia abbastanza carica per fare tutto
    public void serveTea() {
        try {
            //mettere lo stop in FollowPathController
            this.followPathController.stop();

            System.out.println("Waiting for a client !");
            while(!button.isPressed()) { Thread.sleep(100); }
            Thread.sleep(2000);
            button.setPressed(false);
            System.out.println("A client is arrived !");

            System.out.println("Going to the client !");
            goingToTheClient();
        
            System.out.println("At table, waiting for orders !");
            while(! button.isPressed()) { Thread.sleep(100); }
            button.setPressed(false);
            System.out.println("The client has choose his tea");

            // System.out.println("Going to bar !");
            // goingToTheBar();

            // System.out.println("At bar, waiting for preparation !");
            // while(!button.isPressed()) { Thread.sleep(100); }
            // button.setPressed(false);
            // System.out.println("The tea is ready ! ");

            // System.out.println("Going back to client to delivery the tea !");
            // goingToTheClient();

            // System.out.println("At client, waiting for him to retrieve his tea !");
            // while(!button.isPressed()) { Thread.sleep(100); }
            // button.setPressed(false);
            // System.out.println("The client has retrieve the tea !");

            // System.out.println("Going to wait for the client to finish his tea !");
            // goingToTheChargeStation();

            // System.out.println("At charge station, waiting for the client to finish his tea !");
            // while(!button.isPressed()) { Thread.sleep(100); }
            // button.setPressed(false);
            // System.out.println("The client has finish his tea !");

            // System.out.println("Going back to client to receive the empty cup !");
            // goingToTheClient();

            // System.out.println("At client, waiting for him to receive his empty cup !");
            // while(!button.isPressed()) { Thread.sleep(100); }
            // button.setPressed(false);
            // System.out.println("Received the empty cup !");
            // System.out.println("Thank you for choosing us and see you soon !");

            // System.out.println("Going to bar !");
            // goingToTheBar();

            // System.out.println("At bar, waiting for give back the empty cup !");
            // while(!button.isPressed()) { Thread.sleep(100); }
            // button.setPressed(false);
            // System.out.println("The empty cup has been taken ! ");

            // System.out.println("Going to wait for a new client !");
            // goingToTheChargeStation();

        } catch(InterruptedException e) {
            System.err.println("InterruptException detected : " + e);
        }
    }

    private void goingToTheClient() {
        //TODO gestire lo start con la linea nera davanti
        this.followPathController.start();
        System.out.println("isArrested :" + this.motorController.isRobotArrested());
        while(!this.motorController.isRobotArrested()) { 
            try {
                System.out.println("now isArrested ? " + this.motorController.isRobotArrested());
                Thread.sleep(100); 
            } catch (InterruptedException ex) {} 
        }
        System.out.println("now isArrested ? " + this.motorController.isRobotArrested());
        this.location = this.locationController.getLocation();
        System.out.println("location :" + this.location);
        System.out.println("getLocation :" + this.locationController.getLocation());

        while(!this.location.equals("CLIENT'S TABLE")){
            if(this.location.equals("UNKNOWN")) {
                checkLocation();
            } else {
                goingToTheClient();
            }
        }

        System.out.println("Sono nel posto giusto, esco " + this.location);
    }

    private void checkLocation() {
        try{
            Thread.sleep(500);
            this.location = this.locationController.getLocation();
            System.out.println("check Location :" + this.locationController.getLocation());
            if(this.location.equals("UNKNOWN")) {
                checkLocation();
            }
        }   catch(InterruptedException ex) {}
    }

    private void goingToTheBar() {
        this.followPathController.start();
        while(!this.motorController.isRobotArrested()) { 
            try {
                Thread.sleep(100); 
            } catch (InterruptedException ex) {} 
        }
        this.location = this.locationController.getLocation();
        if(this.location == "UNKNOWN") {
            checkLocation();
        } else if(this.location != "BAR") {
            goingToTheBar();
        }
    }

    private void goingToTheChargeStation() {
        this.followPathController.start();
        while(!this.motorController.isRobotArrested()) { 
            try {
                Thread.sleep(100); 
            } catch (InterruptedException ex) {} 
        }
        this.location = this.locationController.getLocation();
        if(this.location == "UNKNOWN") {
            checkLocation();
        } else if(this.location != "CHARGE STATION") {
            goingToTheClient();
        }
    }

}